package com.yh.service;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.yh.dao.OrderDao;
import com.yh.pojo.Order;
import com.yh.utils.DataSourceUtils;
/*
 * 
 * */
public interface OrderService {
	//提交订单 数据都在order中,将订单项的数据存储到数据库中
	public void submitOrder(Order order);

	public void updateOrder(Order order);

	public void updateOrderState(String r6_Order);

	//获得指定用户的订单集合
	public List<Order> findAllOrders(String uid);

	public List<Map<String, Object>> findAllOrderItemByOid(String oid);

	public List<Order> findAllOrder() throws SQLException;
}
