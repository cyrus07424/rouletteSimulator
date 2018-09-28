package enums;

import java.util.ArrayList;
import java.util.List;

/**
 * ベットの種類. https://en.wikipedia.org/wiki/Roulette#Bet_odds_table
 *
 * @author
 */
public enum BetType {
	TOP_LINE_OR_BASKET, ZERO, DOUBLE_ZERO, STRAIGHT_UP_1, STRAIGHT_UP_2, STRAIGHT_UP_3, STRAIGHT_UP_4, STRAIGHT_UP_5, STRAIGHT_UP_6, STRAIGHT_UP_7, STRAIGHT_UP_8, STRAIGHT_UP_9, STRAIGHT_UP_10, STRAIGHT_UP_11, STRAIGHT_UP_12, STRAIGHT_UP_13, STRAIGHT_UP_14, STRAIGHT_UP_15, STRAIGHT_UP_16, STRAIGHT_UP_17, STRAIGHT_UP_18, STRAIGHT_UP_19, STRAIGHT_UP_20, STRAIGHT_UP_21, STRAIGHT_UP_22, STRAIGHT_UP_23, STRAIGHT_UP_24, STRAIGHT_UP_25, STRAIGHT_UP_26, STRAIGHT_UP_27, STRAIGHT_UP_28, STRAIGHT_UP_29, STRAIGHT_UP_30, STRAIGHT_UP_31, STRAIGHT_UP_32, STRAIGHT_UP_33, STRAIGHT_UP_34, STRAIGHT_UP_35, STRAIGHT_UP_36, SPLIT_0_00, SPLIT_0_1, SPLIT_0_2, SPLIT_0_3, SPLIT_1_2, SPLIT_2_3, SPLIT_4_5, SPLIT_5_6, SPLIT_7_8, SPLIT_8_9, SPLIT_10_11, SPLIT_11_12, SPLIT_13_14, SPLIT_14_15, SPLIT_15_16, SPLIT_16_17, SPLIT_17_18, SPLIT_19_20, SPLIT_20_21, SPLIT_22_23, SPLIT_23_24, SPLIT_25_26, SPLIT_26_27, SPLIT_28_29, SPLIT_29_30, SPLIT_31_32, SPLIT_32_33, SPLIT_34_35, SPLIT_35_36, SPLIT_1_4, SPLIT_2_5, SPLIT_3_6, SPLIT_4_7, SPLIT_5_8, SPLIT_6_9, SPLIT_7_10, SPLIT_8_11, SPLIT_9_12, SPLIT_10_13, SPLIT_11_14, SPLIT_12_15, SPLIT_13_16, SPLIT_14_17, SPLIT_15_18, SPLIT_16_19, SPLIT_17_20, SPLIT_18_21, SPLIT_19_22, SPLIT_20_23, SPLIT_21_24, SPLIT_22_25, SPLIT_23_26, SPLIT_24_27, SPLIT_25_28, SPLIT_26_29, SPLIT_27_30, SPLIT_28_31, SPLIT_29_32, SPLIT_30_33, SPLIT_31_34, SPLIT_32_35, SPLIT_33_36, STREET_0_1_2, STREET_0_00_2, STREET_00_2_3, STREET_1_2_3, STREET_4_5_6, STREET_7_8_9, STREET_10_11_12, STREET_13_14_15, STREET_16_17_18, STREET_19_20_21, STREET_22_23_24, STREET_25_26_27, STREET_28_29_30, STREET_31_32_33, STREET_34_35_36, CORNER_0_1_2_3, CORNER_1_2_4_5, CORNER_2_3_5_6, CORNER_4_5_7_8, CORNER_5_6_8_9, CORNER_7_8_10_11, CORNER_8_9_11_12, CORNER_10_11_13_14, CORNER_11_12_14_15, CORNER_13_14_16_17, CORNER_14_15_17_18, CORNER_19_20_22_23, CORNER_20_21_23_24, CORNER_21_23_25_26, CORNER_23_24_26_27, CORNER_25_26_28_29, CORNER_26_27_29_30, CORNER_28_29_31_32, CORNER_29_30_32_33, CORNER_31_32_34_35, CORNER_32_33_35_36, SIX_LINE_1_2_3_4_5_6, SIX_LINE_4_5_6_7_8_9, SIX_LINE_7_8_9_10_11_12, SIX_LINE_10_11_12_13_14_15, SIX_LINE_13_14_15_16_17_18, SIX_LINE_16_17_18_19_20_21, SIX_LINE_19_20_21_22_23_24, SIX_LINE_22_23_24_25_26_27, SIX_LINE_25_26_27_28_29_30, SIX_LINE_28_29_30_31_32_33, SIX_LINE_31_32_33_34_35_36, FIRST_DOZEN, SECOND_DOZEN, THIRD_DOZEN, FIRST_COLUMN, SECOND_COLUMN, THIRD_COLUMN, ODD, EVEN, RED, BLACK, ONE_TO_EIGHTEEN, NINETEEN_TO_THIRTY_SIX;

