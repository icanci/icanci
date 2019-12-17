package cn.icanci.web.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.icanci.dao.IUserSpaceDao;
import cn.icanci.dao.impl.UserSpaceDaoImpl;
import cn.icanci.domain.User;
import cn.icanci.domain.UserSpace;

/**
 * 得到用户的 userSpace 的数据封装为 List集合 存储在session中
 * 
 * @author CC
 *
 */

@WebServlet("/user/getUserSpaceInformation")
public class GetUserSpaceInformationServlet extends HttpServlet {

	private IUserSpaceDao userSpaceDao;
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		userSpaceDao = new UserSpaceDaoImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接收请求参数
		// 接收通过上一个页面传递的session
		User u = (User) req.getSession().getAttribute("USER_IN_SESSION");

		// 判断 u 是否存在 如果不存在 就直接跳转到登陆界面
		if (u.getId() == null) {
			resp.sendRedirect("/index.jsp");
			return;
		}

		// 如果用户存在 获取此用户的 userSpace 信息

		// 如果是 用户主界面搜索框提示
		// String userScach = req.getParameter("");

		// 调用业务逻辑方法
		// 如果是 显示列表 封装成一个 List<UserSpace> 集合传递
		// 获取个人自己的空间
		List<UserSpace> listUserSpace = userSpaceDao.listAll(u.getId());

		for (UserSpace us : listUserSpace) {
			System.out.println("1:" + us);
		}

		// 把获取的 List<UserSpace> 对象保存到 session
		req.getSession().setAttribute("USERSELFINFO_IN_SESSION", listUserSpace);

		// 控制页面跳转

		// 跳转到设置页面分页
		req.getRequestDispatcher("/user/userSpaceInfomationPages").forward(req, resp);
		return;

	}
}
