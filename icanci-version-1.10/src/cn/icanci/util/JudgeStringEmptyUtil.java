package cn.icanci.util;
/**
 *	判断字符串是否为空或者null
 * @author CC
 *
 */
public class JudgeStringEmptyUtil {
	/**
	 * 如果把 str != null 放在后面 当传入 null  的时候会报错 报空指针异常
	 * @param str 需要判断的字符串
	 * @return	返回 true 或者 false
	 */
	public static boolean hasLength(String str) {
		return str != null && !"".equals(str.trim()) ;
	}
}
