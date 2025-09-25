<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>새 상품 등록</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
  var cnt=0;
  function fn_addFile(){
    $("#d_file").append("<br><input type='file' name='detail_image" + cnt + "' />");
    cnt++;
  }
  
  function fn_add_new_goods(obj){
    var fileName = $('#f_main_image').val();
    if(fileName != null && fileName != undefined && fileName != ""){
      obj.submit();
    } else {
      alert("메인 이미지는 반드시 첨부해야 합니다.");
      return;
    }
  }
</script>    
<style>
  body {
    font-family: 'Arial', sans-serif;
    margin: 20px;
  }
  h1 {
    margin-bottom: 20px;
  }
  table {
    border-spacing: 10px;
  }
  td {
    padding: 5px;
  }
  input[type=text], select, textarea {
    width: 400px;
    padding: 5px;
  }
</style>
</head>

<body>
<form action="${contextPath}/admin/goods/addNewGoods.do" 
      method="post" enctype="multipart/form-data">
  <h1>새 상품 등록</h1>
  
  <table>
    <tr>
      <td width="200">카테고리</td>
      <td>
        <select name="goods_sort" required>
          <option value="" selected>-- 선택 --</option>
          <option value="men">남성의류</option>
          <option value="women">여성의류</option>
          <option value="shoes">신발</option>
          <option value="bag">가방/잡화</option>
          <option value="mobile">모바일</option>
          <option value="pc">컴퓨터/노트북</option>
          <option value="tv">TV/가전</option>
          <option value="audio">음향기기</option>
          <option value="cosmetics">화장품</option>
          <option value="skincare">스킨케어</option>
          <option value="perfume">향수</option>
          <option value="kitchen">주방용품</option>
          <option value="living">리빙/인테리어</option>
          <option value="cleaning">청소/세탁</option>
          <option value="food">식품</option>
        </select>
      </td>
    </tr>
    <tr>
      <td>상품 이름</td>
      <td><input name="goods_title" type="text" required /></td>
    </tr>
    <tr>
      <td>정가</td>
      <td><input name="goods_price" type="text" required /></td>
    </tr>
    <tr>
      <td>판매가</td>
      <td><input name="goods_sales_price" type="text" required /></td>
    </tr>
    <tr>
      <td>배송비</td>
      <td><input name="goods_delivery_price" type="text" required /></td>
    </tr>
    <tr>
      <td>배송 예정일</td>
      <td><input name="goods_delivery_date" type="date" required /></td>
    </tr>
    <tr>
      <td>상태</td>
      <td>
        <select name="goods_status">
          <option value="bestseller">베스트셀러</option>
          <option value="steadyseller">스테디셀러</option>
          <option value="newbook" selected>신상품</option>
          <option value="on_sale">판매중</option>
          <option value="buy_out">품절</option>
        </select>
      </td>
    </tr>
    <tr>
      <td>상품 소개</td>
      <td><textarea name="goods_intro" rows="5"></textarea></td>
    </tr>
    <tr>
      <td>메인 이미지</td>
      <td>
        <input type="file" name="main_image" id="f_main_image" required />
        <br><small style="color:gray;">※ 반드시 등록해야 합니다</small>
      </td>
    </tr>
    <tr>
      <td>추가 이미지</td>
      <td>
        <input type="button" value="파일 추가" onclick="fn_addFile()" />
        <div id="d_file"></div>
      </td>
    </tr>
  </table>
  
  <br>
  <center>
    <input type="button" value="상품 등록하기" onclick="fn_add_new_goods(this.form)" />
  </center>
</form>	 
</body>
</html>
