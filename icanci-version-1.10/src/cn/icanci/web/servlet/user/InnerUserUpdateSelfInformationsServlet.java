package cn.icanci.web.servlet.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.icanci.dao.IUserDao;
import cn.icanci.dao.impl.UserDaoImpl;
import cn.icanci.domain.User;
import cn.icanci.util.FileUtil;

@WebServlet("/user/updateInformation")
public class InnerUserUpdateSelfInformationsServlet extends HttpServlet {

	private IUserDao userDao;
	private User user;
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		user = new User();
		userDao = new UserDaoImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 封装普通表达的数据

		Map<String, String> map = new HashMap<String, String>();
		FileUtil.upload(req, map);
		//首先获取 user 的其他信息   然后再进行更新操作
		user = userDao.getUserByUsername(map.get("username"));
		//获取其他的信息 进行更新
		user.setUsername(map.get("username"));
		user.setAge(Long.valueOf(map.get("age")));
		user.setSex("男");
		user.setPersonalizedSignature(map.get("personalizedSignature"));
		user.setHeadimage(map.get("file"));
		userDao.update(user.getId(), user);
		
		System.out.println("打印更新之后的user对象信息   "+ user);
		
		// 存储session
		req.getSession().setAttribute("USER_IN_SESSION", user);
		
		// 控制页面跳转 跳转到判断的的界面中 判断用户信息之后再进行下一步的操作
		req.getRequestDispatcher("/user/judgeImproveInformation").forward(req, resp);
		return;
	}
}
