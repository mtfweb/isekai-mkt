package com.bookshop01.admin.goods.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.goods.vo.ImageFileVO;
import com.bookshop01.order.vo.OrderVO;

public interface AdminGoodsDAO {
    int insertNewGoods(Map<String, Object> newGoodsMap); // throws 없음
    // ✅ selectNewGoodsList는 여기로 빼야 함
    List<GoodsVO> selectNewGoodsList(Map<String, Object> dateMap) throws DataAccessException;

    GoodsVO selectGoodsDetail(int goods_id) throws DataAccessException;
    List<ImageFileVO> selectGoodsImageFileList(int goods_id) throws DataAccessException;
    void insertGoodsImageFile(List<ImageFileVO> fileList) throws DataAccessException;
    void updateGoodsInfo(Map<String, Object> goodsMap) throws DataAccessException;
    void updateGoodsImage(List<ImageFileVO> imageFileList) throws DataAccessException;
    void deleteGoodsImage(int image_id) throws DataAccessException;
    void deleteGoodsImage(List<ImageFileVO> fileList) throws DataAccessException;
    List<OrderVO> selectOrderGoodsList(Map<String, Object> condMap) throws DataAccessException;
    void updateOrderGoods(Map<String, Object> orderMap) throws DataAccessException;
}
