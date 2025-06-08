package application;

import java.util.LinkedList;

import constants.Configurations;
import enums.RouletteType;
import enums.Spot;

/**
 * ルーレットのコンテキスト.
 *
 * @author cyrus
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
	 * シミュレーション速度(ミリ秒単位のウェイト時間).
	 */
	public long simulationSpeed;

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
		this.simulationSpeed = 1000; // デフォルト1秒
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

	/**
	 * 各出目の出現回数を取得.
	 *
	 * @return 出目と出現回数のマップ
	 */
	public java.util.Map<Spot, Integer> getSpotFrequency() {
		java.util.Map<Spot, Integer> frequencyMap = new java.util.HashMap<>();
		for (Spot spot : spotHistoryList) {
			frequencyMap.put(spot, frequencyMap.getOrDefault(spot, 0) + 1);
		}
		return frequencyMap;
	}

	/**
	 * 指定した出目の出現回数を取得.
	 *
	 * @param spot 出目
	 * @return 出現回数
	 */
	public int getSpotCount(Spot spot) {
		int count = 0;
		for (Spot s : spotHistoryList) {
			if (s == spot) {
				count++;
			}
		}
		return count;
	}
}