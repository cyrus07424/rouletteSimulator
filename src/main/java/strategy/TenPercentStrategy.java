package strategy;

import java.util.Collections;
import java.util.List;

import application.RouletteContext;
import enums.BetType;
import model.Bet;

/**
 * 10%法(赤のみ).<br>
 * http://www.silversandscasino.jp/strategy/10percent.php
 *
 * @author cyrus
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
		// 所持金の10%を計算
		long value = Math.min(currentBalance / 10, rouletteContext.maximumBet);

		// 最小ベット額より大きい場合
		if (rouletteContext.minimumBet <= value) {
			return Collections.singletonList(new Bet(USE_BET_TYPE, value));
		} else {
			// FIXME 最小ベット額を使用
			return Collections.singletonList(new Bet(USE_BET_TYPE, rouletteContext.minimumBet));
		}
	}
}