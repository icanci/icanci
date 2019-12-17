package cn.icanci.web.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.icanci.dao.IUserSpaceDao;
import cn.icanci.dao.impl.UserSpaceDaoImpl;
import cn.icanci.domain.User;

/**
 * 删除用户选择的
 * 
 * @author CC
 *
 */

@WebServlet("/user/deleteSelfUserSpace")
public class UserDeleteSelfUserSpaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserSpaceDao userSpaceDao;
	
	@Override
		public void init() throws ServletException {
		userSpaceDao = new UserSpaceDaoImpl();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数
		User u = (User) req.getSession().getAttribute("USER_IN_SESSION");

		// 判断 u 是否存在 如果不存在 就直接跳转到登陆界面
		if (u.getId() == null) {
			resp.sendRedirect("/index.jsp");
			return;
		}
		
		Long deleteUserSpaceID =Long.valueOf(req.getParameter("deleteUserSpaceID")) ;
		//处理业务
		userSpaceDao.delete(deleteUserSpaceID);
		//控制页面跳转
		
		//删除之后继续跳转到 用户显示的界面
		req.getRequestDispatcher("/user/userSpaceInfomationPages").forward(req, resp);
		return ;
	}
}
