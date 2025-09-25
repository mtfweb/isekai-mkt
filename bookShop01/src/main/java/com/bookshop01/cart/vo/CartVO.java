package com.bookshop01.cart.vo;

import org.springframework.stereotype.Component;

@Component("cartVO")
public class CartVO {
	private int cart_id;
	private String member_id;
	private int goods_id;
	private int cart_goods_qty = 1;  // ✅ 기본값 1 설정
	private String credate;
	private String del_yn;

	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public int getCart_goods_qty() {
		return cart_goods_qty;
	}
	public void setCart_goods_qty(int cart_goods_qty) {
		this.cart_goods_qty = cart_goods_qty;
	}
	public String getCredate() {
		return credate;
	}
	public void setCredate(String credate) {
		this.credate = credate;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
}
