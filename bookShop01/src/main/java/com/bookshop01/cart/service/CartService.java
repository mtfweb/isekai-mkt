package com.bookshop01.cart.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.bookshop01.cart.vo.CartVO;

public interface CartService {
    public void addGoodsInCart(CartVO cartVO) throws DataAccessException;
    public List<CartVO> myCartList(CartVO cartVO) throws DataAccessException;
    public void removeCartGoods(int cart_id) throws DataAccessException;
    public void modifyCartQty(CartVO cartVO) throws DataAccessException;
}
