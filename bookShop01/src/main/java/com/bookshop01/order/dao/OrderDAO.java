package com.bookshop01.order.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.bookshop01.order.vo.OrderVO;

public interface OrderDAO {
    List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws DataAccessException;

    void insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException;

    OrderVO findMyOrder(String order_id) throws DataAccessException;

    void removeGoodsFromCart(OrderVO orderVO) throws DataAccessException;

    // ✅ 리스트 삭제용 오버로드 추가
    void removeGoodsFromCart(List<OrderVO> myOrderList) throws DataAccessException;

    void insertOrderSequence() throws DataAccessException;
}
