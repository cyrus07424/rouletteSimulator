package strategy;

import application.RouletteContext;
import constants.Configurations;
import enums.BetType;
import model.Bet;
import utils.BetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * ランダム2.
 *
 * @author
 */
public class RandomStrategy2 extends BaseStrategy {

	/**
	 * ベット一覧のサイズ.
	 */
	private static int BET_LIST_SIZE = 10;

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public RandomStrategy2(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "ランダム2";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		List<Bet> betList = new ArrayList<>();
		List<BetType> betTypeList = BetType.getAvailableList(rouletteContext.rouletteType);

		// 前回当選の場合
		if (wasLastBetWon(rouletteContext)) {
			// 前回当選のベットをコピー
			for (Bet bet : lastBetList) {
				if (BetHelper.isWin(bet, rouletteContext.getLastSpot())) {
					betList.add(bet);
				}
			}
		}

		// ランダムに作成
		while (betList.size() < BET_LIST_SIZE) {
			BetType betType = betTypeList.get(Configurations.RANDOM.nextInt(betTypeList.size()));
			int multiplier = Configurations.RANDOM.nextInt(10) + 1;
			betList.add(new Bet(betType, rouletteContext.minimumBet * multiplier));
		}

		return betList;
	}
}