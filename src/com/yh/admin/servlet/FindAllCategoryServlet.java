package com.yh.admin.servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yh.pojo.Category;
import com.yh.service.AdminService;
import com.yh.service.impl.AdminServiceImpl;

@WebServlet("/findAllCategory")
public class FindAllCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//提供一个集合list<Category> 转成json字符串
		AdminService admin = new AdminServiceImpl();
		try {
			List<Category> categorylist = admin.findAllCategory();
			//这里是ajax请求访问的数据,必须返回json格式,这里假如放到域里面,ajax访问不到的
			//谁request我,我就response给谁
			Gson gson  = new Gson();
			String json = gson.toJson(categorylist);
			//这里面有中文
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
