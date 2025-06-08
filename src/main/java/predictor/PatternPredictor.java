package predictor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.RouletteContext;
import enums.Spot;
import model.SpotPrediction;

/**
 * パターン分析による予測器.
 * 過去の出目から連続パターン、ホット・コールド数字を分析して次の出目を予測する.
 *
 * @author cyrus
 */
public class PatternPredictor extends BasePredictor {

	@Override
	public List<SpotPrediction> getNextSpotPredictionList(RouletteContext rouletteContext) {
		List<SpotPrediction> spotPredictionList = new ArrayList<>();
		
		if (rouletteContext.spotHistoryList.isEmpty()) {
			return spotPredictionList;
		}

		// 各出目の基本確率を計算
		Map<Spot, Double> baseFrequency = calculateBaseFrequency(rouletteContext);
		
		// ホット・コールド分析による重み付け
		Map<Spot, Double> hotColdWeights = calculateHotColdWeights(rouletteContext);
		
		// 連続パターン分析による重み付け
		Map<Spot, Double> patternWeights = calculatePatternWeights(rouletteContext);
		
		// 各出目の最終予測確率を計算
		for (Spot spot : Spot.getAvailableList(rouletteContext.rouletteType)) {
			double baseProbability = baseFrequency.getOrDefault(spot, 0.0);
			double hotColdWeight = hotColdWeights.getOrDefault(spot, 1.0);
			double patternWeight = patternWeights.getOrDefault(spot, 1.0);
			
			// 重み付き確率を計算
			double finalProbability = baseProbability * hotColdWeight * patternWeight;
			
			if (finalProbability > 0) {
				spotPredictionList.add(new SpotPrediction(spot, finalProbability));
			}
		}
		
		// 確率の正規化
		return normalizeProbabilities(spotPredictionList);
	}

	/**
	 * 基本出現頻度を計算.
	 *
	 * @param rouletteContext
	 * @return
	 */
	private Map<Spot, Double> calculateBaseFrequency(RouletteContext rouletteContext) {
		Map<Spot, Double> frequency = new HashMap<>();
		Map<Spot, Integer> spotCount = rouletteContext.getSpotFrequency();
		int totalCount = rouletteContext.spotHistoryList.size();
		
		for (Map.Entry<Spot, Integer> entry : spotCount.entrySet()) {
			frequency.put(entry.getKey(), (double) entry.getValue() / totalCount);
		}
		
		return frequency;
	}

	/**
	 * ホット・コールド数字分析による重み付けを計算.
	 * 最近出現した数字（ホット）には高い重み、長期間出現していない数字（コールド）には低い重みを付ける.
	 *
	 * @param rouletteContext
	 * @return
	 */
	private Map<Spot, Double> calculateHotColdWeights(RouletteContext rouletteContext) {
		Map<Spot, Double> weights = new HashMap<>();
		
		// 最近の履歴（最新10回程度）でホット数字を特定
		int recentHistorySize = Math.min(10, rouletteContext.spotHistoryList.size());
		Map<Spot, Integer> recentCount = new HashMap<>();
		
		for (int i = rouletteContext.spotHistoryList.size() - recentHistorySize; 
			 i < rouletteContext.spotHistoryList.size(); i++) {
			Spot spot = rouletteContext.spotHistoryList.get(i);
			recentCount.put(spot, recentCount.getOrDefault(spot, 0) + 1);
		}
		
		// 各出目の最後の出現位置を記録
		Map<Spot, Integer> lastAppearance = new HashMap<>();
		for (int i = 0; i < rouletteContext.spotHistoryList.size(); i++) {
			lastAppearance.put(rouletteContext.spotHistoryList.get(i), i);
		}
		
		for (Spot spot : Spot.getAvailableList(rouletteContext.rouletteType)) {
			double weight = 1.0;
			
			// ホット数字の重み付け（最近よく出る数字は重みを上げる）
			int recentAppearances = recentCount.getOrDefault(spot, 0);
			if (recentAppearances > 1) {
				weight *= 1.2; // ホット数字は20%重みを増加
			}
			
			// コールド数字の重み付け（長期間出ていない数字は重みを下げる）
			Integer lastPos = lastAppearance.get(spot);
			if (lastPos != null) {
				int gapSinceLastAppearance = rouletteContext.spotHistoryList.size() - lastPos - 1;
				if (gapSinceLastAppearance > 15) {
					weight *= 0.8; // 長期間出ていない数字は20%重みを減少
				}
			} else {
				weight *= 0.5; // 一度も出ていない数字は重みを大幅減少
			}
			
			weights.put(spot, weight);
		}
		
		return weights;
	}

