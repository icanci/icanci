package cn.icanci.web.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.icanci.domain.User;

/**
 * 用户跳转的中转 Servlet
 * @author CC
 *
 */

@WebServlet("/user/userspace")
public class UserInitLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接收请求参数

		// 接收通过上一个页面传递的session
		User u = (User) req.getSession().getAttribute("USER_IN_SESSION");

		// 判断 u 是否存在 如果不存在 就直接跳转到登陆界面
		if (u.getId() == null) {
			resp.sendRedirect("/index.jsp");
			return;
		}
		//如果用户存在  控制页面跳转到 获取用户数据的界面
		
		req.getRequestDispatcher("/user/getUserSpaceInformation").forward(req, resp);
		return ;
		
		// 2.处理逻辑
		// 3.控制页面跳转

	}
}
