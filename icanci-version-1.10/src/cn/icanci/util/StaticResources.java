package cn.icanci.util;

import java.util.Properties;

public class StaticResources {

	private static Properties p = new Properties();

	public static String encoding = "UTF-8";
	
	static {
		try {
			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("encoding.properties"));
			if (JudgeStringEmptyUtil.hasLength(p.getProperty("encoding"))) {
				encoding = p.getProperty("encoding");
			}
		} catch (Exception e) {
			
		}
	}
}
