package strategy;

import application.RouletteContext;
import enums.BetType;
import model.Bet;

import java.util.Collections;
import java.util.List;

/**
 * 10%法(赤のみ). http://www.silversandscasino.jp/strategy/10percent.php
 *
 * @author
 */
public class TenPercentStrategy extends BaseStrategy {

	/**
	 * 使用するベットの種類.
	 */
	private static final BetType USE_BET_TYPE = BetType.RED;

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public TenPercentStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "10%法(赤のみ)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		// 所持金の10%をベット
		long value = currentBalance / 10;
		if (0 < value) {
			return Collections.singletonList(new Bet(USE_BET_TYPE, currentBalance / 10));
		} else {
			// 1をベット
			return Collections.singletonList(new Bet(USE_BET_TYPE, 1));
		}
	}
}