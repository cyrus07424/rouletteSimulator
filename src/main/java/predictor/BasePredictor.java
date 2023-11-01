package predictor;

import java.util.Collections;
import java.util.List;

import application.RouletteContext;
import model.BetTypePrediction;
import model.ColorPrediction;
import model.SpotPrediction;

/**
 * 予測器のベースクラス.
 *
 * @author cyrus
 */
public abstract class BasePredictor {

	/**
	 * 次の出目の予測一覧を取得.
	 *
	 * @param rouletteContext
	 * @return
	 */
	public List<SpotPrediction> getNextSpotPredictionList(RouletteContext rouletteContext) {
		return Collections.emptyList();
	}

	/**
	 * 次のベットの種類の予測一覧を取得.
	 *
	 * @param rouletteContext
	 * @return
	 */
	public List<BetTypePrediction> getNextBetTypePredictionList(RouletteContext rouletteContext) {
		return Collections.emptyList();
	}

	/**
	 * 次の出目の色の予測を取得.
	 *
	 * @param rouletteContext
	 * @return
	 */
	public ColorPrediction getNextColorPrediction(RouletteContext rouletteContext) {
		return new ColorPrediction(0, 0, 0);
	}
}