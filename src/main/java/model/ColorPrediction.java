package model;

/**
 * 出目の色の予測.
 *
 * @author cyrus
 */
public class ColorPrediction {

	/**
	 * 赤の確率(0-1).
	 */
	public double redProbability;

	/**
	 * 黒の確率(0-1).
	 */
	public double blackProbability;

	/**
	 * 緑の確率(0-1).
	 */
	public double greenProbability;

	/**
	 * コンストラクタ.
	 *
	 * @param redProbability
	 * @param blackProbability
	 * @param greenProbability
	 */
	public ColorPrediction(double redProbability, double blackProbability, double greenProbability) {
		this.redProbability = redProbability;
		this.blackProbability = blackProbability;
		this.greenProbability = greenProbability;
	}
}