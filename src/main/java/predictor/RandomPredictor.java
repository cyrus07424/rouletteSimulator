package predictor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import application.RouletteContext;
import enums.BetType;
import enums.Spot;
import model.BetTypePrediction;
import model.SpotPrediction;

/**
 * ランダム予測器.
 *
 * @author
 */
public class RandomPredictor extends BasePredictor {

	@Override
	public List<SpotPrediction> getNextSpotPredictionList(RouletteContext rouletteContext) {
		List<SpotPrediction> spotPredictionList = new ArrayList<>();
		Random random = new Random();
		for (Spot spot : Spot.getAvailableList(rouletteContext.rouletteType)) {
			spotPredictionList.add(new SpotPrediction(spot, random.nextDouble()));
		}
		return spotPredictionList;
	}

	@Override
	public List<BetTypePrediction> getNextBetTypePredictionList(RouletteContext rouletteContext) {
		List<BetTypePrediction> betTypePredictionList = new ArrayList<>();
		Random random = new Random();
		for (BetType betType : BetType.getAvailableList(rouletteContext.rouletteType)) {
			betTypePredictionList.add(new BetTypePrediction(betType, random.nextDouble()));
		}
		return betTypePredictionList;
	}
}