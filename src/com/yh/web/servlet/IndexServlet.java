package com.yh.web.servlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.yh.pojo.Product;
import com.yh.service.ProductService;
import com.yh.service.impl.ProductServiceImpl;
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductService service = new ProductServiceImpl();
		//准备热门商品---List<Product>
		List<Product> hotProductList = service.findHotProductList();
		//准备最新商品---List<Product>
		List<Product> newProductList = service.findNewProductList();
		//准备分类数据
		//List<Category> categoryList = service.findAllCategory();
		
		//request.setAttribute("categoryList", categoryList);
		request.setAttribute("hotProductList", hotProductList);
		request.setAttribute("newProductList", newProductList);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
