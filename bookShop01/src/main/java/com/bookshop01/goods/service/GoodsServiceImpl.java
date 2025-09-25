package com.bookshop01.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop01.goods.dao.GoodsDAO;
import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.goods.vo.ImageFileVO;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDAO goodsDAO;

    @Override
    public Map<String, List<GoodsVO>> listGoods() throws Exception {
        Map<String, List<GoodsVO>> goodsMap = new HashMap<>();

        List<GoodsVO> bestseller = goodsDAO.selectGoodsListByStatus("bestseller");
        List<GoodsVO> newbook = goodsDAO.selectGoodsListByStatus("newbook");
        List<GoodsVO> steadyseller = goodsDAO.selectGoodsListByStatus("steadyseller");

        goodsMap.put("bestseller", bestseller);
        goodsMap.put("newbook", newbook);
        goodsMap.put("steadyseller", steadyseller);

        return goodsMap;
    }

    @Override
    public Map<String, Object> goodsDetail(String goods_id) throws Exception {
        Map<String, Object> goodsMap = new HashMap<>();
        GoodsVO goodsVO = goodsDAO.selectGoodsDetail(goods_id);
        List<ImageFileVO> imageList = goodsDAO.selectGoodsDetailImage(goods_id);

        goodsMap.put("goodsVO", goodsVO);
        goodsMap.put("imageList", imageList);
        return goodsMap;
    }

    @Override
    public List<GoodsVO> searchGoods(String searchWord) throws Exception {
        return goodsDAO.selectGoodsBySearchWord(searchWord);
    }

    @Override
    public List<String> keywordSearch(String keyword) throws Exception {
        return goodsDAO.selectKeywordSearch(keyword);
    }

    @Override
    public List<ImageFileVO> goodsDetailImage(String goods_id) throws Exception {
        return goodsDAO.selectGoodsDetailImage(goods_id);
    }
}
