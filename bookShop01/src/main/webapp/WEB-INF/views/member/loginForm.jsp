<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	 isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
body {
  margin: 0;
  height: 100vh;
  background: linear-gradient(-45deg, #23a6d5, #23d5ab, #f0e130);
  background-size: 400% 400%;
  animation: auroraGradient 10s ease-in-out infinite;
  transition: background 0.5s;
  color: #666;
  font-family: "Dotum", Arial, serif;
  font-size: 1em;
  overflow-x: hidden;
  width: 100%;
}
@keyframes auroraGradient {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  } 
  100% {
    background-position: 0% 50%;
  }
}
#login_table {
  
  padding: 40px 40px 50px;
  border-radius: 12px;

  width: 360px;
  margin: 40px auto;
  font-family: 'Nanum Gothic', sans-serif;
  color: #333;
}

#login_table h3 {
  margin-bottom: 30px;
  font-weight: 700;
  font-size: 1.8rem;
  text-align: center;
  color: #5a3e99;
}

#login_table form table {
  width: 100%;
  border-collapse: separate;
  border-spacing: 0 18px;
}

#login_table .fixed_join {
  width: 80px;
  font-weight: 600;
  color: #666;
  font-size: 1em;
  vertical-align: middle;
}

#login_table input[type="text"],
#login_table input[type="password"] {
  width: 100%;
  padding: 12px 14px;
  font-size: 1rem;
  border: 1.8px solid #ccc;
  border-radius: 8px;
  transition: border-color 0.3s ease;
  box-sizing: border-box;
}

#login_table input[type="text"]:focus,
#login_table input[type="password"]:focus {
  border-color: #764ba2;
  outline: none;
  box-shadow: 0 0 6px #7b59c6aa;
}

#login_table input[type="submit"],
#login_table input[type="button"] {
  margin-top: 30px;
  width: 48%;
  padding: 12px 0;
  font-weight: 600;
  font-size: 1.1rem;
  border-radius: 24px;
  border: none;
  cursor: pointer;
  box-shadow: 0 4px 15px #764ba2cc;
  transition: background-color 0.3s ease;
  display: inline-block;
}

#login_table input[type="submit"] {
  background-color: #1b3552;
  color: white;
  margin: 0px auto 0;
  display: block;
  width: 50%;
  border-radius: 24px;
  border: none;
  cursor: pointer;
  box-shadow: none;
  transition: background-color 0.3s ease;
}

#login_table input[type="submit"]:hover {
  background-color: #5a3e99;
}

#login_table input[type="button"] {
  background-color: #eee;
  color: #555;
}

#login_table input[type="button"]:hover {
  background-color: #ddd;
}

#login_table .links {
  margin-top: 28px;
  font-size: 0.9rem;
  color: #555;
  text-align: center;
}

#login_table .links a {
  color: #764ba2;
  text-decoration: none;
  margin: 0 8px;
  transition: color 0.3s ease;
}
#login_table .links a:hover {
  color: #5a3e99;
}
</style>
<c:if test='${not empty message }'>
<script>
window.onload=function()
{
  result();
}

function result(){
	alert("아이디나  비밀번호가 틀립니다. 다시 로그인해주세요");
}
</script>
</c:if>
</head>
<body>
	<br><br><br><br><br>
	<DIV id="login_table">
	<form action="${contextPath}/member/login.do" method="post">
		<TABLE>
			<TBODY>
				<TR class="dot_line">
					<TD class="fixed_join">아이디</TD>
					<TD><input name="member_id" type="text" size="20" /></TD>
				</TR>
				<TR class="solid_line">
					<TD class="fixed_join">비밀번호</TD>
					<TD><input name="member_pw" type="password" size="20" /></TD>
				</TR>
			</TBODY>
		</TABLE>
		<br>
		<INPUT	type="submit" value="로그인"> 

		
		<Br>
		   <a href="#">아이디 찾기</a>  | 
		   <a href="#">비밀번호 찾기</a> | 
		   <a href="${contextPath}/member/addMember.do">회원가입</a>     
					   
	</form>
	</DIV>		
</body>
</html>