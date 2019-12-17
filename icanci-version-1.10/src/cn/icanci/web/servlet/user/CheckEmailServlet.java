package cn.icanci.web.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.icanci.util.EmailUtil;

@WebServlet("/user/checkemail")
public class CheckEmailServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.获取请求参数
		
		String email = req.getParameter("email");
		//获取 EmailUtil 的验证码
		System.out.println(email);
		String userEmailCode = EmailUtil.getRandomCode();
		HttpSession session = req.getSession();
		//System.out.println("得到邮箱验证码:"+userEmailCode);
		//把验证码存放在 session中
		session.setAttribute("USER_EMAIL_CODE_6_IN_SESSION", userEmailCode);
		//发送验证码  需要得到验证码
		EmailUtil.sendEmail(email, userEmailCode);
		//2.处理业务请求
		// 获取session 传输出去
		resp.getWriter().print(userEmailCode);
		//3.控制页面跳转
		//此处不跳转页面
	}
}
