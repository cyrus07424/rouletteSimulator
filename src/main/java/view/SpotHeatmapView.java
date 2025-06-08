package view;

import java.util.List;
import java.util.Map;

import application.RouletteContext;
import enums.Spot;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * 出目ヒートマップビュー.
 * 
 * @author cyrus
 */
public class SpotHeatmapView extends GridPane {

	private RouletteContext rouletteContext;
	private Label[][] spotLabels;

	/**
	 * コンストラクタ.
	 * 
	 * @param rouletteContext ルーレットコンテキスト
	 */
	public SpotHeatmapView(RouletteContext rouletteContext) {
		this.rouletteContext = rouletteContext;
		initializeHeatmap();
	}

	/**
	 * ヒートマップを初期化.
	 */
	private void initializeHeatmap() {
		// ヒートマップのグリッドサイズ (6列 x 7行で0-36と00を配置)
		spotLabels = new Label[7][6];
		
		// グリッドのスタイル設定
		setHgap(2);
		setVgap(2);
		setStyle("-fx-padding: 5;");

		// 利用可能な出目リストを取得
		List<Spot> availableSpots = Spot.getAvailableList(rouletteContext.rouletteType);
		
		// ラベルを作成して配置
		int row = 0;
		int col = 0;
		
		for (Spot spot : availableSpots) {
			Label label = createSpotLabel(spot);
			spotLabels[row][col] = label;
			add(label, col, row);
			
			col++;
			if (col >= 6) {
				col = 0;
				row++;
			}
		}
	}

	/**
	 * 出目ラベルを作成.
	 * 
	 * @param spot 出目
	 * @return ラベル
	 */
	private Label createSpotLabel(Spot spot) {
		Label label = new Label(getSpotDisplayText(spot));
		label.setMinSize(35, 25);
		label.setMaxSize(35, 25);
		label.setStyle("-fx-alignment: center; -fx-border-color: gray; -fx-border-width: 1;");
		label.setFont(Font.font("System", FontWeight.BOLD, 9));
		
		// 初期色設定
		updateSpotLabelColor(label, spot, 0);
		
		return label;
	}

	/**
	 * 出目の表示テキストを取得.
	 * 
	 * @param spot 出目
	 * @return 表示テキスト
	 */
	private String getSpotDisplayText(Spot spot) {
		if (spot == Spot.SPOT_00) {
			return "00";
		} else if (spot == Spot.SPOT_0) {
			return "0";
		} else {
			return String.valueOf(spot.getNumber());
		}
	}

	/**
	 * ヒートマップを更新.
	 */
	public void updateHeatmap() {
		if (rouletteContext.spotHistoryList.isEmpty()) {
			return;
		}

		// 各出目の出現回数を取得
		Map<Spot, Integer> frequencyMap = rouletteContext.getSpotFrequency();
		
		// 最大出現回数を取得 (色の正規化用)
		int maxCount = frequencyMap.values().stream().mapToInt(Integer::intValue).max().orElse(1);
		
		// 各ラベルの色を更新
		for (int row = 0; row < spotLabels.length; row++) {
			for (int col = 0; col < spotLabels[row].length; col++) {
				Label label = spotLabels[row][col];
				if (label != null) {
					Spot spot = getSpotFromLabel(label);
					int count = frequencyMap.getOrDefault(spot, 0);
					updateSpotLabelColor(label, spot, count == 0 ? 0 : (double) count / maxCount);
				}
			}
		}
	}

	/**
	 * ラベルから出目を取得.
	 * 
	 * @param label ラベル
	 * @return 出目
	 */
	private Spot getSpotFromLabel(Label label) {
		String text = label.getText();
		if ("00".equals(text)) {
			return Spot.SPOT_00;
		} else if ("0".equals(text)) {
			return Spot.SPOT_0;
		} else {
			try {
				int number = Integer.parseInt(text);
				return Spot.getByNumber(number);
			} catch (NumberFormatException e) {
				return Spot.SPOT_0; // デフォルト
			}
		}
	}

	/**
	 * 出目ラベルの色を更新.
	 * 
	 * @param label ラベル
	 * @param spot 出目
	 * @param intensity 強度 (0.0-1.0)
	 */
	private void updateSpotLabelColor(Label label, Spot spot, double intensity) {
		// 強度に基づいて背景色を決定
		if (intensity == 0) {
			// 出現していない場合は白背景
			String textColor = spot.isRed() ? "red" : (spot.isBlack() ? "black" : "green");
			label.setStyle(label.getStyle() + "; -fx-background-color: white; -fx-text-fill: " + textColor + ";");
		} else {
			// 強度に基づいて背景色と文字色を決定
			String backgroundColor;
			String textColor;
			
			if (spot.isRed()) {
				double alpha = 0.3 + (intensity * 0.7);
				backgroundColor = String.format("rgba(255,0,0,%.2f)", alpha);
				textColor = intensity > 0.5 ? "white" : "red";
			} else if (spot.isBlack()) {
				double alpha = 0.3 + (intensity * 0.7);
				backgroundColor = String.format("rgba(0,0,0,%.2f)", alpha);
				textColor = intensity > 0.3 ? "white" : "black";
			} else {
				// Green (0, 00)
				double alpha = 0.3 + (intensity * 0.7);
				backgroundColor = String.format("rgba(0,128,0,%.2f)", alpha);
				textColor = intensity > 0.5 ? "white" : "green";
			}
			
			label.setStyle(label.getStyle() + "; -fx-background-color: " + backgroundColor + 
				"; -fx-text-fill: " + textColor + ";");
		}
	}
}