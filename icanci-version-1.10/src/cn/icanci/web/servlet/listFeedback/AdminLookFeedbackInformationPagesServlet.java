package cn.icanci.web.servlet.listFeedback;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.icanci.dao.IUserFeedbackDao;
import cn.icanci.dao.impl.UserFeedbackImpl;
import cn.icanci.domain.Admin;
import cn.icanci.page.PageResult;
import cn.icanci.util.JudgeStringEmptyUtil;
import cn.icanci.util.StaticResources;

/**
 * 控制界面的分页
 * 
 * @author CC
 *
 */

@WebServlet("/admin/list")
public class AdminLookFeedbackInformationPagesServlet extends HttpServlet {

	private IUserFeedbackDao userFeedbackDap;
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		userFeedbackDap = new UserFeedbackImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接收通过上一个页面传递的session
		Admin adm = (Admin) req.getSession().getAttribute("ADMIN_IN_SESSION");

		// 判断 u 是否存在 如果不存在 就直接跳转到登陆界面
		if (adm.getId() == null) {
			resp.sendRedirect("/viewOfAdmins/index.jsp");
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
		//此处不需要传递 管理员的 id 参数
		PageResult pr = userFeedbackDap.query(currentPage, pageSize);
		
		req.setAttribute("pageResult", pr);
		req.setAttribute("PageSize", pageSize);
		//设置好属性之后 就进行跳转 跳转到
		req.getRequestDispatcher("/WEB-INF/views/admins/show.jsp").forward(req, resp);
		return;
	}
}
