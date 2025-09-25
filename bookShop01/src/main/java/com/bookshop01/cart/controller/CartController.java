package com.bookshop01.cart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface CartController {

    // ��ٱ��� ���
    public ModelAndView addGoodsInCart(int goods_id,
                                       HttpServletRequest request) throws Exception;

    // ��ٱ��� ��� ����
    public ModelAndView myCartMain(HttpServletRequest request,
                                   HttpServletResponse response) throws Exception;

    // ��ٱ��� ��ǰ ����
    public ModelAndView removeCartGoods(int cart_id) throws Exception;

    // ��ٱ��� ���� ����
    public ModelAndView modifyCartQty(int cart_id,
                                      int cart_goods_qty) throws Exception;
}