	/**
	 * 連続パターン分析による重み付けを計算.
	 * 連続する数字や繰り返しパターンを分析する.
	 *
	 * @param rouletteContext
	 * @return
	 */
	private Map<Spot, Double> calculatePatternWeights(RouletteContext rouletteContext) {
		Map<Spot, Double> weights = new HashMap<>();
		
		if (rouletteContext.spotHistoryList.size() < 2) {
			return weights;
		}
		
		// 直前の出目を取得
		Spot lastSpot = rouletteContext.getLastSpot();
		
		for (Spot spot : Spot.getAvailableList(rouletteContext.rouletteType)) {
			double weight = 1.0;
			
			// 連続数字パターンの分析
			if (isConsecutiveNumber(lastSpot, spot)) {
				weight *= 1.3; // 連続する数字は30%重みを増加
			}
			
			// 繰り返しパターンの分析
			if (hasRepeatingPattern(rouletteContext, spot)) {
				weight *= 1.1; // 繰り返しパターンは10%重みを増加
			}
			
			// 同じ数字の連続を避ける
			if (lastSpot == spot) {
				weight *= 0.7; // 直前と同じ数字は30%重みを減少
			}
			
			weights.put(spot, weight);
		}
		
		return weights;
	}

	/**
	 * 2つの出目が連続する数字かどうかを判定.
	 *
	 * @param spot1
	 * @param spot2
	 * @return
	 */
	private boolean isConsecutiveNumber(Spot spot1, Spot spot2) {
		int num1 = spot1.getNumber();
		int num2 = spot2.getNumber();
		
		// 0と00は特殊ケースとして除外
		if (num1 <= 0 || num2 <= 0) {
			return false;
		}
		
		return Math.abs(num1 - num2) == 1;
	}

	/**
	 * 指定した出目が繰り返しパターンに含まれるかを判定.
	 *
	 * @param rouletteContext
	 * @param targetSpot
	 * @return
	 */
	private boolean hasRepeatingPattern(RouletteContext rouletteContext, Spot targetSpot) {
		if (rouletteContext.spotHistoryList.size() < 4) {
			return false;
		}
		
		// 最新の3つの出目を取得
		List<Spot> recentSpots = new ArrayList<>();
		for (int i = rouletteContext.spotHistoryList.size() - 3; 
			 i < rouletteContext.spotHistoryList.size(); i++) {
			recentSpots.add(rouletteContext.spotHistoryList.get(i));
		}
		
		// パターン「A-B-C-A」の場合、次はBが来る可能性が高い
		if (recentSpots.size() >= 3) {
			Spot firstSpot = recentSpots.get(0);
			Spot secondSpot = recentSpots.get(1);
			Spot thirdSpot = recentSpots.get(2);
			
			// 最初と3番目が同じ場合、次は2番目と同じ可能性が高い
			if (firstSpot == thirdSpot && targetSpot == secondSpot) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * 予測確率を正規化.
	 *
	 * @param spotPredictionList
	 * @return
	 */
	private List<SpotPrediction> normalizeProbabilities(List<SpotPrediction> spotPredictionList) {
		// 総確率を計算
		double totalProbability = spotPredictionList.stream()
			.mapToDouble(pred -> pred.probability)
			.sum();
		
		if (totalProbability <= 0) {
			return spotPredictionList;
		}
		
		// 正規化された予測リストを作成
		List<SpotPrediction> normalizedList = new ArrayList<>();
		for (SpotPrediction prediction : spotPredictionList) {
			double normalizedProbability = prediction.probability / totalProbability;
			normalizedList.add(new SpotPrediction(prediction.spot, normalizedProbability));
		}
		
		return normalizedList;
	}
}