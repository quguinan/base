package com.cms.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import my.web.RequestContext;
import my.web.RequestUtils;

public class ContextInterceptor extends my.web.ContextInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		RequestContext.begin(new LocaleRequestWrapper(request, response), /* 54 */ response);

		String strUrl = request.getRequestURI();

		logger.debug(strUrl);
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		RequestContext.end();
	}

	public static class LocaleRequestWrapper extends HttpServletRequestWrapper {
		private final static int MAX_AGE = 86400 * 365;
		HttpServletResponse response;

		public LocaleRequestWrapper(HttpServletRequest request, HttpServletResponse response) {
			super(request);
			this.response = response;
		}

		/**
		 * parameter->cookie->request
		 */
		public Locale getLocale() {

			Locale result = (Locale) getRequest().getAttribute("lang");
			if (result != null) {
				return result;
			}

			String locale = getRequest().getParameter("lang");

			if (locale != null) {
				RequestUtils.setCookie((HttpServletRequest) getRequest(), response, "lang", locale, MAX_AGE);
			} else {
				javax.servlet.http.Cookie cookie = RequestUtils.getCookie((HttpServletRequest) getRequest(), "lang");

				if (cookie != null) {
					locale = cookie.getValue();
				}

				if (locale == null) {
					locale = ((HttpServletRequest) getRequest()).getLocale().toString();
				}
			}

			if ("zh_CN".equals(locale)) {
				getRequest().setAttribute("lang", Locale.CHINA);
				getRequest().setAttribute("langstr", Locale.CHINA.toString());
				return Locale.CHINA;
			} else if ("mn_MON".equals(locale)) {
				getRequest().setAttribute("lang", new Locale("mn","MON"));
				getRequest().setAttribute("langstr", "mn_MON");
				return Locale.US;
			} else {
				getRequest().setAttribute("lang", Locale.CHINA);
				getRequest().setAttribute("langstr", "zh_CN");
				return Locale.CHINA;// 默认中国
			}
		}
	}
	
	public static void main(String[] args) {
		Locale locale = new Locale("mn", "MON");
		System.out.println(locale.toString());
	}
	
}
