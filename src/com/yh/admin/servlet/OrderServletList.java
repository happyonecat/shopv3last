package com.yh.admin.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.pojo.Order;
import com.yh.service.OrderService;
import com.yh.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class OrderServletList
 */
@WebServlet("/listorder")
public class OrderServletList extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String state =request.getParameter("state");
		//业务操作
		OrderService service  = new OrderServiceImpl();
		List<Order> orders = null;
		int states = Integer.parseInt(state);
		try {
		if(states == 1){
			orders = service.findAllOrder();//查询所有
		}else{
			//查询某个订单状态
			orders = service.findOrderBystate(states);
		}
		 request.getSession().setAttribute("orders", orders);
		 response.sendRedirect(request.getContextPath()+"/admin/order/list.jsp");
		} catch (Exception e) {
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
