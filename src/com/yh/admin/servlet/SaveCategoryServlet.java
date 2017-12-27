package com.yh.admin.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.pojo.Category;
import com.yh.service.AdminService;
import com.yh.service.impl.AdminServiceImpl;
import com.yh.utils.CommonsUtils;

import sun.print.resources.serviceui_pt_BR;
@WebServlet("/saveCategoryServlet")
public class SaveCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cname = request.getParameter("cname");
		String cid = CommonsUtils.getUUID();
		Category category  = new Category();
		category.setCid(cid);
		category.setCname(cname);
		AdminService  service = new AdminServiceImpl();
		try {
			Boolean isExits =  service.addCategory(category);
			if(isExits){
				//假如添加成功,重定向到admincategory,再查一次
				response.sendRedirect(request.getContextPath()+"/admincategory");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
