package com.yh.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.filefilter.TrueFileFilter;

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

	@Override
	public List<Category> queryAllCategory() throws SQLException {
		return dao.queryAllCategory();
	}

	@Override
	public Boolean addCategory(Category category) throws Exception {
		return (dao.addCategory(category))>0?true:false;
	}

	@Override
	public Category selectCategoryByCid(String cid) throws SQLException {
		return dao.selectCategoryByCid(cid);
	}

	@Override
	public Boolean updateCategoryByCid(Category category) throws SQLException {
		// TODO Auto-generated method stub
		return dao.updateCategoryByCid(category)>0?true:false;
	}

	@Override
	public Boolean deleteCategoryByCid(String cid) throws SQLException {
		return dao.deleteCategoryByCid(cid)>0?true:false;
	}



}
