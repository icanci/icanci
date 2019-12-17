package cn.icanci.web.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.icanci.domain.User;
import cn.icanci.util.JudgeStringEmptyUtil;

@WebServlet("/user/judgeAddFriends")
public class UserJudgeAddFriendsServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.接收请求参数
		//1.1判断是否有有用户的 session  如果没有就跳转到登陆界面
		
		String tokenInReq = req.getParameter("token");
		// 获取session中的参数
		String tokenInSession = (String) req.getSession().getAttribute("TOKEN_IN_SESSION");
		// 判断口令是不是一样 一样就销毁
		if (JudgeStringEmptyUtil.hasLength(tokenInReq) && tokenInReq.equals(tokenInSession)) {
			req.getSession().removeAttribute("TOKEN_IN_SESSION");
		}
		
		User user = (User)req.getSession().getAttribute("USER_IN_SESSION");
		if(user.getId()== null) {
			//用户的 session 为空  那就直接跳转到登陆界面
			resp.sendRedirect("/index.jsp");
			return ;
		}
		//2.处理业务
		//如果不是空 就跳转到添加添加好友的界面
		req.getRequestDispatcher("/WEB-INF/views/users/index-add-friends.jsp").forward(req, resp);
		return ;
		//3.控制页面跳转
	}
}
