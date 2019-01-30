package strategy;

import java.util.Collections;
import java.util.List;

import application.RouletteContext;
import enums.BetType;
import model.Bet;
import model.ColorPrediction;
import predictor.BasePredictor;
import predictor.CountPredictor2;
import utils.BetHelper;
import utils.PredictorHelper;

/**
 * マーチンゲール法(予測器を使用).
 *
 * @author cyrus
 */
public class MartingaleStrategy3 extends BaseStrategy {

	/**
	 * 使用する予測器.
	 */
	private static final BasePredictor PREDICTOR = PredictorHelper.getInstance(CountPredictor2.class);

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
		// 次の出目の色の予測を取得
		ColorPrediction colorPrediction = PREDICTOR.getNextColorPrediction(rouletteContext);

		// 使用するベットの種類を選択
		BetType useBetType;
		if (colorPrediction.blackProbability <= colorPrediction.redProbability) {
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