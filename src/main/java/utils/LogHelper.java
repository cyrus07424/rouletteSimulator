package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ログヘルパー.
 *
 * @author
 */
public class LogHelper {

	/**
	 * コンソールにログを出力.
	 *
	 * @param message
	 */
	public static void info(String message) {
		System.out.println(getFormattedLogMessage("INFO", message));
	}

	/**
	 * コンソールにログを出力.
	 *
	 * @param message
	 */
	public static void debug(String message) {
		System.out.println(getFormattedLogMessage("DEBUG", message));
	}

	/**
	 * コンソールにログを出力.
	 *
	 * @param message
	 */
	public static void error(String message) {
		System.err.println(getFormattedLogMessage("ERROR", message));
	}

	/**
	 * ログメッセージを整形.
	 *
	 * @param logLevel
	 * @param message
	 * @return
	 */
	private static String getFormattedLogMessage(String logLevel, String message) {
		return String.format("%s [%s] from %s - %s", getDateString(), logLevel, getCalledFrom(), message);
	}

	/**
	 * 呼び出し元メソッド情報を取得.<br>
	 * http://javasampleokiba.blog.fc2.com/blog-entry-4.html
	 *
	 * @return
	 */
	private static String getCalledFrom() {
		int useIndex = 4;
		StackTraceElement[] steArray = Thread.currentThread().getStackTrace();
		if (steArray.length <= useIndex) {
			return "NOT AVAILABLE";
		}
		StackTraceElement stackTraceElement = steArray[useIndex];
		return stackTraceElement.getClassName() + "#" + stackTraceElement.getMethodName();
	}

	/**
	 * 日付を取得.
	 *
	 * @return
	 */
	private static String getDateString() {
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss,SSS").format(new Date());
	}
}