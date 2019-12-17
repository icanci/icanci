package cn.icanci.web.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.icanci.dao.IUserDao;
import cn.icanci.dao.impl.UserDaoImpl;
import cn.icanci.domain.Admin;
import cn.icanci.domain.User;

/**
 * 管理员的管理界面
 * 
 * @author CC
 *
 */

@WebServlet("/admin/Administration")
public class AdministrationUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IUserDao userDao;
	
	@Override
	public void init() throws ServletException {
		userDao = new UserDaoImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接受请求参数
		Admin adm = (Admin) req.getSession().getAttribute("ADMIN_IN_SESSION");

		// 判断 u 是否存在 如果不存在 就直接跳转到登陆界面
		if (adm.getId() == null) {
			resp.sendRedirect("/viewOfAdmins/index.html");
			return;
		}
		// 处理业务请求

		List<User> listUser = userDao.listAll();
		//存储session
		
		req.getSession().setAttribute("USRE_LIST_IN_SESSION", listUser);
		// 控制页面跳转

		req.getRequestDispatcher("/WEB-INF/views/admins/index-userInfo.jsp").forward(req, resp);
		return;
	}
}
