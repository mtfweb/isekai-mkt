package com.bookshop01.cart.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.bookshop01.cart.vo.CartVO;

@Repository("cartDAO")
public class CartDAOImpl implements CartDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void insertCart(CartVO cartVO) throws DataAccessException {
        sqlSession.insert("mapper.cart.insertCart", cartVO);
    }

    @Override
    public List<CartVO> selectCartList(CartVO cartVO) throws DataAccessException {
        return sqlSession.selectList("mapper.cart.selectCartListByMemberId", cartVO.getMember_id());
    }

    @Override
    public void deleteCart(int cart_id) throws DataAccessException {
        sqlSession.delete("mapper.cart.deleteCart", cart_id);
    }

    @Override
    public void updateCartQty(CartVO cartVO) throws DataAccessException {
        sqlSession.update("mapper.cart.updateCartQty", cartVO);
    }
}