	/**
	 * 有効なベットの種類一覧を取得.
	 *
	 * @param rouletteType
	 */
	public static List<BetType> getAvailableList(RouletteType rouletteType) {
		List<BetType> availableBetTypeList = new ArrayList<>();
		for (BetType betType : values()) {
			switch (rouletteType) {
				case ONE_TO_36: {
					switch (betType) {
						case ZERO:
						case DOUBLE_ZERO:
						case TOP_LINE_OR_BASKET:
						case SPLIT_0_00:
						case SPLIT_0_1:
						case SPLIT_0_2:
						case SPLIT_0_3:
						case STREET_0_1_2:
						case STREET_0_00_2:
						case STREET_00_2_3:
						case CORNER_0_1_2_3:
							continue;
						default:
							break;
					}
				}
				case EUROPEAN_STYLE: {
					switch (betType) {
						case DOUBLE_ZERO:
						case SPLIT_0_00:
						case STREET_0_00_2:
						case STREET_00_2_3:
							continue;
						default:
							break;
					}
				}
				default:
					break;
			}
			availableBetTypeList.add(betType);
		}
		return availableBetTypeList;
	}

	/**
	 * オッズを取得.
	 *
	 * @return
	 */
	public int getOdds() {
		switch (this) {
			case ZERO:
			case DOUBLE_ZERO:
			case STRAIGHT_UP_1:
			case STRAIGHT_UP_2:
			case STRAIGHT_UP_3:
			case STRAIGHT_UP_4:
			case STRAIGHT_UP_5:
			case STRAIGHT_UP_6:
			case STRAIGHT_UP_7:
			case STRAIGHT_UP_8:
			case STRAIGHT_UP_9:
			case STRAIGHT_UP_10:
			case STRAIGHT_UP_11:
			case STRAIGHT_UP_12:
			case STRAIGHT_UP_13:
			case STRAIGHT_UP_14:
			case STRAIGHT_UP_15:
			case STRAIGHT_UP_16:
			case STRAIGHT_UP_17:
			case STRAIGHT_UP_18:
			case STRAIGHT_UP_19:
			case STRAIGHT_UP_20:
			case STRAIGHT_UP_21:
			case STRAIGHT_UP_22:
			case STRAIGHT_UP_23:
			case STRAIGHT_UP_24:
			case STRAIGHT_UP_25:
			case STRAIGHT_UP_26:
			case STRAIGHT_UP_27:
			case STRAIGHT_UP_28:
			case STRAIGHT_UP_29:
			case STRAIGHT_UP_30:
			case STRAIGHT_UP_31:
			case STRAIGHT_UP_32:
			case STRAIGHT_UP_33:
			case STRAIGHT_UP_34:
			case STRAIGHT_UP_35:
			case STRAIGHT_UP_36:
				return 36;
			case SPLIT_0_00:
			case SPLIT_0_1:
			case SPLIT_0_2:
			case SPLIT_0_3:
			case SPLIT_1_2:
			case SPLIT_2_3:
			case SPLIT_4_5:
			case SPLIT_5_6:
			case SPLIT_7_8:
			case SPLIT_8_9:
			case SPLIT_10_11:
			case SPLIT_11_12:
			case SPLIT_13_14:
			case SPLIT_14_15:
			case SPLIT_15_16:
			case SPLIT_16_17:
			case SPLIT_17_18:
			case SPLIT_19_20:
			case SPLIT_20_21:
			case SPLIT_22_23:
			case SPLIT_23_24:
			case SPLIT_25_26:
			case SPLIT_26_27:
			case SPLIT_28_29:
			case SPLIT_29_30:
			case SPLIT_31_32:
			case SPLIT_32_33:
			case SPLIT_34_35:
			case SPLIT_35_36:
			case SPLIT_1_4:
			case SPLIT_2_5:
			case SPLIT_3_6:
			case SPLIT_4_7:
			case SPLIT_5_8:
			case SPLIT_6_9:
			case SPLIT_7_10:
			case SPLIT_8_11:
			case SPLIT_9_12:
			case SPLIT_10_13:
			case SPLIT_11_14:
			case SPLIT_12_15:
			case SPLIT_13_16:
			case SPLIT_14_17:
			case SPLIT_15_18:
			case SPLIT_16_19:
			case SPLIT_17_20:
			case SPLIT_18_21:
			case SPLIT_19_22:
			case SPLIT_20_23:
			case SPLIT_21_24:
			case SPLIT_22_25:
			case SPLIT_23_26:
			case SPLIT_24_27:
			case SPLIT_25_28:
			case SPLIT_26_29:
			case SPLIT_27_30:
			case SPLIT_28_31:
			case SPLIT_29_32:
			case SPLIT_30_33:
			case SPLIT_31_34:
			case SPLIT_32_35:
			case SPLIT_33_36:
				return 18;
			case STREET_1_2_3:
			case STREET_4_5_6:
			case STREET_7_8_9:
			case STREET_10_11_12:
			case STREET_13_14_15:
			case STREET_16_17_18:
			case STREET_19_20_21:
			case STREET_22_23_24:
			case STREET_25_26_27:
			case STREET_28_29_30:
			case STREET_31_32_33:
			case STREET_34_35_36:
				return 12;
			case CORNER_1_2_4_5:
			case CORNER_2_3_5_6:
			case CORNER_4_5_7_8:
			case CORNER_5_6_8_9:
			case CORNER_7_8_10_11:
			case CORNER_8_9_11_12:
			case CORNER_10_11_13_14:
			case CORNER_11_12_14_15:
			case CORNER_13_14_16_17:
			case CORNER_14_15_17_18:
			case CORNER_19_20_22_23:
			case CORNER_20_21_23_24:
			case CORNER_21_23_25_26:
			case CORNER_23_24_26_27:
			case CORNER_25_26_28_29:
			case CORNER_26_27_29_30:
			case CORNER_28_29_31_32:
			case CORNER_29_30_32_33:
			case CORNER_31_32_34_35:
			case CORNER_32_33_35_36:
				return 9;
			case TOP_LINE_OR_BASKET:
				return 7;
			case SIX_LINE_1_2_3_4_5_6:
			case SIX_LINE_4_5_6_7_8_9:
			case SIX_LINE_7_8_9_10_11_12:
			case SIX_LINE_10_11_12_13_14_15:
			case SIX_LINE_13_14_15_16_17_18:
			case SIX_LINE_16_17_18_19_20_21:
			case SIX_LINE_19_20_21_22_23_24:
			case SIX_LINE_22_23_24_25_26_27:
			case SIX_LINE_25_26_27_28_29_30:
			case SIX_LINE_28_29_30_31_32_33:
			case SIX_LINE_31_32_33_34_35_36:
				return 6;
			case FIRST_DOZEN:
			case SECOND_DOZEN:
			case THIRD_DOZEN:
			case FIRST_COLUMN:
			case SECOND_COLUMN:
			case THIRD_COLUMN:
				return 3;
			case ODD:
			case EVEN:
			case RED:
			case BLACK:
			case ONE_TO_EIGHTEEN:
			case NINETEEN_TO_THIRTY_SIX:
				return 2;
			default:
				throw new IllegalArgumentException();
		}
	}
}