package strategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import application.RouletteContext;
import constants.Configurations;
import enums.Spot;
import model.Bet;
import utils.BetHelper;

/**
 * 戦略のベースクラス.
 *
 * @author
 */
public abstract class BaseStrategy {

	/**
	 * 現在の所持金.
	 */
	public long currentBalance;

	/**
	 * 所持金の最大値.
	 */
	public long maximumBalance = Long.MIN_VALUE;

	/**
	 * 所持金の最小値.
	 */
	public long minimumBalance = Long.MAX_VALUE;

	/**
	 * ベット総額の最大値.
	 */
	public long maximumTotalBetValue = Long.MIN_VALUE;

	/**
	 * 当選回数.
	 */
	public long wonCount = 0;

	/**
	 * 負け回数.
	 */
	public long lostCount = 0;

	/**
	 * ベット回数(1箇所以上のベットを行った試行回数).
	 */
	public long betCount = 0;

	/**
	 * 総ベット額.
	 */
	public long wholeTotalBetValue = 0;

	/**
	 * 総配当額.
	 */
	public long wholeTotalPayoutValue = 0;

	/**
	 * 所持金の履歴(最後の要素が最新).
	 */
	public final LinkedList<Long> balanceHistoryList = new LinkedList<>();

	/**
	 * 前回のベット一覧.
	 */
	protected List<Bet> lastBetList;

	/**
	 * 戦略名を取得.
	 *
	 * @return
	 */
	public abstract String getStrategyName();

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public BaseStrategy(RouletteContext rouletteContext) {
		currentBalance = rouletteContext.initialBalance;
	}

	/**
	 * 次のベット一覧を取得.
	 *
	 * @param rouletteContext
	 */
	public List<Bet> getNextBetList(RouletteContext rouletteContext) {
		if (isLive()) {
			// 次のベット一覧を取得
			lastBetList = getNextBetListImpl(rouletteContext);
		} else {
			// ベットなし
			lastBetList = Collections.emptyList();
		}

		// ベット総額の最大値を更新
		long totalBetValue = BetHelper.getTotalBetValue(lastBetList);
		if (maximumTotalBetValue < totalBetValue) {
			maximumTotalBetValue = totalBetValue;
		}

		return lastBetList;
	}

	/**
	 * 次のベット一覧を取得.
	 *
	 * @param rouletteContext
	 */
	protected abstract List<Bet> getNextBetListImpl(RouletteContext rouletteContext);

	/**
	 * 前回のベット総額を取得.
	 *
	 * @return
	 */
	public long getLastTotalBetValue() {
		return BetHelper.getTotalBetValue(lastBetList);
	}

	/**
	 * パラメータを更新.
	 *
	 * @param betList
	 * @param spot
	 *            最新の出目
	 */
	public void updateStrategyParameter(List<Bet> betList, Spot spot) {
		// ベットが存在する場合
		if (!betList.isEmpty()) {
			// 所持金からベット総額を減算
			long currentTotalBetValue = BetHelper.getTotalBetValue(betList);
			wholeTotalBetValue += currentTotalBetValue;
			currentBalance -= currentTotalBetValue;

			// 所持金に配当額を加算
			long currentTotalPayout = BetHelper.getTotalPayout(betList, spot);
			wholeTotalPayoutValue += currentTotalPayout;
			currentBalance += currentTotalPayout;

			// カウントを加算
			betCount++;
			if (BetHelper.hasWin(betList, spot)) {
				// ベットに当選が含まれている場合
				wonCount++;
			} else {
				lostCount++;
			}
		}

		// 所持金の最大値、最小値を更新
		if (maximumBalance < currentBalance) {
			maximumBalance = currentBalance;
		}
		if (currentBalance < minimumBalance) {
			minimumBalance = currentBalance;
		}

		// 所持金の履歴を更新
		balanceHistoryList.offer(currentBalance);
		if (Configurations.BALANCE_HISTORY_SIZE < balanceHistoryList.size()) {
			balanceHistoryList.poll();
		}
	}

	/**
	 * 有効な戦略(脱落していない)かどうかを取得.<br>
	 * 所持金が0以下になった場合脱落.
	 *
	 * @return
	 */
	public boolean isLive() {
		return 0 < currentBalance;
	}

	/**
	 * 勝率を取得.
	 */
	public double getWinningAverage() {
		if (betCount == 0) {
			return 0;
		} else {
			return (double) wonCount / (double) betCount;
		}
	}

	/**
	 * 平均ベット額を取得.
	 *
	 * @return
	 */
	public long getAverageTotalBetValue() {
		if (betCount == 0) {
			return 0;
		} else {
			return wholeTotalBetValue / betCount;
		}
	}

	/**
	 * 平均配当額を取得.
	 *
	 * @return
	 */
	public long getAverageTotalPayoutValue() {
		if (betCount == 0) {
			return 0;
		} else {
			return wholeTotalPayoutValue / betCount;
		}
	}

	/**
	 * 前回のベットで当選したかどうかを取得.
	 *
	 * @param rouletteContext
	 * @return
	 */
	protected boolean wasLastBetWon(RouletteContext rouletteContext) {
		return BetHelper.hasWin(lastBetList, rouletteContext.getLastSpot());
	}

	/**
	 * 前回のベットが存在するかどうかを取得..
	 *
	 * @return
	 */
	protected boolean hasLastBet() {
		return lastBetList != null && !lastBetList.isEmpty();
	}

	/**
	 * 現在の所持金の降順にソートするComparatorを取得.
	 *
	 * @return
	 */
	public static Comparator<BaseStrategy> getBalanceComparator() {
		return (o1, o2) -> Long.compare(o2.currentBalance, o1.currentBalance);
	}

	/**
	 * 戦略名の昇順にソートするComparatorを取得.
	 *
	 * @return
	 */
	public static Comparator<BaseStrategy> getStrategyNameComparator() {
		return Comparator.comparing(BaseStrategy::getStrategyName);
	}
}
