package com.yh.admin.servlet;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.pojo.Category;
import com.yh.service.AdminService;
import com.yh.service.impl.AdminServiceImpl;
@WebServlet("/updateCategoryServlet")
public class UpdateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		Category category  = new Category();
		category.setCid(cid);
		category.setCname(cname);
		AdminService service = new AdminServiceImpl();
		try {
			Boolean isExites = service.updateCategoryByCid(category);
			if(isExites){
				response.sendRedirect(request.getContextPath()+"/admincategory");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
