package com.bookshop01.cart.dao;

import java.util.List;
import com.bookshop01.cart.vo.CartVO;

public interface CartDAO {
	public void insertCart(CartVO cartVO) throws Exception;
	public List<CartVO> selectCartListByMemberId(String member_id) throws Exception;
	public int updateCartQty(CartVO cartVO) throws Exception;
	public void deleteCartItem(int cart_id) throws Exception;
}
