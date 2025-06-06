package enums;

import java.util.ArrayList;
import java.util.List;

import application.RouletteContext;
import constants.Configurations;

/**
 * 出目.
 *
 * @author cyrus
 */
public enum Spot {
	SPOT_0, SPOT_00, SPOT_01, SPOT_02, SPOT_03, SPOT_04, SPOT_05, SPOT_06, SPOT_07, SPOT_08, SPOT_09, SPOT_10, SPOT_11, SPOT_12, SPOT_13, SPOT_14, SPOT_15, SPOT_16, SPOT_17, SPOT_18, SPOT_19, SPOT_20, SPOT_21, SPOT_22, SPOT_23, SPOT_24, SPOT_25, SPOT_26, SPOT_27, SPOT_28, SPOT_29, SPOT_30, SPOT_31, SPOT_32, SPOT_33, SPOT_34, SPOT_35, SPOT_36;

	/**
	 * 数値から出目を取得.
	 *
	 * @param number
	 * @return
	 */
	public static Spot getByNumber(int number) {
		for (Spot spot : values()) {
			if (number == spot.getNumber()) {
				return spot;
			}
		}
		throw new IllegalArgumentException();
	}

	/**
	 * 有効な出目一覧を取得.
	 *
	 * @param rouletteType
	 * @return
	 */
	public static List<Spot> getAvailableList(RouletteType rouletteType) {
		List<Spot> availableSpotList = new ArrayList<>();
		for (Spot spot : values()) {
			if ((rouletteType == RouletteType.ONE_TO_36 && (spot == SPOT_0 || spot == SPOT_00))
					|| (rouletteType == RouletteType.EUROPEAN_STYLE && spot == SPOT_00)) {
				continue;
			}
			availableSpotList.add(spot);
		}
		return availableSpotList;
	}

	/**
	 * 次の出目を取得.
	 *
	 * @param rouletteContext
	 * @return
	 */
	public static Spot getRandomNextSpot(RouletteContext rouletteContext) {
		List<Spot> availableSpotList = getAvailableList(rouletteContext.rouletteType);
		switch (Configurations.SPOT_GENERATE_TYPE) {
			case RANDOM:
				return availableSpotList.get(Configurations.RANDOM.nextInt(availableSpotList.size()));
			case ROTATION_NUMBER:
				return availableSpotList.get((int) (rouletteContext.currentLoopCount % availableSpotList.size()));
			case ROTATION_WHEEL:
				switch (rouletteContext.rouletteType) {
					case EUROPEAN_STYLE:
						Spot[] spotArray = { SPOT_0, SPOT_32, SPOT_15, SPOT_19, SPOT_04, SPOT_21, SPOT_02, SPOT_25,
								SPOT_17, SPOT_34, SPOT_06, SPOT_27, SPOT_13, SPOT_36, SPOT_11, SPOT_30, SPOT_08,
								SPOT_23, SPOT_10, SPOT_05, SPOT_24, SPOT_16, SPOT_33, SPOT_01, SPOT_20, SPOT_14,
								SPOT_31, SPOT_09, SPOT_22, SPOT_18, SPOT_29, SPOT_07, SPOT_28, SPOT_12, SPOT_35,
								SPOT_03, SPOT_26 };
						return spotArray[((int) (rouletteContext.currentLoopCount % spotArray.length))];
					default:
						throw new IllegalArgumentException();
				}
			case RANDOM_RED_ONLY: {
				while (true) {
					Spot spot = availableSpotList.get(Configurations.RANDOM.nextInt(availableSpotList.size()));
					if (spot.isRed()) {
						return spot;
					}
				}
			}
			case RANDOM_BLACK_ONLY: {
				while (true) {
					Spot spot = availableSpotList.get(Configurations.RANDOM.nextInt(availableSpotList.size()));
					if (spot.isBlack()) {
						return spot;
					}
				}
			}
			case RANDOM_EXCEPT_ONE:
				while (true) {
					Spot spot = availableSpotList.get(Configurations.RANDOM.nextInt(availableSpotList.size()));
					if (spot != SPOT_01) {
						return spot;
					}
				}
			default:
				throw new IllegalArgumentException();
		}
	}

