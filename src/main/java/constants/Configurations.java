package constants;

import java.io.File;
import java.security.SecureRandom;
import java.util.Random;

import enums.SpotGenerateType;

/**
 * 環境設定.
 *
 * @author cyrus
 */
public interface Configurations {

	/**
	 * 使用する乱数.
	 */
	Random RANDOM = new SecureRandom();

	/**
	 * 出目の生成方法.
	 */
	SpotGenerateType SPOT_GENERATE_TYPE = SpotGenerateType.RANDOM;

	/**
	 * 出目の履歴の最大サイズ.
	 */
	int SPOT_HISTORY_LIST_SIZE = 100;

	/**
	 * 所持金の履歴の最大サイズ.
	 */
	int BALANCE_HISTORY_SIZE = 100;

	/**
	 * 初期所持金の初期値.
	 */
	int DEFAULT_INITIAL_BALANCE = 10000;

	/**
	 * 最小ベット額の初期値.
	 */
	int DEFAULT_MINIMUM_BET = 100;

	/**
	 * 最大ベット額の初期値.
	 */
	int DEFAULT_MAXIMUM_BET = 50000;

	/**
	 * 設定ファイルの保存場所.
	 */
	File SETTING_FILE_DIRECTORY = new File("data/settings");

	/**
	 * 使用する戦略一覧設定ファイル名.
	 */
	String ENABLE_STRATEGY_LIST_SETTING_FILE_NAME = "enableStrategy.txt";
}