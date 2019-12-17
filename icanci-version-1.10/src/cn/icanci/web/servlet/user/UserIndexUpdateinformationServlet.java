package cn.icanci.web.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.icanci.domain.User;

/**
 * 用来处理用户的更新信息操作
 * 
 * @author CC
 *
 */

@WebServlet("/user/indexUpdateinformation")
public class UserIndexUpdateinformationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.获取请求参数
		// 1.1 获取session中的数据
		User user = (User) req.getSession().getAttribute("USER_IN_SESSION");

		// 判断session是否存在 如果不存在 就返回登陆界面 成功之后
		if (user.getId() == null) {
			resp.sendRedirect("/index.jsp");
			return ;
		}

		//如果存在    就进行页面进行跳转
		req.getRequestDispatcher("/WEB-INF/views/users/index-updateinformation.jsp").forward(req, resp);
		return ;
		
		// 2.调用业务方法处理请求
		// 3.控制页面跳转
	}
}