	/**
	 * 出目の数値を取得(0=0、00=-1).
	 *
	 * @return
	 */
	public int getNumber() {
		switch (this) {
			case SPOT_0:
				return 0;
			case SPOT_00:
				return -1;
			case SPOT_01:
				return 1;
			case SPOT_02:
				return 2;
			case SPOT_03:
				return 3;
			case SPOT_04:
				return 4;
			case SPOT_05:
				return 5;
			case SPOT_06:
				return 6;
			case SPOT_07:
				return 7;
			case SPOT_08:
				return 8;
			case SPOT_09:
				return 9;
			case SPOT_10:
				return 10;
			case SPOT_11:
				return 11;
			case SPOT_12:
				return 12;
			case SPOT_13:
				return 13;
			case SPOT_14:
				return 14;
			case SPOT_15:
				return 15;
			case SPOT_16:
				return 16;
			case SPOT_17:
				return 17;
			case SPOT_18:
				return 18;
			case SPOT_19:
				return 19;
			case SPOT_20:
				return 20;
			case SPOT_21:
				return 21;
			case SPOT_22:
				return 22;
			case SPOT_23:
				return 23;
			case SPOT_24:
				return 24;
			case SPOT_25:
				return 25;
			case SPOT_26:
				return 26;
			case SPOT_27:
				return 27;
			case SPOT_28:
				return 28;
			case SPOT_29:
				return 29;
			case SPOT_30:
				return 30;
			case SPOT_31:
				return 31;
			case SPOT_32:
				return 32;
			case SPOT_33:
				return 33;
			case SPOT_34:
				return 34;
			case SPOT_35:
				return 35;
			case SPOT_36:
				return 36;
			default:
				throw new IllegalArgumentException();
		}
	}

	/**
	 * 赤であるかを取得.
	 *
	 * @return
	 */
	public boolean isRed() {
		switch (this) {
			case SPOT_01:
			case SPOT_03:
			case SPOT_05:
			case SPOT_07:
			case SPOT_09:
			case SPOT_12:
			case SPOT_14:
			case SPOT_16:
			case SPOT_18:
			case SPOT_19:
			case SPOT_21:
			case SPOT_23:
			case SPOT_25:
			case SPOT_27:
			case SPOT_30:
			case SPOT_32:
			case SPOT_34:
			case SPOT_36:
				return true;
			default:
				return false;
		}
	}

	/**
	 * 黒であるかを取得.
	 *
	 * @return
	 */
	public boolean isBlack() {
		switch (this) {
			case SPOT_02:
			case SPOT_04:
			case SPOT_06:
			case SPOT_08:
			case SPOT_10:
			case SPOT_11:
			case SPOT_13:
			case SPOT_15:
			case SPOT_17:
			case SPOT_20:
			case SPOT_22:
			case SPOT_24:
			case SPOT_26:
			case SPOT_28:
			case SPOT_29:
			case SPOT_31:
			case SPOT_33:
			case SPOT_35:
				return true;
			default:
				return false;
		}
	}

	/**
	 * 緑であるかを取得.
	 *
	 * @return
	 */
	public boolean isGreen() {
		return !isRed() && !isBlack();
	}

	/**
	 * 偶数であるかを取得.
	 *
	 * @return
	 */
	public boolean isEven() {
		switch (this) {
			case SPOT_02:
			case SPOT_04:
			case SPOT_06:
			case SPOT_08:
			case SPOT_10:
			case SPOT_12:
			case SPOT_14:
			case SPOT_16:
			case SPOT_18:
			case SPOT_20:
			case SPOT_22:
			case SPOT_24:
			case SPOT_26:
			case SPOT_28:
			case SPOT_30:
			case SPOT_32:
			case SPOT_34:
			case SPOT_36:
				return true;
			default:
				return false;
		}
	}

	/**
	 * 奇数であるかを取得.
	 *
	 * @return
	 */
	public boolean isOdd() {
		switch (this) {
			case SPOT_01:
			case SPOT_03:
			case SPOT_05:
			case SPOT_07:
			case SPOT_09:
			case SPOT_11:
			case SPOT_13:
			case SPOT_15:
			case SPOT_17:
			case SPOT_19:
			case SPOT_21:
			case SPOT_23:
			case SPOT_25:
			case SPOT_27:
			case SPOT_29:
			case SPOT_31:
			case SPOT_33:
			case SPOT_35:
				return true;
			default:
				return false;
		}
	}

