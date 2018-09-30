package utils;

import java.io.File;

import constants.Configurations;

/**
 * ファイルヘルパー.
 *
 * @author
 */
public class FileHelper {

	/**
	 * 設定ファイルを取得.
	 *
	 * @param fileName
	 * @return
	 */
	public static File getSettingFile(String fileName) {
		File file = new File(Configurations.SETTING_FILE_DIRECTORY, fileName);
		if (!file.getParentFile().exists()) {
			if (!file.getParentFile().mkdirs()) {
				System.err.println("ディレクトリの作成に失敗しました。");
			}
		}
		return file;
	}
}