package com.bookshop01.cart.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.bookshop01.cart.vo.CartVO;

public interface CartDAO {
    public void insertCart(CartVO cartVO) throws DataAccessException;
    public List<CartVO> selectCartList(CartVO cartVO) throws DataAccessException;
    public void deleteCart(int cart_id) throws DataAccessException;
    public void updateCartQty(CartVO cartVO) throws DataAccessException;
}
