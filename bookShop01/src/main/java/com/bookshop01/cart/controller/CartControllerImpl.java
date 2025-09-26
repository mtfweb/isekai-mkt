package com.bookshop01.cart.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.cart.service.CartService;
import com.bookshop01.cart.vo.CartVO;
import com.bookshop01.goods.service.GoodsService;
import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.member.vo.MemberVO;

@Controller("cartController")
@RequestMapping(value="/cart")
public class CartControllerImpl implements CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private GoodsService goodsService;  // ✅ 상품 정보 조회 추가
	
	@Autowired
	private MemberVO memberVO;
	
	// 장바구니 담기
	@RequestMapping(value="/addGoodsInCart.do", method=RequestMethod.POST)
	public ModelAndView addGoodsInCart(@RequestParam("goods_id") int goods_id,
			                     HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("memberInfo");
		
		ModelAndView mav = new ModelAndView();
		
		// 로그인 여부 확인
		if (memberVO == null) {
			mav.setViewName("redirect:/member/loginForm.do");
			return mav;
		}
		
		CartVO cartVO = new CartVO();
		cartVO.setGoods_id(goods_id);
		cartVO.setMember_id(memberVO.getMember_id());
		cartVO.setCart_goods_qty(1);
		
		cartService.addGoodsInCart(cartVO);
		
		mav.setViewName("redirect:/cart/myCartList.do");
		return mav;
	}
	
	
	// 장바구니 목록 보기
	@RequestMapping(value="/myCartList.do", method=RequestMethod.GET)
	public ModelAndView myCartMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("memberInfo");

		ModelAndView mav = new ModelAndView();

		// 로그인 체크
		if (memberVO == null) {
			mav.setViewName("redirect:/member/loginForm.do");
			return mav;
		}

		String member_id = memberVO.getMember_id();
		CartVO cartVO = new CartVO();
		cartVO.setMember_id(member_id);
		
		// 1. 장바구니 VO 목록
		List<CartVO> myCartList = cartService.myCartList(cartVO);

		// 2. VO에 맞는 상품 정보 리스트
		List<GoodsVO> myGoodsList = new ArrayList<>();
		for (CartVO c : myCartList) {
			GoodsVO goods = goodsService.goodsDetail(String.valueOf(c.getGoods_id()));
			if (goods != null) {
				myGoodsList.add(goods);
			}
		}

		// 3. JSP에서 쓰는 cartMap 구조
		Map<String, Object> cartMap = new HashMap<>();
		cartMap.put("myCartList", myCartList);
		cartMap.put("myGoodsList", myGoodsList);
		
		mav.setViewName("/cart/myCartList");
		mav.addObject("cartMap", cartMap);
		return mav;
	}
	
	
	// 장바구니 상품 삭제
	@RequestMapping(value="/removeCartGoods.do", method=RequestMethod.POST)
	public ModelAndView removeCartGoods(@RequestParam("cart_id") int cart_id) throws Exception {
		cartService.removeCartGoods(cart_id);
		return new ModelAndView("redirect:/cart/myCartList.do");
	}
	
	
	// 장바구니 수량 수정
	@RequestMapping(value="/modifyCartQty.do", method=RequestMethod.POST)
	public ModelAndView modifyCartQty(@RequestParam("cart_id") int cart_id,
			                    @RequestParam("cart_goods_qty") int cart_goods_qty) throws Exception {
		CartVO cartVO = new CartVO();
		cartVO.setCart_id(cart_id);
		cartVO.setCart_goods_qty(cart_goods_qty);
		
		cartService.modifyCartQty(cartVO);
		return new ModelAndView("redirect:/cart/myCartList.do");
	}
}
