package cn.icanci.web.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用来处理从其他资源发送的请求 转发到 这里中转 然后给个弹窗提示错误 然后再跳转界面
 * 
 * @author CC
 *
 */

@WebServlet("/user/resultInformationServlet")
public class ResultInformationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 没有就是null
		resp.sendRedirect("/resultInformation.jsp");
	}
}
