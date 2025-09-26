package com.bookshop01.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.bookshop01.cart.dao.CartDAO;
import com.bookshop01.cart.vo.CartVO;

@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDAO cartDAO;
    
    @Override
    public void addGoodsInCart(CartVO cartVO) throws DataAccessException {
        cartDAO.insertCart(cartVO);
    }

    @Override
    public List<CartVO> myCartList(CartVO cartVO) throws DataAccessException {
        return cartDAO.selectCartList(cartVO);
    }

    @Override
    public void removeCartGoods(int cart_id) throws DataAccessException {
        cartDAO.deleteCart(cart_id);
    }

    @Override
    public void modifyCartQty(CartVO cartVO) throws DataAccessException {
        cartDAO.updateCartQty(cartVO);
    }
}
