package com.yh.admin.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.omg.PortableServer.THREAD_POLICY_ID;

import com.sun.org.apache.xerces.internal.util.EntityResolver2Wrapper;
import com.yh.pojo.Category;
import com.yh.pojo.Product;
import com.yh.service.AdminService;
import com.yh.service.impl.AdminServiceImpl;
import com.yh.utils.CommonsUtils;

/**
 * Servlet implementation class SaveProduct
 */
@WebServlet("/saveProduct")
public class SaveProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Product product  = new Product();
		//收集数据的容器
		Map<String, Object> map = new HashMap<String, Object>();
		//pname
		//market_price
		//shop_price
		//upload
		//cid
		//pdesc
		//目的: 收集表单数据 并封装成一个实体类  product,将上传的图片存到磁盘里面
		//1 创建磁盘文件项工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//2 创建文件上传的核心对象
		ServletFileUpload upload  = new ServletFileUpload(factory);
		//3 解析request获得文件项集合
		List<FileItem> parseRequest = null;
		try {
			parseRequest = upload.parseRequest(request);
			
		for(FileItem item :parseRequest){
			//判断是否是普通表单项
			boolean formField = item.isFormField();
			if(formField){
				//普通表单项 获得表单输入的数据,封装到product实体中
				String fieldName = item.getFieldName();
				String fieldValue = item.getString("utf-8");
				map.put(fieldName, fieldValue);
			    
			}else{
				//文件上传项  获得文件的名称,获得文件的内容
				String filename = item.getName();
				String path = this.getServletContext().getRealPath("upload");
				InputStream in = item.getInputStream();
				FileOutputStream out = new  FileOutputStream(path+"/"+filename);
				//文件拷贝
				IOUtils.copy(in, out);
				map.put("pimage", "upload/"+filename);
				in.close();
				out.close();
				item.delete();
			}
			
		}	
			
		BeanUtils.populate(product, map);	
		//检验表单中的数据和实体类是否完整,封装完全了
		/**
		 *
		 *  private String pid;
			private Date pdate;
			private int pflag;
			private Category category;
		 */
		product.setPid(CommonsUtils.getUUID());//假如是修改,在页面用隐藏域,把CID传过来,然后根据cid修改,代码不用变
		Date date  = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//先指定日期的形式
		String pdate = simpleDateFormat.format(date);//再格式化日期
		//simpleDateFormat.parse(pdate);  //把字符串转化为日期类型存储到mysql数据库
		product.setPdate(simpleDateFormat.parse(pdate));
		
		product.setPflag(0);//0上架  1下架
		Category category = new Category();
		category.setCid(map.get("cid").toString());//object转化为String用toString()
		product.setCategory(category);
		
		//将封装到好的product,传递给service
		AdminService service = new AdminServiceImpl();
		service.saveProduct(product);
		response.sendRedirect(request.getContextPath()+"/admin/product/list.jsp");
		} catch (Exception e) {
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
