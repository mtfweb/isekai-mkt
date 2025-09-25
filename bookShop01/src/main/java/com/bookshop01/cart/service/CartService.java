package com.bookshop01.cart.service;

import java.util.List;
import com.bookshop01.cart.vo.CartVO;

public interface CartService {
	public void addGoodsInCart(CartVO cartVO) throws Exception;
	public List<CartVO> myCartList(CartVO cartVO) throws Exception;
	public boolean modifyCartQty(CartVO cartVO) throws Exception;
	public void removeCartGoods(int cart_id) throws Exception;
}
