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
public class OrderService {
	//提交订单 数据都在order中,将订单项的数据存储到数据库中
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

	public void updateOrder(Order order) {
		OrderDao dao = new OrderDao();
		try {
			dao.updateOrder(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateOrderState(String r6_Order) {
		OrderDao dao = new OrderDao();
		try {
			dao.updateOrderState(r6_Order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	//获得指定用户的订单集合
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
}
