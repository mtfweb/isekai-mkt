package com.bookshop01.cart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface CartController {

    // 장바구니 담기
    public ModelAndView addGoodsInCart(int goods_id,
                                       HttpServletRequest request) throws Exception;

    // 장바구니 목록 보기
    public ModelAndView myCartMain(HttpServletRequest request,
                                   HttpServletResponse response) throws Exception;

    // 장바구니 상품 삭제
    public ModelAndView removeCartGoods(int cart_id) throws Exception;

    // 장바구니 수량 수정
    public ModelAndView modifyCartQty(int cart_id,
                                      int cart_goods_qty) throws Exception;
}
