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
import cn.icanci.page.PageResult;
import cn.icanci.util.JudgeStringEmptyUtil;
import cn.icanci.util.StaticResources;

/**
 * 控制界面的分页
 * 
 * @author CC
 *
 */

@WebServlet("/user/userSpaceInfomationPages")
public class UserSpaceInformationPagesServlet extends HttpServlet {

	private IUserSpaceDao userSpaceDao;
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		userSpaceDao = new UserSpaceDaoImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接收通过上一个页面传递的session
		User u = (User) req.getSession().getAttribute("USER_IN_SESSION");

		// 判断 u 是否存在 如果不存在 就直接跳转到登陆界面
		if (u.getId() == null) {
			resp.sendRedirect("/index.jsp");
			return;
		}
		// 设置编码
		req.setCharacterEncoding(StaticResources.encoding);
		// --------------------------------------currentPage
		String sCurrentPage = req.getParameter("currentPage");
		String sPageSize = req.getParameter("pageSize");
		Integer currentPage = 1;
		Integer pageSize = 10;
		if (JudgeStringEmptyUtil.hasLength(sCurrentPage)) {
			currentPage = Integer.valueOf(sCurrentPage);
		}
		if (JudgeStringEmptyUtil.hasLength(sPageSize)) {
			pageSize = Integer.valueOf(sPageSize);
		}
		PageResult pr = userSpaceDao.query(currentPage, pageSize, u.getId());
		
		req.setAttribute("pageResult", pr);
		req.setAttribute("PageSize", pageSize);
		//设置好属性之后 就进行跳转 跳转到
		req.getRequestDispatcher("/WEB-INF/views/users/index.jsp").forward(req, resp);
		return;
	}
}
