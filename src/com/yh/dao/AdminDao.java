package com.yh.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.mchange.v2.c3p0.stmt.PerConnectionMaxOnlyStatementCache;
import com.yh.pojo.Category;
import com.yh.pojo.Product;
import com.yh.pojo.User;
import com.yh.utils.DataSourceUtils;

public class AdminDao {
	public List<Category> findAllCatefory() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category";
		Connection conn = DataSourceUtils.getConnection();
		return runner.query(sql, new BeanListHandler<Category>(Category.class));
	}

	public void saveProduct(Product product) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
		runner.update(sql, product.getPid(), product.getPname(), product.getMarket_price(), product.getShop_price(),
				product.getPimage(), product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(),
				product.getCategory().getCid());
	}

	public List<Map<String, Object>> findorderInfoByOid(String oid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select p.pimage,p.pname,p.shop_price,i.count,i.subtotal " + " from orderitem i,product p "
				+ " where i.pid=p.pid and i.oid=? ";
		return runner.query(sql, new MapListHandler(), oid);
	}

	public List<Category> queryAllCategory() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category";
		Connection conn = DataSourceUtils.getConnection();
		return runner.query(sql, new BeanListHandler<Category>(Category.class));
	}

	public Long addCategory(Category category) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into category values(?,?)";
		return (long) runner.update(sql,category.getCid(),category.getCname() );
	}


	public Category selectCategoryByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category where cid=?";
		return runner.query(sql,new BeanHandler<Category>(Category.class), cid);
	}

	public int updateCategoryByCid(Category category) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update category set cname=? where cid=?";
		return runner.update(sql, category.getCname(),category.getCid());
	}

	public int deleteCategoryByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from category  where cid=?";
		return runner.update(sql, cid);
	}

}
