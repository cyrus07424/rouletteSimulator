package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.RouletteContext;
import cell.SimulationModeStrategyCell;
import enums.Spot;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.stage.WindowEvent;
import model.Bet;
import strategy.BaseStrategy;
import utils.LogHelper;
import utils.StrategyHelper;
import view.SpotHeatmapView;

/**
 * シミュレーションモード画面コントローラー.
 *
 * @author cyrus
 */
public class SimulationModeController extends BaseController {

	/**
	 * ルーレットのコンテキスト.
	 */
	private RouletteContext rouletteContext;

	/**
	 * 戦略一覧.
	 */
	private ObservableList<BaseStrategy> strategyList;

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
	 * 出目履歴リストビュー.
	 */
	@FXML
	private ListView<Spot> spotHistoryListView;

	/**
	 * ヒートマップ用スクロールペイン.
	 */
	@FXML
	private ScrollPane heatmapScrollPane;

	/**
	 * 出目ヒートマップビュー.
	 */
	private SpotHeatmapView spotHeatmapView;

	/**
	 * メインループを行うサービス.
	 */
	private ScheduledService<Boolean> mainLoopService;

	/**
	 * 制御ボタン.
	 */
	@FXML
	private Button pauseButton;

	@FXML
	private Button resumeButton;

	@FXML
	private Button stopButton;

	/**
	 * シミュレーション速度スライダー（シミュレーションモード内）.
	 */
	@FXML
	private Slider simulationSpeedSliderInMode;

	/**
	 * シミュレーション速度ラベル（シミュレーションモード内）.
	 */
	@FXML
	private Label simulationSpeedLabelInMode;

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
			// 戦略一覧を読み込み
			strategyList = StrategyHelper.createStrategyList(StrategyHelper.getEnableStrategyClassSet(),
					rouletteContext);

			// 制御ボタンの初期状態を設定
			resumeButton.setDisable(true);

			// シミュレーション速度スライダーを初期化
			simulationSpeedSliderInMode.setValue(rouletteContext.simulationSpeed);
			simulationSpeedLabelInMode.setText(rouletteContext.simulationSpeed + "ms");

			// シミュレーション速度スライダーの変更監視
			simulationSpeedSliderInMode.valueProperty().addListener((observable, oldValue, newValue) -> {
				int speed = newValue.intValue();
				rouletteContext.simulationSpeed = speed;
				simulationSpeedLabelInMode.setText(speed + "ms");
			});

			// ヒートマップビューを初期化
			spotHeatmapView = new SpotHeatmapView(rouletteContext);
			heatmapScrollPane.setContent(spotHeatmapView);

			// 戦略一覧リストビューを初期化
			strategyListView.setCellFactory(listView -> new SimulationModeStrategyCell(rouletteContext));
			strategyListView.setItems(strategyList);

			// 出目履歴のObservableList
			ObservableList<Spot> spotHistoryObservableList = FXCollections.observableArrayList();

			// 出目履歴リストビューを初期化
			spotHistoryListView.setCellFactory(listView -> new ListCell<Spot>() {
				@Override
				protected void updateItem(Spot spot, boolean empty) {
					super.updateItem(spot, empty);
					if (empty || spot == null) {
						setText(null);
						setStyle("");
					} else {
						setText(String.valueOf(spot.getNumber()));
						// 色に応じて背景色を設定
						if (spot.isRed()) {
							setStyle("-fx-background-color: #ffcccc;");
						} else if (spot.isBlack()) {
							setStyle("-fx-background-color: #cccccc;");
						} else if (spot.isGreen()) {
							setStyle("-fx-background-color: #ccffcc;");
						}
					}
				}
			});
			spotHistoryListView.setItems(spotHistoryObservableList);

			// メインループを行うサービス
			mainLoopService = new ScheduledService<Boolean>() {

				@Override
				protected Task<Boolean> createTask() {
					return new Task<Boolean>() {

						@Override
						protected Boolean call() throws Exception {
							// スピード設定に応じたウェイト
							if (rouletteContext.simulationSpeed > 0) {
								Thread.sleep(rouletteContext.simulationSpeed);
							}

							// 出目を取得
							Spot nextSpot = Spot.getRandomNextSpot(rouletteContext);

							// 精算処理
							// 全ての戦略に対して実行
							for (BaseStrategy strategy : strategyList) {
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

								// 戦略一覧リストビューを最新化
								strategyListView.refresh();

								// 出目履歴リストビューを最新化
								spotHistoryObservableList.setAll(rouletteContext.spotHistoryList);
								// 最新の出目が見えるようにスクロール
								if (!spotHistoryObservableList.isEmpty()) {
									spotHistoryListView.scrollTo(spotHistoryObservableList.size() - 1);
								}

								// 現在の試行回数を表示
								currentLoopCountLabel.setText(String.valueOf(rouletteContext.currentLoopCount));

								// 色毎の出現率を表示
								redRateLabel.setText(String.format("%.2f%%", rouletteContext.getRedRate() * 100));
								greenRateLabel.setText(String.format("%.2f%%", rouletteContext.getGreenRate() * 100));
								blackRateLabel.setText(String.format("%.2f%%", rouletteContext.getBlackRate() * 100));

								// ヒートマップを更新
								if (spotHeatmapView != null) {
									spotHeatmapView.updateHeatmap();
								}
							});

							// コンソールに情報を表示
							LogHelper.info(
									String.format("試行回数=%d, 出目=%s", rouletteContext.currentLoopCount, nextSpot.name()));

							// 全ての戦略が無効な場合
							if (strategyList.stream().allMatch(strategy -> !strategy.isLive())) {
								System.out.println("全ての戦略が無効です");
								// TODO メインループを一時停止
							}

							return true;
						}
					};
				}
			};

			// シミュレーションを自動開始
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

	/**
	 * 出目ヒートマップビューを取得.
	 *
	 * @return 出目ヒートマップビュー
	 */
	public SpotHeatmapView getSpotHeatmapView() {
		return spotHeatmapView;
	}

	/**
	 * 一時停止ボタンクリック時の処理.
	 */
	@FXML
	private void onPauseButtonClick() {
		if (mainLoopService != null && mainLoopService.isRunning()) {
			mainLoopService.cancel();
			pauseButton.setDisable(true);
			resumeButton.setDisable(false);
			LogHelper.info("シミュレーションを一時停止しました");
		}
	}

	/**
	 * 再開ボタンクリック時の処理.
	 */
	@FXML
	private void onResumeButtonClick() {
		if (mainLoopService != null) {
			// サービスを再起動
			mainLoopService.restart();
			pauseButton.setDisable(false);
			resumeButton.setDisable(true);
			LogHelper.info("シミュレーションを再開しました");
		}
	}

	/**
	 * 終了ボタンクリック時の処理.
	 */
	@FXML
	private void onStopButtonClick() {
		// メインループを終了
		if (mainLoopService != null) {
			mainLoopService.cancel();
		}
		// ステージを終了
		getThisStage().close();
		LogHelper.info("シミュレーションを終了しました");
	}
}