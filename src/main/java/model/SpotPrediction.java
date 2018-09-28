package model;

import enums.Spot;

/**
 * 出目の予測.
 *
 * @author
 */
public class SpotPrediction {

	/**
	 * 出目.
	 */
	public Spot spot;

	/**
	 * 確率(0-1).
	 */
	public double probability;

	/**
	 * コンストラクタ.
	 *
	 * @param spot
	 * @param probability
	 */
	public SpotPrediction(Spot spot, double probability) {
		this.spot = spot;
		this.probability = probability;
	}
}