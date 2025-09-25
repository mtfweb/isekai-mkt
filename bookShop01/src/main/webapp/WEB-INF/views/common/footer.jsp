<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style>
  /* 푸터 고정 스타일 */
  #footer {
    position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
    background: #333;
    color: #fff;
    text-align: center;
    padding: 15px 0;
    font-size: 14px;
    z-index: 999;
  }

  #footer a {
    color: #fff;
    text-decoration: none;
    margin: 0 8px;
  }
  #footer a:hover {
    text-decoration: underline;
  }
</style>

<div id="footer">
  <p>
    ⓒ 2025 이세계 마켓. All Rights Reserved. | 
    <a href="${contextPath}/about/company.do">회사소개</a> | 
    <a href="${contextPath}/about/policy.do">이용약관</a> | 
    <a href="${contextPath}/about/privacy.do">개인정보처리방침</a> | 
    <a href="${contextPath}/customer/contact.do">고객센터</a>
  </p>
</div>
