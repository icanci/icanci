package cn.icanci.web.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.icanci.domain.User;

@WebServlet("/user/questions")
public class UserQuestionsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接受请求参数
		// 1.1 先取出 session 中的学生对象 如果不存在 就直接 回到登陆界面
		User user = (User) req.getSession().getAttribute("USER_IN_SESSION");
		if (user.getId() == null) {
			//如果用户的id==null  那么就是session中不存在
			//跳转到 index.jsp
			resp.sendRedirect("/index.jsp");
			return ;
		}
		
		//如果存在 那就直接跳转到 index-questions.jsp    /WEB-INF/views/users/index-questions.jsp
		req.getRequestDispatcher("/WEB-INF/views/users/index-questions.jsp").forward(req, resp);
		return ;
		// 2.处理业务请求
		// 3.控制页面跳转
	}
}
