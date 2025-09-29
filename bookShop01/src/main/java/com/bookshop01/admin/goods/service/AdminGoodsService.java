package com.bookshop01.admin.goods.service;

import java.util.List;
import java.util.Map;

import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.goods.vo.ImageFileVO;
import com.bookshop01.order.vo.OrderVO;

public interface AdminGoodsService {
    public int addNewGoods(Map newGoodsMap) throws Exception;
    public List<GoodsVO> listNewGoods(Map condMap) throws Exception;
    public Map goodsDetail(int goods_id) throws Exception;
    public List goodsImageFile(int goods_id) throws Exception;
    public void modifyGoodsInfo(Map goodsMap) throws Exception;
    public void modifyGoodsImage(List<ImageFileVO> imageFileList) throws Exception;
    public List<OrderVO> listOrderGoods(Map condMap) throws Exception;
    public void modifyOrderGoods(Map orderMap) throws Exception;
    public void removeGoodsImage(int image_id) throws Exception;
    public void addNewGoodsImage(List imageFileList) throws Exception;

    // ===== �߰��� ��ɵ� =====
    public void removeGoods(int goods_id) throws Exception; // ��ǰ ����
    public List<GoodsVO> searchGoods(Map<String, Object> searchMap) throws Exception; // ��ǰ �˻�
    public void changeGoodsStatus(Map<String, Object> statusMap) throws Exception; // ��ǰ ���� ����
}
