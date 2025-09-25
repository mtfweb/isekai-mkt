package com.bookshop01.order.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.bookshop01.order.vo.OrderVO;

@Repository("orderDAO")
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws DataAccessException {
        return sqlSession.selectList("mapper.order.selectOrderListByMemberId", orderVO);
    }

    @Override
    public void insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException {
        for (OrderVO orderVO : myOrderList) {
            sqlSession.insert("mapper.order.insertOrder", orderVO);
        }
    }

    @Override
    public OrderVO findMyOrder(String order_id) throws DataAccessException {
        return sqlSession.selectOne("mapper.order.selectOrderDetail", order_id);
    }

    @Override
    public void removeGoodsFromCart(OrderVO orderVO) throws DataAccessException {
        sqlSession.delete("mapper.order.deleteGoodsFromCart", orderVO);
    }

    // ✅ 리스트 삭제용 구현 추가
    @Override
    public void removeGoodsFromCart(List<OrderVO> myOrderList) throws DataAccessException {
        for (OrderVO orderVO : myOrderList) {
            sqlSession.delete("mapper.order.deleteGoodsFromCart", orderVO);
        }
    }

    @Override
    public void insertOrderSequence() throws DataAccessException {
        sqlSession.insert("mapper.order.insertOrderSequence");
    }
}
