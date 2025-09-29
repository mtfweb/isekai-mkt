package com.bookshop01.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.common.base.BaseController;
import com.bookshop01.goods.service.GoodsService;
import com.bookshop01.goods.vo.GoodsVO;

@Controller("mainController")
public class MainController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    // 진짜 메인 핸들러: /main.do
    @RequestMapping(value="/main.do", method=RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = (String) request.getAttribute("viewName");
        if (viewName == null || viewName.isEmpty()) viewName = "main/main"; // Tiles or JSP 경로
        Map<String, List<GoodsVO>> goodsMap = goodsService.listGoods();
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("goodsMap", goodsMap);
        return mav;
    }

    // 예전 주소로 들어오면 새 주소로 302 리다이렉트
    @RequestMapping(value="/main/main.do", method=RequestMethod.GET)
    public String legacyMain() {
        return "redirect:/main.do";
    }
}
