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

@WebServlet("/user/loginByEmail")
public class IndexByEmailServlet extends HttpServlet {

	private IUserDao userDao;
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		userDao = new UserDaoImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接收请求参数
		// 获取表单传入的参数
		String tokenInReq = req.getParameter("token");
		// 获取session中的参数
		String tokenInSession = (String) req.getSession().getAttribute("TOKEN_IN_SESSION");
		// 判断口令是不是一样 一样就销毁
		if (JudgeStringEmptyUtil.hasLength(tokenInReq) && tokenInReq.equals(tokenInSession)) {
			req.getSession().removeAttribute("TOKEN_IN_SESSION");
		}
		// 获取 email
		String email = req.getParameter("email");
		// 获取 6位数字验证码
		String usercode = req.getParameter("usercode");
		// 获取 5 位 图片验证码
		String userkey = req.getParameter("userkey");
		// 检验验证码是不是正确的
		// 获取 正确的 5 位数字验证码
		String code = (String) req.getSession().getAttribute("CODE_IN_SESSION");
		// 获取正确的 6 位数字验证码
		String emailCode = (String) req.getSession().getAttribute("USER_EMAIL_CODE_6_IN_SESSION");

		// 判断是不是验证不成立
		
		//此处注意  5位 图片验证码是不区分大小写的
		if (!(JudgeStringEmptyUtil.hasLength(usercode) && JudgeStringEmptyUtil.hasLength(userkey)
				&& JudgeStringEmptyUtil.hasLength(code) && JudgeStringEmptyUtil.hasLength(emailCode)
				&& userkey.equalsIgnoreCase(code) && usercode.equals(emailCode))) {
			// 验证不成立 跳转回登陆邮箱的页面
			req.getSession().setAttribute("UPDATE_INFO_SESSION", "验证码为空或者不正确");
			req.getRequestDispatcher("/index-email.jsp").forward(req, resp);
			// 结束方法
			return;
		}
		// 验证码验证通过
		// 验证是否有这个用户
		User u = userDao.getUserByEmail(email);
		// 如果不等于 null 就是存在这个user用户 把用户保存到 session 中 然后共享 跳转界面
		if (u != null) {
			// 把user对象存储到 session 中 //之后这里显示有问题的话就是对象信息
			System.out.println("IndexByEmailServlet.service()  打印对象 ");
			System.out.println(u);
			req.getSession().setAttribute("USER_IN_SESSION", u);
			req.getSession().setMaxInactiveInterval(60 * 10);
			//注意 此处跳转到 /user/improveInformation
			req.getRequestDispatcher("/user/judgeImproveInformation").forward(req, resp);
			return;
		} else {
			// 为 null 不存在这个用户 提示用户 此用户不存在 然后跳转到 登陆界面 或者 注册界面
			// 设置共享参数 用来提示用户
			req.getSession().setAttribute("UPDATE_INFO_SESSION", "此邮箱还没注册,赶快注册吧");
			resp.sendRedirect("/resultInformation.jsp");
			return;
		}
		// 2.调用业务方法

		// 3.控制页面跳转
	}
}
