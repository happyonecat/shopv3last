package com.yh.web.servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yh.pojo.Cart;
import com.yh.pojo.CartItem;
import com.yh.pojo.Order;
import com.yh.pojo.OrderItem;
import com.yh.pojo.Product;
import com.yh.pojo.User;
import com.yh.service.OrderService;
import com.yh.utils.CommonsUtils;
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//判断用户是否已经登录,没有登录,下面代码不执行
		//目的:封装好一个order对象,传递给servie层
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//下面已经放到filter中了
		/*if(user==null){
			//没有登录
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}*/
		Order order  = new Order();
		//private String oid;//该订单的订单号
		String oid = CommonsUtils.getUUID();
		order.setOid(oid);
		//private Date ordertime;//下单时间
		//order.setOrdertime(new Date());
		//private double total;//该订单总金额  购物车中的总金额
		//获得session中的购物车
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart!=null){ //判断购物车不为空
			order.setTotal(cart.getTotal());
		}
		
		//private int state;//订单支付状态 1代表已付款 0代表未付款
		order.setState(0);
		//private String addr;//收货地址
		order.setAddr(null);
		//private String name;//收货人
		order.setName(null);
		//private String telephone;//收货电话
		order.setTelephone(null);
		//private User user;//该订单属于哪个用户
		order.setUser(user);
		//List<OrderItem> orderItems= new ArrayList<OrderItem>();//订单内部有多个订单项
		//获得购物车中购物项的集合
		Map<String, CartItem> catItems = cart.getCatItems();
		for(Entry<String, CartItem> entry:catItems.entrySet()){
			//取出每一个购物项
			CartItem cartItem = entry.getValue();
			OrderItem orderItem = new OrderItem();
			//private String itemid;//订单项id
			orderItem.setItemid(CommonsUtils.getUUID());//表中的itemid是唯一的
			//private int count;//订单项内商品的购买数量
			orderItem.setCount(cartItem.getBuyNum());
			//private Double subtotal;//订单项小计
			orderItem.setSubtotal(cartItem.getSubtotal());
			//private Product product;//订单项内的商品
			orderItem.setProduct(cartItem.getProduct());
			//private Order order;//该订单项属于哪个订单
			orderItem.setOrder(order);
			//将订单项添加到订单项集合中
			List<OrderItem> orderItems = order.getOrderItems();
			orderItems.add(orderItem);
		}
		//order对象封装完毕
		//传递到orderservice层
		OrderService orderService = new OrderService();
		orderService.submitOrder(order);
		//将order存到session
		session.setAttribute("order", order);
		//页面跳转
		response.sendRedirect(request.getContextPath()+"/order_info.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
