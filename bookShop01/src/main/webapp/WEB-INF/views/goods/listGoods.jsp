<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="wrap">
	<h2>상품 목록</h2>

	<c:choose>
		<c:when test="${not empty goodsList}">
			<div class="cards">
				<c:forEach var="g" items="${goodsList}">
					<div class="card">
						<a class="thumb" href="${ctx}/goods/detail?goods_id=${g.goods_id}"
							aria-label="${g.goods_title}"> <c:choose>
								<c:when test="${not empty g.goods_fileName}">
									<img loading="lazy"
										src="${ctx}/thumbnails.do?goods_id=${g.goods_id}&fileName=${g.goods_fileName}"
										onerror="this.onerror=null; this.src='${contextPath}/resources/image/no-image.png';"
										alt="${g.goods_title}"
										/ onerror="this.onerror=null; this.src='${contextPath}/resources/image/no-image.png';"
										decoding="async">
								</c:when>
								<c:otherwise>
									<img loading="lazy"
										src="${contextPath}/resources/image/no-image.png"
										alt="no image"
										/ onerror="this.onerror=null; this.src='${contextPath}/resources/image/no-image.png';"
										decoding="async">
								</c:otherwise>
							</c:choose>
						</a>

						<div class="body">
							<div class="title" title="${g.goods_title}">
								${g.goods_title}</div>

							<div class="price-row">
								<c:if test="${not empty g.goods_sales_price}">
									<div class="price">
										<fmt:formatNumber value="${g.goods_sales_price}" type="number" />
										원
									</div>
								</c:if>
								<c:if test="${not empty g.goods_price}">
									<div class="price-ori">
										<del>
											<fmt:formatNumber value="${g.goods_price}" type="number" />
											원
										</del>
									</div>
								</c:if>
							</div>

							<div class="meta">
								<span class="status-chip" data-status="${g.goods_status}">
									<c:out value="${g.goods_status}" />
								</span> <span class="file muted" title="${g.goods_fileName}"> <c:out
										value="${g.goods_fileName}" />
								</span>
							</div>

							<div class="actions">
								<a class="btn btn-view"
									href="${ctx}/goods/detail?goods_id=${g.goods_id}">상세보기</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:when>
		<c:otherwise>
			<div class="empty">
				<div class="empty-title">표시할 상품이 없습니다.</div>
				<div class="empty-sub">카테고리를 변경하거나 검색을 시도해 보세요.</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<style>
:root { -
	-bg: #f7f7fb; -
	-card: #fff; -
	-border: #e6e8ef; -
	-text: #111827; -
	-muted: #6b7280; -
	-accent: #6a5acd; -
	-shadow: 0 10px 24px rgba(17, 24, 39, .06);
}

* {
	box-sizing: border-box
}

.wrap {
	padding: 16px 20px;
	background: var(- -bg);
}

h2 {
	margin: 0 0 14px;
	font-size: 20px;
	color: var(- -text);
}

/* grid */
.cards {
	display: grid;
	grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
	gap: 16px;
}

/* card */
.card {
	background: var(- -card);
	border: 1px solid var(- -border);
	border-radius: 16px;
	overflow: hidden;
	box-shadow: var(- -shadow);
	display: flex;
	flex-direction: column;
	transition: transform .08s ease, box-shadow .2s ease;
}

.card:hover {
	transform: translateY(-2px);
	box-shadow: 0 14px 28px rgba(17, 24, 39, .10);
}

/* thumb: 3:2 비율 */
.thumb {
	position: relative;
	display: block;
	background: #f3f4f6;
}

.thumb::before {
	content: "";
	display: block;
	padding-top: 66.66%;
}

.thumb img {
	position: absolute;
	inset: 0;
	width: 100%;
	height: 100%;
	object-fit: cover;
}

/* body */
.body {
	padding: 12px;
	display: flex;
	flex-direction: column;
	gap: 10px;
}

.title {
	font-size: 14px;
	font-weight: 700;
	color: var(- -text);
	display: -webkit-box;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
	overflow: hidden;
}

/* price */
.price-row {
	display: flex;
	align-items: baseline;
	gap: 8px;
}

.price {
	color: #111827;
	font-weight: 800;
}

.price-ori {
	color: #9ca3af;
	font-size: 12px;
}

/* meta */
.meta {
	display: flex;
	align-items: center;
	gap: 8px;
}

.status-chip {
	display: inline-block;
	padding: 4px 8px;
	border-radius: 999px;
	font-size: 11px;
	font-weight: 700;
	letter-spacing: .2px;
	background: #eef2ff;
	color: #4f46e5;
	border: 1px solid #e0e7ff;
}

.status-chip[data-status="ON"] {
	background: #dcfce7;
	color: #166534;
	border-color: #bbf7d0;
}

.status-chip[data-status="OFF"] {
	background: #fee2e2;
	color: #991b1b;
	border-color: #fecaca;
}

.muted {
	color: var(- -muted);
	font-size: 11px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

/* actions */
.actions {
	margin-top: auto;
}

.btn {
	display: inline-block;
	padding: 9px 12px;
	border-radius: 10px;
	text-decoration: none;
}

.btn-view {
	width: 100%;
	text-align: center;
	background: var(- -accent);
	color: #fff;
	font-weight: 800;
	box-shadow: 0 6px 14px rgba(106, 90, 205, .22);
	transition: transform .06s ease, box-shadow .2s ease;
}

.btn-view:hover {
	transform: translateY(-1px);
	box-shadow: 0 10px 20px rgba(106, 90, 205, .28);
}

/* empty */
.empty {
	background: var(- -card);
	border: 1px dashed var(- -border);
	border-radius: 16px;
	padding: 36px 24px;
	text-align: center;
	color: var(- -muted);
	box-shadow: var(- -shadow);
}

.empty .empty-title {
	font-size: 16px;
	color: #374151;
	font-weight: 700;
	margin-bottom: 6px;
}

.empty .empty-sub {
	font-size: 13px;
}

/* responsive */
@media ( max-width :520px) {
	.cards {
		grid-template-columns: repeat(2, 1fr);
	}
}
</style>
