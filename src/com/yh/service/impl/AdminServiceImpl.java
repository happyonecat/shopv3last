package com.yh.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yh.pojo.Category;
import com.yh.pojo.Product;
import com.yh.service.AdminService;

public class AdminServiceImpl implements AdminService{

	@Override
	public List<Category> findAllCategory() throws SQLException {
		List<Category> listCategory = dao.findAllCatefory();
		return listCategory;
	}

	@Override
	public void saveProduct(Product product) throws SQLException {
		dao.saveProduct(product);
		
	}

	@Override
	public List<Map<String, Object>> findorderInfoByOid(String oid) throws SQLException {
		List<Map<String, Object>> mapList =  	dao.findorderInfoByOid(oid);
		return mapList;
	}

}
