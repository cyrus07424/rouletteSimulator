package strategy;

import java.util.ArrayList;
import java.util.List;

import application.RouletteContext;
import model.Bet;

/**
 * マンシュリアン法.<br>
 * https://casino-kingdom.com/mansurians/
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