package com.yh.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yh.dao.OrderDao;
import com.yh.pojo.Order;
import com.yh.service.OrderService;
import com.yh.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService {

	@Override
	public void submitOrder(Order order) {
		OrderDao dao = new OrderDao();
		try {
			//1、开启事务
			DataSourceUtils.startTransaction();
			//2、调用dao存储order表数据的方法
			dao.addOrders(order);
			//3、调用dao存储orderitem表数据的方法
			dao.addOrderItem(order);
		} catch (SQLException e) {
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DataSourceUtils.commitAndRelease();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}

	@Override
	public void updateOrder(Order order) {
		OrderDao dao = new OrderDao();
		try {
			dao.updateOrder(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateOrderState(String r6_Order) {
		OrderDao dao = new OrderDao();
		try {
			dao.updateOrderState(r6_Order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public List<Order> findAllOrders(String uid) {
		OrderDao dao = new OrderDao();
		List<Order> orderList = null;
		try {
			orderList = dao.findAllOrders(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public List<Map<String, Object>> findAllOrderItemByOid(String oid) {
		OrderDao dao = new OrderDao();
		List<Map<String, Object>> mapList = null;
		try {
			mapList = dao.findAllOrderItemByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("myOrderList"+mapList);
		return mapList;
	}

	@Override
	public List<Order> findAllOrder() throws SQLException {
		OrderDao dao = new OrderDao();
		return dao.findAllOrder();
	}

}
