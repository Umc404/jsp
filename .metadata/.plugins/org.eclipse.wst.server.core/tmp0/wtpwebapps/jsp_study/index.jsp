<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><a href="../">Welcome to JSP World!</a></h1>
	<hr>
	<h5><a href="/mem/join">회원가입</a></h5>
	
	<div>
		<c:if test="${ses.id eq null }">
			<form action="/mem/login" method="post">
				id : <input type="text" name="id" placeholder="write id..">
				pwd : <input type="password" name="pwd" placeholder="write pwd..">
				<button type="submit">login</button> 
			</form>
		</c:if>
	</div>
	
	<div>
	<!-- 로그인 이후 나와야하는 정보 : ses 객체가-->
	<!-- eq : equals   ne : not equals -->
		<c:if test="${ses.id ne null }">
			${ses.id }님이 login 하였습니다.<br>
			계정생성일 : ${ses.regdate } / 마지막접속일 : ${ses.lastlogin }<br>
			<a href="/mem/modify"><button type="button">회원정보수정</button></a>
			<c:if test="${ses.id eq 'admin' }">
				<a href=""><button type="button">회원리스트</button></a>
			</c:if>
			<a href="/mem/logout"><button type="button">logout</button></a>
		</c:if>
	</div>
	
	<h3><a href="/brd/register">board 글쓰기 페이지로 이동</a></h3>
	<!-- jsp의 모든 경로는 controller로 이동되개 해야함. -->
	<h3><a href="/brd/list">board 리스트 페이지로 이동</a></h3>
	
	<script type="text/javascript">
		const msg_login = `<c:out value="${msg_login}"/>`;
		if(msg_login == '-1') {
			alert("로그인정보 불일치");
		}
	</script>
		
</body>
</html>