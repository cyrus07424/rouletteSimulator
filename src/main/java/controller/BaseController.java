package controller;

import application.RouletteContext;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * コントローラーのベースクラス.
 *
 * @author
 */
public abstract class BaseController implements Initializable {

	/**
	 * 現在のステージ.
	 */
	private Stage thisStage;

	/**
	 * 現在のステージを設定.
	 *
	 * @param stage
	 */
	public void setThisStage(Stage stage) {
		thisStage = stage;
	}

	/**
	 * 現在のステージを取得.
	 *
	 * @return
	 */
	public Stage getThisStage() {
		return thisStage;
	}

	/**
	 * 画面初期化処理.
	 *
	 * @param location
	 * @param resources
	 */
	public abstract void initialize(URL location, ResourceBundle resources);

	/**
	 * 終了リクエスト発生時.
	 *
	 * @param event
	 */
	public abstract void setOnCloseRequest(WindowEvent event);

	/**
	 * 初期設定画面を表示.
	 *
	 * @param stage
	 */
	public static void openInitSetting(Stage stage) {
		try {
			// FXMLのレイアウトをロード
			FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.class.getResource("/fxml/InitialSetting.fxml"));
			Parent root = fxmlLoader.load();

			// コントローラーを取得
			BaseController controller = fxmlLoader.getController();
			controller.setThisStage(stage);

			// タイトルセット
			stage.setTitle("ルーレットシミュレーター");

			// シーン生成
			Scene scene = new Scene(root);

			// CSS
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 戦略選択画面を表示.
	 *
	 * @param stage
	 * @param rouletteContext
	 */
	public static void openSelectStrategyList(Stage stage, RouletteContext rouletteContext) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.class.getResource("/fxml/SelectStrategyList.fxml"));
			Parent root = fxmlLoader.load();

			// コントローラーを取得
			SelectStrategyListController controller = fxmlLoader.getController();
			initializeController(controller, stage);
			controller.setRouletteContext(rouletteContext);

			// タイトルセット
			stage.setTitle("戦略選択");

			// シーン生成
			Scene scene = new Scene(root);

			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * シミュレーションモード画面を表示.
	 *
	 * @param stage
	 * @param rouletteContext
	 */
	public static void openSimulationMode(Stage stage, RouletteContext rouletteContext) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.class.getResource("/fxml/SimulationMode.fxml"));
			Parent root = fxmlLoader.load();

			// コントローラーを取得
			SimulationModeController controller = fxmlLoader.getController();
			initializeController(controller, stage);
			controller.setRouletteContext(rouletteContext);

			// タイトルセット
			stage.setTitle("シミュレーションモード");

			// シーン生成
			Scene scene = new Scene(root);

			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * コントローラーの初期設定.
	 *
	 * @param controller
	 * @param stage
	 */
	private static void initializeController(BaseController controller, Stage stage) {
		controller.setThisStage(stage);
		stage.setOnCloseRequest(event -> {
			controller.setOnCloseRequest(event);
		});
	}
}