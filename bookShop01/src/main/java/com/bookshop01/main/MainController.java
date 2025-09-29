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

	/**
	 * 메인 화면: 상품 목록 조회 짧은 URL을 위해 "/"도 같은 핸들러로 매핑한다.
	 */
	@RequestMapping(value = { "/main/main.do", "/main", "/"}, method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ViewNameInterceptor가 없거나 "/"에서 viewName을 못 찾으면 기본값 사용
		String viewName = (String) request.getAttribute("viewName");
		if (viewName == null || viewName.isEmpty()) {
			viewName = "main/main";
		}

		Map<String, List<GoodsVO>> goodsMap = goodsService.listGoods();

		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("goodsMap", goodsMap);
		return mav;
	}
}
