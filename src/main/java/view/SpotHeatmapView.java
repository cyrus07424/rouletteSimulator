package view;

import java.util.List;
import java.util.Map;

import application.RouletteContext;
import enums.RouletteType;
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
		// ホイール配置を取得
		Spot[] wheelLayout = Spot.getWheelLayout(rouletteContext.rouletteType);
		
		// 円形配置を試行、失敗した場合は通常のグリッド配置にフォールバック
		if (tryCircularLayout(wheelLayout)) {
			return;
		}
		
		// 通常のグリッド配置（フォールバック）
		initializeGridLayout(wheelLayout);
	}

	/**
	 * 円形レイアウトでヒートマップを初期化を試行.
	 * 
	 * @param wheelLayout ホイール配置
	 * @return 成功した場合true
	 */
	private boolean tryCircularLayout(Spot[] wheelLayout) {
		try {
			int numSpots = wheelLayout.length;
			
			// 円の半径を計算（スポット数に基づく）
			double radius = Math.max(4, numSpots / (2 * Math.PI));
			int gridSize = (int) (radius * 2 + 4); // マージンを追加
			
			spotLabels = new Label[gridSize][gridSize];
			
			// グリッドのスタイル設定
			setHgap(2);
			setVgap(2);
			setStyle("-fx-padding: 5;");

			double centerX = gridSize / 2.0;
			double centerY = gridSize / 2.0;

			// 各スポットを円形に配置
			for (int i = 0; i < numSpots; i++) {
				double angle = 2 * Math.PI * i / numSpots - Math.PI / 2; // -π/2で12時方向から開始
				
				int x = (int) Math.round(centerX + radius * Math.cos(angle));
				int y = (int) Math.round(centerY + radius * Math.sin(angle));
				
				// グリッド範囲内かチェック
				if (x >= 0 && x < gridSize && y >= 0 && y < gridSize) {
					Label label = createSpotLabel(wheelLayout[i]);
					spotLabels[y][x] = label;
					add(label, x, y);
				} else {
					// 範囲外の場合は円形配置失敗
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			// 例外が発生した場合は円形配置失敗
			return false;
		}
	}

	/**
	 * 通常のグリッドレイアウトでヒートマップを初期化.
	 * 
	 * @param wheelLayout ホイール配置
	 */
	private void initializeGridLayout(Spot[] wheelLayout) {
		// グリッドサイズを動的に計算
		int numSpots = wheelLayout.length;
		int cols = calculateOptimalColumns(numSpots);
		int rows = (int) Math.ceil((double) numSpots / cols);
		
		spotLabels = new Label[rows][cols];
		
		// グリッドのスタイル設定
		setHgap(2);
		setVgap(2);
		setStyle("-fx-padding: 5;");

		// ホイール順序でラベルを作成して配置
		int row = 0;
		int col = 0;
		
		for (Spot spot : wheelLayout) {
			Label label = createSpotLabel(spot);
			spotLabels[row][col] = label;
			add(label, col, row);
			
			col++;
			if (col >= cols) {
				col = 0;
				row++;
			}
		}
	}

	/**
	 * 出目数に基づいて最適な列数を計算.
	 * 
	 * @param numSpots 出目数
	 * @return 最適な列数
	 */
	private int calculateOptimalColumns(int numSpots) {
		// より正方形に近い形になるように列数を決定
		if (numSpots <= 36) {
			return 6; // 6x6 or 6x7
		} else if (numSpots <= 42) {
			return 7; // 7x6 or 7x7
		} else {
			return 8; // 8列以上
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