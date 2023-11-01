package model;

import enums.BetType;

/**
 * ベットの種類の予測.
 *
 * @author cyrus
 */
public class BetTypePrediction {

	/**
	 * ベットの種類.
	 */
	public BetType betType;

	/**
	 * 確率(0-1).
	 */
	public double probability;

	/**
	 * コンストラクタ.
	 *
	 * @param betType
	 * @param probability
	 */
	public BetTypePrediction(BetType betType, double probability) {
		this.betType = betType;
		this.probability = probability;
	}
}