	/**
	 * 1stダズンまたは0、00であるかを取得.
	 *
	 * @return
	 */
	public boolean isFirstDozenOrZeroAndDoubleZero() {
		switch (this) {
			case SPOT_0:
			case SPOT_00:
			case SPOT_01:
			case SPOT_02:
			case SPOT_03:
			case SPOT_04:
			case SPOT_05:
			case SPOT_06:
			case SPOT_07:
			case SPOT_08:
			case SPOT_09:
			case SPOT_10:
			case SPOT_11:
			case SPOT_12:
				return true;
			default:
				return false;
		}
	}

	/**
	 * 1stダズンであるかを取得.
	 *
	 * @return
	 */
	public boolean isFirstDozen() {
		switch (this) {
			case SPOT_01:
			case SPOT_02:
			case SPOT_03:
			case SPOT_04:
			case SPOT_05:
			case SPOT_06:
			case SPOT_07:
			case SPOT_08:
			case SPOT_09:
			case SPOT_10:
			case SPOT_11:
			case SPOT_12:
				return true;
			default:
				return false;
		}
	}

	/**
	 * 2ndダズンであるかを取得.
	 *
	 * @return
	 */
	public boolean isSecondDozen() {
		switch (this) {
			case SPOT_13:
			case SPOT_14:
			case SPOT_15:
			case SPOT_16:
			case SPOT_17:
			case SPOT_18:
			case SPOT_19:
			case SPOT_20:
			case SPOT_21:
			case SPOT_22:
			case SPOT_23:
			case SPOT_24:
				return true;
			default:
				return false;
		}
	}

	/**
	 * 3rdダズンであるかを取得.
	 *
	 * @return
	 */
	public boolean isThirdDozen() {
		switch (this) {
			case SPOT_25:
			case SPOT_26:
			case SPOT_27:
			case SPOT_28:
			case SPOT_29:
			case SPOT_30:
			case SPOT_31:
			case SPOT_32:
			case SPOT_33:
			case SPOT_34:
			case SPOT_35:
			case SPOT_36:
				return true;
			default:
				return false;
		}
	}

	/**
	 * 1stカラムであるかを取得.
	 *
	 * @return
	 */
	public boolean isFirstColumn() {
		switch (this) {
			case SPOT_01:
			case SPOT_04:
			case SPOT_07:
			case SPOT_10:
			case SPOT_13:
			case SPOT_16:
			case SPOT_19:
			case SPOT_22:
			case SPOT_25:
			case SPOT_28:
			case SPOT_31:
			case SPOT_34:
				return true;
			default:
				return false;
		}
	}

	/**
	 * 2ndカラムであるかを取得.
	 *
	 * @return
	 */
	public boolean isSecondColumn() {
		switch (this) {
			case SPOT_02:
			case SPOT_05:
			case SPOT_08:
			case SPOT_11:
			case SPOT_14:
			case SPOT_17:
			case SPOT_20:
			case SPOT_23:
			case SPOT_26:
			case SPOT_29:
			case SPOT_32:
			case SPOT_35:
				return true;
			default:
				return false;
		}
	}

	/**
	 * 3rdカラムであるかを取得.
	 *
	 * @return
	 */
	public boolean isThirdColumn() {
		switch (this) {
			case SPOT_03:
			case SPOT_06:
			case SPOT_09:
			case SPOT_12:
			case SPOT_15:
			case SPOT_18:
			case SPOT_21:
			case SPOT_24:
			case SPOT_27:
			case SPOT_30:
			case SPOT_33:
			case SPOT_36:
				return true;
			default:
				return false;
		}
	}

	/**
	 * 1to18であるかを取得.
	 *
	 * @return
	 */
	public boolean is1To18() {
		return 1 <= getNumber() && getNumber() <= 18;
	}

	/**
	 * 19to36であるかを取得.
	 *
	 * @return
	 */
	public boolean is19To36() {
		return 19 <= getNumber() && getNumber() <= 36;
	}

