package com.bookshop01.cart.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookshop01.cart.dao.CartDAO;
import com.bookshop01.cart.vo.CartVO;

@Service("cartService")
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDAO cartDAO;
	
	@Override
	public void addGoodsInCart(CartVO cartVO) throws Exception {
		cartDAO.insertCart(cartVO);
	}

	@Override
	public List<CartVO> myCartList(CartVO cartVO) throws Exception {
		return cartDAO.selectCartListByMemberId(cartVO.getMember_id());
	}

	@Override
	public boolean modifyCartQty(CartVO cartVO) throws Exception {
		int updated = cartDAO.updateCartQty(cartVO);
		return updated > 0;
	}

	@Override
	public void removeCartGoods(int cart_id) throws Exception {
		cartDAO.deleteCartItem(cart_id);
	}
}
