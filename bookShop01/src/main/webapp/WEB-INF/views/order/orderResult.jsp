<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<body>
  <h1>1. 최종 주문 내역서</h1>
  <table class="list_view" border="1" cellspacing="0" cellpadding="8" align="center">
    <thead>
      <tr style="background: #33ff00">
        <th>주문번호</th>
        <th colspan="2">주문상품명</th>
        <th>수량</th>
        <th>주문금액</th>
        <th>배송비</th>
        <th>예상적립금</th>
        <th>주문금액합계</th>
      </tr>
    </thead>
    <tbody align="center">
      <c:forEach var="item" items="${myOrderList}">
        <tr>
          <td>${item.order_id}</td>
          <td class="goods_image">
            <a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id}">
              <img width="75" alt="" src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
            </a>
          </td>
          <td>
            <h2>
              <a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id}">
                ${item.goods_title}
              </a>
            </h2>
          </td>
          <td><h2>${item.order_goods_qty} 개</h2></td>
          <td><h2>${item.order_goods_qty * item.goods_sales_price} 원</h2></td>
          <td><h2>0 원</h2></td>
          <td><h2>${1500 * item.order_goods_qty} 원</h2></td>
          <td><h2>${item.order_goods_qty * item.goods_sales_price} 원</h2></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

  <br><br>
  <h1>2. 배송지 정보</h1>
  <table border="1" cellspacing="0" cellpadding="6" width="70%">
    <tbody>
      <tr><td>배송방법</td><td>${myOrderInfo.delivery_method}</td></tr>
      <tr><td>받으실 분</td><td>${myOrderInfo.receiver_name}</td></tr>
      <tr><td>휴대폰번호</td>
          <td>${myOrderInfo.receiver_hp1}-${myOrderInfo.receiver_hp2}-${myOrderInfo.receiver_hp3}</td></tr>
      <tr><td>유선전화(선택)</td>
          <td>${myOrderInfo.receiver_tel1}-${myOrderInfo.receiver_tel2}-${myOrderInfo.receiver_tel3}</td></tr>
      <tr><td>주소</td><td>${myOrderInfo.delivery_address}</td></tr>
      <tr><td>배송 메시지</td><td>${myOrderInfo.delivery_message}</td></tr>
      <tr><td>선물 포장</td><td>${myOrderInfo.gift_wrapping}</td></tr>
    </tbody>
  </table>

  <br><br>
  <h2>주문 고객 정보</h2>
  <table border="1" cellpadding="6">
    <tbody>
      <tr><td>이름</td><td><input type="text" value="${orderer.member_name}" disabled></td></tr>
      <tr><td>핸드폰</td><td><input type="text" value="${orderer.hp1}-${orderer.hp2}-${orderer.hp3}" disabled></td></tr>
      <tr><td>이메일</td><td><input type="text" value="${orderer.email1}@${orderer.email2}" disabled></td></tr>
    </tbody>
  </table>

  <br><br>
  <h1>3. 결제정보</h1>
  <table border="1" cellpadding="6">
    <tbody>
      <tr><td>결제방법</td><td>${myOrderInfo.pay_method}</td></tr>
      <tr><td>결제카드</td><td>${myOrderInfo.card_com_name}</td></tr>
      <tr><td>할부기간</td><td>${myOrderInfo.card_pay_month}</td></tr>
    </tbody>
  </table>

  <br><br>
  <center>
    <a href="${contextPath}/main/main.do">
      <img width="120" alt="쇼핑 계속하기" src="${contextPath}/resources/image/btn_shoping_continue.jpg">
    </a>
  </center>
</body>
