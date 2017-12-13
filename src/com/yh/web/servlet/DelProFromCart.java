package com.yh.web.servlet;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.yh.pojo.Cart;
import com.yh.pojo.CartItem;
@WebServlet("/delProFromCart")
public class DelProFromCart extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//获得要删除的item的pid
		String pid = request.getParameter("pid");
		//删除session中的购物车的购物项集合中的item
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart!=null){
			Map<String, CartItem> catItems = cart.getCatItems();
			//需要修改总价  购物车中总计-删掉的map集合中的itemcart
			cart.setTotal(cart.getTotal()-catItems.get(pid).getSubtotal());
			catItems.remove(pid);
			cart.setCatItems(catItems);
		}
		//再重新把cart放入到session域中
		session.setAttribute("cart", cart);
		//跳转回购物车页
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
