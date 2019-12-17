package cn.icanci.util;

import java.util.Random;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import cn.icanci.dao.IUserDao;
import cn.icanci.dao.impl.UserDaoImpl;
import cn.icanci.domain.User;

public class EmailUtil {

	/**
	 * 生成6位数 验证
	 * 
	 * @return 返回验证码 code
	 */
	public static String getRandomCode() {
		String code = "";
		Random rd = new Random();
		for (int i = 0; i < 6; i++) {
			int r = rd.nextInt(10); // 每次随机出一个数字（0-9）
			code = code + r; // 把每次随机出的数字拼在一起
		}
		System.out.println(code);
		return code;
	}

	/**
	 * 向邮件发送验证码
	 * 
	 * @param email      需要发送的邮箱地址
	 * @param randomCode 需要发送的code值
	 */
	public static void sendEmail(String email, String randomCode) {

		IUserDao userDao = new UserDaoImpl();
		User user = new User();
		String sendMessage = "登陆";

		HtmlEmail send = new HtmlEmail();// 创建一个HtmlEmail实例对象
		// 获取随机验证码
		String resultCode = randomCode;
		// ---------------------------------------------------------
		// 在这里判断数据库是否存在该账户,用来发送不同的验证码
		user = userDao.getUserByEmail(email);
		User u = userDao.isRoot(user);

		if (u != null) {
			// user存在 那就是登陆
			sendMessage = "登陆";
		} else {
			// user不存在 那就是注册
			sendMessage = "注册";
		}
		// ------------------------------------------------------

		try {
			send.setHostName("smtp.163.com");
			send.setAuthentication("icanci@163.com", "icancixswl"); // 第一个参数是发送者的Eamil邮箱 第二个参数是获取的授权码
			send.setFrom("icanci@163.com", "璨詞文化");// 发送人的邮箱为自己的，用户名可以随便填 记得是自己的邮箱不是
			send.setSmtpPort(587); // 端口号 必须开
			send.setSSLOnConnect(true); // 开启SSL加密
			send.setCharset("utf-8");
			send.addTo(email); // 设置收件人 email为你要发送给谁的邮箱账户 上方参数
			send.setSubject("欢迎" + sendMessage + " ICANCI 官网"); // 邮箱标题
			send.setMsg("【璨詞文化】你正在" + sendMessage + " ICANCI 官网，验证码: " + resultCode
					+ " 验证码十分钟之内有效。 转发可能导致帐号被盗。如果这不是你本人操作请忽略..."); // Eamil发送的内容
			send.send(); // 发送
			System.out.println("发送成功");
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

}
