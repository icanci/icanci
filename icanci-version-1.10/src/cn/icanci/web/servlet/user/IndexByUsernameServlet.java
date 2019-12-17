package cn.icanci.web.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.icanci.dao.IUserDao;
import cn.icanci.dao.impl.UserDaoImpl;
import cn.icanci.domain.User;
import cn.icanci.util.JudgeStringEmptyUtil;
import cn.icanci.util.MD5Util;

@WebServlet("/user/loginByusername")
public class IndexByUsernameServlet extends HttpServlet {
	private IUserDao userDao;
	private User user;

	@Override
	public void init() throws ServletException {
		userDao = new UserDaoImpl();
		user = new User();
	}

	// 用来判断登陆
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取表单传入的参数
		String tokenInReq = req.getParameter("token");
		// 获取session中的参数
		String tokenInSession = (String) req.getSession().getAttribute("TOKEN_IN_SESSION");
		// 判断口令是不是一样 一样就销毁
		if (JudgeStringEmptyUtil.hasLength(tokenInReq) && tokenInReq.equals(tokenInSession)) {
			req.getSession().removeAttribute("TOKEN_IN_SESSION");
		}

		// 获取其他参数
		String username = req.getParameter("username");
		String password1 = req.getParameter("password1");

		// 获取 5 位 图片验证码
		String userCode = req.getParameter("userkey");
		// 获取 正确的 5 位 图片验证码
		String code = (String) req.getSession().getAttribute("CODE_IN_SESSION");

		// 判断验证码是不是正确的 此处 5 位图片验证码 不区分大小写
		if (!(JudgeStringEmptyUtil.hasLength(userCode) && JudgeStringEmptyUtil.hasLength(code)
				&& code.equalsIgnoreCase(userCode))) {
			// 如果不对 就返回登陆界面
			req.getSession().setAttribute("UPDATE_INFO_SESSION", "验证码为空或者错误");
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
			return;
		}
		String password = MD5Util.getMD5String(password1);
		user.setUsername(username);
		user.setPassword(password);

		// 判断是不是有这个用户
		User u = userDao.isRoot(user);
		// 如果不等于 null 就是存在这个user用户 把用户保存到 session 中 然后共享 跳转界面
		if (u != null) {
			// 把user对象存储到 session 中
			req.getSession().setAttribute("USER_IN_SESSION", user);
			req.getSession().setMaxInactiveInterval(60 * 10);
//			req.getRequestDispatcher("/WEB-INF/views/users/index.jsp").forward(req, resp);
			req.getRequestDispatcher("/user/judgeImproveInformation").forward(req, resp);
			return;
		} else {
			// 为 null 不存在这个用户 提示用户 此用户不存在 然后跳转到 登陆界面 或者 注册界面
			// 设置共享参数 用来提示用户
			req.getSession().setAttribute("UPDATE_INFO_SESSION", "账户名或密码错误");
//			resp.sendRedirect("/resultInformation.jsp");
			req.getRequestDispatcher("/resultInformation.jsp").forward(req, resp);
			return;
		}

	}
}
