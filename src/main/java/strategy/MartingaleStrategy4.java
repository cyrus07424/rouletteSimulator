package strategy;

import java.util.ArrayList;
import java.util.List;

import application.RouletteContext;
import enums.BetType;
import model.Bet;

/**
 * マーチンゲール法(1st・2ndダズンのみ).
 *
 * @author cyrus
 */
public class MartingaleStrategy4 extends BaseStrategy {

	/**
	 * 使用するベットの種類.
	 */
	private static final BetType USE_BET_TYPE1 = BetType.FIRST_DOZEN;

	private static final BetType USE_BET_TYPE2 = BetType.SECOND_DOZEN;

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public MartingaleStrategy4(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "マーチンゲール法(1st・2ndダズンのみ)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		List<Bet> betList = new ArrayList<>();

		// 前回当選した場合
		if (wasLastBetWon(rouletteContext)) {
			// 最小ベット額をベット
			betList.add(new Bet(USE_BET_TYPE1, rouletteContext.minimumBet));
			betList.add(new Bet(USE_BET_TYPE2, rouletteContext.minimumBet));
		} else {
			if (hasLastBet()) {
				// 前回のベット額の3倍をベット
				// FIXME 最大ベット額を考慮
				long betValue = lastBetList.get(0).value * 3;
				betList.add(new Bet(USE_BET_TYPE1, betValue));
				betList.add(new Bet(USE_BET_TYPE2, betValue));
			} else {
				// 最小ベット額をベット
				betList.add(new Bet(USE_BET_TYPE1, rouletteContext.minimumBet));
				betList.add(new Bet(USE_BET_TYPE2, rouletteContext.minimumBet));
			}
		}
		return betList;
	}
}