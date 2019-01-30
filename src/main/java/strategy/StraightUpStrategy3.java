package strategy;

import java.util.ArrayList;
import java.util.List;

import application.RouletteContext;
import enums.Spot;
import model.Bet;
import utils.BetHelper;

/**
 * ストレート複数賭け2.<br>
 * http://casino55.blog85.fc2.com/blog-entry-15.html
 *
 * @author cyrus
 */
public class StraightUpStrategy3 extends BaseStrategy {

	/**
	 * 連続して当選した回数.
	 */
	private int count = 0;

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public StraightUpStrategy3(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "ストレート複数賭け2";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		List<Bet> betList = new ArrayList<>();

		// 前回当選の場合
		if (wasLastBetWon(rouletteContext)) {
			count++;
		} else {
			count = 0;
		}

		// 全てので目に対して実行
		LOOP1: for (Spot spot : Spot.getAvailableList(rouletteContext.rouletteType)) {
			// 直近に出現していない出目にストレートでベットする
			for (int i = 0; i < Math.min(rouletteContext.spotHistoryList.size(), count); i++) {
				if (spot == rouletteContext.spotHistoryList.get(rouletteContext.spotHistoryList.size() - (i + 1))) {
					continue LOOP1;
				}
			}
			betList.add(new Bet(BetHelper.getStraightUpBetType(spot), rouletteContext.minimumBet));
		}

		return betList;
	}
}