package controller;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import application.RouletteContext;
import cell.SelectStrategyListStrategyCell;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.stage.WindowEvent;
import strategy.BaseStrategy;
import utils.StrategyHelper;

/**
 * 戦略選択画面コントローラー.
 *
 * @author cyrus
 */
public class SelectStrategyListController extends BaseController {

	/**
	 * ルーレットのコンテキスト.
	 */
	private RouletteContext rouletteContext;

	/**
	 * 戦略一覧リストビュー.
	 */
	@FXML
	private ListView<BaseStrategy> strategyListView;

	/**
	 * 全て選択チェックボックス.
	 */
	@FXML
	private CheckBox selectAllCheckBox;

	/**
	 * OKボタン.
	 */
	@FXML
	private Button okButton;

	/**
	 * キャンセルボタン.
	 */
	@FXML
	private Button cancelButton;

	/**
	 * チェックボックスの状態を保持するマップ.
	 */
	private Map<String, SimpleBooleanProperty> onMap = new HashMap<>();

	@Override
	public void setOnCloseRequest(WindowEvent event) {
		// NOP
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 初期化完了後に実行
		Platform.runLater(() -> {
			// 全ての戦略のクラス一覧を取得
			Set<Class<? extends BaseStrategy>> allStrategySet = StrategyHelper.getAllStrategyClassSet();

			// 使用するの戦略のクラス一覧を取得
			Set<Class<? extends BaseStrategy>> enableStrategySet = StrategyHelper.getEnableStrategyClassSet();

			// マップを初期化
			for (Class<? extends BaseStrategy> strategyClass : allStrategySet) {
				onMap.put(strategyClass.getName(),
						new SimpleBooleanProperty(enableStrategySet.contains(strategyClass)));
			}

			// リストビューを初期化
			strategyListView.setCellFactory(SelectStrategyListStrategyCell
					.forListView((BaseStrategy strategy) -> onMap.get(strategy.getClass().getName())));
			strategyListView.setItems(StrategyHelper.createStrategyList(allStrategySet, rouletteContext));

			// 戦略一覧をソート
			strategyListView.getItems().sort(BaseStrategy.getStrategyNameComparator());
			
			// 全て選択チェックボックスの初期状態を設定
			updateSelectAllCheckBox();
			
			// 全て選択チェックボックスのリスナーを設定
			selectAllCheckBox.setOnAction(event -> {
				boolean selectAll = selectAllCheckBox.isSelected();
				for (BaseStrategy strategy : strategyListView.getItems()) {
					SimpleBooleanProperty property = onMap.get(strategy.getClass().getName());
					if (property != null) {
						property.setValue(selectAll);
					}
				}
			});
			
			// 各戦略のチェックボックス状態変更時のリスナーを設定
			for (SimpleBooleanProperty property : onMap.values()) {
				property.addListener((observable, oldValue, newValue) -> {
					updateSelectAllCheckBox();
				});
			}
		});

		// OKボタンをクリックした時
		okButton.setOnMouseClicked(event -> {
			// 使用するの戦略のクラス一覧を取得
			Set<Class<? extends BaseStrategy>> enableStrategySet = new HashSet<>();
			for (BaseStrategy strategy : strategyListView.getItems()) {
				try {
					if (onMap.get(strategy.getClass().getName()).getValue()) {
						enableStrategySet.add(strategy.getClass());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 保存
			StrategyHelper.saveEnableStrategyClassSet(enableStrategySet);

			// 画面を閉じる
			getThisStage().close();
		});

		// キャンセルボタンをクリックした時
		cancelButton.setOnMouseClicked(event -> {
			// 設定は保存せずに画面を閉じる
			getThisStage().close();
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
	 * 全て選択チェックボックスの状態を更新.
	 */
	private void updateSelectAllCheckBox() {
		if (strategyListView.getItems() == null || strategyListView.getItems().isEmpty()) {
			selectAllCheckBox.setSelected(false);
			return;
		}
		
		boolean allSelected = true;
		for (BaseStrategy strategy : strategyListView.getItems()) {
			SimpleBooleanProperty property = onMap.get(strategy.getClass().getName());
			if (property == null || !property.getValue()) {
				allSelected = false;
				break;
			}
		}
		
		selectAllCheckBox.setSelected(allSelected);
	}
}