package com.bookshop01.goods.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.goods.vo.ImageFileVO;

public interface GoodsDAO {
    // 상태별 상품 조회
    public List<GoodsVO> selectGoodsListByStatus(String goods_status) throws DataAccessException;

    // 상품 상세
    public GoodsVO selectGoodsDetail(String goods_id) throws DataAccessException;

    // 상품 상세 이미지 리스트
    public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException;

    // 검색
    public List<GoodsVO> selectGoodsBySearchWord(String searchWord) throws DataAccessException;

    // 키워드 자동완성
    public List<String> selectKeywordSearch(String keyword) throws DataAccessException;
}
