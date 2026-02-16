package strategy;

import java.util.Collections;
import java.util.List;

import application.RouletteContext;
import enums.BetType;
import model.Bet;

/**
 * わらしべ長者法(赤のみ).<br>
 * 勝った時にベット額を1単位ずつ増やし、負けた時に最小ベット額にリセットする戦略
 *
 * @author cyrus
 */
public class WarashibeChojuStrategy extends BaseStrategy {

	/**
	 * 使用するベットの種類.
	 */
	private static final BetType USE_BET_TYPE = BetType.RED;

	/**
	 * 現在のベット倍率.
	 */
	private int currentMultiplier = 1;

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public WarashibeChojuStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "わらしべ長者法(赤のみ)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		// 前回のベットがある場合、結果に応じて倍率を調整
		if (hasLastBet()) {
			if (wasLastBetWon(rouletteContext)) {
				// 勝った場合、倍率を1増やす
				currentMultiplier++;
			} else {
				// 負けた場合、倍率を1にリセット
				currentMultiplier = 1;
			}
		}

		// ベット額を計算（最小ベット額 × 現在の倍率）
		long betValue = rouletteContext.minimumBet * currentMultiplier;

		// 最大ベット額を超えないように制限
		if (betValue > rouletteContext.maximumBet) {
			betValue = rouletteContext.maximumBet;
		}

		// 所持金を超えないように制限
		if (betValue > currentBalance) {
			betValue = Math.max(rouletteContext.minimumBet, currentBalance);
		}

		return Collections.singletonList(new Bet(USE_BET_TYPE, betValue));
	}
}