package com.bookshop01.goods.service;

import java.util.List;
import java.util.Map;

import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.goods.vo.ImageFileVO;

public interface GoodsService {
    // 메인 화면에서 쓸 전체 상품 분류 (bestseller, newbook, steadyseller)
    public Map<String, List<GoodsVO>> listGoods() throws Exception;

    // 상품 상세
    public Map<String, Object> goodsDetail(String goods_id) throws Exception;

    // 검색
    public List<GoodsVO> searchGoods(String searchWord) throws Exception;

    // 키워드 자동완성
    public List<String> keywordSearch(String keyword) throws Exception;

    // 🔽 추가: 상품 상세 이미지 리스트
    public List<ImageFileVO> goodsDetailImage(String goods_id) throws Exception;
}
