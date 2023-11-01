package strategy;

import java.util.Collections;
import java.util.List;

import application.RouletteContext;
import enums.BetType;
import model.Bet;

/**
 * イーストコーストプログレッション(赤のみ).<br>
 * http://www.silversandscasino.jp/strategy/east_coast.php
 *
 * @author cyrus
 */
public class EastCoastProgressionStrategy extends BaseStrategy {

	/**
	 * 使用するベットの種類.
	 */
	private static final BetType USE_BET_TYPE = BetType.RED;

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public EastCoastProgressionStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "イーストコーストプログレッション(赤のみ)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		// 前回当選した場合
		if (wasLastBetWon(rouletteContext)) {
			// 差額の半分をベット
			long betValue = (currentBalance - rouletteContext.initialBalance) / 2;
			if (0 < betValue) {
				return Collections.singletonList(new Bet(USE_BET_TYPE, betValue));
			} else {
				// TODO
				return Collections.singletonList(new Bet(USE_BET_TYPE, rouletteContext.minimumBet));
			}
		} else {
			// 最小値の2倍
			return Collections.singletonList(new Bet(USE_BET_TYPE, rouletteContext.minimumBet * 2));
		}
	}
}