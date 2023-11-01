package cell;

import java.io.IOException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import strategy.BaseStrategy;

/**
 * 戦略選択画面の戦略のセル.
 *
 * @author cyrus
 */
public class SelectStrategyListStrategyCell<T> extends ListCell<BaseStrategy> {

	/**
	 * コンテナ.
	 */
	@FXML
	private Node cellContainer;

	/**
	 * チェックボックス.
	 */
	@FXML
	private CheckBox enableCheckBox;

	/**
	 * クラス名.
	 */
	@FXML
	private Label strategyClassNameLabel;

	private ObservableValue<Boolean> booleanProperty;

	// --- selected state callback property
	private ObjectProperty<Callback<BaseStrategy, ObservableValue<Boolean>>> selectedStateCallback = new SimpleObjectProperty<Callback<BaseStrategy, ObservableValue<Boolean>>>(
			this, "selectedStateCallback");

	/**
	 * コンストラクタ.
	 *
	 * @param getSelectedProperty
	 */
	public SelectStrategyListStrategyCell(Callback<BaseStrategy, ObservableValue<Boolean>> getSelectedProperty) {
		setSelectedStateCallback(getSelectedProperty);

		// レイアウトを読み込み
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/fxml/cell/SelectStrategyListStrategyCell.fxml"));
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * インスタンスを取得.
	 *
	 * @param getSelectedProperty
	 * @return
	 */
	public static Callback<ListView<BaseStrategy>, ListCell<BaseStrategy>> forListView(
			Callback<BaseStrategy, ObservableValue<Boolean>> getSelectedProperty) {
		return list -> new SelectStrategyListStrategyCell<>(getSelectedProperty);
	}

	@Override
	public void updateItem(BaseStrategy strategy, boolean empty) {
		super.updateItem(strategy, empty);
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			// ラベルを設定
			setText(null);
			enableCheckBox.setText(strategy.getStrategyName());
			strategyClassNameLabel.setText(strategy.getClass().getName());

			Callback<BaseStrategy, ObservableValue<Boolean>> callback = getSelectedStateCallback();
			if (callback == null) {
				throw new NullPointerException(
						"The CheckBoxListCell selectedStateCallbackProperty can not be null");
			}
			if (booleanProperty != null) {
				enableCheckBox.selectedProperty().unbindBidirectional((BooleanProperty) booleanProperty);
			}
			booleanProperty = callback.call(strategy);
			if (booleanProperty != null) {
				enableCheckBox.selectedProperty().bindBidirectional((BooleanProperty) booleanProperty);
			}

			setGraphic(cellContainer);
		}
	}

	/**
	 * Property representing the {@link Callback} that is bound to by the
	 * CheckBox shown on screen.
	 */
	public final ObjectProperty<Callback<BaseStrategy, ObservableValue<Boolean>>> selectedStateCallbackProperty() {
		return selectedStateCallback;
	}

	/**
	 * Sets the {@link Callback} that is bound to by the CheckBox shown on
	 * screen.
	 */
	public final void setSelectedStateCallback(Callback<BaseStrategy, ObservableValue<Boolean>> value) {
		selectedStateCallbackProperty().set(value);
	}

	/**
	 * Returns the {@link Callback} that is bound to by the CheckBox shown on
	 * screen.
	 */
	public final Callback<BaseStrategy, ObservableValue<Boolean>> getSelectedStateCallback() {
		return selectedStateCallbackProperty().get();
	}
}