package com.yh.pojo;
/**
 * 购物车项
 * @author Administrator
 *
 */
public class CartItem {
	private Product product; //商品信息
	private int buyNum; //购买数量
	private double subtotal;//小计=商品价格*购买数量
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
}
