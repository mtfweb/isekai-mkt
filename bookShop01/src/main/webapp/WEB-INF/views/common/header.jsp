<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<style>
  body {
    margin: 0;
    font-family: 'Nunito', sans-serif;
    color: #333;
    background: #fdfdfd;
    overflow-x: hidden;
  }

  /* ===== Sticky Header Wrapper ===== */
  .sticky-header {
    position: sticky;
    top: 0;
    z-index: 1000;
    width: 100%;
  }

  /* ===== 상단 헤더 (로고 + 링크) ===== */
  header {
    width: 100%;
    display: flex;
    background: rgba(255,255,255,0.95);
    box-shadow: 0 1px 4px rgba(0,0,0,0.08);
    padding: 6px 24px;
    align-items: center;
    justify-content: space-between;
    box-sizing: border-box;
  }

  /* 로고 */
  #logo img {
    height: 38px;
    cursor: pointer;
    transition: transform 0.2s ease;
  }
  #logo img:hover {
    transform: scale(1.05);
  }

  /* 상단 링크 */
  #head_link ul {
    list-style: none;
    margin: 0;
    padding: 0;
    display: flex;
    gap: 14px;
  }
  #head_link ul li a {
    color: #444;
    text-decoration: none;
    font-weight: 600;
    font-size: 0.85em;
  }
  #head_link ul li a:hover {
    color: #6a5acd;
  }

  /* ===== 하단 (카테고리 + 검색창) ===== */
  .category-search-bar {
    width: 100%;
    background: rgba(255,255,255,0.95);
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 6px 24px;
    box-shadow: 0 1px 3px rgba(0,0,0,0.05);
    box-sizing: border-box;
  }

  /* 카테고리 */
  .categories {
    display: flex;
    gap: 24px;
  }
  .categories a {
    color: #333;
    text-decoration: none;
    font-size: 0.95em;
    font-weight: 600;
  }
  .categories a:hover {
    color: #6a5acd;
  }

  /* 검색창 */
  .search-box {
    position: relative;
    width: 260px;
    flex-shrink: 0;
  }
  .search-box input {
    width: 100%;
    padding: 6px 36px 6px 12px;
    border-radius: 20px;
    border: 2px solid #6a5acd;
    font-size: 0.9em;
    outline: none;
  }
  .search-box input:focus {
    border-color: #40c9ff;
    box-shadow: 0 0 4px rgba(64, 201, 255, 0.5);
  }
  .search-box button {
    position: absolute;
    right: 10px;
    top: 50%;
    transform: translateY(-50%);
    border: none;
    background: transparent;
    cursor: pointer;
    padding: 0;
  }
  .search-box button svg {
    fill: #6a5acd;
    width: 16px;
    height: 16px;
  }
</style>
</head>

<body>
  <div class="sticky-header">
    <header>
      <div id="logo">
        <a href="${contextPath}/main/main.do">
          <img alt="logo" src="${contextPath}/resources/image/generated-image.png">
        </a>
      </div>

      <div id="head_link">
        <ul>
          <c:choose>
            <c:when test="${isLogOn==true and not empty memberInfo }">
              <li><a href="${contextPath}/member/logout.do">로그아웃</a></li>
              <li><a href="${contextPath}/mypage/myPageMain.do">마이페이지</a></li>
              <li><a href="${contextPath}/cart/myCartList.do">장바구니</a></li>
              <li><a href="#">주문배송</a></li>
            </c:when>
            <c:otherwise>
              <li><a href="${contextPath}/member/loginForm.do">로그인</a></li>
              <li><a href="${contextPath}/member/memberForm.do">회원가입</a></li>
            </c:otherwise>
          </c:choose>
          <li><a href="#">고객센터</a></li>
          <c:if test="${isLogOn==true and memberInfo.member_id =='admin' }">
            <li><a href="${contextPath}/admin/goods/adminGoodsMain.do">관리자</a></li>
          </c:if>
        </ul>
      </div>
    </header>

    <div class="category-search-bar">
      <div class="categories">
        <a href="#">식품</a>
        <a href="#">패션</a>
        <a href="#">가전</a>
        <a href="#">도서</a>
        <a href="#">생활용품</a>
      </div>

      <div class="search-box">
        <form name="frmSearch" action="${contextPath}/goods/searchGoods.do">
          <input name="searchWord" type="text" placeholder="검색어를 입력하세요">
          <button type="submit">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
              <path d="M21.53 20.47l-4.69-4.69A7.92 7.92 0 0018 10a8 8 0 10-8 8 7.92 7.92 0 005.78-2.16l4.69 4.69a.75.75 0 101.06-1.06zM10 16a6 6 0 110-12 6 6 0 010 12z"/>
            </svg>
          </button>
        </form>
      </div>
    </div>
  </div>
</body>
</html>
