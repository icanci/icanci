package cn.icanci.web.servlet.listFeedback;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.icanci.dao.IUserFeedbackDao;
import cn.icanci.dao.impl.UserFeedbackImpl;
import cn.icanci.domain.UserFeedback;
import cn.icanci.util.JudgeStringEmptyUtil;

@WebServlet("/user/feedback")
public class UserFeedbackServlet extends HttpServlet {

	private IUserFeedbackDao userFeedbackDao;
	private UserFeedback userFeedback;
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		userFeedbackDao = new UserFeedbackImpl();
		userFeedback = new UserFeedback();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接收请求参数
		// 备注 可以合并参数 减少空间消耗

		// 接收session中的参数 然后拿到之后就销毁 防止使用口令令牌
		String tokenInSesion = (String) req.getSession().getAttribute("TOKEN_IN_SESSION");

		// 接收用户隐藏表单的token
		String tokenInUser = req.getParameter("token");

		// 判断是否相等 相等就销毁这个session
		if (JudgeStringEmptyUtil.hasLength(tokenInUser) && tokenInUser.equals(tokenInSesion)) {
			req.getSession().removeAttribute("TOKEN_IN_SESSION");
		}

		String feedbackTheme = req.getParameter("feedbackTheme");
		String feedbackContent = req.getParameter("feedbackContent");

		// 获取服务器的时间毫秒值 用来 排序
		Long feedbackTime = System.currentTimeMillis();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		// 获取时间的字符串
		String longToDateTime = df
				.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(feedbackTime), ZoneId.of("Asia/Shanghai")));
		// 给对象传值
		userFeedback.setFeedbackTheme(feedbackTheme);
		userFeedback.setFeedbackContent(feedbackContent);
		userFeedback.setFeedbackTime(feedbackTime);
		userFeedback.setOutPrint(longToDateTime);
		// 2.调用业务方法
		userFeedbackDao.save(userFeedback);

		// 3.页面跳转 重定向 没有共享数据
		// req.getRequestDispatcher("/index-feedback.jsp").forward(req, resp);
		resp.sendRedirect("/index-feedback.jsp");

		// 出现的问题 表单的重复提交问题 需要 不然用户每次回退都有上一次保存的记录 那么需不需要
		// 已经解决

	}
}
