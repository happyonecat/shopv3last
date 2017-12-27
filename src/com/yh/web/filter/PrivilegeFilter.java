package com.yh.web.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.pojo.User;
/**
 * 权限控制,有登录权限的,可以访问到购物车和订单页面,其他页面不给访问
 * @author Administrator
 *
 */
public class PrivilegeFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PrivilegeFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		User user = (User)req.getSession().getAttribute("user");
		if(user==null){
			req.setAttribute("msg", "您还没有登录,没有访问权限!!!");
			req.getRequestDispatcher("/login.jsp").forward(req, res);
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
