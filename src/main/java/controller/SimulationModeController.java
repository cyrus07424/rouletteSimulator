package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.RouletteContext;
import cell.SimulationModeStrategyCell;
import enums.Spot;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.WindowEvent;
import model.Bet;
import strategy.BaseStrategy;
import utils.LogHelper;
import utils.StrategyHelper;

/**
 * シミュレーションモード画面コントローラー.
 *
 * @author
 */
public class SimulationModeController extends BaseController {

	/**
	 * ルーレットのコンテキスト.
	 */
	private RouletteContext rouletteContext;

	/**
	 * 現在の試行回数.
	 */
	@FXML
	private Label currentLoopCountLabel;

	/**
	 * 色毎の出現率.
	 */
	@FXML
	private Label redRateLabel;

	@FXML
	private Label greenRateLabel;

	@FXML
	private Label blackRateLabel;

	/**
	 * 戦略一覧リストビュー.
	 */
	@FXML
	private ListView<BaseStrategy> strategyListView;

	/**
	 * メインループを行うサービス.
	 */
	private ScheduledService<Boolean> mainLoopService;

	@Override
	public void setOnCloseRequest(WindowEvent event) {
		// メインループを終了
		if (mainLoopService != null) {
			mainLoopService.cancel();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 初期化完了後に実行
		Platform.runLater(() -> {
			// リストビューを初期化
			strategyListView.setCellFactory(listView -> new SimulationModeStrategyCell(rouletteContext));
			strategyListView.setItems(
					StrategyHelper.createStrategyList(StrategyHelper.getEnableStrategyClassSet(), rouletteContext));

			// メインループを行うサービス
			mainLoopService = new ScheduledService<Boolean>() {

				@Override
				protected Task<Boolean> createTask() {
					return new Task<Boolean>() {

						@Override
						protected Boolean call() {
							// 出目を取得
							Spot nextSpot = Spot.getRandomNextSpot(rouletteContext);

							// 精算処理
							// 全ての戦略に対して実行
							for (BaseStrategy strategy : strategyListView.getItems()) {
								// ベット一覧を取得
								List<Bet> betList = strategy.getNextBetList(rouletteContext);

								// 戦略のパラメータを更新
								strategy.updateStrategyParameter(betList, nextSpot);
							}

							// 試行回数を加算
							rouletteContext.currentLoopCount++;

							// 出目をコンテキストに追加
							rouletteContext.addSpotHistory(nextSpot);

							// 画面描画
							Platform.runLater(() -> {
								// 戦略一覧をソート
								strategyListView.getItems().sort(BaseStrategy.getBalanceComparator());

								// リストビューを最新化
								strategyListView.refresh();

								// 現在の試行回数を表示
								currentLoopCountLabel.setText(String.valueOf(rouletteContext.currentLoopCount));

								// 色毎の出現率を表示
								redRateLabel.setText(String.format("%.2f%%", rouletteContext.getRedRate() * 100));
								greenRateLabel.setText(String.format("%.2f%%", rouletteContext.getGreenRate() * 100));
								blackRateLabel.setText(String.format("%.2f%%", rouletteContext.getBlackRate() * 100));
							});

							// コンソールに情報を表示
							LogHelper.info(
									String.format("試行回数=%d, 出目=%s", rouletteContext.currentLoopCount, nextSpot.name()));

							return true;
						}
					};
				}
			};
			mainLoopService.start();
		});
	}

	/**
	 * ルーレットのコンテキストを設定.
	 *
	 * @param rouletteContext
	 */
	public void setRouletteContext(RouletteContext rouletteContext) {
		this.rouletteContext = rouletteContext;
	}
}