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
import com.yh.utils.JedisPoolUtils;

import redis.clients.jedis.Jedis;
@WebServlet("/admincategory")
public class AdmincategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService service =  new AdminServiceImpl();
		List<Category> list  = null;
		try {
		   list  =  service.queryAllCategory();
		   if(list!=null){
				//先从缓存中查询categoryList 如果有直接使用 没有在从数据库中查询 存到缓存中
				//1、获得jedis对象 连接redis数据库
				Jedis jedis = JedisPoolUtils.getJedis();
				jedis.del("categoryListJson");
			   request.setAttribute("list", list);
			   request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);
		   }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
