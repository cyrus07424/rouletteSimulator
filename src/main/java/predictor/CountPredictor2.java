package predictor;

import application.RouletteContext;
import enums.BetType;
import enums.Spot;
import model.BetTypePrediction;
import model.SpotPrediction;
import utils.BetHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 出現回数による予測器(出目の履歴内のみ).
 *
 * @author
 */
public class CountPredictor2 extends BasePredictor {

	/**
	 * シングルトンのインスタンス.
	 */
	private static CountPredictor2 instance;

	/**
	 * インスタンスを取得.
	 *
	 * @return
	 */
	public static CountPredictor2 getInstance() {
		if (instance == null) {
			instance = new CountPredictor2();
		}
		return instance;
	}

	@Override
	public List<SpotPrediction> getNextSpotPredictionList(RouletteContext rouletteContext) {
		List<SpotPrediction> spotPredictionList = new ArrayList<>();
		if (!rouletteContext.spotHistoryList.isEmpty()) {
			Map<Spot, Long> countMap = new HashMap<>();

			// 出現回数を作成
			for (Spot spot : rouletteContext.spotHistoryList) {
				if (countMap.containsKey(spot)) {
					countMap.put(spot, countMap.get(spot) + 1L);
				} else {
					countMap.put(spot, 1L);
				}
			}

			// 出目毎の確率を作成
			for (Spot spot : Spot.getAvailableList(rouletteContext.rouletteType)) {
				if (countMap.containsKey(spot)) {
					spotPredictionList.add(new SpotPrediction(spot,
							((double) countMap.get(spot)) / ((double) rouletteContext.spotHistoryList.size())));
				} else {
					spotPredictionList.add(new SpotPrediction(spot, 0));
				}
			}
		}
		return spotPredictionList;
	}

	@Override
	public List<BetTypePrediction> getNextBetTypePredictionList(RouletteContext rouletteContext) {
		List<BetTypePrediction> betTypePredictionList = new ArrayList<>();
		if (!rouletteContext.spotHistoryList.isEmpty()) {
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

			// ベットの種類毎の確率を作成
			for (BetType betType : BetType.getAvailableList(rouletteContext.rouletteType)) {
				if (countMap.containsKey(betType)) {
					betTypePredictionList.add(new BetTypePrediction(betType,
							((double) countMap.get(betType)) / ((double) rouletteContext.spotHistoryList.size())));
				} else {
					betTypePredictionList.add(new BetTypePrediction(betType, 0));
				}
			}
		}
		return betTypePredictionList;
	}
}