package strategy;

import application.RouletteContext;
import enums.BetType;
import model.Bet;

import java.util.Collections;
import java.util.List;

/**
 * 31システム(赤のみ). http://www.silversandscasino.jp/strategy/31system.php
 *
 * @author
 */
public class ThirtyOneSystemStrategy extends BaseStrategy {

	/**
	 * 使用するベットの種類.
	 */
	private static final BetType USE_BET_TYPE = BetType.RED;

	/**
	 * 2回前のベットで当選したかどうか.
	 */
	private boolean wonSecondFromLastBet;

	/**
	 * セット内の試行回数.
	 */
	private int setCount;

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public ThirtyOneSystemStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "31システム(赤のみ)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		// 2連勝した場合、または8回目の試行で負けた場合は試行回数をリセット
		boolean wasLastBetWon = wasLastBetWon(rouletteContext);
		if ((wonSecondFromLastBet && wasLastBetWon) || ((setCount - 1) % 9 == 7 && !wasLastBetWon)) {
			setCount = 0;
		}
		wonSecondFromLastBet = wasLastBetWon;

		// 試行回数を加算
		setCount++;

		switch ((setCount - 1) % 9) {
			case 0:
			case 1:
			case 2:
				return Collections.singletonList(new Bet(USE_BET_TYPE, rouletteContext.minimumBet));
			case 3:
			case 4:
				return Collections.singletonList(new Bet(USE_BET_TYPE, rouletteContext.minimumBet * 2));
			case 5:
			case 6:
				return Collections.singletonList(new Bet(USE_BET_TYPE, rouletteContext.minimumBet * 4));
			case 7:
			case 8:
				return Collections.singletonList(new Bet(USE_BET_TYPE, rouletteContext.minimumBet * 8));
			default:
				throw new IllegalArgumentException();
		}
	}
}