package cn.icanci.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.icanci.util.StaticResources;

public class CheckLoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 转换请求
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 设置编码
		req.setCharacterEncoding(StaticResources.encoding);
		// 进行过滤
		String requestUri = req.getRequestURI();
		System.out.println(requestUri);
		if (!"/index.jsp".equals(requestUri) && !"/login".equals(requestUri) && !"/".equals(requestUri)) {
			Object obj = req.getSession().getAttribute("USER_IN_SESSION");
			System.out.println(obj);
			if (obj == null) {
				resp.sendRedirect("/index.jsp");
				return;
			}
		}
		// 放行
		chain.doFilter(req, resp);

	}

	@Override
	public void destroy() {

	}

}
