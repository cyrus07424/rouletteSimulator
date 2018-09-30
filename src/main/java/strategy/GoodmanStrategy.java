package strategy;

import java.util.Collections;
import java.util.List;

import application.RouletteContext;
import enums.BetType;
import model.Bet;

/**
 * グッドマン法(赤のみ).<br>
 * http://www.silversandscasino.jp/strategy/goodman.php
 *
 * @author
 */
public class GoodmanStrategy extends BaseStrategy {

	/**
	 * 使用するベットの種類.
	 */
	private static final BetType USE_BET_TYPE = BetType.RED;

	/**
	 * セット内の試行回数.
	 */
	private int setCount;

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public GoodmanStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "グッドマン法(赤のみ)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		// 負けた場合は試行回数をリセット
		if (!wasLastBetWon(rouletteContext)) {
			setCount = 0;
		}

		// 試行回数を加算
		setCount++;

		switch (setCount) {
			case 0:
				return Collections.singletonList(new Bet(USE_BET_TYPE, rouletteContext.minimumBet));
			case 1:
				return Collections.singletonList(new Bet(USE_BET_TYPE, rouletteContext.minimumBet * 2));
			case 2:
				return Collections.singletonList(new Bet(USE_BET_TYPE, rouletteContext.minimumBet * 3));
			case 3:
			default:
				return Collections.singletonList(new Bet(USE_BET_TYPE, rouletteContext.minimumBet * 5));
		}
	}
}