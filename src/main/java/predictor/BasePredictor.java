package predictor;

import application.RouletteContext;
import model.BetTypePrediction;
import model.SpotPrediction;

import java.util.List;

/**
 * 予測器のベースクラス.
 *
 * @author
 */
public abstract class BasePredictor {

	/**
	 * 次の出目の予測一覧を取得.
	 *
	 * @param rouletteContext
	 * @return
	 */
	public abstract List<SpotPrediction> getNextSpotPredictionList(RouletteContext rouletteContext);

	/**
	 * 次のベットの種類の予測一覧を取得.
	 *
	 * @param rouletteContext
	 * @return
	 */
	public abstract List<BetTypePrediction> getNextBetTypePredictionList(RouletteContext rouletteContext);
}