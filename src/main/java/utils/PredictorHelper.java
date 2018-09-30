package utils;

import java.util.HashMap;
import java.util.Map;

import predictor.BasePredictor;

/**
 * 予測器ヘルパー.
 *
 * @author
 */
public class PredictorHelper {

	/**
	 * 予測器のインスタンスを保持するマップ.
	 */
	private static final Map<Class<? extends BasePredictor>, BasePredictor> INSTANCE_MAP = new HashMap<>();

	/**
	 * 予測器のインスタンスを取得.
	 *
	 * @param clazz
	 * @return
	 */
	public static BasePredictor getInstance(Class<? extends BasePredictor> clazz) {
		try {
			if (!INSTANCE_MAP.containsKey(clazz)) {
				// インスタンスを作成
				INSTANCE_MAP.put(clazz, clazz.newInstance());
			}
			// インスタンスを取得
			return INSTANCE_MAP.get(clazz);
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}
}