package utils;

import java.util.ArrayList;
import java.util.List;

import enums.BetType;
import enums.Spot;
import model.Bet;

/**
 * ベットヘルパー.
 *
 * @author cyrus
 */
public class BetHelper {

	/**
	 * ベット総額を取得.
	 *
	 * @param betList
	 * @return
	 */
	public static long getTotalBetValue(List<Bet> betList) {
		long totalValue = 0;
		if (betList != null) {
			for (Bet bet : betList) {
				totalValue += bet.value;
			}
		}
		return totalValue;
	}

	/**
	 * 配当額を取得.
	 *
	 * @param betList
	 * @param spot
	 * @return
	 */
	public static long getTotalPayout(List<Bet> betList, Spot spot) {
		long totalValue = 0;
		if (betList != null) {
			for (Bet bet : betList) {
				if (isWin(bet, spot)) {
					totalValue += (bet.value * bet.betType.getOdds());
				}
			}
		}
		return totalValue;
	}

	/**
	 * ベット一覧に当選が含まれているかどうかを取得.
	 *
	 * @param betList
	 * @param spot
	 * @return
	 */
	public static boolean hasWin(List<Bet> betList, Spot spot) {
		if (betList != null) {
			for (Bet bet : betList) {
				if (isWin(bet, spot)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * ベットが当選かどうかを取得.
	 *
	 * @param bet
	 * @param spot
	 * @return
	 */
	public static boolean isWin(Bet bet, Spot spot) {
		return isWin(bet.betType, spot);
	}

	/**
	 * ベットが当選かどうかを取得.
	 *
	 * @param betType
	 * @param spot
	 * @return
	 */
	public static boolean isWin(BetType betType, Spot spot) {
		switch (betType) {
		case BLACK:
			return spot.isBlack();
		case RED:
			return spot.isRed();
		case EVEN:
			return spot.isEven();
		case ODD:
			return spot.isOdd();
		case ONE_TO_EIGHTEEN:
			return spot.is1To18();
		case NINETEEN_TO_THIRTY_SIX:
			return spot.is19To36();
		case FIRST_DOZEN:
			return spot.isFirstDozen();
		case SECOND_DOZEN:
			return spot.isSecondDozen();
		case THIRD_DOZEN:
			return spot.isThirdDozen();
		case FIRST_COLUMN:
			return spot.isFirstColumn();
		case SECOND_COLUMN:
			return spot.isSecondColumn();
		case THIRD_COLUMN:
			return spot.isThirdColumn();
		case ZERO:
			return spot == Spot.SPOT_0;
		case DOUBLE_ZERO:
			return spot == Spot.SPOT_00;
		case STRAIGHT_UP_1:
			return spot == Spot.SPOT_01;
		case STRAIGHT_UP_2:
			return spot == Spot.SPOT_02;
		case STRAIGHT_UP_3:
			return spot == Spot.SPOT_03;
		case STRAIGHT_UP_4:
			return spot == Spot.SPOT_04;
		case STRAIGHT_UP_5:
			return spot == Spot.SPOT_05;
		case STRAIGHT_UP_6:
			return spot == Spot.SPOT_06;
		case STRAIGHT_UP_7:
			return spot == Spot.SPOT_07;
		case STRAIGHT_UP_8:
			return spot == Spot.SPOT_08;
		case STRAIGHT_UP_9:
			return spot == Spot.SPOT_09;
		case STRAIGHT_UP_10:
			return spot == Spot.SPOT_10;
		case STRAIGHT_UP_11:
			return spot == Spot.SPOT_11;
		case STRAIGHT_UP_12:
			return spot == Spot.SPOT_12;
		case STRAIGHT_UP_13:
			return spot == Spot.SPOT_13;
		case STRAIGHT_UP_14:
			return spot == Spot.SPOT_14;
		case STRAIGHT_UP_15:
			return spot == Spot.SPOT_15;
		case STRAIGHT_UP_16:
			return spot == Spot.SPOT_16;
		case STRAIGHT_UP_17:
			return spot == Spot.SPOT_17;
		case STRAIGHT_UP_18:
			return spot == Spot.SPOT_18;
		case STRAIGHT_UP_19:
			return spot == Spot.SPOT_19;
		case STRAIGHT_UP_20:
			return spot == Spot.SPOT_20;
		case STRAIGHT_UP_21:
			return spot == Spot.SPOT_21;
		case STRAIGHT_UP_22:
			return spot == Spot.SPOT_22;
		case STRAIGHT_UP_23:
			return spot == Spot.SPOT_23;
		case STRAIGHT_UP_24:
			return spot == Spot.SPOT_24;
		case STRAIGHT_UP_25:
			return spot == Spot.SPOT_25;
		case STRAIGHT_UP_26:
			return spot == Spot.SPOT_26;
		case STRAIGHT_UP_27:
			return spot == Spot.SPOT_27;
		case STRAIGHT_UP_28:
			return spot == Spot.SPOT_28;
		case STRAIGHT_UP_29:
			return spot == Spot.SPOT_29;
		case STRAIGHT_UP_30:
			return spot == Spot.SPOT_30;
		case STRAIGHT_UP_31:
			return spot == Spot.SPOT_31;
		case STRAIGHT_UP_32:
			return spot == Spot.SPOT_32;
		case STRAIGHT_UP_33:
			return spot == Spot.SPOT_33;
		case STRAIGHT_UP_34:
			return spot == Spot.SPOT_34;
		case STRAIGHT_UP_35:
			return spot == Spot.SPOT_35;
		case STRAIGHT_UP_36:
			return spot == Spot.SPOT_36;
		case SPLIT_0_00:
			return spot == Spot.SPOT_0 || spot == Spot.SPOT_00;
		case SPLIT_0_1:
			return spot == Spot.SPOT_0 || spot == Spot.SPOT_01;
		case SPLIT_0_2:
			return spot == Spot.SPOT_0 || spot == Spot.SPOT_02;
		case SPLIT_0_3:
			return spot == Spot.SPOT_0 || spot == Spot.SPOT_03;
		case SPLIT_1_2:
			return spot == Spot.SPOT_01 || spot == Spot.SPOT_02;
		case SPLIT_1_4:
			return spot == Spot.SPOT_01 || spot == Spot.SPOT_04;
		case SPLIT_2_3:
			return spot == Spot.SPOT_02 || spot == Spot.SPOT_03;
		case SPLIT_2_5:
			return spot == Spot.SPOT_02 || spot == Spot.SPOT_05;
		case SPLIT_3_6:
			return spot == Spot.SPOT_03 || spot == Spot.SPOT_06;
		case SPLIT_4_5:
			return spot == Spot.SPOT_04 || spot == Spot.SPOT_05;
		case SPLIT_4_7:
			return spot == Spot.SPOT_04 || spot == Spot.SPOT_07;
		case SPLIT_5_6:
			return spot == Spot.SPOT_05 || spot == Spot.SPOT_06;
		case SPLIT_5_8:
			return spot == Spot.SPOT_05 || spot == Spot.SPOT_08;
		case SPLIT_6_9:
			return spot == Spot.SPOT_06 || spot == Spot.SPOT_09;
		case SPLIT_7_8:
			return spot == Spot.SPOT_07 || spot == Spot.SPOT_08;
		case SPLIT_7_10:
			return spot == Spot.SPOT_07 || spot == Spot.SPOT_10;
		case SPLIT_8_9:
			return spot == Spot.SPOT_08 || spot == Spot.SPOT_09;
		case SPLIT_8_11:
			return spot == Spot.SPOT_08 || spot == Spot.SPOT_11;
		case SPLIT_9_12:
			return spot == Spot.SPOT_09 || spot == Spot.SPOT_12;
		case SPLIT_10_11:
			return spot == Spot.SPOT_10 || spot == Spot.SPOT_11;
		case SPLIT_10_13:
			return spot == Spot.SPOT_10 || spot == Spot.SPOT_13;
		case SPLIT_11_12:
			return spot == Spot.SPOT_11 || spot == Spot.SPOT_12;
		case SPLIT_11_14:
			return spot == Spot.SPOT_11 || spot == Spot.SPOT_14;
		case SPLIT_12_15:
			return spot == Spot.SPOT_12 || spot == Spot.SPOT_15;
		case SPLIT_13_14:
			return spot == Spot.SPOT_13 || spot == Spot.SPOT_14;
		case SPLIT_13_16:
			return spot == Spot.SPOT_13 || spot == Spot.SPOT_16;
		case SPLIT_14_15:
			return spot == Spot.SPOT_14 || spot == Spot.SPOT_15;
		case SPLIT_14_17:
			return spot == Spot.SPOT_14 || spot == Spot.SPOT_17;
		case SPLIT_15_16:
			return spot == Spot.SPOT_15 || spot == Spot.SPOT_16;
		case SPLIT_15_18:
			return spot == Spot.SPOT_15 || spot == Spot.SPOT_18;
		case SPLIT_16_17:
			return spot == Spot.SPOT_16 || spot == Spot.SPOT_17;
		case SPLIT_16_19:
			return spot == Spot.SPOT_16 || spot == Spot.SPOT_19;
		case SPLIT_17_18:
			return spot == Spot.SPOT_17 || spot == Spot.SPOT_18;
		case SPLIT_17_20:
			return spot == Spot.SPOT_17 || spot == Spot.SPOT_20;
		case SPLIT_18_21:
			return spot == Spot.SPOT_18 || spot == Spot.SPOT_21;
		case SPLIT_19_20:
			return spot == Spot.SPOT_19 || spot == Spot.SPOT_20;
		case SPLIT_19_22:
			return spot == Spot.SPOT_19 || spot == Spot.SPOT_22;
		case SPLIT_20_21:
			return spot == Spot.SPOT_20 || spot == Spot.SPOT_21;
		case SPLIT_20_23:
			return spot == Spot.SPOT_20 || spot == Spot.SPOT_23;
		case SPLIT_21_24:
			return spot == Spot.SPOT_21 || spot == Spot.SPOT_24;
		case SPLIT_22_23:
			return spot == Spot.SPOT_22 || spot == Spot.SPOT_23;
		case SPLIT_22_25:
			return spot == Spot.SPOT_22 || spot == Spot.SPOT_25;
		case SPLIT_23_24:
			return spot == Spot.SPOT_23 || spot == Spot.SPOT_24;
		case SPLIT_23_26:
			return spot == Spot.SPOT_23 || spot == Spot.SPOT_26;
		case SPLIT_24_27:
			return spot == Spot.SPOT_24 || spot == Spot.SPOT_27;
		case SPLIT_25_26:
			return spot == Spot.SPOT_25 || spot == Spot.SPOT_26;
		case SPLIT_25_28:
			return spot == Spot.SPOT_25 || spot == Spot.SPOT_28;
		case SPLIT_26_27:
			return spot == Spot.SPOT_26 || spot == Spot.SPOT_27;
		case SPLIT_26_29:
			return spot == Spot.SPOT_26 || spot == Spot.SPOT_29;
		case SPLIT_27_30:
			return spot == Spot.SPOT_27 || spot == Spot.SPOT_30;
		case SPLIT_28_29:
			return spot == Spot.SPOT_28 || spot == Spot.SPOT_29;
		case SPLIT_28_31:
			return spot == Spot.SPOT_28 || spot == Spot.SPOT_31;
		case SPLIT_29_30:
			return spot == Spot.SPOT_29 || spot == Spot.SPOT_30;
		case SPLIT_29_32:
			return spot == Spot.SPOT_29 || spot == Spot.SPOT_32;
		case SPLIT_30_33:
			return spot == Spot.SPOT_30 || spot == Spot.SPOT_33;
		case SPLIT_31_32:
			return spot == Spot.SPOT_31 || spot == Spot.SPOT_32;
		case SPLIT_31_34:
			return spot == Spot.SPOT_31 || spot == Spot.SPOT_34;
		case SPLIT_32_33:
			return spot == Spot.SPOT_32 || spot == Spot.SPOT_33;
		case SPLIT_32_35:
			return spot == Spot.SPOT_32 || spot == Spot.SPOT_35;
		case SPLIT_33_36:
			return spot == Spot.SPOT_33 || spot == Spot.SPOT_36;
		case SPLIT_34_35:
			return spot == Spot.SPOT_34 || spot == Spot.SPOT_35;
		case SPLIT_35_36:
			return spot == Spot.SPOT_35 || spot == Spot.SPOT_36;
		case STREET_0_00_2:
			return spot == Spot.SPOT_0 || spot == Spot.SPOT_00 || spot == Spot.SPOT_02;
		case STREET_00_2_3:
			return spot == Spot.SPOT_00 || spot == Spot.SPOT_02 || spot == Spot.SPOT_03;
		case STREET_0_1_2:
			return spot == Spot.SPOT_0 || spot == Spot.SPOT_01 || spot == Spot.SPOT_02;
		case STREET_1_2_3:
			return spot == Spot.SPOT_01 || spot == Spot.SPOT_02 || spot == Spot.SPOT_03;
		case STREET_4_5_6:
			return spot == Spot.SPOT_04 || spot == Spot.SPOT_05 || spot == Spot.SPOT_06;
		case STREET_7_8_9:
			return spot == Spot.SPOT_07 || spot == Spot.SPOT_08 || spot == Spot.SPOT_09;
		case STREET_10_11_12:
			return spot == Spot.SPOT_10 || spot == Spot.SPOT_11 || spot == Spot.SPOT_12;
		case STREET_13_14_15:
			return spot == Spot.SPOT_13 || spot == Spot.SPOT_14 || spot == Spot.SPOT_15;
		case STREET_16_17_18:
			return spot == Spot.SPOT_16 || spot == Spot.SPOT_17 || spot == Spot.SPOT_18;
		case STREET_19_20_21:
			return spot == Spot.SPOT_19 || spot == Spot.SPOT_20 || spot == Spot.SPOT_21;
		case STREET_22_23_24:
			return spot == Spot.SPOT_22 || spot == Spot.SPOT_23 || spot == Spot.SPOT_24;
		case STREET_25_26_27:
			return spot == Spot.SPOT_25 || spot == Spot.SPOT_26 || spot == Spot.SPOT_27;
		case STREET_28_29_30:
			return spot == Spot.SPOT_28 || spot == Spot.SPOT_29 || spot == Spot.SPOT_30;
		case STREET_31_32_33:
			return spot == Spot.SPOT_31 || spot == Spot.SPOT_32 || spot == Spot.SPOT_33;
		case STREET_34_35_36:
			return spot == Spot.SPOT_34 || spot == Spot.SPOT_35 || spot == Spot.SPOT_36;
		case CORNER_0_1_2_3:
			return spot == Spot.SPOT_0 || spot == Spot.SPOT_01 || spot == Spot.SPOT_02 || spot == Spot.SPOT_03;
		case CORNER_1_2_4_5:
			return spot == Spot.SPOT_01 || spot == Spot.SPOT_02 || spot == Spot.SPOT_04 || spot == Spot.SPOT_05;
		case CORNER_2_3_5_6:
			return spot == Spot.SPOT_02 || spot == Spot.SPOT_03 || spot == Spot.SPOT_05 || spot == Spot.SPOT_06;
		case CORNER_4_5_7_8:
			return spot == Spot.SPOT_04 || spot == Spot.SPOT_05 || spot == Spot.SPOT_07 || spot == Spot.SPOT_08;
		case CORNER_5_6_8_9:
			return spot == Spot.SPOT_05 || spot == Spot.SPOT_06 || spot == Spot.SPOT_08 || spot == Spot.SPOT_09;
		case CORNER_7_8_10_11:
			return spot == Spot.SPOT_07 || spot == Spot.SPOT_08 || spot == Spot.SPOT_10 || spot == Spot.SPOT_11;
		case CORNER_8_9_11_12:
			return spot == Spot.SPOT_08 || spot == Spot.SPOT_09 || spot == Spot.SPOT_11 || spot == Spot.SPOT_12;
		case CORNER_10_11_13_14:
			return spot == Spot.SPOT_10 || spot == Spot.SPOT_11 || spot == Spot.SPOT_13 || spot == Spot.SPOT_14;
		case CORNER_11_12_14_15:
			return spot == Spot.SPOT_11 || spot == Spot.SPOT_12 || spot == Spot.SPOT_14 || spot == Spot.SPOT_15;
		case CORNER_13_14_16_17:
			return spot == Spot.SPOT_13 || spot == Spot.SPOT_14 || spot == Spot.SPOT_16 || spot == Spot.SPOT_17;
		case CORNER_14_15_17_18:
			return spot == Spot.SPOT_14 || spot == Spot.SPOT_15 || spot == Spot.SPOT_17 || spot == Spot.SPOT_18;
		case CORNER_16_17_19_20:
			return spot == Spot.SPOT_16 || spot == Spot.SPOT_17 || spot == Spot.SPOT_19 || spot == Spot.SPOT_20;
		case CORNER_17_18_20_21:
			return spot == Spot.SPOT_17 || spot == Spot.SPOT_18 || spot == Spot.SPOT_20 || spot == Spot.SPOT_21;
		case CORNER_19_20_22_23:
			return spot == Spot.SPOT_19 || spot == Spot.SPOT_20 || spot == Spot.SPOT_22 || spot == Spot.SPOT_23;
		case CORNER_20_21_23_24:
			return spot == Spot.SPOT_20 || spot == Spot.SPOT_21 || spot == Spot.SPOT_23 || spot == Spot.SPOT_24;
		case CORNER_22_23_25_26:
			return spot == Spot.SPOT_22 || spot == Spot.SPOT_23 || spot == Spot.SPOT_25 || spot == Spot.SPOT_26;
		case CORNER_23_24_26_27:
			return spot == Spot.SPOT_23 || spot == Spot.SPOT_24 || spot == Spot.SPOT_26 || spot == Spot.SPOT_27;
		case CORNER_25_26_28_29:
			return spot == Spot.SPOT_25 || spot == Spot.SPOT_26 || spot == Spot.SPOT_28 || spot == Spot.SPOT_29;
		case CORNER_26_27_29_30:
			return spot == Spot.SPOT_26 || spot == Spot.SPOT_27 || spot == Spot.SPOT_29 || spot == Spot.SPOT_30;
		case CORNER_28_29_31_32:
			return spot == Spot.SPOT_28 || spot == Spot.SPOT_29 || spot == Spot.SPOT_31 || spot == Spot.SPOT_32;
		case CORNER_29_30_32_33:
			return spot == Spot.SPOT_29 || spot == Spot.SPOT_30 || spot == Spot.SPOT_32 || spot == Spot.SPOT_33;
		case CORNER_31_32_34_35:
			return spot == Spot.SPOT_31 || spot == Spot.SPOT_32 || spot == Spot.SPOT_34 || spot == Spot.SPOT_35;
		case CORNER_32_33_35_36:
			return spot == Spot.SPOT_32 || spot == Spot.SPOT_33 || spot == Spot.SPOT_35 || spot == Spot.SPOT_36;
		case TOP_LINE_OR_BASKET:
			return spot == Spot.SPOT_0 || spot == Spot.SPOT_00 || spot == Spot.SPOT_01 || spot == Spot.SPOT_02
					|| spot == Spot.SPOT_03;
		case SIX_LINE_1_2_3_4_5_6:
			return spot == Spot.SPOT_01 || spot == Spot.SPOT_02 || spot == Spot.SPOT_03 || spot == Spot.SPOT_04
					|| spot == Spot.SPOT_05 || spot == Spot.SPOT_06;
		case SIX_LINE_4_5_6_7_8_9:
			return spot == Spot.SPOT_04 || spot == Spot.SPOT_05 || spot == Spot.SPOT_06 || spot == Spot.SPOT_07
					|| spot == Spot.SPOT_08 || spot == Spot.SPOT_09;
		case SIX_LINE_7_8_9_10_11_12:
			return spot == Spot.SPOT_07 || spot == Spot.SPOT_08 || spot == Spot.SPOT_09 || spot == Spot.SPOT_10
					|| spot == Spot.SPOT_11 || spot == Spot.SPOT_12;
		case SIX_LINE_10_11_12_13_14_15:
			return spot == Spot.SPOT_10 || spot == Spot.SPOT_11 || spot == Spot.SPOT_12 || spot == Spot.SPOT_13
					|| spot == Spot.SPOT_14 || spot == Spot.SPOT_15;
		case SIX_LINE_13_14_15_16_17_18:
			return spot == Spot.SPOT_13 || spot == Spot.SPOT_14 || spot == Spot.SPOT_15 || spot == Spot.SPOT_16
					|| spot == Spot.SPOT_17 || spot == Spot.SPOT_18;
		case SIX_LINE_16_17_18_19_20_21:
			return spot == Spot.SPOT_16 || spot == Spot.SPOT_17 || spot == Spot.SPOT_18 || spot == Spot.SPOT_19
					|| spot == Spot.SPOT_20 || spot == Spot.SPOT_21;
		case SIX_LINE_19_20_21_22_23_24:
			return spot == Spot.SPOT_19 || spot == Spot.SPOT_20 || spot == Spot.SPOT_21 || spot == Spot.SPOT_22
					|| spot == Spot.SPOT_23 || spot == Spot.SPOT_24;
		case SIX_LINE_22_23_24_25_26_27:
			return spot == Spot.SPOT_22 || spot == Spot.SPOT_23 || spot == Spot.SPOT_24 || spot == Spot.SPOT_25
					|| spot == Spot.SPOT_26 || spot == Spot.SPOT_27;
		case SIX_LINE_25_26_27_28_29_30:
			return spot == Spot.SPOT_25 || spot == Spot.SPOT_26 || spot == Spot.SPOT_27 || spot == Spot.SPOT_28
					|| spot == Spot.SPOT_29 || spot == Spot.SPOT_30;
		case SIX_LINE_28_29_30_31_32_33:
			return spot == Spot.SPOT_28 || spot == Spot.SPOT_29 || spot == Spot.SPOT_30 || spot == Spot.SPOT_31
					|| spot == Spot.SPOT_32 || spot == Spot.SPOT_33;
		case SIX_LINE_31_32_33_34_35_36:
			return spot == Spot.SPOT_31 || spot == Spot.SPOT_32 || spot == Spot.SPOT_33 || spot == Spot.SPOT_34
					|| spot == Spot.SPOT_35 || spot == Spot.SPOT_36;
		default:
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 出目に一致する1点賭けのベットの種類を取得.
	 *
	 * @param spot
	 * @return
	 */
	public static BetType getStraightUpBetType(Spot spot) {
		switch (spot) {
		case SPOT_0:
			return BetType.ZERO;
		case SPOT_00:
			return BetType.DOUBLE_ZERO;
		case SPOT_01:
			return BetType.STRAIGHT_UP_1;
		case SPOT_02:
			return BetType.STRAIGHT_UP_2;
		case SPOT_03:
			return BetType.STRAIGHT_UP_3;
		case SPOT_04:
			return BetType.STRAIGHT_UP_4;
		case SPOT_05:
			return BetType.STRAIGHT_UP_5;
		case SPOT_06:
			return BetType.STRAIGHT_UP_6;
		case SPOT_07:
			return BetType.STRAIGHT_UP_7;
		case SPOT_08:
			return BetType.STRAIGHT_UP_8;
		case SPOT_09:
			return BetType.STRAIGHT_UP_9;
		case SPOT_10:
			return BetType.STRAIGHT_UP_10;
		case SPOT_11:
			return BetType.STRAIGHT_UP_11;
		case SPOT_12:
			return BetType.STRAIGHT_UP_12;
		case SPOT_13:
			return BetType.STRAIGHT_UP_13;
		case SPOT_14:
			return BetType.STRAIGHT_UP_14;
		case SPOT_15:
			return BetType.STRAIGHT_UP_15;
		case SPOT_16:
			return BetType.STRAIGHT_UP_16;
		case SPOT_17:
			return BetType.STRAIGHT_UP_17;
		case SPOT_18:
			return BetType.STRAIGHT_UP_18;
		case SPOT_19:
			return BetType.STRAIGHT_UP_19;
		case SPOT_20:
			return BetType.STRAIGHT_UP_20;
		case SPOT_21:
			return BetType.STRAIGHT_UP_21;
		case SPOT_22:
			return BetType.STRAIGHT_UP_22;
		case SPOT_23:
			return BetType.STRAIGHT_UP_23;
		case SPOT_24:
			return BetType.STRAIGHT_UP_24;
		case SPOT_25:
			return BetType.STRAIGHT_UP_25;
		case SPOT_26:
			return BetType.STRAIGHT_UP_26;
		case SPOT_27:
			return BetType.STRAIGHT_UP_27;
		case SPOT_28:
			return BetType.STRAIGHT_UP_28;
		case SPOT_29:
			return BetType.STRAIGHT_UP_29;
		case SPOT_30:
			return BetType.STRAIGHT_UP_30;
		case SPOT_31:
			return BetType.STRAIGHT_UP_31;
		case SPOT_32:
			return BetType.STRAIGHT_UP_32;
		case SPOT_33:
			return BetType.STRAIGHT_UP_33;
		case SPOT_34:
			return BetType.STRAIGHT_UP_34;
		case SPOT_35:
			return BetType.STRAIGHT_UP_35;
		case SPOT_36:
			return BetType.STRAIGHT_UP_36;
		default:
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 出目に一致するフラワーベットのベットの種類を取得.
	 *
	 * @param spot
	 * @return
	 */
	public static List<BetType> getFlowerBetBetTypeList(Spot spot) {
		List<BetType> betTypeList = new ArrayList<>();
		for (BetType betType : new BetType[] { BetType.STRAIGHT_UP_1, BetType.STRAIGHT_UP_2, BetType.STRAIGHT_UP_3,
				BetType.STRAIGHT_UP_4, BetType.STRAIGHT_UP_5, BetType.STRAIGHT_UP_6, BetType.STRAIGHT_UP_7,
				BetType.STRAIGHT_UP_8, BetType.STRAIGHT_UP_9, BetType.STRAIGHT_UP_10, BetType.STRAIGHT_UP_11,
				BetType.STRAIGHT_UP_12, BetType.STRAIGHT_UP_13, BetType.STRAIGHT_UP_14, BetType.STRAIGHT_UP_15,
				BetType.STRAIGHT_UP_16, BetType.STRAIGHT_UP_17, BetType.STRAIGHT_UP_18, BetType.STRAIGHT_UP_19,
				BetType.STRAIGHT_UP_20, BetType.STRAIGHT_UP_21, BetType.STRAIGHT_UP_22, BetType.STRAIGHT_UP_23,
				BetType.STRAIGHT_UP_24, BetType.STRAIGHT_UP_25, BetType.STRAIGHT_UP_26, BetType.STRAIGHT_UP_27,
				BetType.STRAIGHT_UP_28, BetType.STRAIGHT_UP_29, BetType.STRAIGHT_UP_30, BetType.STRAIGHT_UP_31,
				BetType.STRAIGHT_UP_32, BetType.STRAIGHT_UP_33, BetType.STRAIGHT_UP_34, BetType.STRAIGHT_UP_35,
				BetType.STRAIGHT_UP_36, BetType.SPLIT_1_2, BetType.SPLIT_2_3, BetType.SPLIT_4_5, BetType.SPLIT_5_6,
				BetType.SPLIT_7_8, BetType.SPLIT_8_9, BetType.SPLIT_10_11, BetType.SPLIT_11_12, BetType.SPLIT_13_14,
				BetType.SPLIT_14_15, BetType.SPLIT_15_16, BetType.SPLIT_16_17, BetType.SPLIT_17_18, BetType.SPLIT_19_20,
				BetType.SPLIT_20_21, BetType.SPLIT_22_23, BetType.SPLIT_23_24, BetType.SPLIT_25_26, BetType.SPLIT_26_27,
				BetType.SPLIT_28_29, BetType.SPLIT_29_30, BetType.SPLIT_31_32, BetType.SPLIT_32_33, BetType.SPLIT_34_35,
				BetType.SPLIT_35_36, BetType.SPLIT_1_4, BetType.SPLIT_2_5, BetType.SPLIT_3_6, BetType.SPLIT_4_7,
				BetType.SPLIT_5_8, BetType.SPLIT_6_9, BetType.SPLIT_7_10, BetType.SPLIT_8_11, BetType.SPLIT_9_12,
				BetType.SPLIT_10_13, BetType.SPLIT_11_14, BetType.SPLIT_12_15, BetType.SPLIT_13_16, BetType.SPLIT_14_17,
				BetType.SPLIT_15_18, BetType.SPLIT_16_19, BetType.SPLIT_17_20, BetType.SPLIT_18_21, BetType.SPLIT_19_22,
				BetType.SPLIT_20_23, BetType.SPLIT_21_24, BetType.SPLIT_22_25, BetType.SPLIT_23_26, BetType.SPLIT_24_27,
				BetType.SPLIT_25_28, BetType.SPLIT_26_29, BetType.SPLIT_27_30, BetType.SPLIT_28_31, BetType.SPLIT_29_32,
				BetType.SPLIT_30_33, BetType.SPLIT_31_34, BetType.SPLIT_32_35, BetType.SPLIT_33_36,
				BetType.CORNER_1_2_4_5, BetType.CORNER_2_3_5_6, BetType.CORNER_4_5_7_8, BetType.CORNER_5_6_8_9,
				BetType.CORNER_7_8_10_11, BetType.CORNER_8_9_11_12, BetType.CORNER_10_11_13_14,
				BetType.CORNER_11_12_14_15, BetType.CORNER_13_14_16_17, BetType.CORNER_14_15_17_18,
				BetType.CORNER_16_17_19_20, BetType.CORNER_17_18_20_21, BetType.CORNER_19_20_22_23,
				BetType.CORNER_20_21_23_24, BetType.CORNER_22_23_25_26, BetType.CORNER_23_24_26_27,
				BetType.CORNER_25_26_28_29, BetType.CORNER_26_27_29_30, BetType.CORNER_28_29_31_32,
				BetType.CORNER_29_30_32_33, BetType.CORNER_31_32_34_35, BetType.CORNER_32_33_35_36 }) {
			if (isWin(betType, spot)) {
				betTypeList.add(betType);
			}
		}
		return betTypeList;
	}
}