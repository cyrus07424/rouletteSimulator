package strategy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import application.RouletteContext;
import enums.BetType;
import model.Bet;

/**
 * 2in1法(赤のみ).<br>
 * http://www.silversandscasino.jp/strategy/2in1.php
 *
 * @author cyrus
 */
public class TwoInOneStrategy extends BaseStrategy {

	/**
	 * 使用するベットの種類.
	 */
	private static final BetType USE_BET_TYPE = BetType.RED;

	/**
	 * 2in1法用リスト.
	 */
	private LinkedList<Integer> numberList = new LinkedList<>();

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public TwoInOneStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "2in1法(赤のみ)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		// 前回当選した場合
		if (wasLastBetWon(rouletteContext)) {
			// 先頭と最後から要素を1個ずつ削除
			if (!numberList.isEmpty()) {
				numberList.removeFirst();
			}
			if (!numberList.isEmpty()) {
				numberList.removeLast();
			}
		} else {
			// 前回負けの場合
			if (hasLastBet()) {
				// 要素を追加
				if (numberList.size() <= 1) {
					numberList.addLast(1);
				} else {
					int sum = numberList.getFirst() + numberList.getLast();
					numberList.addLast(sum);
				}
			}
		}

		int multiplier;
		if (numberList.size() <= 1) {
			multiplier = 1;
		} else {
			// 両端の数字の合計値を取得
			multiplier = numberList.getFirst() + numberList.getLast();
		}

		// ベット
		return Collections.singletonList(new Bet(USE_BET_TYPE, rouletteContext.minimumBet * multiplier));
	}
}