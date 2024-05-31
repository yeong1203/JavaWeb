<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>스트립트 반복문 연습</title>
	<!-- array : 배열 집합을 뜻함 == 스크립트에서! -->
	<script>
		// 기본 자바스크립트 문법으로 사용할 때, 이렇게 작성
	
		var arr = [{"empno":"7733", "ename":"smith"} , 
	        		{"empno":"7799", "ename":"king"}] ;
		
		// for문 : 순수 자바스크립트
		// for문 -- list > map의 key로 사용
		for(var i=0; i<arr.length; i++ ) {
			console.log(arr[i]["empno"]);	// list.map
		}
		
		// for문 -- list > list
		var arr2 = [ ["7733","smith"] , ["7799","king"] ] ;
		for(var i=0; i<arr2.length; i++ ) {
			console.log(arr2[i][0]);	// list.list
		}
		// foreach : 
		// foreach
		var arr3 = [ ["7733","smith"] , ["7799","king"] ] ;
		// 떼거지 데이터를 foreach하면서 루프 돌면서 하나씩 꺼내서 담아준다.
		arr3.forEach(function(val, idx){
			//console.log(val[1]);
			console.log(idx +", "+val[1]);
		});	
		
		// 함수생성
		// 얘가 하는 일 == 출력
		function myprint(prm) {
			console.log("myprint() 호출 "+ prm);
		}
		
		function myprint2(prm) {
			alert("myprint() 호출 : "+ prm);
			console.log("호출 완료");
		}
		
	</script>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		// $(document).ready (function() {	// 준비되면 안에 있는 기능 수행해라!
		$(function() {
			myprint("홍길동");	// 시작하자마자 실행.
			
			// javascript 사용 -- list.list	
			var arr4 = [ ["7733","smith"] , ["7799","king"] ] ;
			$.each(arr4, function(idx, val){
				//console.log(val[1]);
				console.log(idx +", "+val[1] + " ==== ; ");
				// function(idx, val) :: function에서 스크립트와 순서 서로 다름.
			});	
			
			// javascript 사용 -- list map
			var arr5 = [{"empno":"7733", "ename":"smith"} , 
        		{"empno":"7799", "ename":"king"}] ;
			$.map(arr5, function(val, idx) {
				console.log(idx+", "+ val["ename"]);	// map이기ㅣ 때문에  key로 값을 꺼낼 수 있다.
			});  // -- 여기까지는 실행만 되고 멈춘것. 이벤트 선택이 되어야 시행됨.
			
			
			// jQuery에서 일반 function 호출  -- 이벤트 시작시 진행.
			$("#myBtn").click ( function() {
				myprint2("제이쿼리 길동");
			});
			
			$("#myDiv").click ( function() {
				myprint2("제이쿼리 div");
			});
			// document에서 다 읽고 난 후에 참조해서 실행되어야 하기 때문에, 외부참조 안될 수 있기 때문에 ready 안에 꼭 사용하기.
		});
	</script>
</head>
<body>
	스크립트 반목문 연습 -- 순수 자바스크립트 문법
	<br>
	<a href="#" onClick="myprint2('홍길동')">스크립트 호출</a>
	<table border="1" width="200">
		<tr>
			<td style="cursor:pointer" onClick="myprint2('홍길동')">스트립트 호출</td>
	</table>
	<input type="button" id="myBtn" value="button">
	<div id="myDiv" style="cursor:pointer">div클릭</div>
	
</body>
</html>