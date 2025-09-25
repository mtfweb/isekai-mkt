<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"
    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<head>
 <title>검색 상품 목록 페이지</title>
</head>
<body>
	<hgroup>
		<h1>검색 결과</h1>
	</hgroup>

	<!-- 🔥 관련 상품(Carousel) 부분 제거됨 -->

	<div class="clear"></div>

	<div id="sorting">
		<ul>
			<li><a class="active" href="#">베스트 셀러</a></li>
			<li><a href="#">최신 출간</a></li>
			<li><a href="#">최근 등록</a></li>
		</ul>
	</div>

	<table id="list_view">
		<tbody>
		  <c:forEach var="item" items="${goodsList}"> 
			<tr>
				<td class="goods_image">
					<a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id}">
					    <img width="75" alt="" src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
					</a>
				</td>
				<td class="goods_description">
					<h2>
						<a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id}">
							${item.goods_title}
						</a>
					</h2>
					<div class="writer_press">
						상품 소개: ${item.goods_intro}
					</div>
				</td>
				<td class="price">
					<span>${item.goods_price}원</span><br>
					<strong>
						<fmt:formatNumber value="${item.goods_price*0.9}" type="number" var="discounted_price" />
				        ${discounted_price}원
					</strong><br>(10% 할인)
				</td>
				<td><input type="checkbox" value=""></td>
				<td class="buy_btns">
					<ul>
						<li><a href="#">장바구니</a></li>
						<li><a href="#">구매하기</a></li>
						<li><a href="#">비교하기</a></li>
					</ul>
				</td>
			</tr>
		  </c:forEach>
		</tbody>
	</table>

	<div class="clear"></div>

	<div id="page_wrap">
		<ul id="page_control">
			<li><a class="no_border" href="#">Prev</a></li>
			<c:set var="page_num" value="0" />
			<c:forEach var="count" begin="1" end="10" step="1">
				<c:choose>
					<c:when test="${count==1}">
						<li><a class="page_contrl_active" href="#">${count+page_num*10}</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="#">${count+page_num*10}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li><a class="no_border" href="#">Next</a></li>
		</ul>
	</div>
</body>
