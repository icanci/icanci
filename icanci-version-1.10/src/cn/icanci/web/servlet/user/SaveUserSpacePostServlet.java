package cn.icanci.web.servlet.user;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.icanci.dao.IUserSpaceDao;
import cn.icanci.dao.impl.UserSpaceDaoImpl;
import cn.icanci.domain.User;
import cn.icanci.domain.UserSpace;
import cn.icanci.util.FileUtilUserSpace;
import cn.icanci.util.JudgeStringEmptyUtil;

/**
 * 处理user传递的表单 用来处理界面用户提交的的userSpace表单
 * 
 * @author CC
 *
 */

@WebServlet("/user/saveUserSpacePost")
public class SaveUserSpacePostServlet extends HttpServlet {

	private IUserSpaceDao userSpaceDao;
	private UserSpace userSpace;
	private User user;
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		userSpaceDao = new UserSpaceDaoImpl();
		userSpace = new UserSpace();
		user = new User();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取参数

		// 接收通过上一个页面传递的session
		user = (User) req.getSession().getAttribute("USER_IN_SESSION");
		String tokenInReq = req.getParameter("token");
		// 获取session中的参数
		String tokenInSession = (String) req.getSession().getAttribute("TOKEN_IN_SESSION");
		// 判断口令是不是一样 一样就销毁
		if (JudgeStringEmptyUtil.hasLength(tokenInReq) && tokenInReq.equals(tokenInSession)) {
			req.getSession().removeAttribute("TOKEN_IN_SESSION");
		}
		System.out.println("获取/user/saveUserSpacePost:" + user);
		// 判断 u 是否存在 如果不存在 就直接跳转到登陆界面
		if (user.getId() == null) {
			resp.sendRedirect("/index.jsp");
			return;
		}

		// 首先获取 user 的 id保存起来
		userSpace.setUserId(user.getId());
		// 获取当前的系统时间 保存起来
		Long outputTime = System.currentTimeMillis();;
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		//获取时间的字符串
		String longToDateTime = df.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(outputTime), ZoneId.of("Asia/Shanghai")));
		//保存时间用来排序
		userSpace.setOutputTime(System.currentTimeMillis());
		//保存时间用来显示
		userSpace.setOutPrint(longToDateTime);
		Map<String, String> map = new HashMap<String, String>();
		FileUtilUserSpace.upload(req, map);
		// 继续保存
		if (JudgeStringEmptyUtil.hasLength(map.get("file"))
				|| JudgeStringEmptyUtil.hasLength(map.get("userMessages"))) {
			// 如果用户输入的都不为空 就进行保存
			userSpace.setUserMessages(map.get("userMessages"));
			userSpace.setUserImage(map.get("file"));
		}
		// 打印一下用户的说说
		System.out.println("user 的发布 : " + userSpace);
		// 保存到数据库
		userSpaceDao.save(userSpace);
		// 调用业务逻辑方法
		// 数据已经保存完毕 保存之后需要跳转到显示的界面
		req.getRequestDispatcher("/user/getUserSpaceInformation").forward(req, resp);
		return;
		// 控制页面跳转
	}
}
