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
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			String ccookie_username = null;
			String cookie_password = null;
			//获取携带用户名和密码cookie
			Cookie[] cookie = req.getCookies();
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
					e.printStackTrace();
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
