package enums;

import java.util.ArrayList;
import java.util.List;

/**
 * ベットの種類. https://en.wikipedia.org/wiki/Roulette#Bet_odds_table
 *
 * @author cyrus
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

	/**
	 * ベット台上での位置を取得.
	 * 
	 * @return ベット台上での位置座標 [x, y] (見つからない場合はnull)
	 */
	public int[] getTablePosition() {
		switch (this) {
			// Straight up bets - 数字の直接ベット
			case ZERO:
				return new int[]{1, 0}; // 0は特別な位置
			case DOUBLE_ZERO:
				return new int[]{0, 0}; // 00は特別な位置
			case STRAIGHT_UP_1:
				return new int[]{0, 3}; // 1は左下
			case STRAIGHT_UP_2:
				return new int[]{0, 2}; 
			case STRAIGHT_UP_3:
				return new int[]{0, 1}; // 3は左上
			case STRAIGHT_UP_4:
				return new int[]{1, 3};
			case STRAIGHT_UP_5:
				return new int[]{1, 2};
			case STRAIGHT_UP_6:
				return new int[]{1, 1};
			case STRAIGHT_UP_7:
				return new int[]{2, 3};
			case STRAIGHT_UP_8:
				return new int[]{2, 2};
			case STRAIGHT_UP_9:
				return new int[]{2, 1};
			case STRAIGHT_UP_10:
				return new int[]{3, 3};
			case STRAIGHT_UP_11:
				return new int[]{3, 2};
			case STRAIGHT_UP_12:
				return new int[]{3, 1};
			case STRAIGHT_UP_13:
				return new int[]{4, 3};
			case STRAIGHT_UP_14:
				return new int[]{4, 2};
			case STRAIGHT_UP_15:
				return new int[]{4, 1};
			case STRAIGHT_UP_16:
				return new int[]{5, 3};
			case STRAIGHT_UP_17:
				return new int[]{5, 2};
			case STRAIGHT_UP_18:
				return new int[]{5, 1};
			case STRAIGHT_UP_19:
				return new int[]{6, 3};
			case STRAIGHT_UP_20:
				return new int[]{6, 2};
			case STRAIGHT_UP_21:
				return new int[]{6, 1};
			case STRAIGHT_UP_22:
				return new int[]{7, 3};
			case STRAIGHT_UP_23:
				return new int[]{7, 2};
			case STRAIGHT_UP_24:
				return new int[]{7, 1};
			case STRAIGHT_UP_25:
				return new int[]{8, 3};
			case STRAIGHT_UP_26:
				return new int[]{8, 2};
			case STRAIGHT_UP_27:
				return new int[]{8, 1};
			case STRAIGHT_UP_28:
				return new int[]{9, 3};
			case STRAIGHT_UP_29:
				return new int[]{9, 2};
			case STRAIGHT_UP_30:
				return new int[]{9, 1};
			case STRAIGHT_UP_31:
				return new int[]{10, 3};
			case STRAIGHT_UP_32:
				return new int[]{10, 2};
			case STRAIGHT_UP_33:
				return new int[]{10, 1};
			case STRAIGHT_UP_34:
				return new int[]{11, 3};
			case STRAIGHT_UP_35:
				return new int[]{11, 2};
			case STRAIGHT_UP_36:
				return new int[]{11, 1};
			
			// Split bets - 隣接する2つの数字のベット (横方向)
			case SPLIT_1_2:
				return new int[]{0, 2}; // 1と2の間
			case SPLIT_2_3:
				return new int[]{0, 1}; // 2と3の間
			case SPLIT_4_5:
				return new int[]{1, 2}; 
			case SPLIT_5_6:
				return new int[]{1, 1};
			case SPLIT_7_8:
				return new int[]{2, 2};
			case SPLIT_8_9:
				return new int[]{2, 1};
			case SPLIT_10_11:
				return new int[]{3, 2};
			case SPLIT_11_12:
				return new int[]{3, 1};
			case SPLIT_13_14:
				return new int[]{4, 2};
			case SPLIT_14_15:
				return new int[]{4, 1};
			case SPLIT_16_17:
				return new int[]{5, 2};
			case SPLIT_17_18:
				return new int[]{5, 1};
			case SPLIT_19_20:
				return new int[]{6, 2};
			case SPLIT_20_21:
				return new int[]{6, 1};
			case SPLIT_22_23:
				return new int[]{7, 2};
			case SPLIT_23_24:
				return new int[]{7, 1};
			case SPLIT_25_26:
				return new int[]{8, 2};
			case SPLIT_26_27:
				return new int[]{8, 1};
			case SPLIT_28_29:
				return new int[]{9, 2};
			case SPLIT_29_30:
				return new int[]{9, 1};
			case SPLIT_31_32:
				return new int[]{10, 2};
			case SPLIT_32_33:
				return new int[]{10, 1};
			case SPLIT_34_35:
				return new int[]{11, 2};
			case SPLIT_35_36:
				return new int[]{11, 1};
			
			// Split bets - 隣接する2つの数字のベット (縦方向)
			case SPLIT_1_4:
				return new int[]{0, 3}; // 1と4の間
			case SPLIT_2_5:
				return new int[]{0, 2}; 
			case SPLIT_3_6:
				return new int[]{0, 1};
			case SPLIT_4_7:
				return new int[]{1, 3};
			case SPLIT_5_8:
				return new int[]{1, 2};
			case SPLIT_6_9:
				return new int[]{1, 1};
			case SPLIT_7_10:
				return new int[]{2, 3};
			case SPLIT_8_11:
				return new int[]{2, 2};
			case SPLIT_9_12:
				return new int[]{2, 1};
			case SPLIT_10_13:
				return new int[]{3, 3};
			case SPLIT_11_14:
				return new int[]{3, 2};
			case SPLIT_12_15:
				return new int[]{3, 1};
			case SPLIT_13_16:
				return new int[]{4, 3};
			case SPLIT_14_17:
				return new int[]{4, 2};
			case SPLIT_15_18:
				return new int[]{4, 1};
			case SPLIT_16_19:
				return new int[]{5, 3};
			case SPLIT_17_20:
				return new int[]{5, 2};
			case SPLIT_18_21:
				return new int[]{5, 1};
			case SPLIT_19_22:
				return new int[]{6, 3};
			case SPLIT_20_23:
				return new int[]{6, 2};
			case SPLIT_21_24:
				return new int[]{6, 1};
			case SPLIT_22_25:
				return new int[]{7, 3};
			case SPLIT_23_26:
				return new int[]{7, 2};
			case SPLIT_24_27:
				return new int[]{7, 1};
			case SPLIT_25_28:
				return new int[]{8, 3};
			case SPLIT_26_29:
				return new int[]{8, 2};
			case SPLIT_27_30:
				return new int[]{8, 1};
			case SPLIT_28_31:
				return new int[]{9, 3};
			case SPLIT_29_32:
				return new int[]{9, 2};
			case SPLIT_30_33:
				return new int[]{9, 1};
			case SPLIT_31_34:
				return new int[]{10, 3};
			case SPLIT_32_35:
				return new int[]{10, 2};
			case SPLIT_33_36:
				return new int[]{10, 1};
			
			// Special split bets with 0
			case SPLIT_0_00:
				return new int[]{0, 0}; // 0と00の間
			case SPLIT_0_1:
				return new int[]{0, 1}; // 0と1の間
			case SPLIT_0_2:
				return new int[]{0, 2}; // 0と2の間
			case SPLIT_0_3:
				return new int[]{0, 3}; // 0と3の間
			
			// Corner bets - 4つの数字の角のベット
			case CORNER_1_2_4_5:
				return new int[]{0, 2}; // 1,2,4,5の中心
			case CORNER_2_3_5_6:
				return new int[]{0, 1}; // 2,3,5,6の中心
			case CORNER_4_5_7_8:
				return new int[]{1, 2}; // 4,5,7,8の中心
			case CORNER_5_6_8_9:
				return new int[]{1, 1}; // 5,6,8,9の中心
			case CORNER_7_8_10_11:
				return new int[]{2, 2}; // 7,8,10,11の中心
			case CORNER_8_9_11_12:
				return new int[]{2, 1}; // 8,9,11,12の中心
			case CORNER_10_11_13_14:
				return new int[]{3, 2}; // 10,11,13,14の中心
			case CORNER_11_12_14_15:
				return new int[]{3, 1}; // 11,12,14,15の中心
			case CORNER_13_14_16_17:
				return new int[]{4, 2}; // 13,14,16,17の中心
			case CORNER_14_15_17_18:
				return new int[]{4, 1}; // 14,15,17,18の中心
			case CORNER_19_20_22_23:
				return new int[]{6, 2}; // 19,20,22,23の中心
			case CORNER_20_21_23_24:
				return new int[]{6, 1}; // 20,21,23,24の中心
			case CORNER_21_23_25_26:
				return new int[]{7, 2}; // 21,23,25,26の中心（注意：実際のコーナーではないが近似）
			case CORNER_23_24_26_27:
				return new int[]{7, 1}; // 23,24,26,27の中心
			case CORNER_25_26_28_29:
				return new int[]{8, 2}; // 25,26,28,29の中心
			case CORNER_26_27_29_30:
				return new int[]{8, 1}; // 26,27,29,30の中心
			case CORNER_28_29_31_32:
				return new int[]{9, 2}; // 28,29,31,32の中心
			case CORNER_29_30_32_33:
				return new int[]{9, 1}; // 29,30,32,33の中心
			case CORNER_31_32_34_35:
				return new int[]{10, 2}; // 31,32,34,35の中心
			case CORNER_32_33_35_36:
				return new int[]{10, 1}; // 32,33,35,36の中心
			
			// その他のベットタイプは中心点で近似
			default:
				return null; // 未対応のベットタイプ
		}
	}

	/**
	 * 他のベットタイプとの、ベット台上での物理的な距離を取得.
	 *
	 * @param otherBetType 比較対象のベットタイプ
	 * @return 物理的な距離（ユークリッド距離）
	 */
	public double getPhysicalDistance(BetType otherBetType) {
		if (otherBetType == null) {
			throw new IllegalArgumentException("otherBetType cannot be null");
		}

		int[] thisPosition = this.getTablePosition();
		int[] otherPosition = otherBetType.getTablePosition();

		if (thisPosition == null || otherPosition == null) {
			throw new IllegalArgumentException("One or both bet types do not have table positions defined");
		}

		// ユークリッド距離を計算
		double dx = thisPosition[0] - otherPosition[0];
		double dy = thisPosition[1] - otherPosition[1];
		return Math.sqrt(dx * dx + dy * dy);
	}
}