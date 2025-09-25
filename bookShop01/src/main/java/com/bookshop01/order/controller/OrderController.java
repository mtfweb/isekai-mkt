package com.bookshop01.order.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface OrderController {

    // ���� ��ǰ �ֹ�
    public ModelAndView orderEachGoods(int goods_id,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception;

    // ��ٱ��� ��ü �ֹ�
    public ModelAndView orderAllCartGoods(String[] cart_goods_qty,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception;

    // ���� �� �ֹ� ó��
    public ModelAndView payToOrderGoods(Map<String, String> orderMap,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception;
}
