package com.yh.pojo;
import java.util.HashMap;
/**
 * 购物车
 * @author Administrator
 *
 */
import java.util.Map;
public class Cart {
	//该购物车存储的n多个购物项
	//用map 可以在页面接受id,然后根据id删除,不用遍历list集合
	Map<String, CartItem> catItems = new HashMap<String, CartItem>();
	//购物车总计
	private double total;
	public Map<String, CartItem> getCatItems() {
		return catItems;
	}
	public void setCatItems(Map<String, CartItem> catItems) {
		this.catItems = catItems;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
