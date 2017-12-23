package com.yh.admin.servlet;

import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.yh.pojo.Product;
import com.yh.service.ProductService;
import com.yh.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class EditProductByPidServlet
 */
@WebServlet("/edit")
public class EditProductByPidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProductByPidServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//${ pageContext.request.contextPath }/admin/product/edit.jsp

        String pid = request.getParameter("pid");
        ProductService service  = new ProductServiceImpl();
        Product pro = service.findProductByPid(pid);
		System.out.println(pro);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
