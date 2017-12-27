package com.yh.admin.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.service.AdminService;
import com.yh.service.impl.AdminServiceImpl;

@WebServlet("/deleteCartgoryByCid")
public class DeleteCartgoryByCid extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		AdminService service = new AdminServiceImpl();
		try {
			Boolean isExites = service.deleteCategoryByCid(cid);
			if(isExites){
				response.sendRedirect(request.getContextPath()+"/admincategory");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
