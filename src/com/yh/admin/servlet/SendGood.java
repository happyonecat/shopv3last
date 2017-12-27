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

import com.yh.pojo.Order;
import com.yh.service.OrderService;
import com.yh.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class SendGood
 */
@WebServlet("/sendGood")
public class SendGood extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendGood() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//先查询,然后修改状态,最后更新
		//接受oid
		String oid = request.getParameter("oid");
		//根据oid查询
		OrderService service = new OrderServiceImpl();
		Order order = null;
		try {
			order = service.queryOrderByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		order.setState(3);
		service.updateOrder(order);
		response.sendRedirect(request.getContextPath()+"/listorder");
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
