<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script>
function search_goods_list(fixedSearchPeriod){
	var formObj=document.createElement("form");
	var i_fixedSearch_period = document.createElement("input");
	i_fixedSearch_period.name="fixedSearchPeriod";
	i_fixedSearch_period.value=fixedSearchPeriod;
    formObj.appendChild(i_fixedSearch_period);
    document.body.appendChild(formObj); 
    formObj.method="get";
    formObj.action="${contextPath}/admin/goods/adminGoodsMain.do";
    formObj.submit();
}
</script>
</head>
<body>
	<h3>상품 조회</h3>
	<form method="post">	
		<table cellpadding="10" cellspacing="10">
			<tbody>
				<tr>
					<td>
						<input type="radio" name="r_search" checked/> 등록일로조회 &nbsp;&nbsp;&nbsp;
						<input type="radio" name="r_search"/> 상세조회 &nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<a href="javascript:search_goods_list('today')">
						   <img src="${contextPath}/resources/image/btn_search_one_day.jpg">
						</a>
						<a href="javascript:search_goods_list('one_week')">
						   <img src="${contextPath}/resources/image/btn_search_1_week.jpg">
						</a>
						<a href="javascript:search_goods_list('two_week')">
						   <img src="${contextPath}/resources/image/btn_search_2_week.jpg">
						</a>
						<a href="javascript:search_goods_list('one_month')">
						   <img src="${contextPath}/resources/image/btn_search_1_month.jpg">
						</a>
						<a href="javascript:search_goods_list('two_month')">
						   <img src="${contextPath}/resources/image/btn_search_2_month.jpg">
						</a>
						<a href="javascript:search_goods_list('three_month')">
						   <img src="${contextPath}/resources/image/btn_search_3_month.jpg">
						</a>
						<a href="javascript:search_goods_list('four_month')">
						   <img src="${contextPath}/resources/image/btn_search_4_month.jpg">
						</a>
					</td>
				</tr>
			</tbody>
		</table>
	</form>

	<div class="clear"></div>

	<table class="list_view" border="1" cellpadding="5" cellspacing="0" align="center">
		<thead>
			<tr style="background:#33ff00">
				<th>상품번호</th>
				<th>상품이름</th>
				<th>상품가격</th>
				<th>입고일자</th>
			</tr>
		</thead>
		<tbody align="center">
			<c:choose>
				<c:when test="${empty newGoodsList}">
					<tr>
						<td colspan="4" class="fixed">
							<strong>조회된 상품이 없습니다.</strong>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="item" items="${newGoodsList}">
						<tr>
							<td><strong>${item.goods_id}</strong></td>
							<td>
								<a href="${contextPath}/admin/goods/modifyGoodsForm.do?goods_id=${item.goods_id}">
									<strong>${item.goods_title}</strong>
								</a>
							</td>
							<td><strong>${item.goods_sales_price}</strong></td>
							<td><strong>${item.goods_creDate}</strong></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>

	<br><br><br>
	<h3>상품등록하기</h3>
	<div id="search">
		<form action="${contextPath}/admin/goods/addNewGoodsForm.do">
			<input type="submit" value="상품 등록하기">
		</form>
	</div>
</body>
</html>
