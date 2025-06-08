package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.RouletteContext;
import constants.Configurations;
import enums.HeatmapLayoutType;
import enums.RouletteType;
import enums.SpotGenerateType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utils.LogHelper;

/**
 * 初期設定画面コントローラー.
 *
 * @author cyrus
 */
public class InitialSettingController extends BaseController {

	/**
	 * このアプリについて.
	 */
	@FXML
	private MenuItem aboutMenuItem;

	/**
	 * ルーレットのタイプ.
	 */
	@FXML
	private RadioButton rouletteTypeRadioButton1;

	@FXML
	private RadioButton rouletteTypeRadioButton2;

	@FXML
	private RadioButton rouletteTypeRadioButton3;

	/**
	 * 初期所持金.
	 */
	@FXML
	private TextField initialBalanceTextField;

	/**
	 * 最小ベット額.
	 */
	@FXML
	private TextField minimumBetTextField;

	/**
	 * 最大ベット額.
	 */
	@FXML
	private TextField maximumBetTextField;

	/**
	 * 実行モード.
	 */
	@FXML
	private RadioButton runModeRadioButton1;

	@FXML
	private RadioButton runModeRadioButton2;

	/**
	 * ヒートマップレイアウト.
	 */
	@FXML
	private RadioButton heatmapLayoutRadioButton1;

	@FXML
	private RadioButton heatmapLayoutRadioButton2;

	/**
	 * 出目の生成方法.
	 */
	@FXML
	private RadioButton spotGenerateTypeRadioButton1;

	@FXML
	private RadioButton spotGenerateTypeRadioButton2;

	@FXML
	private RadioButton spotGenerateTypeRadioButton3;

	@FXML
	private RadioButton spotGenerateTypeRadioButton4;

	@FXML
	private RadioButton spotGenerateTypeRadioButton5;

	@FXML
	private RadioButton spotGenerateTypeRadioButton6;

	/**
	 * 戦略選択ボタン.
	 */
	@FXML
	private Button selectStrategyButton;

	/**
	 * スタートボタン.
	 */
	@FXML
	private Button startButton;

	/**
	 * シミュレーション速度スライダー.
	 */
	@FXML
	private Slider simulationSpeedSlider;

	/**
	 * シミュレーション速度ラベル.
	 */
	@FXML
	private Label simulationSpeedLabel;

	@Override
	public void setOnCloseRequest(WindowEvent event) {
		// TODO
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 初期値を設定
		initialBalanceTextField.setText(String.valueOf(Configurations.DEFAULT_INITIAL_BALANCE));
		minimumBetTextField.setText(String.valueOf(Configurations.DEFAULT_MINIMUM_BET));
		maximumBetTextField.setText(String.valueOf(Configurations.DEFAULT_MAXIMUM_BET));

		// このアプリについて
		aboutMenuItem.setOnAction(event -> {
			// アラートを表示
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("ルーレットシミュレーター " + Main.class.getPackage().getImplementationVersion());
			alert.setHeaderText("Roulette Simulator");
			alert.setContentText("Copyright (c) 2018 cyrus");
			alert.show();
		});

		// 戦略選択ボタンをクリックした時
		selectStrategyButton.setOnMouseClicked(event -> {
			// 新しいウインドウを生成
			Stage newStage = new Stage();
			// モーダルウインドウに設定
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.initOwner(getThisStage());

			// 戦略選択画面を表示
			openSelectStrategyList(newStage, createRouletteContext());
		});

		// スタートボタンをクリックした時
		startButton.setOnMouseClicked(event -> {
			// 新しいウインドウを生成
			Stage newStage = new Stage();
			// モーダルウインドウに設定
			newStage.initModality(Modality.APPLICATION_MODAL);
			newStage.initOwner(getThisStage());

			// シミュレーションモード画面を表示
			openSimulationMode(newStage, createRouletteContext());
		});

		// シミュレーション速度スライダーの変更監視
		simulationSpeedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			int speed = newValue.intValue();
			simulationSpeedLabel.setText(speed + "ms");
		});
	}

	/**
	 * ルーレットのコンテキストを作成して取得.
	 *
	 * @return
	 */
	private RouletteContext createRouletteContext() {
		// 設定値を取得
		RouletteType rouletteType = null;
		if (rouletteTypeRadioButton1.isSelected()) {
			rouletteType = RouletteType.ONE_TO_36;
		} else if (rouletteTypeRadioButton2.isSelected()) {
			rouletteType = RouletteType.EUROPEAN_STYLE;
		} else if (rouletteTypeRadioButton3.isSelected()) {
			rouletteType = RouletteType.AMERICAN_STYLE;
		}
		
		// ヒートマップレイアウトを取得
		HeatmapLayoutType heatmapLayoutType = null;
		if (heatmapLayoutRadioButton1.isSelected()) {
			heatmapLayoutType = HeatmapLayoutType.CIRCULAR;
		} else if (heatmapLayoutRadioButton2.isSelected()) {
			heatmapLayoutType = HeatmapLayoutType.RECTANGULAR;
		}
		
		// 出目の生成方法を取得
		SpotGenerateType spotGenerateType = null;
		if (spotGenerateTypeRadioButton1.isSelected()) {
			spotGenerateType = SpotGenerateType.RANDOM;
		} else if (spotGenerateTypeRadioButton2.isSelected()) {
			spotGenerateType = SpotGenerateType.ROTATION_NUMBER;
		} else if (spotGenerateTypeRadioButton3.isSelected()) {
			spotGenerateType = SpotGenerateType.ROTATION_WHEEL;
		} else if (spotGenerateTypeRadioButton4.isSelected()) {
			spotGenerateType = SpotGenerateType.RANDOM_RED_ONLY;
		} else if (spotGenerateTypeRadioButton5.isSelected()) {
			spotGenerateType = SpotGenerateType.RANDOM_BLACK_ONLY;
		} else if (spotGenerateTypeRadioButton6.isSelected()) {
			spotGenerateType = SpotGenerateType.RANDOM_EXCEPT_ONE;
		}
		
		long initialBalance = Long.parseLong(initialBalanceTextField.getText());
		long minimumBet = Long.parseLong(minimumBetTextField.getText());
		long maximumBet = Long.parseLong(maximumBetTextField.getText());

		// 設定値を出力
		LogHelper.info("ルーレットのタイプ=" + rouletteType.name());
		LogHelper.info("ヒートマップレイアウト=" + heatmapLayoutType.name());
		LogHelper.info("出目の生成方法=" + spotGenerateType.name());
		LogHelper.info("初期所持金=" + initialBalance);
		LogHelper.info("最小ベット額=" + minimumBet);
		LogHelper.info("最大ベット額=" + maximumBet);
		if (runModeRadioButton1.isSelected()) {
			LogHelper.info("実行モード=" + runModeRadioButton1.getText());
		} else if (runModeRadioButton2.isSelected()) {
			LogHelper.info("実行モード=" + runModeRadioButton2.getText());
		}

		// ルーレットのコンテキストを作成
		RouletteContext context = new RouletteContext(rouletteType, heatmapLayoutType, spotGenerateType, initialBalance, minimumBet, maximumBet);
		context.simulationSpeed = (long) simulationSpeedSlider.getValue();
		return context;
	}
}