<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style>
  body {
    margin: 0;
    font-family: 'Nunito', sans-serif;
    background: radial-gradient(circle at top left, #fdfbfb, #f3e7f3, #e3f0ff);
  }

  /* 메인 전체 */
  #main {
    width: 100%;
    padding: 30px 40px;
    box-sizing: border-box;
  }

  #main h3 {
    font-size: 1.8em;
    margin: 30px 0 20px;
    color: #222;
    border-left: 5px solid #6a5acd;
    padding-left: 14px;
  }

  /* 상품 리스트: 좌우 꽉 차도록 */
  .goods-list {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
    gap: 25px;
    width: 100%;
  }

  /* 상품 카드 */
  .goods-item {
    background: #fff;
    border-radius: 12px;
    padding: 18px;
    text-align: center;
    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    transition: transform 0.3s ease, box-shadow 0.3s ease;
  }

  .goods-item:hover {
    transform: translateY(-6px);
    box-shadow: 0 8px 20px rgba(106,90,205,0.25);
  }

  .goods-item img {
    max-width: 100%;
    height: auto;
    border-radius: 6px;
    margin-bottom: 12px;
  }

  .goods-title {
    font-weight: 700;
    margin: 6px 0;
  }

  .goods-category {
    font-size: 0.9em;
    color: #777;
  }

  .goods-intro {
    font-size: 0.85em;
    color: #555;
    height: 36px;
    overflow: hidden;
  }

  .goods-price {
    font-size: 1em;
    font-weight: bold;
    color: #6a5acd;
    margin-top: 6px;
  }
</style>

<div id="main">
  <!-- 베스트 상품 -->
  <h3>베스트 상품</h3>
  <div class="goods-list">
    <c:forEach var="item" items="${goodsMap.bestseller}">
      <div class="goods-item">
        <a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id}">
          <img src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}" />
          <p class="goods-title">${item.goods_title}</p>
          <p class="goods-category">카테고리: ${item.goods_sort}</p>
          <p class="goods-intro">${item.goods_intro}</p>
          <p class="goods-price">
            <fmt:formatNumber value="${item.goods_sales_price}" type="number" /> 원
          </p>
        </a>
      </div>
    </c:forEach>
  </div>

  <!-- 신상품 -->
  <h3>신상품</h3>
  <div class="goods-list">
    <c:forEach var="item" items="${goodsMap.newbook}">
      <div class="goods-item">
        <a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id}">
          <img src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}" />
          <p class="goods-title">${item.goods_title}</p>
          <p class="goods-category">카테고리: ${item.goods_sort}</p>
          <p class="goods-intro">${item.goods_intro}</p>
          <p class="goods-price">
            <fmt:formatNumber value="${item.goods_sales_price}" type="number" /> 원
          </p>
        </a>
      </div>
    </c:forEach>
  </div>

  <!-- 스테디셀러 -->
  <h3>스테디셀러</h3>
  <div class="goods-list">
    <c:forEach var="item" items="${goodsMap.steadyseller}">
      <div class="goods-item">
        <a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id}">
          <img src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}" />
          <p class="goods-title">${item.goods_title}</p>
          <p class="goods-category">카테고리: ${item.goods_sort}</p>
          <p class="goods-intro">${item.goods_intro}</p>
          <p class="goods-price">
            <fmt:formatNumber value="${item.goods_sales_price}" type="number" /> 원
          </p>
        </a>
      </div>
    </c:forEach>
  </div>
</div>
