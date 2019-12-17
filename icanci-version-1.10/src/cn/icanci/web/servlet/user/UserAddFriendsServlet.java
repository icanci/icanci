package cn.icanci.web.servlet.user;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.icanci.dao.IUserDao;
import cn.icanci.dao.impl.UserDaoImpl;
import cn.icanci.domain.User;
import cn.icanci.util.JudgeStringEmptyUtil;

@WebServlet("/user/addFriends")
public class UserAddFriendsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IUserDao userDao;

	@Override
	public void init() throws ServletException {
		userDao = new UserDaoImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接收请求参数
		// 1.1判断是否有有用户的 session 如果没有就跳转到登陆界面
		
		String tokenInReq = req.getParameter("token");
		// 获取session中的参数
		String tokenInSession = (String) req.getSession().getAttribute("TOKEN_IN_SESSION");
		// 判断口令是不是一样 一样就销毁
		if (JudgeStringEmptyUtil.hasLength(tokenInReq) && tokenInReq.equals(tokenInSession)) {
			req.getSession().removeAttribute("TOKEN_IN_SESSION");
		}
		
		User user = (User) req.getSession().getAttribute("USER_IN_SESSION");
		if (user.getId() == null) {
			// 用户的 session 为空 那就直接跳转到登陆界面
			resp.sendRedirect("/index.jsp");
			return;
		}

		// 接收请求的参数
		String seach = req.getParameter("seach");
		// 如果为空
		if (!JudgeStringEmptyUtil.hasLength(seach)) {
			// 就直接跳转到 添加好友的界面
			req.getRequestDispatcher("/WEB-INF/views/users/index-add-friends.jsp").forward(req, resp);
			return;
		}

		// 如果不是空
		// 判断是不是邮箱

		String rule = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(rule);
		matcher = pattern.matcher(seach);
		// 如果是邮箱
		if (matcher.matches()) {
			User uemail = userDao.getUserByEmail(seach);
			// 把结果保存到session中去 进去 前台界面再去判断
			// 首先打印
			System.out.println("打印email:" + uemail);
			// 保存用户的 输入的值 到session中准备回显
			req.getSession().setAttribute("USER_INPUT_VALUE_IN_SESSION", seach);
			req.getSession().setAttribute("USER_BY_SEACH_SESSION", uemail);
			// 转发请求
			req.getRequestDispatcher("/WEB-INF/views/users/index-add-friends.jsp").forward(req, resp);
			return;
		}

		// 判断是不是名字 这里可以模糊查询 暂时先不开发 只做精准查询

		User uname = userDao.getUserByUsername(seach);
		req.getSession().setAttribute("USER_INPUT_VALUE_IN_SESSION", seach);
		req.getSession().setAttribute("USER_BY_SEACH_SESSION", uname);
		System.out.println("打印名字:" + uname);
		// 转发请求
		req.getRequestDispatcher("/WEB-INF/views/users/index-add-friends.jsp").forward(req, resp);
		return;

		// 2.处理业务 判断是否为空 判断是否为 email 或者 用户名 修改 实现类
		// 3.控制页面跳转
	}
}
