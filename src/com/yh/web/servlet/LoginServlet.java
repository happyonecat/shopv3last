package com.yh.web.servlet;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yh.pojo.User;
import com.yh.service.UserService;
import com.yh.service.impl.UserServiceImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		//获得输入的用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		//将用户名和密码传递给service层
		UserService service = new UserServiceImpl();
		User user = null;
		try {
			user = service.login(username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//判断用户是否登录成功 user是否是null
		if(user!=null){
			//登录成功
			//***************判断用户是否勾选了自动登录*****************
			String autoLogin = request.getParameter("autoLogin");
			if("autoLogin".equals(autoLogin)){
				//要自动登录
				//创建存储用户名的cookie
				Cookie cookie_username = new Cookie("cookie_username",user.getUsername());
				cookie_username.setMaxAge(10*60);
				//创建存储密码的cookie
				Cookie cookie_password = new Cookie("cookie_password",user.getPassword());
				cookie_password.setMaxAge(10*60);

				response.addCookie(cookie_username);
				response.addCookie(cookie_password);

			}
			//***************************************************
			//将user对象存到session中
			session.setAttribute("user", user);

			//重定向到首页
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}else{
			request.setAttribute("loginError", "用户名或密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}

}