package strategy;

import java.util.Collections;
import java.util.List;

import application.RouletteContext;
import enums.BetType;
import model.Bet;

/**
 * マーチンゲール法(赤のみ).
 *
 * @author cyrus
 */
public class MartingaleStrategy extends BaseStrategy {

	/**
	 * 使用するベットの種類.
	 */
	private static final BetType USE_BET_TYPE = BetType.RED;

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public MartingaleStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "マーチンゲール法(赤のみ)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		// 前回当選した場合
		if (wasLastBetWon(rouletteContext)) {
			// 最小ベット額をベット
			return Collections.singletonList(new Bet(USE_BET_TYPE, rouletteContext.minimumBet));
		} else {
			// 前回のベット額の倍額をベット
			return Collections.singletonList(new Bet(USE_BET_TYPE, (getLastTotalBetValue() * 2)));
		}
	}
}