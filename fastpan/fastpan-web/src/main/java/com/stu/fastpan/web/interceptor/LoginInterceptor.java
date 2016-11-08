package com.stu.fastpan.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.stu.fastpan.util.StringUtil;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	/**
	 * 免登入 免检查地址
	 */
	private List<String> uncheckUrls;

	public List<String> getUncheckUrls() {
		return uncheckUrls;
	}

	public void setUncheckUrls(List<String> uncheckUrls) {
		this.uncheckUrls = uncheckUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestUri = request.getRequestURI();
		log.info(requestUri);
		String contextPath = request.getContextPath();
		log.info(contextPath);
		String url = requestUri.substring(contextPath.length());

		log.info("url:" + url);
		uncheckUrls.contains(url);
		boolean flag = false;
		for (String string : uncheckUrls) {
			if (url.startsWith(string)) {
				flag = true;
				break;
			}
		}
		if (flag) {
			return true;
		} else {
			String username = (String) request.getSession().getAttribute("user");
			if (username == null) {
				log.info(this.getClass().getSimpleName() + "：此url被拦截,跳转到login页面！");
				response.sendRedirect(contextPath+"/user/login");
				return false;
			} else
				return true;
		}

	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
