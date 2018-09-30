package strategy;

import java.util.ArrayList;
import java.util.List;

import application.RouletteContext;
import model.Bet;
import model.BetTypePrediction;
import predictor.BasePredictor;
import predictor.MarkovPredictor2;
import utils.PredictorHelper;

/**
 * 複数賭け(予測器を使用).
 *
 * @author
 */
public class MultiBetStrategy extends BaseStrategy {

	/**
	 * 使用する予測器.
	 */
	private static final BasePredictor PREDICTOR = PredictorHelper.getInstance(MarkovPredictor2.class);

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public MultiBetStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "複数賭け(予測器を使用)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		List<Bet> betList = new ArrayList<>();

		// ベットに使用する金額を取得
		long limit = currentBalance / 10;
		if (limit < 0) {
			limit = rouletteContext.initialBalance / 10;
		}
		if (rouletteContext.maximumBet < limit) {
			limit = rouletteContext.maximumBet;
		}

		// 予測一覧に対して実行
		for (BetTypePrediction betTypePrediction : PREDICTOR.getNextBetTypePredictionList(rouletteContext)) {
			if (0.3 < betTypePrediction.probability) {
				long betValue = (long) (limit * betTypePrediction.probability);
				if (rouletteContext.minimumBet < betValue) {
					betList.add(new Bet(betTypePrediction.betType, betValue));
				} else {
					betList.add(new Bet(betTypePrediction.betType, rouletteContext.minimumBet));
				}
			}
		}
		return betList;
	}
}