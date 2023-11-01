package strategy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import application.RouletteContext;
import enums.BetType;
import model.Bet;

/**
 * 10ユニット法(赤のみ).<br>
 * http://www.xn--9ckk7he3f9633bhel9uq.jp/10_units-law.html
 *
 * @author cyrus
 */
public class TenUnitStrategy extends BaseStrategy {

	/**
	 * 使用するベットの種類.
	 */
	private static final BetType USE_BET_TYPE = BetType.RED;

	/**
	 * 10ユニット法用リスト.
	 */
	private LinkedList<Integer> numberList = new LinkedList<>();

	/**
	 * セット開始時の所持金.
	 */
	private long setStartBalance;

	/**
	 * リミット.
	 */
	private long limitValue;

	/**
	 * 前回のベットで左端の数字のみを使用したかどうか.
	 */
	private boolean useLastBetLeftNumber;

	/**
	 * リミットの残高全てを使用したかどうか.
	 */
	private boolean useLastBetAllLimit;

	/**
	 * 前回のベットで使用したユニット.
	 */
	private int lastBetMultiplier;

	/**
	 * 前回のリミット残高のユニット.
	 */
	private int lastRemainingLimitMultiplier;

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public TenUnitStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);

		// セットを初期化
		initializeSet(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "10ユニット法(赤のみ)";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		// リミット分負けた場合はセットを初期化
		if (0 <= getRemainingLimit()) {
			initializeSet(rouletteContext);
		}

		// 前回当選した場合
		if (wasLastBetWon(rouletteContext)) {
			if (!useLastBetLeftNumber && !useLastBetAllLimit) {
				// 先頭と最後から要素を削除
				if (!numberList.isEmpty()) {
					numberList.removeFirst();
				}
				if (!numberList.isEmpty()) {
					numberList.removeLast();
				}
			} else if (useLastBetAllLimit) {
				// 先頭の要素を入れ替え
				if (!numberList.isEmpty()) {
					numberList.removeFirst();
				}
				numberList.addFirst(lastBetMultiplier);
			}
		} else {
			// 前回負けの場合
			if (hasLastBet()) {
				if (useLastBetAllLimit) {
					// 要素を全て削除(セットを初期化)
					numberList.clear();
				} else if (useLastBetLeftNumber) {
					// 先頭の要素を入れ替え
					if (!numberList.isEmpty()) {
						numberList.removeFirst();
					}
					numberList.addFirst(lastRemainingLimitMultiplier);
				} else {
					// 要素を追加
					numberList.addLast(lastBetMultiplier);
				}
			}
		}
		useLastBetLeftNumber = false;
		useLastBetAllLimit = false;

		// 要素が無くなった場合
		if (numberList.isEmpty()) {
			// 初期値を追加
			numberList.clear();
			for (int i = 0; i < 10; i++) {
				numberList.addLast(1);
			}
			// セットを初期化
			initializeSet(rouletteContext);
		}

		// 両端の数字の合計値を取得
		int multiplier = numberList.getFirst() + numberList.getLast();

		// リミット残高のユニット
		lastRemainingLimitMultiplier = (int) (getRemainingLimit() / rouletteContext.minimumBet);

		// ベット額がリミット残額より大きい場合
		if (getRemainingLimit() < rouletteContext.minimumBet * multiplier) {
			// 左端の数字のユニット額よりもリミット残額が大きい場合
			if (rouletteContext.minimumBet * numberList.getFirst() < getRemainingLimit()) {
				// 左端の数字を使用
				multiplier = numberList.getFirst();
				useLastBetLeftNumber = true;
			} else {
				// リミット残額を使用
				multiplier = lastRemainingLimitMultiplier;
				useLastBetAllLimit = true;
			}
		}

		// ユニットの最小値を制限
		if (multiplier <= 0) {
			multiplier = 1;
		}

		// ベット
		lastBetMultiplier = multiplier;
		return Collections.singletonList(new Bet(USE_BET_TYPE, rouletteContext.minimumBet * multiplier));
	}

	/**
	 * セットを初期化.
	 *
	 * @param rouletteContext
	 */
	private void initializeSet(RouletteContext rouletteContext) {
		if (0 < currentBalance) {
			setStartBalance = currentBalance;
			// TODO
			limitValue = currentBalance / 10;
		} else {
			setStartBalance = currentBalance;
			limitValue = rouletteContext.initialBalance / 10;
		}
	}

	/**
	 * リミット残額を取得.
	 *
	 * @return
	 */
	private long getRemainingLimit() {
		return limitValue - (setStartBalance - currentBalance);
	}
}