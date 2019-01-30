package strategy;

import java.util.ArrayList;
import java.util.List;

import application.RouletteContext;
import constants.Configurations;
import enums.BetType;
import model.Bet;

/**
 * ランダム.
 *
 * @author cyrus
 */
public class RandomStrategy extends BaseStrategy {

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public RandomStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "ランダム";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		List<Bet> betList = new ArrayList<>();
		List<BetType> betTypeList = BetType.getAvailableList(rouletteContext.rouletteType);

		// ランダムに作成
		for (int i = 0; i < Configurations.RANDOM.nextInt(100); i++) {
			BetType betType = betTypeList.get(Configurations.RANDOM.nextInt(betTypeList.size()));
			int multiplier = Configurations.RANDOM.nextInt(10) + 1;
			betList.add(new Bet(betType, rouletteContext.minimumBet * multiplier));
		}

		return betList;
	}
}