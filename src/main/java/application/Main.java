package application;

import controller.BaseController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * メイン.
 *
 * @author cyrus
 */
public class Main extends Application {

	@Override
	public void start(Stage stage) {
		// 初期設定画面を表示
		BaseController.openInitSetting(stage);
	}

	/**
	 * メイン.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// 起動
			launch(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}