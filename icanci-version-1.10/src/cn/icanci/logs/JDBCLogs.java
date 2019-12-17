package cn.icanci.logs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JDBCLogs {
	public static void inputLogs(String message) throws IOException {
		File dir = new File("logs/OtherLogs");
		if(!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(dir,"JDBCLogs.log");
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
