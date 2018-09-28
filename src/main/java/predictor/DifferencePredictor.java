package predictor;

import application.RouletteContext;
import enums.Spot;
import model.BetTypePrediction;
import model.SpotPrediction;

import java.util.ArrayList;
import java.util.List;

/**
 * 差分予測器.
 *
 * @author
 */
public class DifferencePredictor extends BasePredictor {

	@Override
	public List<SpotPrediction> getNextSpotPredictionList(RouletteContext rouletteContext) {
		List<SpotPrediction> spotPredictionList = new ArrayList<>();
		if (!rouletteContext.spotHistoryList.isEmpty()) {
			// 差分の平均を取得
			int sumNumber = 0;
			double averageDifference = 0;
			for (Spot spot : rouletteContext.spotHistoryList) {
				sumNumber += spot.getNumber();
			}
			averageDifference = sumNumber / rouletteContext.spotHistoryList.size();

			try {
				Spot spot = Spot.getByNumber((int) (rouletteContext.getLastSpot().getNumber() + averageDifference));
				spotPredictionList.add(new SpotPrediction(spot, 0.5));
			} catch (Exception e) {
				// NOP
			}
			try {
				Spot spot = Spot.getByNumber((int) (rouletteContext.getLastSpot().getNumber() - averageDifference));
				spotPredictionList.add(new SpotPrediction(spot, 0.5));
			} catch (Exception e) {
				// NOP
			}
		}
		return spotPredictionList;
	}

	@Override
	public List<BetTypePrediction> getNextBetTypePredictionList(RouletteContext rouletteContext) {
		return null;
	}
}