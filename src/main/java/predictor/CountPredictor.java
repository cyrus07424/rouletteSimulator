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
 * 出現回数による予測器.
 *
 * @author
 */
public class CountPredictor extends BasePredictor {

	/**
	 * シングルトンのインスタンス.
	 */
	private static CountPredictor instance;

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

	/**
	 * インスタンスを取得.
	 *
	 * @return
	 */
	public static CountPredictor getInstance() {
		if (instance == null) {
			instance = new CountPredictor();
		}
		return instance;
	}

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