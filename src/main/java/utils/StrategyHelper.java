package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.reflect.ClassPath;

import application.RouletteContext;
import constants.Configurations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import strategy.BaseStrategy;

/**
 * 戦略ヘルパー.
 *
 * @author cyrus
 */
public class StrategyHelper {

	/**
	 * 戦略のインスタンス一覧を取得.
	 *
	 * @param rouletteContext
	 * @return
	 */
	public static ObservableList<BaseStrategy> createStrategyList(Set<Class<? extends BaseStrategy>> strategyClassSet,
			RouletteContext rouletteContext) {
		ObservableList<BaseStrategy> strategyList = FXCollections.observableArrayList();
		for (Class<? extends BaseStrategy> strategyClass : strategyClassSet) {
			try {
				Constructor constructor = strategyClass.getConstructor(RouletteContext.class);
				strategyList.add((BaseStrategy) constructor.newInstance(rouletteContext));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return strategyList;
	}

	/**
	 * 使用する戦略のクラス一覧を保存.
	 *
	 * @param strategyClassSet
	 */
	public static void saveEnableStrategyClassSet(Set<Class<? extends BaseStrategy>> strategyClassSet) {
		File destFile = FileHelper.getSettingFile(Configurations.ENABLE_STRATEGY_LIST_SETTING_FILE_NAME);
		try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(destFile, false)))) {
			for (Class<? extends BaseStrategy> strategyClass : strategyClassSet) {
				printWriter.println(strategyClass.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使用する戦略のクラス一覧を取得.
	 *
	 * @return
	 */
	public static Set<Class<? extends BaseStrategy>> getEnableStrategyClassSet() {
		Set<Class<? extends BaseStrategy>> strategyClassSet = new HashSet<>();
		File srcFile = FileHelper.getSettingFile(Configurations.ENABLE_STRATEGY_LIST_SETTING_FILE_NAME);
		try {
			for (String line : Files.lines(srcFile.toPath()).collect(Collectors.toSet())) {
				try {
					strategyClassSet.add((Class<? extends BaseStrategy>) Class.forName(line));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strategyClassSet;
	}

	/**
	 * 全ての戦略のクラス一覧を取得.
	 *
	 * @return
	 */
	public static Set<Class<? extends BaseStrategy>> getAllStrategyClassSet() {
		Set<Class<? extends BaseStrategy>> strategyClassSet = new HashSet<>();
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			strategyClassSet.addAll(ClassPath.from(loader)
					.getTopLevelClassesRecursive("strategy").stream()
					.map(ClassPath.ClassInfo::load)
					.filter(clazz -> BaseStrategy.class.isAssignableFrom(clazz)
							&& !Modifier.isAbstract(clazz.getModifiers()))
					.map(clazz -> (Class<? extends BaseStrategy>) clazz)
					.collect(Collectors.toSet()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strategyClassSet;
	}
}