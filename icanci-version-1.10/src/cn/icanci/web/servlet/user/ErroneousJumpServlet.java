package cn.icanci.web.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实现页面出现错误的跳转功能
 * 
 * @author CC
 *
 */

@WebServlet("/user/erroneousJump")
public class ErroneousJumpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 控制页面直接跳转到主页面 会在主页面里面进行访问 是否存在 session 然后控制页面跳转
		
		req.getRequestDispatcher("/WEB-INF/views/users/index.jsp").forward(req, resp);
	}
}
