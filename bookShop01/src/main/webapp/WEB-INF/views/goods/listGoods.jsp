<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!-- goodsList: List<goodsVO> 를 가정 -->
<div class="wrap">
	<h2>상품 목록</h2>

	<c:choose>
		<c:when test="${not empty goodsList}">
			<table class="tbl">
				<thead>
					<tr>
						<th style="width: 80px;">ID</th>
						<th>제목</th>
						<th style="width: 120px;">정가</th>
						<th style="width: 120px;">판매가</th>
						<th style="width: 100px;">상태</th>
						<th style="width: 220px;">대표 이미지 파일명</th>
						<th style="width: 120px;">상세</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="g" items="${goodsList}">
						<tr>
							<td>${g.goods_id}</td>
							<td class="title">${g.goods_title}</td>
							<td><c:out value="${g.goods_price}" /></td>
							<td><c:out value="${g.goods_sales_price}" /></td>
							<td><c:out value="${g.goods_status}" /></td>
							<td><c:out value="${g.goods_fileName}" /></td>
							<td><a href="${ctx}/goods/detail?goods_id=${g.goods_id}">보기</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<div class="empty">표시할 상품이 없습니다.</div>
		</c:otherwise>
	</c:choose>
</div>

<style>
.wrap {
	padding: 16px;
}

h2 {
	margin: 0 0 12px;
	font-size: 18px;
}

.tbl {
	width: 100%;
	border-collapse: collapse;
	table-layout: fixed;
}

.tbl th, .tbl td {
	border: 1px solid #ddd;
	padding: 8px;
	vertical-align: middle;
}

.tbl thead th {
	background: #f5f5f5;
}

.tbl .title {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

.empty {
	padding: 24px;
	text-align: center;
	color: #777;
	border: 1px dashed #ccc;
}
</style>
