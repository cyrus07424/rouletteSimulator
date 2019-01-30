package predictor;

import java.util.ArrayList;
import java.util.List;

import application.RouletteContext;
import enums.Spot;
import model.SpotPrediction;

/**
 * 差分予測器.
 *
 * @author cyrus
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
}