	/**
	 * ヨーロピアンルーレットのホイール配置.
	 */
	private static final Spot[] EUROPEAN_WHEEL = {
		SPOT_0, SPOT_32, SPOT_15, SPOT_19, SPOT_04, SPOT_21, SPOT_02, SPOT_25,
		SPOT_17, SPOT_34, SPOT_06, SPOT_27, SPOT_13, SPOT_36, SPOT_11, SPOT_30, SPOT_08,
		SPOT_23, SPOT_10, SPOT_05, SPOT_24, SPOT_16, SPOT_33, SPOT_01, SPOT_20, SPOT_14,
		SPOT_31, SPOT_09, SPOT_22, SPOT_18, SPOT_29, SPOT_07, SPOT_28, SPOT_12, SPOT_35,
		SPOT_03, SPOT_26
	};

	/**
	 * アメリカンルーレットのホイール配置.
	 */
	private static final Spot[] AMERICAN_WHEEL = {
		SPOT_0, SPOT_28, SPOT_09, SPOT_26, SPOT_30, SPOT_11, SPOT_07, SPOT_20, SPOT_32, SPOT_17,
		SPOT_05, SPOT_22, SPOT_34, SPOT_15, SPOT_03, SPOT_24, SPOT_36, SPOT_13, SPOT_01, SPOT_00,
		SPOT_27, SPOT_10, SPOT_25, SPOT_29, SPOT_12, SPOT_08, SPOT_19, SPOT_31, SPOT_18, SPOT_06,
		SPOT_21, SPOT_33, SPOT_16, SPOT_04, SPOT_23, SPOT_35, SPOT_14, SPOT_02
	};

	/**
	 * 1-36ルーレットのホイール配置(0と00を除く).
	 */
	private static final Spot[] ONE_TO_36_WHEEL = {
		SPOT_32, SPOT_15, SPOT_19, SPOT_04, SPOT_21, SPOT_02, SPOT_25,
		SPOT_17, SPOT_34, SPOT_06, SPOT_27, SPOT_13, SPOT_36, SPOT_11, SPOT_30, SPOT_08,
		SPOT_23, SPOT_10, SPOT_05, SPOT_24, SPOT_16, SPOT_33, SPOT_01, SPOT_20, SPOT_14,
		SPOT_31, SPOT_09, SPOT_22, SPOT_18, SPOT_29, SPOT_07, SPOT_28, SPOT_12, SPOT_35,
		SPOT_03, SPOT_26
	};

	/**
	 * ルーレットの種類に応じたホイール配置を取得.
	 *
	 * @param rouletteType ルーレットの種類
	 * @return ホイール配置
	 */
	private static Spot[] getWheelLayout(RouletteType rouletteType) {
		switch (rouletteType) {
			case EUROPEAN_STYLE:
				return EUROPEAN_WHEEL;
			case AMERICAN_STYLE:
				return AMERICAN_WHEEL;
			case ONE_TO_36:
				return ONE_TO_36_WHEEL;
			default:
				throw new IllegalArgumentException("Unsupported roulette type: " + rouletteType);
		}
	}

	/**
	 * ホイール上での位置を取得.
	 *
	 * @param rouletteType ルーレットの種類
	 * @return ホイール上での位置（見つからない場合は-1）
	 */
	public int getWheelPosition(RouletteType rouletteType) {
		Spot[] wheelLayout = getWheelLayout(rouletteType);
		for (int i = 0; i < wheelLayout.length; i++) {
			if (wheelLayout[i] == this) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 前回の出目との、盤上での物理的な距離を取得.
	 *
	 * @param otherSpot 比較対象の出目
	 * @param rouletteType ルーレットの種類
	 * @return 物理的な距離（ホイール上での最短距離）
	 */
	public int getPhysicalDistance(Spot otherSpot, RouletteType rouletteType) {
		if (otherSpot == null) {
			throw new IllegalArgumentException("otherSpot cannot be null");
		}

		int thisPosition = this.getWheelPosition(rouletteType);
		int otherPosition = otherSpot.getWheelPosition(rouletteType);

		if (thisPosition == -1 || otherPosition == -1) {
			throw new IllegalArgumentException("One or both spots are not available in the specified roulette type");
		}

		Spot[] wheelLayout = getWheelLayout(rouletteType);
		int wheelSize = wheelLayout.length;

		// 円周上での最短距離を計算
		int clockwiseDistance = Math.abs(thisPosition - otherPosition);
		int counterClockwiseDistance = wheelSize - clockwiseDistance;

		return Math.min(clockwiseDistance, counterClockwiseDistance);
	}
}