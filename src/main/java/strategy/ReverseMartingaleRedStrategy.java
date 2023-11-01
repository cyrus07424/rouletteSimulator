package strategy;

import java.util.Collections;
import java.util.List;

import application.RouletteContext;
import enums.BetType;
import model.Bet;

/**
 * 逆マーチンゲール法(赤のみ).<br>
 * http://www.silversandscasino.jp/strategy/paley.php
 *
 * @author cyrus
 */
public class ReverseMartingaleRedStrategy extends BaseStrategy {

	/**
	 * 使用するベットの種類.
	 */
	private static final BetType USE_BET_TYPE = BetType.RED;

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public ReverseMartingaleRedStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "逆マーチンゲール法(赤のみ)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		// ベットを中断する閾値
		long thresholdValue = (long) (rouletteContext.minimumBet * Math.pow(2, 5));

		// 前回当選した場合
		if (wasLastBetWon(rouletteContext) && getLastTotalBetValue() < thresholdValue) {
			// 前回のベット額の倍額をベット
			return Collections.singletonList(new Bet(USE_BET_TYPE, (getLastTotalBetValue() * 2)));
		} else {
			// 最小ベット額をベット
			return Collections.singletonList(new Bet(USE_BET_TYPE, rouletteContext.minimumBet));
		}
	}
}
