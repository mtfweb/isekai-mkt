package com.bookshop01.order.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.order.service.OrderService;

@Controller("orderController")
@RequestMapping(value="/order")
public class OrderControllerImpl implements OrderController {

    @Autowired
    private OrderService orderService;

    // 개별 상품 주문
    @Override
    @RequestMapping(value="/orderEachGoods.do", method=RequestMethod.POST)
    public ModelAndView orderEachGoods(int goods_id,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        // 서비스 호출 및 로직 추가 예정
        mav.setViewName("order/orderEachGoods");
        return mav;
    }

    // 장바구니 전체 주문
    @Override
    @RequestMapping(value="/orderAllCartGoods.do", method=RequestMethod.POST)
    public ModelAndView orderAllCartGoods(String[] cart_goods_qty,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        // 서비스 호출 및 로직 추가 예정
        mav.setViewName("order/orderAllCartGoods");
        return mav;
    }

    // 결제 후 주문 처리
    @Override
    @RequestMapping(value="/payToOrderGoods.do", method=RequestMethod.POST)
    public ModelAndView payToOrderGoods(Map<String, String> orderMap,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        // 서비스 호출 및 로직 추가 예정
        mav.setViewName("order/payToOrderGoods");
        return mav;
    }
}
