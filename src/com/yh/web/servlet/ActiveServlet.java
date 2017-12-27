package com.yh.web.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.yh.service.UserService;
import com.yh.service.impl.UserServiceImpl;
@WebServlet("/active")
public class ActiveServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//1 获得激活码
			String activeCode = request.getParameter("activeCode");
			//2 用户激活
			UserService service = new UserServiceImpl();
			service.active(activeCode);
			//3 成功提示 跳转到登录页面
			request.setAttribute("msg", "激活成功,请登录");
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
