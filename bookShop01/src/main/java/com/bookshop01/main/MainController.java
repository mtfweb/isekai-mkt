package com.bookshop01.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.common.base.BaseController;
import com.bookshop01.goods.service.GoodsService;
import com.bookshop01.goods.vo.GoodsVO;

@Controller("mainController")
public class MainController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/main/main.do")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = (String) request.getAttribute("viewName");

        // 메인화면 상품 데이터 불러오기
        Map<String, List<GoodsVO>> goodsMap = goodsService.listGoods();

        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("goodsMap", goodsMap);
        return mav;
    }
}
