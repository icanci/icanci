package cn.icanci.web.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//方式1 删除session中 key中 USER_IN_SESSION的属性
//		req.getSession().removeAttribute("USER_IN_SESSION");

		//方式2 删除所有的 session
		req.getSession().invalidate();
		//页面跳转
		resp.sendRedirect("/index.jsp");
	}
}
