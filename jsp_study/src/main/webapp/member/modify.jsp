<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>정보수정 Page</h1>
	<!--
		변경 x : id
		변경 : pw, email, phone
	 -->
	<!-- form 태그 선언 시 enctype="multipart/form-data" 얘가 있으면 수정데이터가 안감. 다 null값됨 -->
		<form action="/mem/update" method="post">
		<input type="hidden" name="id" value="${ses.id }">
			id : ${ses.id } <br>
			pwd : <br>
			<input type="password" name="pwd" value="${ses.pwd }" placeholder="write pwd.."><br>
			email : <br>
			<input type="text" name="email" value="${ses.email }" placeholder="write email.."><br>
			phone : <br>
			<input type="text" name="phone" value="${ses.phone }" placeholder="write phone.."><br>
			<button type="submit">수정</button>
		</form>
		<a href="/mem/delete"><button>회원 탈퇴</button></a>
</body>
</html>