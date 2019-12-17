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

/**
 * 当用户登陆的时候 用于检测用户是否已经完善信息 没有完善信息 就必须强制完善信息 然后才能登陆
 * 
 * @author CC
 *
 */

@WebServlet("/user/judgeImproveInformation")
public class JudgeUsersImproveInformationServlet extends HttpServlet {

	private IUserDao userDao;
	private User u;
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		userDao = new UserDaoImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.获取请求参数
		// 获取用户数据 从session中获取对象 User 对象
		User usession = (User) req.getSession().getAttribute("USER_IN_SESSION");
		usession = userDao.getUserByUsername(usession.getUsername());
		System.out.println("usession:" + usession);

		User user = userDao.getUserByUsername(req.getParameter("username"));

		System.out.println("user:" + user);

//		User u = new User();

		// 如果数据来自session 就保存在session

		System.out.println(usession.getUsername());
		if (usession.getUsername() != null && !"".equals(usession.getUsername())) {
			u = usession;
			System.out.println(1);
		} else {
			// 如果数据来自表单
			u = user;
			System.out.println(2);
		}
		System.out.println("打印对象来自判断是否上传用户必需的信息等    " + u);
		// 将用户保存到 session 中
		req.getSession().setAttribute("USER_IN_SESSION", u);
		// 如果用户对象为 null 那就直接跳转到登陆界面
		if (u == null) {
			req.setAttribute("CODE_IN_SESSION", "用户登陆已经被系统注销,请重新登陆");
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
			return;
		}
		// 如果为 null 就去完善信息 如果完善了 再去登陆
		if (!JudgeStringEmptyUtil.hasLength(u.getHeadimage()) || !JudgeStringEmptyUtil.hasLength(u.getAge().toString())
				|| !JudgeStringEmptyUtil.hasLength(u.getPersonalizedSignature())) {
			req.setAttribute("CODE_IN_SESSION", "用户没有完善信息,请完善");
			req.getRequestDispatcher("/WEB-INF/views/users/index-updateinfo.jsp").forward(req, resp);
			return;
		} else {
			// 如果完善了 就直接登陆
			req.getRequestDispatcher("/user/userspace").forward(req, resp);
			return;
		}

	}
}
