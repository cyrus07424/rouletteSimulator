package model;

import enums.BetType;

/**
 * ベット.
 *
 * @author cyrus
 */
public class Bet {

	/**
	 * ベットの種類.
	 */
	public BetType betType;

	/**
	 * ベット額.
	 */
	public long value;

	/**
	 * コンストラクタ.
	 *
	 * @param betType
	 * @param value
	 */
	public Bet(BetType betType, long value) {
		this.betType = betType;
		this.value = value;
	}
}