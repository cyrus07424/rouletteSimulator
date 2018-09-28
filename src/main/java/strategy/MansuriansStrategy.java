package strategy;

import application.RouletteContext;
import model.Bet;

import java.util.ArrayList;
import java.util.List;

/**
 * マンシュリアン法. https://casino-kingdom.com/mansurians/
 *
 * @author
 */
public class MansuriansStrategy extends BaseStrategy {

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public MansuriansStrategy(RouletteContext rouletteContext) {
		super(rouletteContext);
	}

	@Override
	public String getStrategyName() {
		return "マンシュリアン法";
	}

	@Override
	public List<Bet> getNextBetListImpl(RouletteContext rouletteContext) {
		List<Bet> betList = new ArrayList<>();

		// TODO

		return betList;
	}
}