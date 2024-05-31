<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.kosta.sample.chart.EmpVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 
C3.js : d3기반에 쉽게 구현할 수 있게 만든 라이브러리
D3.js : 오픈소스 시각화를 위한 자바스크립트 라이브러리

부트스트랩에서 주는 예쁜 것을 쓸 것인지, 아님 C3꺼 사용하던지.

* https://cdnjs.com/libraries/c3
https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.min.js
https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.css
https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.js
https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.min.css
-->

<!-- Load c3.css -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.min.css" rel="stylesheet">

<!-- Load d3.js and c3.js   https://cdnjs.cloudflare.com/ajax/libs/d3/5.15.0/d3.js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/5.15.0/d3.js" charset="utf-8"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.min.js"></script>

</head>
<body>
여기에 차트<br>
<div id="my_chart_div">클릭해주세요</div><br>
<input id="restBtn" type="button" value="REST로 가져오기">

<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
// c3에서 가져온 차트
var chart = c3.generate({
    bindto: '#my_chart_div',
    data: {
      columns: [
        ['급여', 30, 200, 100, 400, 150, 250]
        //,['data2', 50, 20, 10, 40, 15, 25]
      ]
    }
});
</script>
<script>
$(function(){
	//$("#restBtn").click(  function(){
		$.ajax({
			method      : "POST",
	        url         : "<%=request.getContextPath()%>/EmpServlet",
	        contentsType : "application/x-www-form-urlencoded; charset-UTF-8",
       		//data 		: "",
	  		//dataType	: "json",	-- text로 주고 있기 때문에 설정 안해도 됨.
	  		/* -------- 서버로 부터 응답이 str로 오는 경우.
	  		success     : function(myval){ 	
	  										console.log("성공");
	  										// parse꼭 하기 -> 객체로 변경.
	  										var jsonObj = JSON.parse(myval)
	  										$.map(jsonObj, function(val, idx){
	  											console.log(val["ename"]+"\t"+val["sal"]+idx);
	  										});
       		error 	    : function(myval){ console.log("이건에러");   }, 
	  		*/	
	  		dataType	: "json",
			error 	    : function(resObject){ console.log("이건에러");   },
  			success     : function(resObject){ 	
											console.log("성공"+resObject);	// 객체로 넘어오는지 체크
  											var sal_arr = ['급여'];			// 보내는 데이터를 받아야 하는 기본 틀 잡아주는 역할. 앞에 '급여'가 있어서 먼저 arr형태로 생성.
											$.map(resObject, function(val, idx){
												console.log(val["sal"]+", "+val["ename"]+", "+idx);
												sal_arr.push(val["sal"]);	// map으로 sal_arr에 value를 하나씩 push처리함.
											});
											console.log(sal_arr);	// sal-arr 결과 확인.
											// 차트 리로드
											chart.load({	// chart 스크립트를 리로드 하기 위해 실행. 즉, 컬럼 값을 우리가 arr 생성해서 거기에 값을 입력해서 가져와서 뿌리는 것이다.
												columns: [
											        sal_arr
											      ]	
											});
											//console.log();
										}
		});
	//});
});

</script>
	
</body>
</html>