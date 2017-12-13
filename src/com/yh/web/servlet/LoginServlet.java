package com.yh.web.servlet;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.pojo.User;
import com.yh.service.UserService;

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
		UserService userService = new UserService();
		String usernameinput = request.getParameter("username");
		System.out.println(usernameinput);
		String passwordinput = request.getParameter("password");
		System.out.println(passwordinput);
		try {
			 boolean isExist = userService.queryUserByUsernameAndPwd(usernameinput,passwordinput);
			 if(isExist){
				 response.sendRedirect(request.getContextPath()+"/index.jsp");
				 User user = new User();
				 user.setUsername(usernameinput);
				 user.setPassword(passwordinput);
				 request.getSession().setAttribute("user", user);
				 request.getSession().setAttribute("logMsg", "你已经登录了");
			 }else{
				 response.sendRedirect(request.getContextPath()+"/login.jsp");
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}