package enums;

/**
 * 出目の生成方法.
 *
 * @author
 */
public enum SpotGenerateType {

	/**
	 * ランダム.
	 */
	RANDOM,

	/**
	 * 数字の順番.
	 */
	ROTATION_NUMBER,

	/**
	 * 盤上での順番.
	 */
	ROTATION_WHEEL,

	/**
	 * ランダム(赤のみ).
	 */
	RANDOM_RED_ONLY,

	/**
	 * ランダム(黒のみ),
	 */
	RANDOM_BLACK_ONLY,

	/**
	 * ランダム(1以外),
	 */
	RANDOM_EXCEPT_ONE;
}