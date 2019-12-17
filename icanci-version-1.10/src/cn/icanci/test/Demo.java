package cn.icanci.test;


import cn.icanci.util.MD5Util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Demo {
	public static void main(String[] args) {
		Long time = System.currentTimeMillis();
//		LocalDateTime

		
		System.out.println("---------long毫秒值转换为日期---------");
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String longToDateTime = df.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.of("Asia/Shanghai")));
		System.out.println(longToDateTime);
	
		System.out.println(MD5Util.getMD5String("icanci"));
		
	}
}
