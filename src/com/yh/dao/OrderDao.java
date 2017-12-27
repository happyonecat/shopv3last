package com.yh.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.yh.pojo.Order;
import com.yh.pojo.OrderItem;
import com.yh.service.OrderService;
import com.yh.utils.DataSourceUtils;
public class OrderDao {
	//向order中插入数据
	public void addOrders(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orders values(?,now(),?,?,?,?,?,?)";
		Connection conn = DataSourceUtils.getConnection();
		runner.update(conn,sql, order.getOid(),order.getTotal(),order.getState(),
				order.getAddr(),order.getName(),order.getTelephone(),order.getUser().getUid());
	}
	
	//向orderItem中插入数据
	public void addOrderItem(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orderitem values(?,?,?,?,?)";
		Connection conn = DataSourceUtils.getConnection();
		List<OrderItem> orderItems = order.getOrderItems();
		for(OrderItem item : orderItems){
			runner.update(conn,sql,item.getItemid(),item.getCount(),item.getSubtotal(),item.getProduct().getPid(),item.getOrder().getOid());
		}
	}

	public void updateOrder(Order order) throws SQLException {
		//这里需要oid ,所以需要在表单上写一个隐藏域,把oid传递过来
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update  orders set address=?,name=?,telephone=? where oid=?";
		runner.update(sql, order.getAddr(),order.getName(),order.getTelephone(),order.getOid());
	}

	public void updateOrderState(String r6_Order) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update  orders set state=? where oid=?";
		runner.update(sql, 1,r6_Order);
	}
 
	public List<Order> findAllOrders(String uid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where uid=?";
		System.out.println(uid);
		return runner.query(sql, new BeanListHandler<Order>(Order.class), uid);
	}

	public List<Map<String, Object>> findAllOrderItemByOid(String oid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select i.count,i.subtotal,p.pimage,p.pname,p.shop_price from orderitem i,product p where i.pid=p.pid and i.oid=?";
		List<Map<String, Object>> mapList = runner.query(sql, new MapListHandler(), oid);
		return mapList;
	}

	public List<Order> findAllOrder() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders";
		return runner.query(sql, new BeanListHandler<Order>(Order.class));
	}

	public List<Order> findOrderBystate(int states) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where state=?";
		return runner.query(sql, new BeanListHandler<Order>(Order.class),states);
	}

	public Order queryOrderByOid(String oid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where oid=?";
		return runner.query(sql, new BeanHandler<Order>(Order.class),oid);
	}


}
