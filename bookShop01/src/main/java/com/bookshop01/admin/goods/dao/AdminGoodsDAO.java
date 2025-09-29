package com.bookshop01.admin.goods.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.goods.vo.ImageFileVO;
import com.bookshop01.order.vo.OrderVO;

public interface AdminGoodsDAO {
    int insertNewGoods(Map<String, Object> newGoodsMap); // throws 없음
    
    // ✅ 상품 목록 조회
    List<GoodsVO> selectNewGoodsList(Map<String, Object> dateMap) throws DataAccessException;

    // ✅ 상품 상세
    GoodsVO selectGoodsDetail(int goods_id) throws DataAccessException;

    // ✅ 상품 이미지 리스트
    List<ImageFileVO> selectGoodsImageFileList(int goods_id) throws DataAccessException;

    // ✅ 상품 이미지 추가
    void insertGoodsImageFile(List<ImageFileVO> fileList) throws DataAccessException;

    // ✅ 상품 정보 수정
    void updateGoodsInfo(Map<String, Object> goodsMap) throws DataAccessException;

    // ✅ 상품 이미지 수정
    void updateGoodsImage(List<ImageFileVO> imageFileList) throws DataAccessException;

    // ✅ 상품 이미지 삭제 (단일)
    void deleteGoodsImage(int image_id) throws DataAccessException;

    // ✅ 상품 이미지 삭제 (다중)
    void deleteGoodsImage(List<ImageFileVO> fileList) throws DataAccessException;

    // ✅ 주문 상품 목록 조회
    List<OrderVO> selectOrderGoodsList(Map<String, Object> condMap) throws DataAccessException;

    // ✅ 주문 상품 상태 수정
    void updateOrderGoods(Map<String, Object> orderMap) throws DataAccessException;

    // ===== 추가된 기능 =====

    // ✅ 상품 삭제
    void deleteGoods(int goods_id) throws DataAccessException;

    // ✅ 상품 검색
    List<GoodsVO> searchGoods(Map<String, Object> searchMap) throws DataAccessException;

    // ✅ 상품 상태 업데이트 (판매중 / 품절 등)
    void updateGoodsStatus(Map<String, Object> statusMap) throws DataAccessException;
}
