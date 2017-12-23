package com.yh.service;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yh.dao.AdminDao;
import com.yh.pojo.Category;
import com.yh.pojo.Product;
public interface AdminService {
	AdminDao dao = new AdminDao();
	public List<Category> findAllCategory() throws SQLException;
	public void saveProduct(Product product) throws SQLException;
	public List<Map<String, Object>> findorderInfoByOid(String oid) throws SQLException;

}
