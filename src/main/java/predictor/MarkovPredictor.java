package predictor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.RouletteContext;
import enums.BetType;
import enums.Spot;
import model.BetTypePrediction;
import model.SpotPrediction;
import utils.BetHelper;
import utils.LogHelper;

/**
 * マルコフテーブルによる予測器.
 *
 * @author cyrus
 */
public class MarkovPredictor extends BasePredictor {

	/**
	 * 直近2回の出目毎の出現回数.
	 */
	private Map<Spot, Map<Spot, Long>> spotMarkovMap = new HashMap<>();

	/**
	 * 直近2回のベットの種類毎の出現回数.
	 */
	private Map<BetType, Map<BetType, Long>> betTypeMarkovMap = new HashMap<>();

	/**
	 * 総試行回数.
	 */
	private long totalCount = 0;

	@Override
	public List<SpotPrediction> getNextSpotPredictionList(RouletteContext rouletteContext) {
		List<SpotPrediction> spotPredictionList = new ArrayList<>();
		if (!rouletteContext.spotHistoryList.isEmpty()) {
			// 変数を更新
			updateParameter(rouletteContext);

			// 出目毎の確率を作成
			for (Spot nextSpot : Spot.getAvailableList(rouletteContext.rouletteType)) {
				if (spotMarkovMap.containsKey(rouletteContext.getLastSpot())
						&& spotMarkovMap.get(rouletteContext.getLastSpot()).containsKey(nextSpot)) {
					spotPredictionList.add(new SpotPrediction(nextSpot,
							((double) spotMarkovMap.get(rouletteContext.getLastSpot()).get(nextSpot))
									/ ((double) totalCount)));
				}
			}
		}
		return spotPredictionList;
	}

	@Override
	public List<BetTypePrediction> getNextBetTypePredictionList(RouletteContext rouletteContext) {
		List<BetTypePrediction> betTypePredictionList = new ArrayList<>();
		if (!rouletteContext.spotHistoryList.isEmpty()) {
			// 変数を更新
			updateParameter(rouletteContext);

			// 当選となるベットの種類一覧を取得
			List<BetType> lastSpotBetTypeList = new ArrayList<>();
			for (BetType betType : BetType.getAvailableList(rouletteContext.rouletteType)) {
				if (BetHelper.isWin(betType, rouletteContext.getLastSpot())) {
					lastSpotBetTypeList.add(betType);
				}
			}

			// ベットの種類毎の確率を作成
			for (BetType lastSpotBetType : lastSpotBetTypeList) {
				for (BetType nextBetType : BetType.getAvailableList(rouletteContext.rouletteType)) {
					if (betTypeMarkovMap.containsKey(lastSpotBetType)
							&& betTypeMarkovMap.get(lastSpotBetType).containsKey(nextBetType)) {
						betTypePredictionList.add(new BetTypePrediction(nextBetType,
								((double) betTypeMarkovMap.get(lastSpotBetType).get(nextBetType))
										/ ((double) totalCount)));
					}
				}
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
		if (2 <= rouletteContext.spotHistoryList.size()) {
			// 直近2回の出目を取得
			Spot spot1 = rouletteContext.spotHistoryList.get(rouletteContext.spotHistoryList.size() - 2);
			Spot spot2 = rouletteContext.spotHistoryList.get(rouletteContext.spotHistoryList.size() - 1);

			// 出目毎の出現回数を更新
			if (spotMarkovMap.containsKey(spot1)) {
				if (spotMarkovMap.get(spot1).containsKey(spot2)) {
					spotMarkovMap.get(spot1).put(spot2, spotMarkovMap.get(spot1).get(spot2) + 1L);
				} else {
					spotMarkovMap.get(spot1).put(spot2, 1L);
				}
			} else {
				Map<Spot, Long> countMap = new HashMap<>();
				countMap.put(spot2, 1L);
				spotMarkovMap.put(spot1, countMap);
			}

			// 当選となるベットの種類一覧を取得
			List<BetType> betTypeList1 = new ArrayList<>();
			List<BetType> betTypeList2 = new ArrayList<>();
			for (BetType betType : BetType.getAvailableList(rouletteContext.rouletteType)) {
				if (BetHelper.isWin(betType, spot1)) {
					betTypeList1.add(betType);
				}
				if (BetHelper.isWin(betType, spot2)) {
					betTypeList2.add(betType);
				}
			}

			// ベットの種類毎の出現回数を更新
			for (BetType betType1 : betTypeList1) {
				for (BetType betType2 : betTypeList2) {
					if (betTypeMarkovMap.containsKey(betType1)) {
						if (betTypeMarkovMap.get(betType1).containsKey(betType2)) {
							betTypeMarkovMap.get(betType1).put(betType2,
									betTypeMarkovMap.get(betType1).get(betType2) + 1L);
						} else {
							betTypeMarkovMap.get(betType1).put(betType2, 1L);
						}
					} else {
						Map<BetType, Long> countMap = new HashMap<>();
						countMap.put(betType2, 1L);
						betTypeMarkovMap.put(betType1, countMap);
					}
				}
			}
			totalCount++;
		}
	}

	/**
	 * データを出力.
	 */
	private void dumpMarkovMap() {
		LogHelper.debug("--- spotMarkovMap start ---");
		for (Map.Entry<Spot, Map<Spot, Long>> entry1 : spotMarkovMap.entrySet()) {
			for (Map.Entry<Spot, Long> entry2 : entry1.getValue().entrySet()) {
				LogHelper.debug(entry1.getKey().name() + "-" + entry2.getKey().name() + ":" + entry2.getValue());
			}
		}
		LogHelper.debug("--- spotMarkovMap end ---");
	}
}