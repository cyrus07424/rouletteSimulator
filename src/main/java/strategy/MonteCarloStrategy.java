package strategy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import application.RouletteContext;
import enums.BetType;
import model.Bet;

/**
 * モンテカルロ法(1stダズンのみ).<br>
 * http://www.silversandscasino.jp/strategy/montecarlo.php
 *
 * @author cyrus
 */
public class MonteCarloStrategy extends BaseStrategy {

	/**
	 * 使用するベットの種類.
	 */
	private static final BetType USE_BET_TYPE = BetType.FIRST_DOZEN;

	/**
	 * モンテカルロ法用リスト.
	 */
	private LinkedList<Integer> numberList = new LinkedList<>();

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public MonteCarloStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "モンテカルロ法(1stダズンのみ)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		// 前回当選した場合
		if (wasLastBetWon(rouletteContext)) {
			// 先頭と最後から要素を2個ずつ削除
			for (int i = 0; i < 2; i++) {
				if (!numberList.isEmpty()) {
					numberList.removeFirst();
				}
			}
			for (int i = 0; i < 2; i++) {
				if (!numberList.isEmpty()) {
					numberList.removeLast();
				}
			}
		} else {
			// 前回負けの場合
			if (hasLastBet()) {
				// 要素を追加
				int sum = numberList.getFirst() + numberList.getLast();
				numberList.addLast(sum);
			}
		}

		// 要素数が1以下の場合
		if (numberList.size() <= 1) {
			// 初期値を追加
			numberList.clear();
			numberList.addLast(1);
			numberList.addLast(2);
			numberList.addLast(3);
		}

		// 両端の数字の合計値を取得
		int multiplier = numberList.getFirst() + numberList.getLast();

		// ベット
		return Collections.singletonList(new Bet(USE_BET_TYPE, rouletteContext.minimumBet * multiplier));
	}
}