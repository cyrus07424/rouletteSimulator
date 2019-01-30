package strategy;

import java.util.ArrayList;
import java.util.List;

import application.RouletteContext;
import enums.BetType;
import enums.Spot;
import model.Bet;

/**
 * 98.48パーセント法.<br>
 * http://www.xn--9ckk7he3f9633bhel9uq.jp/9848_percent.html
 *
 * @author cyrus
 */
public class Rate_98_48_PercentStrategy extends BaseStrategy {

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public Rate_98_48_PercentStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "98.48パーセント法";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		List<Bet> betList = new ArrayList<>();
		if (3 <= rouletteContext.spotHistoryList.size()) {

			// 出目の履歴を取得
			Spot spot1 = rouletteContext.spotHistoryList.get(rouletteContext.spotHistoryList.size() - 3);
			Spot spot2 = rouletteContext.spotHistoryList.get(rouletteContext.spotHistoryList.size() - 2);
			Spot spot3 = rouletteContext.spotHistoryList.get(rouletteContext.spotHistoryList.size() - 1);

			if (spot1.isFirstDozenOrZeroAndDoubleZero() && spot2.isFirstDozenOrZeroAndDoubleZero()
					&& spot3.isFirstDozenOrZeroAndDoubleZero()) {
				long useBet = rouletteContext.minimumBet;

				// 前回ベットしている場合は前回ベットの倍額を賭ける
				if (lastBetList != null && !lastBetList.isEmpty()) {
					useBet = lastBetList.get(0).value * 2;
				}
				betList.add(new Bet(BetType.SECOND_DOZEN, useBet));
				betList.add(new Bet(BetType.THIRD_DOZEN, useBet));
			}
		}
		return betList;
	}
}
