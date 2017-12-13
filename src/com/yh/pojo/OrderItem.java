package com.yh.pojo;
/**
 * 订单列表
 * @author Administrator
 *
 */
public class OrderItem {
private String itemid;//订单项id
private int count;//订单项内商品的购买数量
private Double subtotal;//订单项小计
private Product product;//订单项内的商品
private Order order;//该订单项属于哪个订单
public String getItemid() {
	return itemid;
}
public void setItemid(String itemid) {
	this.itemid = itemid;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public Double getSubtotal() {
	return subtotal;
}
public void setSubtotal(Double subtotal) {
	this.subtotal = subtotal;
}
public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
public Order getOrder() {
	return order;
}
public void setOrder(Order order) {
	this.order = order;
}
}
