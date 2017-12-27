package com.yh.web.servlet;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.ast.DoubleLiteral;

import com.yh.pojo.Cart;
import com.yh.pojo.CartItem;
import com.yh.pojo.Product;
import com.yh.service.ProductService;
import com.yh.service.impl.ProductServiceImpl;
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//要放到购物车的pid
		String pid = request.getParameter("pid");
		//获得该商品的购买数量
		int buyNum = Integer.parseInt(request.getParameter("buyNum"));
		ProductService service= new ProductServiceImpl();
		//获得product对象
		 Product findProductByPid = service.findProductByPid(pid);
		 double shop_price = findProductByPid.getShop_price();
		 double subtotal = shop_price*buyNum;
		 //封装CartItem
		 CartItem cartItem  = new CartItem();
		 cartItem.setBuyNum(buyNum);
		 cartItem.setSubtotal(subtotal);
		 cartItem.setProduct(findProductByPid);
		 //获得购物车   先判断是否在session中存在购物车了
		 HttpSession session = request.getSession();
		 Cart cart = (Cart) session.getAttribute("cart");
		 if(cart==null){
			cart = new Cart(); 
		 }
		 //先判断购物车中是否已经包含了此购物项了 ,判断key是否已经存在
		 //如果购物车中已经存在该商品,将现在买的数量与原有的数量进行相加
		 Map<String, CartItem> catItems = cart.getCatItems();
		 double newSubtotal =0.0;
		 if(catItems.containsKey(pid)){
			 //取出原有的商品数量
			 CartItem cartItem2 = catItems.get(pid);
			 int oldBuyNum = cartItem2.getBuyNum();
			 oldBuyNum+=buyNum;//让已经有的数量和现在的数量相加
			 cartItem2.setBuyNum(oldBuyNum);//再封装回去cartItem
			 cart.setCatItems(catItems);//再封装回去到cart中
			 //修改小计
			 //cartItem2.setSubtotal(oldBuyNum*findProductByPid.getShop_price());
			 //原来该商品的小计
			 double oldSubtotal  = cartItem2.getSubtotal();
			 //新买的商品的小计
			 newSubtotal = buyNum*findProductByPid.getShop_price();
			 cartItem2.setSubtotal(oldSubtotal+newSubtotal);
		 }else{
			 //将此次购物项放到购物车中  key是pid 
			 catItems.put(findProductByPid.getPid(), cartItem);
			 newSubtotal = buyNum*findProductByPid.getShop_price();
		 }
		 //如果购物车没有改商品的,进行原有的如下操作
		 //计算总计
		 double total = cart.getTotal()+newSubtotal;
		 cart.setTotal(total);
		 //将车再次放回session
		 session.setAttribute("cart", cart);
		 //直接跳转到购物车页面
		 //用请求转发不是很好,页面刷新一次,页面调用一次,因为请求转发的访问地址栏地址不变,重定向会发生变化,在这里用重定向更好一点
		 //request.getRequestDispatcher("/cart.jsp").forward(request, response);
		 //用重定向
		 response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
