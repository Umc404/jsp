<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Board Register Page</h3>
	<!-- 등록 / 수정 post -->
	<form action="/brd/insert" method="post">
		title   : <br><input type="text" name="title" placeholder="제목"><br>
		writer  : <br><input type="text" name="writer" placeholder="작성자"> <br>
		content : <br><textarea rows="10" cols="50" name="content" placeholder="내용을 입력하세요."></textarea><br>
		<button type="submit">등록</button>
	</form>
</body>
</html>