package cn.icanci.web.servlet.verificationCodeServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.icanci.util.CaptchaUtils;

@WebServlet("/user/outputimagecode")
public class GetCodeImageServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取验证码 输出图片到网页上
		CaptchaUtils.genCaptcha(req, resp);
		System.out.println( "中转 : "+req.getSession().getAttribute("CODE_IN_SESSION"));
		//重新赋值给session
	}
}
