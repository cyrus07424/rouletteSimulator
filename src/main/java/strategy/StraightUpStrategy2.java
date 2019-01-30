package strategy;

import java.util.ArrayList;
import java.util.List;

import application.RouletteContext;
import enums.BetType;
import model.Bet;
import model.SpotPrediction;
import predictor.BasePredictor;
import predictor.CountPredictor2;
import utils.BetHelper;
import utils.PredictorHelper;

/**
 * ストレート複数賭け(予測器を使用).
 *
 * @author cyrus
 */
public class StraightUpStrategy2 extends BaseStrategy {

	/**
	 * 使用する予測器.
	 */
	private static final BasePredictor PREDICTOR = PredictorHelper.getInstance(CountPredictor2.class);

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public StraightUpStrategy2(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "ストレート複数賭け(予測器を使用)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		List<Bet> betList = new ArrayList<>();

		// ベットに使用する金額を取得
		long limit = currentBalance / 10;
		if (limit < 0) {
			limit = rouletteContext.initialBalance / 10;
		}

		// 予測一覧に対して実行
		for (SpotPrediction spotPrediction : PREDICTOR.getNextSpotPredictionList(rouletteContext)) {
			// 使用するベットの種類を選択
			BetType useBetType = BetHelper.getStraightUpBetType(spotPrediction.spot);

			if (0.03 < spotPrediction.probability) {
				long betValue = (long) (limit * spotPrediction.probability);
				if (rouletteContext.minimumBet < betValue) {
					betList.add(new Bet(useBetType, betValue));
				} else {
					betList.add(new Bet(useBetType, rouletteContext.minimumBet));
				}
			}
		}
		return betList;
	}
}