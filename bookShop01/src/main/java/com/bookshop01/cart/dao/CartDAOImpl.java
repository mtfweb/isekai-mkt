package com.bookshop01.cart.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.mybatis.spring.SqlSessionTemplate;
import com.bookshop01.cart.vo.CartVO;

@Repository("cartDAO")
public class CartDAOImpl implements CartDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void insertCart(CartVO cartVO) throws Exception {
		sqlSession.insert("mapper.cart.insertCart", cartVO);
	}

	@Override
	public List<CartVO> selectCartListByMemberId(String member_id) throws Exception {
		return sqlSession.selectList("mapper.cart.selectCartListByMemberId", member_id);
	}

	@Override
	public int updateCartQty(CartVO cartVO) throws Exception {
		return sqlSession.update("mapper.cart.updateCartQty", cartVO);
	}

	@Override
	public void deleteCartItem(int cart_id) throws Exception {
		sqlSession.update("mapper.cart.deleteCartItem", cart_id);
	}
}
