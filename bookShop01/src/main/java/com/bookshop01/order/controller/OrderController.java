package com.bookshop01.order.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface OrderController {

    // 개별 상품 주문
    public ModelAndView orderEachGoods(int goods_id,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception;

    // 장바구니 전체 주문
    public ModelAndView orderAllCartGoods(String[] cart_goods_qty,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception;

    // 결제 후 주문 처리
    public ModelAndView payToOrderGoods(Map<String, String> orderMap,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception;
}
