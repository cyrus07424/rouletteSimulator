package cell;

import java.io.IOException;
import java.text.NumberFormat;

import application.RouletteContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import strategy.BaseStrategy;

/**
 * シミュレーションモード画面の戦略のセル.
 *
 * @author cyrus
 */
public class SimulationModeStrategyCell extends ListCell<BaseStrategy> {

	/**
	 * 数値のフォーマット.
	 */
	private static final NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance();

	/**
	 * ルーレットのコンテキスト.
	 */
	private RouletteContext rouletteContext;

	/**
	 * コンテナ.
	 */
	@FXML
	private Node cellContainer;

	/**
	 * 戦略名.
	 */
	@FXML
	private Label strategyNameLabel;

	/**
	 * 現在の所持金.
	 */
	@FXML
	private Label currentBalanceLabel;

	/**
	 * 差額.
	 */
	@FXML
	private Label differenceWithInitialBalanceLabel;

	/**
	 * 所持金の最大値.
	 */
	@FXML
	private Label maximumBalanceLabel;

	/**
	 * 所持金の最小値
	 */
	@FXML
	private Label minimumBalanceLabel;

	/**
	 * 前回のベット額.
	 */
	@FXML
	private Label lastTotalBetValueLabel;

	/**
	 * ベット額の最大値.
	 */
	@FXML
	private Label maximumTotalBetValueLabel;

	/**
	 * 総ベット額.
	 */
	@FXML
	private Label wholeTotalBetValueLabel;

	/**
	 * 平均ベット額.
	 */
	@FXML
	private Label averageTotalBetValueLabel;

	/**
	 * 総配当額.
	 */
	@FXML
	private Label wholeTotalPayoutLabel;

	/**
	 * 平均配当額.
	 */
	@FXML
	private Label averageTotalPayoutLabel;

	/**
	 * 勝率.
	 */
	@FXML
	private Label winningAverageLabel;

	/**
	 * 所持金履歴チャート.
	 */
	@FXML
	private LineChart<Number, Number> balanceHistoryChart;

	/**
	 * チャートのX軸.
	 */
	@FXML
	private NumberAxis chartXAxis;

	/**
	 * チャートのY軸.
	 */
	@FXML
	private NumberAxis chartYAxis;

	/**
	 * コンストラクタ.
	 *
	 * @param rouletteContext
	 */
	public SimulationModeStrategyCell(RouletteContext rouletteContext) {
		this.rouletteContext = rouletteContext;

		// レイアウトを読み込み
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/cell/SimulationModeStrategyCell.fxml"));
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void updateItem(BaseStrategy strategy, boolean empty) {
		super.updateItem(strategy, empty);
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			// ラベルを設定
			strategyNameLabel.setText(strategy.getStrategyName());
			currentBalanceLabel.setText(NUMBER_FORMAT.format(strategy.currentBalance));
			long differenceWithInitialBalance = (strategy.currentBalance - rouletteContext.initialBalance);
			if (0 <= differenceWithInitialBalance) {
				differenceWithInitialBalanceLabel.setText("+" + NUMBER_FORMAT.format(differenceWithInitialBalance));
				differenceWithInitialBalanceLabel.setTextFill(Color.BLUE);
			} else {
				differenceWithInitialBalanceLabel.setText(NUMBER_FORMAT.format(differenceWithInitialBalance));
				differenceWithInitialBalanceLabel.setTextFill(Color.RED);
			}
			maximumBalanceLabel.setText(NUMBER_FORMAT.format(strategy.maximumBalance));
			minimumBalanceLabel.setText(NUMBER_FORMAT.format(strategy.minimumBalance));
			if (0 <= strategy.minimumBalance) {
				minimumBalanceLabel.setTextFill(Color.BLACK);
			} else {
				minimumBalanceLabel.setTextFill(Color.RED);
			}
			lastTotalBetValueLabel.setText(NUMBER_FORMAT.format(strategy.getLastTotalBetValue()));
			maximumTotalBetValueLabel.setText(NUMBER_FORMAT.format(strategy.maximumTotalBetValue));
			wholeTotalBetValueLabel.setText(NUMBER_FORMAT.format(strategy.wholeTotalBetValue));
			averageTotalBetValueLabel.setText(NUMBER_FORMAT.format(strategy.getAverageTotalBetValue()));
			wholeTotalPayoutLabel.setText(NUMBER_FORMAT.format(strategy.wholeTotalPayoutValue));
			averageTotalPayoutLabel.setText(NUMBER_FORMAT.format(strategy.getAverageTotalPayoutValue()));
			winningAverageLabel.setText(String.format("%.2f%%", strategy.getWinningAverage() * 100));

			try {
				// 所持金履歴チャートを更新
				updateBalanceHistoryChart(strategy);
			} catch (Exception e) {
				// NOP
			}

			// 背景色を設定
			if (strategy.isLive()) {
				cellContainer.setStyle("");
			} else {
				cellContainer.setStyle("-fx-background-color: lightgray;");
			}
			setGraphic(cellContainer);
		}
	}

	/**
	 * 所持金履歴チャートを更新.
	 *
	 * @param strategy 戦略
	 */
	private void updateBalanceHistoryChart(BaseStrategy strategy) {
		// チャートデータをクリア
		balanceHistoryChart.getData().clear();

		// 履歴データが存在しない場合は何もしない
		if (strategy.balanceHistoryList.isEmpty()) {
			return;
		}

		// データシリーズを作成
		XYChart.Series<Number, Number> series = new XYChart.Series<>();

		// 履歴データをチャートに追加
		int index = 0;
		for (Long balance : strategy.balanceHistoryList) {
			series.getData().add(new XYChart.Data<>(index, balance));
			index++;
		}

		// シリーズをチャートに追加
		balanceHistoryChart.getData().add(series);

		// Y軸の範囲を調整（最小値と最大値を設定）
		if (!strategy.balanceHistoryList.isEmpty()) {
			long minBalance = strategy.balanceHistoryList.stream().mapToLong(Long::longValue).min().orElse(0);
			long maxBalance = strategy.balanceHistoryList.stream().mapToLong(Long::longValue).max().orElse(0);

			// 少しマージンを持たせる
			long margin = Math.max((maxBalance - minBalance) / 10, 1000);
			chartYAxis.setLowerBound(minBalance - margin);
			chartYAxis.setUpperBound(maxBalance + margin);
		}

		// X軸の範囲を設定
		chartXAxis.setLowerBound(0);
		chartXAxis.setUpperBound(Math.max(strategy.balanceHistoryList.size() - 1, 1));
	}
}