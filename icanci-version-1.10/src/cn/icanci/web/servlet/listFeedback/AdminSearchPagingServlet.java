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

@WebServlet("/admin/seachPaging")
public class AdminSearchPagingServlet extends HttpServlet {
	private IUserFeedbackDao userFeedbackDao;
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		userFeedbackDao = new UserFeedbackImpl();
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
		
		//如果搜索内容为空
	 	String seach = req.getParameter("seach").trim();
	 	if(!JudgeStringEmptyUtil.hasLength(seach)) {
	 		req.getRequestDispatcher("/admin/list").forward(req, resp);
			return;
	 	}
	 	
	 	//此处不需要传递 管理员的 id 参数  
	 	//此处为模糊查询  查询反馈意见的名字的内容
		PageResult pr = userFeedbackDao.query(currentPage, pageSize,seach);

		req.setAttribute("pageResult", pr);
		req.setAttribute("PageSize", pageSize);
		// 设置好属性之后 就进行跳转 跳转到  界面
		req.getRequestDispatcher("/WEB-INF/views/admins/show.jsp").forward(req, resp);
		return;
	}

}
