package com.yh.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.yh.dao.ProductDao;
import com.yh.pojo.Category;
import com.yh.pojo.PageBean;
import com.yh.pojo.Product;
import com.yh.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> findHotProductList() {
		ProductDao dao = new ProductDao();
		List<Product> hotProductList = null;
		try {
			hotProductList = dao.findHotProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hotProductList;
	}

	@Override
	public List<Product> findNewProductList() {
		ProductDao dao = new ProductDao();
		List<Product> newProductList = null;
		try {
			newProductList = dao.findNewProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newProductList;
	}

	@Override
	public List<Category> findAllCategory() {
		ProductDao dao = new ProductDao();
		List<Category> categoryList = null;
		try {
			categoryList = dao.findAllCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	@Override
	public PageBean findProductListByCid(String cid, int currentPage, int currentCount) {
		ProductDao dao = new ProductDao();
		//封装一个PageBean 返回web层
		PageBean<Product> pageBean = new PageBean<Product>();
		
		//1、封装当前页
		pageBean.setCurrentPage(currentPage);
		//2、封装每页显示的条数
		pageBean.setCurrentCount(currentCount);
		//3、封装总条数
		int totalCount = 0;
		try {
			totalCount = dao.getCount(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		//4、封装总页数
		int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
		pageBean.setTotalPage(totalPage);
		
		//5、当前页显示的数据
		// select * from product where cid=? limit ?,?
		// 当前页与起始索引index的关系
		int index = (currentPage-1)*currentCount;
		List<Product> list = null;
		try {
			list = dao.findProductByPage(cid,index,currentCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setList(list);//pageBean里面已经封装好了一个List<T> list; 这里是给自己的list赋值
		return pageBean;
	}

	@Override
	public Product findProductByPid(String pid) {
		ProductDao dao = new ProductDao();
		Product product = null;
		try {
			product = dao.findProductByPid(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public List<Product> queryAllProduct() throws SQLException {
		ProductDao dao = new ProductDao();
		List<Product> list = dao.queryAllProduct();
		return list;
	}

	@Override
	public Product queryProductByPid(String pid) throws SQLException {
		ProductDao dao = new ProductDao();
		return dao.findProductByPid(pid);
	}

	@Override
	public int deleteProByPid(String pid) throws SQLException {
		ProductDao dao = new ProductDao();
		int intdex = dao.deleteProByPid(pid);
		return intdex;
	}

}
