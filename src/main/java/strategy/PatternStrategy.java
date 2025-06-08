package strategy;

import java.util.ArrayList;
import java.util.List;

import application.RouletteContext;
import enums.BetType;
import enums.Spot;
import model.Bet;
import model.SpotPrediction;
import predictor.BasePredictor;
import predictor.PatternPredictor;
import utils.BetHelper;
import utils.PredictorHelper;

/**
 * パターン分析戦略(予測器を使用).
 * 過去の出目のパターンを分析して次の出目を予測し、複数の高確率出目にベットする.
 *
 * @author cyrus
 */
public class PatternStrategy extends BaseStrategy {

	/**
	 * 使用する予測器.
	 */
	private static final BasePredictor PREDICTOR = PredictorHelper.getInstance(PatternPredictor.class);

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public PatternStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "パターン分析戦略(予測器を使用)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		List<Bet> betList = new ArrayList<>();

		// ベットに使用する金額を取得（所持金の10%を基準）
		long totalBudget = currentBalance / 10;
		if (totalBudget <= 0) {
			totalBudget = rouletteContext.initialBalance / 10;
		}
		if (rouletteContext.maximumBet < totalBudget) {
			totalBudget = rouletteContext.maximumBet;
		}

		// 予測一覧を取得し、確率の高い順にソート
		List<SpotPrediction> spotPredictions = PREDICTOR.getNextSpotPredictionList(rouletteContext);
		spotPredictions.sort((a, b) -> Double.compare(b.probability, a.probability));

		// 確率の閾値を設定（上位の予測のみを対象とする）
		double probabilityThreshold = 0.02; // 2%以上の確率
		long usedBudget = 0;
		int maxBets = 5; // 最大5つの出目にベット
		int betCount = 0;

		for (SpotPrediction spotPrediction : spotPredictions) {
			if (betCount >= maxBets || usedBudget >= totalBudget) {
				break;
			}

			if (spotPrediction.probability >= probabilityThreshold) {
				// 確率に応じてベット額を調整
				long betValue = Math.max(
					rouletteContext.minimumBet,
					(long) (totalBudget * spotPrediction.probability * 2) // 確率の2倍を係数として使用
				);

				// 予算の範囲内に調整
				if (usedBudget + betValue > totalBudget) {
					betValue = totalBudget - usedBudget;
				}

				if (betValue >= rouletteContext.minimumBet) {
					// ストレートアップベットを作成
					BetType useBetType = BetHelper.getStraightUpBetType(spotPrediction.spot);
					if (useBetType != null) {
						betList.add(new Bet(useBetType, betValue));
						usedBudget += betValue;
						betCount++;
					}
				}
			}
		}

		// 高確率の予測がない場合は、最も確率の高い出目に最小ベット
		if (betList.isEmpty() && !spotPredictions.isEmpty()) {
			SpotPrediction bestPrediction = spotPredictions.get(0);
			BetType useBetType = BetHelper.getStraightUpBetType(bestPrediction.spot);
			if (useBetType != null) {
				betList.add(new Bet(useBetType, rouletteContext.minimumBet));
			}
		}

		return betList;
	}
}