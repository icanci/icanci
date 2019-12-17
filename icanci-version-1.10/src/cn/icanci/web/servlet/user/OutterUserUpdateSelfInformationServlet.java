package cn.icanci.web.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.icanci.dao.IUserDao;
import cn.icanci.dao.impl.UserDaoImpl;
import cn.icanci.domain.User;
import cn.icanci.util.JudgeStringEmptyUtil;
import cn.icanci.util.MD5Util;

/**
 * 用户修改账户密码的操作
 * 
 * @author CC
 *
 */

@WebServlet("/user/updateSelfInformation")
public class OutterUserUpdateSelfInformationServlet extends HttpServlet {
	private IUserDao userDao;
	private User user;
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		userDao = new UserDaoImpl();
		user = new User();
	}

	/**
	 * 是否需要一个专门的类来管理 所有错误请求 我可以把打印的数据 通过界面编写代码输出 直接设置弹窗数据数据
	 */

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.获取请求参数
		String username = req.getParameter("username");
		String password1 = req.getParameter("password1");
		String password2 = req.getParameter("password2");
		String email = req.getParameter("email");

		// 这里进行第二次验证 验证
		
		// 获取 6位数字验证码
		String usercode = req.getParameter("usercode");
		// 获取 5 位 图片验证码
		String userkey = req.getParameter("userkey");
		// 检验验证码是不是正确的
		// 获取 正确的 5 位数字验证码
		String code = (String) req.getSession().getAttribute("CODE_IN_SESSION");
		// 获取正确的 6 位数字验证码
		String emailCode = (String) req.getSession().getAttribute("USER_EMAIL_CODE_6_IN_SESSION");

		// 如果两个验证码都不为空 那就继续执行 否则 返回
		if (!(JudgeStringEmptyUtil.hasLength(usercode) && JudgeStringEmptyUtil.hasLength(userkey)
				&& JudgeStringEmptyUtil.hasLength(code) && JudgeStringEmptyUtil.hasLength(emailCode))
				&& usercode.equals(emailCode) && userkey.equalsIgnoreCase(code)) {
			// 重回定向 共享数据
			req.getSession().setAttribute("UPDATE_INFO_SESSION", "验证码有误或为空");
			req.getRequestDispatcher("/user/resultInformationServlet").forward(req, resp);
			return;
		}

		// 验证密码是不是一样
		if (!(JudgeStringEmptyUtil.hasLength(username) && (JudgeStringEmptyUtil.hasLength(password1)
				&& JudgeStringEmptyUtil.hasLength(password2) && password1.equals(password2)))) {
			req.getSession().setAttribute("UPDATE_INFO_SESSION", "用户名为空或者密码不一致,或密码为空");
			req.getRequestDispatcher("/user/resultInformationServlet").forward(req, resp);
			return;
		}

		// 验证 邮箱是不是已经注册了 如果没有注册就返回 并且提示用户
		User ue = userDao.getUserByEmail(email);     //这就是根据用户传入的邮箱找到的值  
		// 如果等于 null 就是不存在这个user用户 建议用户登陆
		if (ue.getId() == null) {
			req.getSession().setAttribute("UPDATE_INFO_SESSION", "此邮箱账户不存在,建议检查邮箱账号是否正确或者使用此邮箱注册 ");
			req.getRequestDispatcher("/user/resultInformationServlet").forward(req, resp);
			return;
		}
		// 存在用户这个邮箱 继续验证
		// 已经验证存在邮箱 那么就说明用户输入的邮箱是正确的 不考虑用户验证码泄露问题
		// 那么此时 验证 用户名是否存在 如果不存在就 修改 如果存在再判断是不是和邮箱对应的名字一样 如果和邮箱对应的用户名一样可以修改
		User u = userDao.getUserByUsername(username);
		// 不为 null 存在这个用户 提示用户 此用户存在 名字已经被占用
		// 设置共享参数 用来提示用户

		// 加密密码 MD5 加密
		String password = MD5Util.getMD5String(password1);
		// ue的空指针异常问题 所以首先判断卷是否  
		//因为 创建了对象  根据唯一主键判断
		if (u.getId() != null) {
			if (!(JudgeStringEmptyUtil.hasLength(email) && email.equals(u.getEmail()))) {    //验证邮箱对应
				// 邮箱不对应 不可以修改
				req.getSession().setAttribute("UPDATE_INFO_SESSION", "你的邮箱有误,请检查你的邮箱信息 ");
				req.getRequestDispatcher("/user/resultInformationServlet").forward(req, resp);
				return;
			} else {
				// 邮箱验证正确 可以修改
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
				userDao.update(ue.getId(), user);
				req.getSession().setAttribute("USER_IN_SESSION", user);
				//修改成功  提示用户修改成功   然后跳转到 进入页面
				req.getSession().setAttribute("UPDATE_INFO_SESSION", "修改成功");
				req.getRequestDispatcher("/WEB-INF/views/users/index.jsp").forward(req, resp);
			}
			return ;
		} else {
			// 用户不存在 可以修改
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			userDao.update(ue.getId(), user);
			req.getSession().setAttribute("USER_IN_SESSION", user);
			//修改成功  提示用户修改成功   然后跳转到 进入页面
			req.getSession().setAttribute("UPDATE_INFO_SESSION", "修改成功");
			req.getRequestDispatcher("/WEB-INF/views/users/index.jsp").forward(req, resp);
		}

		// 如果 和邮箱对应的名字一样 就可以修改 如果和邮箱对应的名字不一样
		// 但是 和其他邮箱的名字一样 就不可以修改 否则 就可以修改

		// 2.调用业务方法
		// 3.控制页面跳转
	}
}
