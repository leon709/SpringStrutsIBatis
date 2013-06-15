package leon.ssi.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leon.ssi.util.Constants;
import leon.ssi.util.session.UserSessionInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginFilter implements Filter {
	private FilterConfig config;
	private String mess = "";
	protected final Log logger = LogFactory.getLog(getClass());
	private String excluded;
	private static final String EXCLUDE = "exclude";
	private boolean no_init = true;

	// private LoginServlet loginServlet;
	public void setFilterConfig(FilterConfig paramFilterConfig) {
		if (this.no_init) {
			this.no_init = false;
			this.config = paramFilterConfig;
			if ((this.excluded = paramFilterConfig.getInitParameter("exclude")) != null)
				this.excluded += ",";
		}
	}

	private String getActionName(String actionPath) {
		// logger.info("filter actionPath===="+actionPath);
		StringBuffer actionName = new StringBuffer();
		try {
			int begin = actionPath.lastIndexOf("/");
			if (begin >= 0) {
				actionName.append(actionPath.substring(begin, actionPath.length()));
			}
		} catch (Exception e) {
		}
		return actionName.toString();
	}

	private boolean excluded(String paramString) {
		// logger.info("paramString====" + paramString);
		// logger.info("excluded====" + this.excluded);
		// logger.info(this.excluded.indexOf(paramString + ","));
		if ((paramString == null) || (this.excluded == null))
			return false;
		return (this.excluded.indexOf(paramString + ",") >= 0);
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain arg2) throws IOException,
			ServletException {
		// logger.info(request.getProtocol());

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String url = req.getRequestURI();
		String actionName = getActionName(url);
		//logger.info("actionname===="+actionName);
		String login = req.getContextPath() + "/jsp/login.jsp";
		if (!excluded(actionName)) {
			// logger.info("not the exclude name......");
			UserSessionInfo userSessionInfo = (UserSessionInfo) req.getSession().getAttribute(Constants.USER_SESSION);
			if (userSessionInfo == null) {
				logger.info("session null......");
				resp.sendRedirect(login);
				return;
			}
			// } else {
			// if (!userSessionInfo.isAdmin()) {
			// //logger.info("is not the admin......");
			// resp.sendRedirect(login);
			// return;
			// }
			// }
		}
		try {
			arg2.doFilter(request, response);
		} catch (Exception e) {
			logger.error("LoginFilter Fail:" + e.getMessage(), e);
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
		this.config = arg0;
		if ((this.excluded = arg0.getInitParameter("exclude")) != null)
			this.excluded += ",";
		this.no_init = false;
	}
}
