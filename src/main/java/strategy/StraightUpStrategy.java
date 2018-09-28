package strategy;

import application.RouletteContext;
import enums.BetType;
import enums.Spot;
import model.Bet;
import model.SpotPrediction;
import predictor.BasePredictor;
import predictor.RnnPredictor;
import utils.BetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 1点賭け(予測器を使用).
 *
 * @author
 */
public class StraightUpStrategy extends BaseStrategy {

	/**
	 * 使用する予測器.
	 */
	private static final BasePredictor PREDICTOR = RnnPredictor.getInstance();

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public StraightUpStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "1点賭け(予測器を使用)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		List<Bet> betList = new ArrayList<>();

		// 最も確率の高い出目を取得
		double maxProbability = 0;
		Spot spot = null;
		// 予測一覧に対して実行
		for (SpotPrediction spotPrediction : PREDICTOR.getNextSpotPredictionList(rouletteContext)) {
			if (maxProbability < spotPrediction.probability) {
				maxProbability = spotPrediction.probability;
				spot = spotPrediction.spot;
			}
		}

		if (spot != null) {
			// 使用するベットの種類を選択
			BetType useBetType = BetHelper.getStraightUpBetType(spot);

			// 最小ベット額をベット
			betList.add(new Bet(useBetType, rouletteContext.minimumBet));
		}
		return betList;
	}
}