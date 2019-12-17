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

@WebServlet("/user/register")
public class UserRegisterServlet extends HttpServlet {
	private IUserDao userDao;
	private User user;
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		userDao = new UserDaoImpl();
		user = new User();
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

		String username = req.getParameter("username");
		String password1 = req.getParameter("password1");
		String password2 = req.getParameter("password2");
		String email = req.getParameter("email");
		Long jiontime = System.currentTimeMillis();

		// 这里进行第二次验证 验证

		// 获取 6位数字验证码
		String usercode = req.getParameter("usercode");
		// 获取 5 位 图片验证码
		String userkey = req.getParameter("userkey");
		// 检验验证码是不是正确的
		// 获取 正确的 5 位数字验证码
		String code = (String) req.getSession().getAttribute("CODE_IN_SESSION");
		// 获取正确的 6 位数字验证码
		String emailCode = (String) req.getSession().getAttribute("USER_EMAIL_CODE_6_IN_SESSION");

		// 如果两个验证码都不为空 那就继续执行 否则 返回
		if (!(JudgeStringEmptyUtil.hasLength(usercode) && JudgeStringEmptyUtil.hasLength(userkey)
				& JudgeStringEmptyUtil.hasLength(code) & JudgeStringEmptyUtil.hasLength(emailCode))
				&& usercode.equals(emailCode) && userkey.equalsIgnoreCase(code)) {
			// 重回定向 共享数据
			req.getSession().setAttribute("UPDATE_INFO_SESSION", "验证码有误或为空");
			req.getRequestDispatcher("/resultInformation.jsp").forward(req, resp);
			return;
		}

		// 如果不为空 并且两次密码一样 那么就加入到数据库
		if (!(JudgeStringEmptyUtil.hasLength(username) && JudgeStringEmptyUtil.hasLength(password1)
				&& JudgeStringEmptyUtil.hasLength(email) && JudgeStringEmptyUtil.hasLength(password2)
				&& password1.equals(password2))) {
			// 重回定向 共享数据
			req.getSession().setAttribute("UPDATE_INFO_SESSION", "用户数据有误或为空");
			req.getRequestDispatcher("/resultInformation.jsp").forward(req, resp);
			return;
		}

		// 验证用户名是否有重复的 如果有就不可以注册 建议修改名字
		User u = userDao.getUserByUsername(username);
		// 不为 null 存在这个用户 提示用户 此用户存在 名字已经被占用
//		System.out.println(u);
		// 设置共享参数 用来提示用户
		if (u.getId() != null) {
			req.getSession().setAttribute("UPDATE_INFO_SESSION", "此用户名已经被注册,建议换一个 ");
			req.getRequestDispatcher("/resultInformation.jsp").forward(req, resp);
			return;
		}

		// 验证邮箱是否已经被注册 如何已经注册 提醒用户已经注册 建议修改密码登陆
		User ue = userDao.getUserByEmail(email);
		// 如果不等于 null 就是存在这个user用户已经使用邮箱注册 建议用户修改密码登陆
		if (ue.getId() == null) {
			// 对密码加密 MD5 加密 也可以在网页就加密 然后传输到 servlet 也可以在servlet加密在传输到数据库
			String password = MD5Util.getMD5String(password1);
			// 设置属性值
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setJointime(jiontime);
			userDao.save(user);
			// 把用户对象保存再session中 登陆之后直接跳转到 进入界面
			req.getSession().setAttribute("USER_IN_SESSION", user);
			// 过滤器已经设置了最大的时间
			// 设置最大存在事件是10分钟
			//登陆之后跳转到 user 完善信息 界面
			req.getRequestDispatcher("/user/judgeImproveInformation").forward(req, resp);
			return;
		} else {
			req.getSession().setAttribute("UPDATE_INFO_SESSION", "此邮箱已经被注册,建议使用邮箱登陆或者修改密码 ");
			req.getRequestDispatcher("/resultInformation.jsp").forward(req, resp);
			return;
		}
		
		// 2.调用业务方法处理
		// 3.控制页面跳转
	}
}
