package cn.icanci.logs;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.icanci.domain.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
public class ClassUserCreateLogs {
	public static void inputLogs(Object obj) throws IOException {
		User user = (User)obj;
		String message= new StringBuffer("创建对象 " + user.toString()).toString();
		File dir = new File("logs/OtherLogs");
		if(!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(dir,"AdminBeanClassLogs.log");
		FileOutputStream fos = new FileOutputStream(file,true);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String dateResoult = sdf.format(date);
		byte[] dateLine = dateResoult.getBytes();
		byte[] messageLine = message.getBytes();
		fos.write(dateLine,0,dateLine.length);
		fos.write("\r\n".getBytes());
		fos.write(messageLine,0,messageLine.length);
		fos.write("\r\n".getBytes());
		fos.close();
	}
}