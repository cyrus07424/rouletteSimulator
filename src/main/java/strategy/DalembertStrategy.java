package strategy;

import java.util.Collections;
import java.util.List;

import application.RouletteContext;
import enums.BetType;
import model.Bet;

/**
 * ダランベール法(赤のみ).<br>
 * http://www.silversandscasino.jp/strategy/daremberg.php
 *
 * @author cyrus
 */
public class DalembertStrategy extends BaseStrategy {

	/**
	 * 使用するベットの種類.
	 */
	private static final BetType USE_BET_TYPE = BetType.RED;

	/**
	 * 倍率.
	 */
	private int multiplier = 1;

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public DalembertStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "ダランベール法(赤のみ)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		if (hasLastBet()) {
			// 前回当選した場合
			if (wasLastBetWon(rouletteContext)) {
				// 倍率を加算
				multiplier++;
			} else {
				// 倍率を減算
				multiplier--;
				if (multiplier <= 0) {
					multiplier = 1;
				}
			}
		}
		return Collections.singletonList(new Bet(USE_BET_TYPE, rouletteContext.minimumBet * multiplier));
	}
}