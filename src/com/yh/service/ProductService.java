package com.yh.service;

import java.sql.SQLException;
import java.util.List;

import com.yh.dao.ProductDao;
import com.yh.pojo.Category;
import com.yh.pojo.PageBean;
import com.yh.pojo.Product;

public interface ProductService {

	//获得热门商品
	public List<Product> findHotProductList();

	//获得最新商品
	public List<Product> findNewProductList();

	public List<Category> findAllCategory();

	public PageBean findProductListByCid(String cid,int currentPage,int currentCount);
	

	public Product findProductByPid(String pid);

	public List<Product> queryAllProduct() throws SQLException;

	public Product queryProductByPid(String pid) throws SQLException;

	

	public int deleteProByPid(String pid) throws SQLException;

}
