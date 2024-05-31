<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><!-- head는 이 페이지 열릴 때 사전 준비하는 곳!-->
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<!-- Javascript 테스트 -->
	<script src="js/my.js"></script>
	<script>
	//	public int myprint(int a, int b ) { syso.print... }
		function myprint(a, b) {
			alert(a+b);
			console.log(a+b);
		}
	</script>
	
	<!-- CSS 테스트 -->
	<link href="css/my.css" rel="stylesheet" />
 	<style>
		p {
		  color: red;
		  font-weight: bold;
		}
	</style>
	
	<!-- JQuery  테스트  
	-- $( document ).ready(function() { } );	// $( document ).ready 시작. 생략가능!
	-- $(                  function() { } );
	
	-- id    = "abc"  $("#abc")
	-- class = "abc"  $(".abc")
	-->
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script> <!--  -->
	<script>
		$(function() {
			// $("p").text = "text 변경!!!";		-- 이건 p 태그의 css 속성을 변경하는 것.
			// $("p").text("text 변경!!!");/
			$("#PTAG_ID").text("text 변경- #PTAG_ID");	
			// $(".PTAG_CLASS").text("text 변경-.PTAG_CLASS");
			$("#PTAG_ID_1").text("text 변경-#PTAG_ID");
			$("#PTAG_ID_2").text("text 변경-#PTAG_ID");
			// 한번에 수정처리할 때는 #id 사용해서 일괄 처리하는 것이 좋다.
			// class는 각각 처리해야하기 때문에. 용도에 맞춰 사용하면 좋다.
			
			// name 으로 넣는 방법
			// $("#uname").val("hong");		// id로 접근 < uname에 값 넣기 == value="hong" >
			// alert( $("#uname").val() );	// class로 접근 < var()는 해당 아이디의 값을 가져오는 것.>
			// $("input[name='uname']").val("hong");		// name으로 접근 < 비추천!! 사용 X >
			$("#uname").val("hong");
			
			// var msg = $("#seq").val();		// seq 의 값 가져오기
			// alert(msg);

			
			// 클릭이 되면. input처리 :: uname이 null이 아닐경우,
			$("#regdtn").click(function() {
				if($("#uname").val() == "") {
					alert("id를 입력하세요");
					$("#uname").focus();
					return false;
				} else if ($("#passwd").val() == null) {
					alert("password를 입력하세요");
					$("#passwd").focus();
					return false;
				}
				
				// 직접 지정가능.
				$("#loginForm").attr("method","post");
				$("#loginForm").attr("action","\BoardServlet");
				
				$("#loginForm").submit();		// form 태그에 submit
				return true;
			});
			
			
		})
	</script>

	
</head>
<body>

	<!-- form태그에도 이름이 필요하다. / java는 안에 submit하면 form 통으로 보내기 때문에 이름이 필요 없었다. -->
	
	<form name="loginForm" id="loginForm" >
		hidden : <input type="hidden" name="seq" id="seq" value="12345"><br>
		uname: <input type="text" name="uname" id="uname" class="uname"><br>
		password: <input type="password" name="passwd" id="passwd" class="passwd"><br>
		<input type="button" name="regbtn" id="regdtn" class="regbtn" value="가입">
	</form>



	<p id="PTAG_ID">Not loaded yet.1111</p> <!-- ID 값은 고유해야 한다. -->
	<br>
	<p id="PTAG_ID_1" class="PTAG_CLASS">AAAA</p> <!-- 일괄로 처리해야 할 경우, class로 묶음. -->
	<p id="PTAG_ID_2" class="PTAG_CLASS">BBBB</p> 
	
	<!-- Java 호출
	import webapp.js.all;  
	lec_script ls = new lec_script();
	ls.myprint(5,3);
	-->

	<!-- JavaScript 호출 
		<script src="webapp/   js/scripts.js"></script> 이렇게 말고 webapp은 제외.
		<script src="js/scripts.js"></script>
	-->
	
	<a href="#" onClick="myprint(5,3)">내부 js 클릭</a>
	<div onClick="myprint(5,3)">내부 js div 클릭</div>
	<a href="#" onClick="myjsPrint()">외부 my.js 클릭</a>

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<!-- 
	- form 태그(안에 input... 이런것들 요소) 제어.
	- 조건문(if) 
	- 반복문(for, foreach)
	- 전송 (Ajax)
	jquery 배울예정. 
	-->

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
</body>
</html>