package com.yh.web.filter;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.yh.pojo.User;
import com.yh.service.UserService;
import com.yh.service.impl.UserServiceImpl;
public class AutoLoginFilter implements Filter {
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//强转
		HttpServletRequest req =(HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		//如果是的登录页直接放行
		String path = request.getServletContext().getContextPath();
		if(path.startsWith("login.jsp")){
			chain.doFilter(req, res);
		}
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		//如果用户已经登录,放行,不需要自动登录
		if(user!=null){
			chain.doFilter(req, res);
			return;//程序结束
		}
		if(user==null){
			String ccookie_username = null;
			String cookie_password = null;
			//获取携带用户名和密码cookie
			Cookie[] cookie = req.getCookies();
			//判断自动登录的cookie是否存在,如果没有,没不需要自动登录了
			if(cookie == null){
				chain.doFilter(req, res);
				return;
			}
			if(cookie!=null){
				for(Cookie cookie2 :cookie){
					//获取想要的cookie
					if("ccookie_username".equals(cookie2.getName())){
						ccookie_username = cookie2.getValue();
					}
					if("cookie_password".equals(cookie2.getName())){
						cookie_password = cookie2.getValue();
					}
				}
			}
			if(ccookie_username!=null&&cookie_password!=null){
				//区数据库校验该用户名和密码是否正确
				UserService service = new UserServiceImpl();
				try {
					service.login(ccookie_username, cookie_password);
				} catch (SQLException e) {
					System.out.println("自动登录异常,自动忽略");
				}
				//完成自动登录
				session.setAttribute("user", user);
			}
		}
		//放行
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
