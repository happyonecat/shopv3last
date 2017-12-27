package com.yh.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableServer.THREAD_POLICY_ID;

import com.yh.pojo.Category;
import com.yh.service.AdminService;
import com.yh.service.impl.AdminServiceImpl;

/**
 * Servlet implementation class EditCartgoryByCid
 */
@WebServlet("/editCartgoryByCid")
public class EditCartgoryByCid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		AdminService service = new AdminServiceImpl();
		try {
			Category category  = service.selectCategoryByCid(cid);
			if(category!=null){
				request.setAttribute("category", category);
				request.getRequestDispatcher("/admin/category/edit.jsp").forward(request, response);
			}
		} catch (Exception e) {
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
