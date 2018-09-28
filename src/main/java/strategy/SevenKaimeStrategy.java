package strategy;

import application.RouletteContext;
import enums.BetType;
import model.Bet;

import java.util.ArrayList;
import java.util.List;

/**
 * 7回目の法則(赤のみ). http://www.xn--9ckk7he3f9633bhel9uq.jp/seventh_time-law.html
 *
 * @author
 */
public class SevenKaimeStrategy extends BaseStrategy {

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public SevenKaimeStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "7回目の法則(赤のみ)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		List<Bet> betList = new ArrayList<>();
		if (7 <= rouletteContext.spotHistoryList.size()) {

			// 7回連続で黒であるかを判定
			boolean notMatched = false;
			for (int i = 0; i < 7; i++) {
				if (!rouletteContext.spotHistoryList.get(rouletteContext.spotHistoryList.size() - (1 + i)).isBlack()) {
					notMatched = true;
				}
			}

			// 7回連続で黒の場合
			if (!notMatched) {
				betList.add(new Bet(BetType.RED, rouletteContext.minimumBet));
			}
		}
		return betList;
	}
}
