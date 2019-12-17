package cn.icanci.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.icanci.dao.IUserFeedbackDao;
import cn.icanci.dao.impl.UserFeedbackImpl;
import cn.icanci.domain.Admin;

/**
 * 用户删除某个 留言
 * 
 * @author CC
 *
 */

@WebServlet("/admin/deleteFeedback")
public class AdminDeleteFeedbackServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IUserFeedbackDao userFeedback;

	@Override
	public void init() throws ServletException {
		userFeedback = new UserFeedbackImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取请求参数
		// 接收通过上一个页面传递的session
		Admin adm = (Admin) req.getSession().getAttribute("ADMIN_IN_SESSION");

		// 判断 u 是否存在 如果不存在 就直接跳转到登陆界面
		if (adm.getId() == null) {
			resp.sendRedirect("/viewOfAdmins/index.html");
			return;
		}
		Long id = Long.valueOf(req.getParameter("deleteId"));
		System.out.println(id);
		//删除
		userFeedback.delete(id);
		//删除之后就跳转到分页
		req.getRequestDispatcher("/admin/list").forward(req, resp);
		return ;
	}
}
