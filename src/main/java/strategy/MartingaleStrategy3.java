package strategy;

import java.util.Collections;
import java.util.List;

import application.RouletteContext;
import enums.BetType;
import model.Bet;
import model.SpotPrediction;
import predictor.BasePredictor;
import predictor.RnnPredictor;
import utils.BetHelper;

/**
 * マーチンゲール法(予測器を使用).
 *
 * @author
 */
public class MartingaleStrategy3 extends BaseStrategy {

	/**
	 * 使用する予測器.
	 */
	private static final BasePredictor PREDICTOR = RnnPredictor.getInstance();

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public MartingaleStrategy3(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "マーチンゲール法(予測器を使用)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		// 赤と黒の確率の合計を取得
		double redProbability = 0;
		double blackProbability = 0;
		// 予測一覧に対して実行
		for (SpotPrediction spotPrediction : PREDICTOR.getNextSpotPredictionList(rouletteContext)) {
			if (spotPrediction.spot.isRed()) {
				redProbability += spotPrediction.probability;
			} else if (spotPrediction.spot.isBlack()) {
				blackProbability += spotPrediction.probability;
			}
		}

		// 使用するベットの種類を選択
		BetType useBetType;
		if (blackProbability <= redProbability) {
			useBetType = BetType.RED;
		} else {
			useBetType = BetType.BLACK;
		}

		// 前回のベットで当選したかを取得
		boolean wonLastBet = false;
		long lastBetValue = 0;
		if (lastBetList != null) {
			lastBetValue = BetHelper.getTotalBetValue(lastBetList);
			for (Bet bet : lastBetList) {
				if (BetHelper.isWin(bet, rouletteContext.getLastSpot())) {
					wonLastBet = true;
				}
			}
		}

		// 前回当選した場合
		if (wonLastBet) {
			// 最小ベット額をベット
			return Collections.singletonList(new Bet(useBetType, rouletteContext.minimumBet));
		} else {
			// 前回のベット額の倍額をベット
			return Collections.singletonList(new Bet(useBetType, (lastBetValue * 2)));
		}
	}
}
