package predictor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.RouletteContext;
import enums.BetType;
import enums.Spot;
import model.BetTypePrediction;
import model.ColorPrediction;
import model.SpotPrediction;
import utils.BetHelper;

/**
 * 出現回数による予測器.
 *
 * @author cyrus
 */
public class CountPredictor extends BasePredictor {

	/**
	 * 出目毎の出現回数.
	 */
	private Map<Spot, Long> spotCountMap = new HashMap<>();

	/**
	 * ベットの種類毎の出現回数.
	 */
	private Map<BetType, Long> betTypeCountMap = new HashMap<>();

	/**
	 * 総試行回数.
	 */
	private long totalCount = 0;

	@Override
	public List<SpotPrediction> getNextSpotPredictionList(RouletteContext rouletteContext) {
		// 変数を更新
		updateParameter(rouletteContext);

		// 出目毎の確率を作成
		List<SpotPrediction> spotPredictionList = new ArrayList<>();
		for (Spot spot : Spot.getAvailableList(rouletteContext.rouletteType)) {
			if (spotCountMap.containsKey(spot)) {
				spotPredictionList
						.add(new SpotPrediction(spot, ((double) spotCountMap.get(spot)) / ((double) totalCount)));
			} else {
				spotPredictionList.add(new SpotPrediction(spot, 0));
			}
		}
		return spotPredictionList;
	}

	@Override
	public List<BetTypePrediction> getNextBetTypePredictionList(RouletteContext rouletteContext) {
		// 変数を更新
		updateParameter(rouletteContext);

		// 出目毎の確率を作成
		List<BetTypePrediction> betTypePredictionList = new ArrayList<>();
		for (BetType betType : BetType.getAvailableList(rouletteContext.rouletteType)) {
			if (betTypeCountMap.containsKey(betType)) {
				betTypePredictionList.add(new BetTypePrediction(betType,
						((double) betTypeCountMap.get(betType)) / ((double) totalCount)));
			} else {
				betTypePredictionList.add(new BetTypePrediction(betType, 0));
			}
		}
		return betTypePredictionList;
	}

	@Override
	public ColorPrediction getNextColorPrediction(RouletteContext rouletteContext) {
		// 色毎の出現回数をカウント
		double redCount = 0;
		double blackCount = 0;
		double greenCount = 0;
		for (Spot spot : Spot.getAvailableList(rouletteContext.rouletteType)) {
			if (spotCountMap.containsKey(spot)) {
				if (spot.isRed()) {
					redCount += spotCountMap.get(spot);
				} else if (spot.isBlack()) {
					blackCount += spotCountMap.get(spot);
				} else if (spot.isGreen()) {
					greenCount += spotCountMap.get(spot);
				}
			}
		}
		double totalCount = redCount + blackCount + greenCount;

		// 出現の割合を設定
		return new ColorPrediction(redCount / totalCount, blackCount / totalCount, greenCount / totalCount);
	}

	/**
	 * 変数を更新.
	 *
	 * @param rouletteContext
	 */
	private void updateParameter(RouletteContext rouletteContext) {
		// 出目毎の出現回数を更新
		if (spotCountMap.containsKey(rouletteContext.getLastSpot())) {
			spotCountMap.put(rouletteContext.getLastSpot(), spotCountMap.get(rouletteContext.getLastSpot()) + 1L);
		} else {
			spotCountMap.put(rouletteContext.getLastSpot(), 1L);
		}

		// ベットの種類毎の出現回数を更新
		for (BetType betType : BetType.getAvailableList(rouletteContext.rouletteType)) {
			if (BetHelper.isWin(betType, rouletteContext.getLastSpot())) {
				if (betTypeCountMap.containsKey(betType)) {
					betTypeCountMap.put(betType, betTypeCountMap.get(betType) + 1L);
				} else {
					betTypeCountMap.put(betType, 1L);
				}
			}
		}
		totalCount++;
	}
}