package strategy;

import java.util.ArrayList;
import java.util.List;

import application.RouletteContext;
import enums.RouletteType;
import enums.Spot;
import model.Bet;
import model.SpotPrediction;
import predictor.BasePredictor;
import predictor.MarkovPredictor2;
import utils.BetHelper;
import utils.PredictorHelper;

/**
 * ネイバーベット法(予測器を使用).
 *
 * @author
 */
public class NeighborStrategy extends BaseStrategy {

	/**
	 * 使用する予測器.
	 */
	private static final BasePredictor PREDICTOR = PredictorHelper.getInstance(MarkovPredictor2.class);

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public NeighborStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "ネイバーベット法";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		List<Bet> betList = new ArrayList<>();

		// ヨーロピアンの場合のみ実行
		if (rouletteContext.rouletteType != RouletteType.EUROPEAN_STYLE) {
			return betList;
		}

		// エリア毎の確率
		double probability1 = 0;
		double probability2 = 0;
		double probability3 = 0;
		double probability4 = 0;

		// 予測一覧に対して実行
		for (SpotPrediction spotPrediction : PREDICTOR.getNextSpotPredictionList(rouletteContext)) {
			switch (spotPrediction.spot) {
				case SPOT_32:
				case SPOT_15:
				case SPOT_19:
				case SPOT_04:
				case SPOT_21:
				case SPOT_02:
				case SPOT_25:
				case SPOT_17:
				case SPOT_34:
					probability1 += spotPrediction.probability;
					break;
				case SPOT_06:
				case SPOT_27:
				case SPOT_13:
				case SPOT_36:
				case SPOT_11:
				case SPOT_30:
				case SPOT_08:
				case SPOT_23:
				case SPOT_10:
					probability2 += spotPrediction.probability;
					break;
				case SPOT_05:
				case SPOT_24:
				case SPOT_16:
				case SPOT_33:
				case SPOT_01:
				case SPOT_20:
				case SPOT_14:
				case SPOT_31:
				case SPOT_09:
					probability3 += spotPrediction.probability;
					break;
				case SPOT_22:
				case SPOT_18:
				case SPOT_29:
				case SPOT_07:
				case SPOT_28:
				case SPOT_12:
				case SPOT_35:
				case SPOT_03:
				case SPOT_26:
				case SPOT_0:
					probability4 += spotPrediction.probability;
					break;
				default:
					throw new IllegalArgumentException();
			}
		}

		// 確率の最大値を取得
		double maximumProbability = Math.max(probability1,
				Math.max(probability2, Math.max(probability3, probability4)));

		// ベットを作成
		if (maximumProbability == probability1) {
			for (Spot spot : new Spot[] { Spot.SPOT_32, Spot.SPOT_15, Spot.SPOT_19, Spot.SPOT_04, Spot.SPOT_21,
					Spot.SPOT_02, Spot.SPOT_25, Spot.SPOT_17, Spot.SPOT_34 }) {
				betList.add(new Bet(BetHelper.getStraightUpBetType(spot), rouletteContext.minimumBet));
			}
		} else if (maximumProbability == probability2) {
			for (Spot spot : new Spot[] { Spot.SPOT_06, Spot.SPOT_27, Spot.SPOT_13, Spot.SPOT_36, Spot.SPOT_11,
					Spot.SPOT_30, Spot.SPOT_08, Spot.SPOT_23, Spot.SPOT_10 }) {
				betList.add(new Bet(BetHelper.getStraightUpBetType(spot), rouletteContext.minimumBet));
			}
		} else if (maximumProbability == probability3) {
			for (Spot spot : new Spot[] { Spot.SPOT_05, Spot.SPOT_24, Spot.SPOT_16, Spot.SPOT_33, Spot.SPOT_01,
					Spot.SPOT_20, Spot.SPOT_14, Spot.SPOT_31, Spot.SPOT_09 }) {
				betList.add(new Bet(BetHelper.getStraightUpBetType(spot), rouletteContext.minimumBet));
			}
		} else if (maximumProbability == probability4) {
			for (Spot spot : new Spot[] { Spot.SPOT_22, Spot.SPOT_18, Spot.SPOT_29, Spot.SPOT_07, Spot.SPOT_28,
					Spot.SPOT_12, Spot.SPOT_35, Spot.SPOT_03, Spot.SPOT_26, Spot.SPOT_0 }) {
				betList.add(new Bet(BetHelper.getStraightUpBetType(spot), rouletteContext.minimumBet));
			}
		}

		return betList;
	}
}