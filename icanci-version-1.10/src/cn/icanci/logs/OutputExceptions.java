package cn.icanci.logs;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class OutputExceptions {
	public static void getAllException(Exception e) throws IOException {
		StringWriter sw =  new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		AllException.inputLogs(sw.toString());
	}
}
