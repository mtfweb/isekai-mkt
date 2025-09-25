<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
    request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
  <title>상품 상세보기</title>
</head>
<body>
  <h2>${goodsMap.goodsVO.goods_title}</h2>

  <div>
    <p>정가:
      <fmt:formatNumber value="${goodsMap.goodsVO.goods_price}" type="number" /> 원
    </p>
    <p>판매가:
      <fmt:formatNumber value="${goodsMap.goodsVO.goods_sales_price}" type="number" /> 원
    </p>
    <p>상태: ${goodsMap.goodsVO.goods_status}</p>
    <p>소개: ${goodsMap.goodsVO.goods_intro}</p>
  </div>

  <div>
    <h3>상품 이미지</h3>
    <c:forEach var="img" items="${goodsMap.imageList}">
      <img src="${contextPath}/thumbnails.do?goods_id=${img.goods_id}&fileName=${img.fileName}" width="200" />
    </c:forEach>
  </div>

  <div>
    <form action="${contextPath}/cart/addGoodsInCart.do" method="post">
      <input type="hidden" name="goods_id" value="${goodsMap.goodsVO.goods_id}" />
      <label>수량:</label>
      <input type="number" name="cart_goods_qty" value="1" min="1" />
      <input type="submit" value="장바구니 담기" />
    </form>
  </div>

  <div>
    <h3>리뷰</h3>
    <c:if test="${not empty goodsMap.reviewList}">
      <c:forEach var="review" items="${goodsMap.reviewList}">
        <p>${review.writer} : ${review.content}</p>
      </c:forEach>
    </c:if>
    <c:if test="${empty goodsMap.reviewList}">
      <p>등록된 리뷰가 없습니다.</p>
    </c:if>
  </div>
</body>
</html>
