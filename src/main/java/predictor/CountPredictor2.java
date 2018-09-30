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
 * 出現回数による予測器(出目の履歴内のみ).
 *
 * @author
 */
public class CountPredictor2 extends BasePredictor {

	@Override
	public List<SpotPrediction> getNextSpotPredictionList(RouletteContext rouletteContext) {
		List<SpotPrediction> spotPredictionList = new ArrayList<>();
		Map<Spot, Long> countMap = getSpotTypeCountMap(rouletteContext);

		// 出目毎の確率を作成
		for (Spot spot : Spot.getAvailableList(rouletteContext.rouletteType)) {
			if (countMap.containsKey(spot)) {
				spotPredictionList.add(new SpotPrediction(spot,
						((double) countMap.get(spot)) / ((double) rouletteContext.spotHistoryList.size())));
			} else {
				spotPredictionList.add(new SpotPrediction(spot, 0));
			}
		}
		return spotPredictionList;
	}

	@Override
	public List<BetTypePrediction> getNextBetTypePredictionList(RouletteContext rouletteContext) {
		List<BetTypePrediction> betTypePredictionList = new ArrayList<>();
		Map<BetType, Long> countMap = getBetTypeCountMap(rouletteContext);

		// ベットの種類毎の確率を作成
		for (BetType betType : BetType.getAvailableList(rouletteContext.rouletteType)) {
			if (countMap.containsKey(betType)) {
				betTypePredictionList.add(new BetTypePrediction(betType,
						((double) countMap.get(betType)) / ((double) rouletteContext.spotHistoryList.size())));
			} else {
				betTypePredictionList.add(new BetTypePrediction(betType, 0));
			}
		}
		return betTypePredictionList;
	}

	@Override
	public ColorPrediction getNextColorPrediction(RouletteContext rouletteContext) {
		Map<Spot, Long> countMap = getSpotTypeCountMap(rouletteContext);
		// 色毎の出現回数をカウント
		double redCount = 0;
		double blackCount = 0;
		double greenCount = 0;
		for (Spot spot : Spot.getAvailableList(rouletteContext.rouletteType)) {
			if (countMap.containsKey(spot)) {
				if (spot.isRed()) {
					redCount += countMap.get(spot);
				} else if (spot.isBlack()) {
					blackCount += countMap.get(spot);
				} else if (spot.isGreen()) {
					greenCount += countMap.get(spot);
				}
			}
		}
		double totalCount = redCount + blackCount + greenCount;

		if (0 < totalCount) {
			// 出現の割合を設定
			return new ColorPrediction(redCount / totalCount, blackCount / totalCount, greenCount / totalCount);
		} else {
			return super.getNextColorPrediction(rouletteContext);
		}
	}

	/**
	 * 出目毎出現回数のマップを取得.
	 *
	 * @param rouletteContext
	 * @return
	 */
	private Map<Spot, Long> getSpotTypeCountMap(RouletteContext rouletteContext) {
		Map<Spot, Long> countMap = new HashMap<>();

		// 出現回数を作成
		for (Spot spot : rouletteContext.spotHistoryList) {
			if (countMap.containsKey(spot)) {
				countMap.put(spot, countMap.get(spot) + 1L);
			} else {
				countMap.put(spot, 1L);
			}
		}
		return countMap;
	}

	/**
	 * ベットの種類毎出現回数のマップを取得.
	 *
	 * @param rouletteContext
	 * @return
	 */
	private Map<BetType, Long> getBetTypeCountMap(RouletteContext rouletteContext) {
		Map<BetType, Long> countMap = new HashMap<>();

		// 出現回数を作成
		for (Spot spot : rouletteContext.spotHistoryList) {
			for (BetType betType : BetType.getAvailableList(rouletteContext.rouletteType)) {
				if (BetHelper.isWin(betType, spot)) {
					if (countMap.containsKey(betType)) {
						countMap.put(betType, countMap.get(betType) + 1L);
					} else {
						countMap.put(betType, 1L);
					}
				}
			}
		}
		return countMap;
	}
}