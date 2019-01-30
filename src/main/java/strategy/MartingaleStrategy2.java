package strategy;

import java.util.Collections;
import java.util.List;

import application.RouletteContext;
import enums.BetType;
import model.Bet;

/**
 * マーチンゲール法(赤・黒のうち確率の高い方).
 *
 * @author cyrus
 */
public class MartingaleStrategy2 extends BaseStrategy {

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public MartingaleStrategy2(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "マーチンゲール法(赤・黒のうち確率の高い方)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		// 使用するベットの種類
		BetType useBetType;
		if (rouletteContext.getBlackRate() <= rouletteContext.getRedRate()) {
			useBetType = BetType.RED;
		} else {
			useBetType = BetType.BLACK;
		}

		// 前回当選した場合
		if (wasLastBetWon(rouletteContext)) {
			// 最小ベット額をベット
			return Collections.singletonList(new Bet(useBetType, rouletteContext.minimumBet));
		} else {
			// 前回のベット額の倍額をベット
			return Collections.singletonList(new Bet(useBetType, (getLastTotalBetValue() * 2)));
		}
	}
}