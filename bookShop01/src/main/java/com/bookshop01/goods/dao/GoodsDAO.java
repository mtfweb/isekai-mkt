package com.bookshop01.goods.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.goods.vo.ImageFileVO;

public interface GoodsDAO {
    // ���º� ��ǰ ��ȸ
    public List<GoodsVO> selectGoodsListByStatus(String goods_status) throws DataAccessException;

    // ��ǰ ��
    public GoodsVO selectGoodsDetail(String goods_id) throws DataAccessException;

    // ��ǰ �� �̹��� ����Ʈ
    public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException;

    // �˻�
    public List<GoodsVO> selectGoodsBySearchWord(String searchWord) throws DataAccessException;

    // Ű���� �ڵ��ϼ�
    public List<String> selectKeywordSearch(String keyword) throws DataAccessException;
}
