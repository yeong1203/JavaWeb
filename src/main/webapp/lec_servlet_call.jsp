<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 	<a href="http://localhost:8081/board_servlet_url">서블릿 GET방식 호출</a><br>  -->
	<a href="/board_servlet_url">서블릿 GET방식 호출</a><br>
	<br><br>
	
	<!-- Get 방식 -->
	<form method="get" action="<%=request.getContextPath()%>/board_servlet_url">
		<input name="userid" type="text" ><br>
		<input type="submit" value="서블릿 get 방식 호출" />
	</form>

	
	<!-- post로 들어오는 것은 post라고 명시가 되어야 하는데, 받으려면 form태그의 method="post" 로 된 애들만 데이터로 들어온다. -->
	<!-- POST 방식 : post 보내는 방식은 이것 방법말고는 없다. / action="/board_servlet_url"는 이 주소로 보내는 것. -->
	<form method="post" action="<%=request.getContextPath()%>/board_servlet_url">
		<input name="userid" type="text" > <br>
		<input name="userpw" type="password" > <br>
		
		<input name="gen" type="radio" value="m">남 <br>
		<input name="gen" type="radio" value="f" >여 <br>
		<!-- radio는 네임으로 묶는것, 네임명이 다르면 서로 다른 것. -->
	
		<!-- 멀티 선택 가능한 checkbox /  -->
		<input name="habbit" type="checkbox" value="mount">등산<br>
		<input name="habbit" type="checkbox" value="fish">낚시<br>
		<input name="habbit" type="checkbox" value="music">음악<br>
		<input name="habbit" type="checkbox" value="art">미술<br>
		
		<!-- combobox라고도 함 = select -->
		<select name="subject">
			<option value="kor">국어</option>
			<option value="eng">영어</option>
			<option value="math">수학</option>
		</select>
		<br>
		
		<input name="userfile" type="file" > 파일선택 <br>		
		
		<!-- 다중 -->
		<textarea name="contents" row="10" cols="60" >
			내용입력
		</textarea><br>
		
		<input name="ssn" type="hidden" value="12345" > <br>
		<!-- hidden은 보여줘도 상관없고, 보안적요소도 아니고 사용자한테 입력받는건 아니고 서버로가야하는것 = 예제) 게시판 글번호(seq)같은것. -->
	
		<!-- submit 타입으로 윗 정보들을 보내는 것. 이방식말고도 스크립트로 보내는 일반전송도 있다. -->
		<input type="submit" value="서블릿 post 방식 호출" />
	</form>
	
</body>
</html>