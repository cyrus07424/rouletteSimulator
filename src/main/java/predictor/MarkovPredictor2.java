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

/**
 * マルコフテーブルによる予測器(出目の履歴内のみ).
 *
 * @author cyrus
 */
public class MarkovPredictor2 extends BasePredictor {

	@Override
	public List<SpotPrediction> getNextSpotPredictionList(RouletteContext rouletteContext) {
		List<SpotPrediction> spotPredictionList = new ArrayList<>();
		if (2 <= rouletteContext.spotHistoryList.size()) {
			Map<Spot, Map<Spot, Long>> spotMarkovMap = new HashMap<>();

			for (int i = 0; i < rouletteContext.spotHistoryList.size() - 1; i++) {
				// 直近2回の出目を取得
				Spot spot1 = rouletteContext.spotHistoryList.get(i);
				Spot spot2 = rouletteContext.spotHistoryList.get(i + 1);

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
			}

			// 出目毎の確率を作成
			for (Spot nextSpot : Spot.getAvailableList(rouletteContext.rouletteType)) {
				if (spotMarkovMap.containsKey(rouletteContext.getLastSpot())
						&& spotMarkovMap.get(rouletteContext.getLastSpot()).containsKey(nextSpot)) {
					spotPredictionList.add(new SpotPrediction(nextSpot,
							((double) spotMarkovMap.get(rouletteContext.getLastSpot()).get(nextSpot))
									/ ((double) rouletteContext.spotHistoryList.size())));
				}
			}
		}
		return spotPredictionList;
	}

	@Override
	public List<BetTypePrediction> getNextBetTypePredictionList(RouletteContext rouletteContext) {
		List<BetTypePrediction> betTypePredictionList = new ArrayList<>();
		if (2 <= rouletteContext.spotHistoryList.size()) {
			Map<BetType, Map<BetType, Long>> betTypeMarkovMap = new HashMap<>();

			for (int i = 0; i < rouletteContext.spotHistoryList.size() - 1; i++) {
				// 直近2回の出目を取得
				Spot spot1 = rouletteContext.spotHistoryList.get(i);
				Spot spot2 = rouletteContext.spotHistoryList.get(i + 1);

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
			}

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
										/ ((double) rouletteContext.spotHistoryList.size())));
					}
				}
			}
		}
		return betTypePredictionList;
	}
}