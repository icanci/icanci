package cn.icanci.web.servlet.verificationCodeServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/outputcode")
public class GetCodeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 获取session 传输出去
		String code = (String) req.getSession().getAttribute("CODE_IN_SESSION");
		
		System.out.println("传输 验证码: " + code);
		resp.getWriter().print(code);

		// HttpSession session = req.getSession();
		// 传输之后立即销毁
		// session.removeAttribute("CODE_IN_SESSION");
	}
}
