package com.bookshop01.goods.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.common.base.BaseController;
import com.bookshop01.goods.service.GoodsService;
import com.bookshop01.goods.vo.GoodsVO;

import net.sf.json.JSONObject;

@Controller("goodsController")
@RequestMapping(value = "/goods")
public class GoodsControllerImpl extends BaseController implements GoodsController {

	@Autowired
	private GoodsService goodsService;

	@Autowired
	@Qualifier("sqlSession")   // bean id가 sqlSession이면 붙여주기
    private SqlSessionTemplate sqlSession;
    
	// 상품 상세 페이지
	@RequestMapping(value = "/goodsDetail.do", method = RequestMethod.GET)
	public ModelAndView goodsDetail(@RequestParam("goods_id") String goods_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();

		// 서비스에서 상세 정보 가져오기
		Map<String, Object> goodsMap = goodsService.goodsDetail(goods_id);

		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("goodsMap", goodsMap);

		// 최근 본 상품 리스트 저장
		GoodsVO goodsVO = (GoodsVO) goodsMap.get("goodsVO");
		addGoodsInQuick(goods_id, goodsVO, session);

		return mav;
	}

	// 자동완성 검색
	@RequestMapping(value = "/keywordSearch.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public @ResponseBody String keywordSearch(@RequestParam("keyword") String keyword, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		if (keyword == null || keyword.equals("")) {
			return null;
		}

		keyword = keyword.toUpperCase();
		List<String> keywordList = goodsService.keywordSearch(keyword);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("keyword", keywordList);

		return jsonObject.toString();
	}

	// 상품 검색
	@RequestMapping(value = "/searchGoods.do", method = RequestMethod.GET)
	public ModelAndView searchGoods(@RequestParam("searchWord") String searchWord, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List<GoodsVO> goodsList = goodsService.searchGoods(searchWord);

		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("goodsList", goodsList);

		return mav;
	}

	// 최근 본 상품 저장
	private void addGoodsInQuick(String goods_id, GoodsVO goodsVO, HttpSession session) {
		boolean already_existed = false;
		List<GoodsVO> quickGoodsList = (ArrayList<GoodsVO>) session.getAttribute("quickGoodsList");

		if (quickGoodsList != null) {
			if (quickGoodsList.size() < 4) {
				for (int i = 0; i < quickGoodsList.size(); i++) {
					GoodsVO _goodsBean = quickGoodsList.get(i);
					if (goods_id.equals(String.valueOf(_goodsBean.getGoods_id()))) {
						already_existed = true;
						break;
					}
				}
				if (!already_existed) {
					quickGoodsList.add(goodsVO);
				}
			}
		} else {
			quickGoodsList = new ArrayList<GoodsVO>();
			quickGoodsList.add(goodsVO);
		}

		session.setAttribute("quickGoodsList", quickGoodsList);
		session.setAttribute("quickGoodsListNum", quickGoodsList.size());
	}

    // 카테고리 제목 클릭: group=fashion 등
    // 소분류 클릭: sort=men, sort=mobile 등
    @RequestMapping(value = {"/list", "/listGoods.do"}, method = RequestMethod.GET)
    public String listGoods(@RequestParam(value="group", required=false) String group,
                            @RequestParam(value="sort",  required=false) String sort,
                            @RequestParam(value="status", required=false) String status,
                            @RequestParam(value="page",   required=false, defaultValue="1") int page,
                            Model model) {

        final int pageSize = 20;
        final int offset   = (Math.max(page, 1) - 1) * pageSize;

        // 그룹 → 소분류 코드 매핑 (DB 혼재 대비해 한글 코드도 같이 포함 가능)
        Map<String, List<String>> groupMap = new HashMap<>();
        groupMap.put("fashion",     Arrays.asList("men","women","shoes","bag","패션"));
        groupMap.put("electronics", Arrays.asList("mobile","pc","tv","audio"));
        groupMap.put("beauty",      Arrays.asList("cosmetics","skincare","perfume"));
        groupMap.put("living",      Arrays.asList("kitchen","living","cleaning"));
        // 필요시 확장:
        groupMap.put("book",        Arrays.asList("book","도서"));
        groupMap.put("food",        Arrays.asList("food","식품"));

        List<String> sorts;
        if (sort != null && !sort.trim().isEmpty()) {
            sorts = Collections.singletonList(sort.trim());
        } else if (group != null && groupMap.containsKey(group)) {
            sorts = groupMap.get(group);
        } else {
            sorts = Collections.emptyList(); // 필터 없으면 빈 목록
        }

        Map<String,Object> p = new HashMap<>();
        p.put("goodsSortList", sorts);
        if (status != null && !status.trim().isEmpty()) p.put("goods_status", status.trim());
        p.put("limit",  pageSize);
        p.put("offset", offset);

        // IN 쿼리 하나로 정리
        List<com.bookshop01.goods.vo.GoodsVO> goodsList =
            sqlSession.selectList("mapper.goods.selectGoodsListBySortIn", p);

        model.addAttribute("goodsList", goodsList);
        model.addAttribute("selectedGroup", group);
        model.addAttribute("selectedSort", sort);
        model.addAttribute("selectedStatus", status);
        return "goods/listGoods"; // Tiles/IRVR에서 goods/listGoods.jsp로 렌더
    }
}