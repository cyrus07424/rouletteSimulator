package strategy;

import application.RouletteContext;
import enums.BetType;
import model.Bet;

import java.util.ArrayList;
import java.util.List;

/**
 * ココモ法(1stダズンのみ). http://www.silversandscasino.jp/strategy/cocomo.php
 *
 * @author
 */
public class CocomoStrategy extends BaseStrategy {

	/**
	 * 使用するベットの種類.
	 */
	private static final BetType USE_BET_TYPE = BetType.FIRST_DOZEN;

	/**
	 * 2回前のベット額.
	 */
	private long secondFromLastTotalBetValue;

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public CocomoStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "ココモ法(1stダズンのみ)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		List<Bet> betList = new ArrayList<>();

		// 前回当選した場合
		if (wasLastBetWon(rouletteContext)) {
			// 最小ベット額をベット
			betList.add(new Bet(USE_BET_TYPE, rouletteContext.minimumBet));
		} else {
			// 直近2回のベット額の合計をベット
			long nextBetValue = secondFromLastTotalBetValue + getLastTotalBetValue();
			if (0 < nextBetValue) {
				betList.add(new Bet(USE_BET_TYPE, nextBetValue));
			} else {
				// 最小ベット額をベット
				betList.add(new Bet(USE_BET_TYPE, rouletteContext.minimumBet));
			}
		}

		// 2回前のベット額を更新
		secondFromLastTotalBetValue = getLastTotalBetValue();

		return betList;
	}
}