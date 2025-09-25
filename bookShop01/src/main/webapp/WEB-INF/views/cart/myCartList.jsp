<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="myCartList" value="${cartMap.myCartList}" />
<c:set var="myGoodsList" value="${cartMap.myGoodsList}" />

<c:set var="totalGoodsNum" value="0" />
<c:set var="totalDeliveryPrice" value="0" />
<c:set var="totalDiscountedPrice" value="0" />
<c:set var="totalGoodsPrice" value="0" />

<head>
<script type="text/javascript" src="${contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
function modify_cart_qty(goods_id, bookPrice, index){
   var length=document.frm_order_all_cart.cart_goods_qty.length;
   var _cart_goods_qty=0;
   if(length>1){ 
      _cart_goods_qty=document.frm_order_all_cart.cart_goods_qty[index].value;		
   }else{
      _cart_goods_qty=document.frm_order_all_cart.cart_goods_qty.value;
   }
   var cart_goods_qty=Number(_cart_goods_qty);
   $.ajax({
      type : "post",
      async : false,
      url : "${contextPath}/cart/modifyCartQty.do",
      data : {
         goods_id:goods_id,
         cart_goods_qty:cart_goods_qty
      },
      success : function(data) {
         if(data.trim()=='modify_success'){
            alert("수량을 변경했습니다!");
            location.reload();
         }else{
            alert("다시 시도해 주세요!");
         }
      },
      error : function() {
         alert("에러가 발생했습니다.");
      }
   });	
}

function delete_cart_goods(cart_id){
   var formObj=document.createElement("form");
   var i_cart = document.createElement("input");
   i_cart.name="cart_id";
   i_cart.value=cart_id;
   formObj.appendChild(i_cart);
   document.body.appendChild(formObj); 
   formObj.method="post";
   formObj.action="${contextPath}/cart/removeCartGoods.do";
   formObj.submit();
}

function fn_order_each_goods(goods_id,goods_title,goods_sales_price,fileName){
   var cart_goods_qty=document.getElementById("cart_goods_qty_"+goods_id).value;
   var formObj=document.createElement("form");
   formObj.method="post";
   formObj.action="${contextPath}/order/orderEachGoods.do";

   var i_goods_id = document.createElement("input"); i_goods_id.name="goods_id"; i_goods_id.value=goods_id;
   var i_goods_title = document.createElement("input"); i_goods_title.name="goods_title"; i_goods_title.value=goods_title;
   var i_goods_sales_price=document.createElement("input"); i_goods_sales_price.name="goods_sales_price"; i_goods_sales_price.value=goods_sales_price;
   var i_fileName=document.createElement("input"); i_fileName.name="goods_fileName"; i_fileName.value=fileName;
   var i_order_goods_qty=document.createElement("input"); i_order_goods_qty.name="order_goods_qty"; i_order_goods_qty.value=cart_goods_qty;

   formObj.appendChild(i_goods_id);
   formObj.appendChild(i_goods_title);
   formObj.appendChild(i_goods_sales_price);
   formObj.appendChild(i_fileName);
   formObj.appendChild(i_order_goods_qty);

   document.body.appendChild(formObj); 
   formObj.submit();
}

function fn_order_all_cart_goods(){
   var objForm=document.frm_order_all_cart;
   objForm.method="post";
   objForm.action="${contextPath}/order/orderAllCartGoods.do";
   objForm.submit();
}
</script>
</head>
<body>
<form name="frm_order_all_cart">
	<table class="list_view" border="1" width="100%" align="center">
		<tbody align=center>
			<tr style="background:#33ff00">
				<td>구분</td>
				<td colspan=2>상품명</td>
				<td>정가</td>
				<td>판매가</td>
				<td>수량</td>
				<td>합계</td>
				<td>주문</td>
			</tr>
			
			<c:choose>
			<c:when test="${ empty myCartList }">
				<tr>
					<td colspan=8><strong>장바구니에 상품이 없습니다.</strong></td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="item" items="${myGoodsList}" varStatus="cnt">
					<c:set var="cart_goods_qty" value="${myCartList[cnt.count-1].cart_goods_qty}" />
					<c:set var="cart_id" value="${myCartList[cnt.count-1].cart_id}" />
					<tr>
						<td>
							<input type="checkbox" name="checked_goods" checked value="${item.goods_id}">
						</td>
						<td>
							<a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id}">
								<img width="75" src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}" />
							</a>
						</td>
						<td>
							<h2><a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id}">${item.goods_title}</a></h2>
						</td>
						<td>${item.goods_price}원</td>
						<td>
							<strong>
								<fmt:formatNumber value="${item.goods_sales_price*0.9}" type="number" var="discounted_price"/>
								${discounted_price}원 (10%할인)
							</strong>
						</td>
						<td>
							<input type="text" id="cart_goods_qty_${item.goods_id}" name="cart_goods_qty" size=3 value="${cart_goods_qty}">
							<a href="javascript:modify_cart_qty(${item.goods_id},${item.goods_sales_price*0.9},${cnt.count-1});">
								<img width=25 src="${contextPath}/resources/image/btn_modify_qty.jpg">
							</a>
						</td>
						<td>
							<strong>
								<fmt:formatNumber value="${item.goods_sales_price*0.9*cart_goods_qty}" type="number" var="total_sales_price"/>
								${total_sales_price}원
							</strong>
						</td>
						<td>
							<a href="javascript:fn_order_each_goods('${item.goods_id}','${item.goods_title}','${item.goods_sales_price}','${item.goods_fileName}');">
								<img width="75" src="${contextPath}/resources/image/btn_order.jpg">
							</a><br>
							<a href="javascript:delete_cart_goods('${cart_id}');">
								<img width="75" src="${contextPath}/resources/image/btn_delete.jpg">
							</a>
						</td>
					</tr>
					<c:set var="totalGoodsPrice" value="${totalGoodsPrice + (item.goods_sales_price*0.9*cart_goods_qty)}" />
					<c:set var="totalGoodsNum" value="${totalGoodsNum+1}" />
				</c:forEach>
			</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
	<br><br>
	<table width="80%" class="list_view" align="center" style="background:#cacaff">
		<tbody>
			<tr align=center>
				<td>총 상품수</td>
				<td>총 상품금액</td>
				<td>총 배송비</td>
				<td>총 할인금액</td>
				<td>최종 결제금액</td>
			</tr>
			<tr align=center>
				<td>${totalGoodsNum}개</td>
				<td>
					<fmt:formatNumber value="${totalGoodsPrice}" type="number" var="total_goods_price"/>
					${total_goods_price}원
				</td>
				<td>${totalDeliveryPrice}원</td>
				<td>${totalDiscountedPrice}원</td>
				<td>
					<fmt:formatNumber value="${totalGoodsPrice+totalDeliveryPrice-totalDiscountedPrice}" type="number" var="total_price"/>
					${total_price}원
				</td>
			</tr>
		</tbody>
	</table>
	
	<center>
		<a href="javascript:fn_order_all_cart_goods()">
			<img width="75" src="${contextPath}/resources/image/btn_order_final.jpg">
		</a>
		<a href="${contextPath}/main/main.do">
			<img width="75" src="${contextPath}/resources/image/btn_shoping_continue.jpg">
		</a>
	</center>
</form>
</body>
