package application;

import constants.Configurations;
import enums.RouletteType;
import enums.Spot;

import java.util.LinkedList;

/**
 * ルーレットのコンテキスト. TODO シングルトンにする?
 *
 * @author
 */
public class RouletteContext {

	/**
	 * ルーレットの種類.
	 */
	public final RouletteType rouletteType;

	/**
	 * 出目の履歴(最後の要素が最新).
	 */
	public final LinkedList<Spot> spotHistoryList = new LinkedList<>();

	/**
	 * 初期所持金..
	 */
	public final long initialBalance;

	/**
	 * 最小ベット額.
	 */
	public final long minimumBet;

	/**
	 * 最大ベット額.
	 */
	public final long maximumBet;

	/**
	 * 現在の試行回数.
	 */
	public long currentLoopCount;

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteType
	 * @param initialBalance
	 * @param minimumBet
	 * @param maximumBet
	 */
	public RouletteContext(RouletteType rouletteType, long initialBalance, long minimumBet, long maximumBet) {
		this.rouletteType = rouletteType;
		this.initialBalance = initialBalance;
		this.minimumBet = minimumBet;
		this.maximumBet = maximumBet;
		this.currentLoopCount = 0;
	}

	/**
	 * 最新の出目を取得.
	 *
	 * @return
	 */
	public Spot getLastSpot() {
		return spotHistoryList.peekLast();
	}

	/**
	 * 出目の履歴を追加.
	 *
	 * @return
	 */
	public void addSpotHistory(Spot spot) {
		spotHistoryList.offer(spot);
		if (Configurations.SPOT_HISTORY_LIST_SIZE < spotHistoryList.size()) {
			spotHistoryList.poll();
		}
	}

	/**
	 * 出目の履歴のうち赤の割合を取得.
	 *
	 * @return
	 */
	public double getRedRate() {
		if (spotHistoryList.isEmpty()) {
			return 0;
		} else {
			int redCount = 0;
			for (Spot spot : spotHistoryList) {
				if (spot.isRed()) {
					redCount++;
				}
			}
			return ((double) redCount) / ((double) spotHistoryList.size());
		}
	}

	/**
	 * 出目の履歴のうち黒の割合を取得.
	 *
	 * @return
	 */
	public double getBlackRate() {
		if (spotHistoryList.isEmpty()) {
			return 0;
		} else {
			int blackCount = 0;
			for (Spot spot : spotHistoryList) {
				if (spot.isBlack()) {
					blackCount++;
				}
			}
			return ((double) blackCount) / ((double) spotHistoryList.size());
		}
	}

	/**
	 * 出目の履歴のうち緑の割合を取得.
	 *
	 * @return
	 */
	public double getGreenRate() {
		if (spotHistoryList.isEmpty()) {
			return 0;
		} else {
			int greenCount = 0;
			for (Spot spot : spotHistoryList) {
				if (spot.isGreen()) {
					greenCount++;
				}
			}
			return ((double) greenCount) / ((double) spotHistoryList.size());
		}
	}
}