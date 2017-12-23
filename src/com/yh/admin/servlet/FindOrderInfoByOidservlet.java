package com.yh.admin.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sun.java_cup.internal.runtime.virtual_parse_stack;
import com.yh.service.AdminService;
import com.yh.service.impl.AdminServiceImpl;

/**
 * Servlet implementation class FindOrderInfoByOid
 */
@WebServlet("/findOrderInfoByOid")
public class FindOrderInfoByOidservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  /**
   * 根据订单项id查询订单项和商品信息
   */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//模拟网络延迟,让页面加载图片
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		//页面传过来的是json
		String oid = request.getParameter("oid");
		AdminService service  = new AdminServiceImpl();
		List<Map<String, Object>> map = null;
		try {
			 map =  service.findorderInfoByOid(oid);
			Gson sGson = new Gson();
			String json = sGson.toJson(map);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
		} catch (SQLException e) {
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
