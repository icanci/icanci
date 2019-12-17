package cn.icanci.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.icanci.dao.IAdminDao;
import cn.icanci.dao.impl.AdminDaoImpl;
import cn.icanci.domain.Admin;
import cn.icanci.util.JudgeStringEmptyUtil;
import cn.icanci.util.MD5Util;

/**
 * 管理员的登陆界面
 * 
 * @author CC
 *
 */

@WebServlet("/admin/login")
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IAdminDao adminDao;

	@Override
	public void init() throws ServletException {
		adminDao = new AdminDaoImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接收请求参数
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		// 对于接收到的参数进行判断

		// 如果不都存在
		if (!(JudgeStringEmptyUtil.hasLength(username) && JudgeStringEmptyUtil.hasLength(password))) {
			resp.sendRedirect("/viewOfAdmins/index.html");
			return;
		}

		// 如果有数据 判断
		///对密码加密
		password = MD5Util.getMD5String(password);
		Admin admin = adminDao.LoginByUsername(username);
		//如果为空 就不存在
		if(admin.getId() == null) {
			resp.sendRedirect("/viewOfAdmins/index.html");
			return;
		}
		
		if(!password.equals(admin.getPassword())) {
			resp.sendRedirect("/viewOfAdmins/index.html");
			return;
		}
		//存储admin到session
		req.getSession().setAttribute("ADMIN_IN_SESSION",admin);
		
		//跳转到分页界面
		req.getRequestDispatcher("/admin/list").forward(req, resp);
		return ;
		// 处理业务
		// 控制页面跳转
	}

}
