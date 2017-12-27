package com.yh.web.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.pojo.Product;
import com.yh.service.ProductService;
import com.yh.service.impl.ProductServiceImpl;
@WebServlet("/productByPid")
public class ProductByPid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String  pid = request.getParameter("pid");
		ProductService service = new ProductServiceImpl();
		Product product = service.findProductByPid(pid);
		if(product != null){
			//将查询结果放到作用域
			request.setAttribute("product", product);
			request.getRequestDispatcher("/product_info.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
