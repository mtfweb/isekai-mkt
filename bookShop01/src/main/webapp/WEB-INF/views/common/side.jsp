<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<aside class="cute-sidebar">
  <nav>
    <ul>
      <!-- 패션 -->
      <li>
        <h3>👗 패션</h3>
        <ul>
          <li><a href="${contextPath}/goods/listGoods.do?sort=men">남성의류</a></li>
          <li><a href="${contextPath}/goods/listGoods.do?sort=women">여성의류</a></li>
          <li><a href="${contextPath}/goods/listGoods.do?sort=shoes">신발</a></li>
          <li><a href="${contextPath}/goods/listGoods.do?sort=bag">가방/잡화</a></li>
        </ul>
      </li>

      <!-- 전자제품 -->
      <li>
        <h3>📱 전자제품</h3>
        <ul>
          <li><a href="${contextPath}/goods/listGoods.do?sort=mobile">모바일</a></li>
          <li><a href="${contextPath}/goods/listGoods.do?sort=pc">컴퓨터/노트북</a></li>
          <li><a href="${contextPath}/goods/listGoods.do?sort=tv">TV/가전</a></li>
          <li><a href="${contextPath}/goods/listGoods.do?sort=audio">음향기기</a></li>
        </ul>
      </li>

      <!-- 뷰티 -->
      <li>
        <h3>💄 뷰티</h3>
        <ul>
          <li><a href="${contextPath}/goods/listGoods.do?sort=cosmetics">화장품</a></li>
          <li><a href="${contextPath}/goods/listGoods.do?sort=skincare">스킨케어</a></li>
          <li><a href="${contextPath}/goods/listGoods.do?sort=perfume">향수</a></li>
        </ul>
      </li>

      <!-- 생활 -->
      <li>
        <h3>🍳 생활/주방</h3>
        <ul>
          <li><a href="${contextPath}/goods/listGoods.do?sort=kitchen">주방용품</a></li>
          <li><a href="${contextPath}/goods/listGoods.do?sort=living">리빙/인테리어</a></li>
          <li><a href="${contextPath}/goods/listGoods.do?sort=cleaning">청소/세탁</a></li>
        </ul>
      </li>

      <!-- 고객센터 -->
     
    </ul>
  </nav>
</aside>
