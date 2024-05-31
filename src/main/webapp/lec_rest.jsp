<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
	<REST 서비스를 활용한...! 이거 들어가면 무조건 알고 있어야 하는 정보>
	
	REST(Representational State Transfer)이란?
		: 웹서비스를 위한 분산 시스템의 아키텍처
			- http 프로토콜
			- 데이터 포맷 : json / xml 을 주로 사용.
			- 개인정보 데이터 접근을 위해서는 토큰(OAuth2)를 이용함. (-> 메뉴 어디어디 사용했는지, 데이터표현을 위해 JSON을 사용했습니다. JQUERY의 AJAX를 사용했습니다)
			- 서버(프로바이터=데이터 주는쪽)가 정한 규칙에 맞게 요청을 해야 응답(한가지)을 받을 수 있다.
			ex) kakao 사용자 목록 가져오기.
				https://kapi.kakao.com/v1/user/lids (url)		=> 어떤 방식으로 호출 할 것인지를 다 규약되어 있음.
				${SERVEICE_APP_ADMIN_KEY}		// 필수조건 = 키
				Content-type: application/x-www-form-urlencoded;charset=utf-8	// UTF-8에 String으로 보내라 하는 것.
				
	* 비동기 통신이란? 순서를 기다리지 않고 보장되지 않음
	// 자바 클래스를 사용해서 자바 api, 커넥트 api로도 가져다 사용할 수 있다. == AJAX통신
	AJAX : 스크립트에서 비동기 통신을 위한 기술 (jQuery 스크립트 라이브러리) = jQuery 스크립트 라이브러리이며, 비동기 통신이다.
		 : get 방식 / post 방식 지원.=> 아마 서비스를 브라우저를 통해 받을 것이다.
		 : put/delete ... 가능.
		 : JSON/XML/TEXT/... 데이터 표기를 사용해 데이터 송수신.
		 : 기본 작성 문법 == $(JQuery).ajax(url,[setting]); -- Object 타입으로 사용 가능.
		 
		 $.ajax( { key : val } );	== ajax 사용방법.
		 $.ajax( { settings } ) ;
		 $.ajax({
		 	//accepts 		: {key : val},
		 	//async 		: false,
		 	*method 		: get(default)/post (브라우저에서만 사용) , 		// 버전 1.9 부터 사용, type 대신 이젠 method! 통신으로 주고 받기만 할 것이면 put, get, post, delete, demd
		 	*url			: "/BoardServlet?pagecode=B002"		// 
		 	beforSend 		: function(){ } 					// 보내기전 해야될 일. function 사용가능,
		 	complete 		: function(){ }						// 갔다와서 할일.
		 	*error  		: function() {  },
		 	*success		: function(okval) { okval };
		 	crossDomain default = false							// rest는 내가 요청을 날리면 응답을 내가 받아야한다. crossDomain 허용을 해야 다른 곳으로 응답을 받을 수 있다.
		 	headers 		: { key, value },  -- 인증할 때,
		 	// 우리가 보내는 것 : contentsType => data가 뭔지는 보내야함. 이것은 필수!
		 	contentsType 	: 'application/x-www-form-urlencoded; charset-UTF-8";  // -- 보낼 데이터 타입 설정.(내가 보낸 data 타입)	기본값이 저것이라 작성할 필요 없다. ( 스트링으로 주세요! 하는 것! == application/x-www-form-urlencoded; )	 	
		 				  	:  'application/json; charset-UTF-8"; 	// 모양만 json , 보내는 타입은 String 
		 	*data 			:{ "name" : "kim", "pw" : "123" }	=> data는 알아서 컴버팅해줌.
		 		 			:{ "name" ["kim","hong"] }		
		 		 			: "nam,e=kim&pw=123"	
		 	*** data에서 JSON표기 : 내부적으로 name=kim&pw=123 == 즉, 내가 JSON을 보내도 나갈 때는 쿼리(스트링)으로 나가진다!!!
		 					객체적으로 꼭 보내고 싶을 땐,  contentsType : 'application/json; charset-UTF-8";
		 	//서버가 보내는 것은 dataType : WAS, 브라우저가 
		 	dataType  		: (xml, json, script, or html, text) 	// -- 서버가 알아서 선택해준다.// 서버로부터 온 응답데이터 타입 , 즉, 서버꺼
			timeout			: number 		 	
		 });
 -->
 
<!--  ##################### 서버로 부터의 응답 String #######################  -->
<h2>초간단 AJAX <font color=red>(F12필수)</font></h2>
<!-- <form id="Str_Str_Form" action="/RestServlet" method="get"> ajax통신에서 지정해주기 때문에 action, method 처리 필요없음 -->
<form id="Str_Str_Form">
<input id="searchGubun" type="hidden" name="searchGubun" value="" > <!-- dumy 값이며, 구분자 -->
<input id="searchStr" type="text" name="searchStr">
<input type="button" id="AjaxBtn" value="초간단AJAX전송">
</form>
<hr><br><br>
<div id="resultDIV"></div>



<!--  ##################### 서버로 부터의 응답 String #######################  -->
<h2>서버로 부터의 응답 String <font color=red>(F12필수)</font></h2>
<form id="Str_Str_Form1">
<select name="searchGubun">
	<option value="title">제목</option>
	<option value="contents">내용</option>
	<option value="regid">글쓴이</option>
</select>
<input type="text" name="searchStr">
<input type="button" id="1__Str_Str_Btn" value="1.Str-Str">
</form>
<hr>

<form id="JsonStr_Str_Form" action="/RestServlet" method="get">
<select name="searchGubun">
	<option value="title">제목</option>
	<option value="contents">내용</option>
	<option value="regid">글쓴이</option>
</select>
<input type="text" name="searchStr">
<input type="button" id="2__JsonStr_Str_Btn"  value="2.JsonStr-Str">
</form>
<hr>

<form id="Json_Str_Form" action="/RestServlet" method="get">
<select name="searchGubun">
	<option value="title">제목</option>
	<option value="contents">내용</option>
	<option value="regid">글쓴이</option>
</select>
<input type="text" name="searchStr">
<input type="button" id="3__Json_Str_Btn"  value="3.Json-Str">
</form>
<hr><br><br>

<!--  ##################### 서버로 부터의 응답 JSON #######################  -->
<h2>서버로 부터의 응답 JSON <font color=red>(F12필수)</font></h2>
<form id="Str_Json_Form">
<select name="searchGubun">
	<option value="title">제목</option>
	<option value="contents">내용</option>
	<option value="regid">글쓴이</option>
</select>
<input type="text" name="searchStr">
<input type="button" id="4__Str_Json_Btn"  value="1.Str-JSON">
</form>
<hr>

<form id="JsonStr_Json_Form" action="/RestServlet" method="get">
<select name="searchGubun">
	<option value="title">제목</option>
	<option value="contents">내용</option>
	<option value="regid">글쓴이</option>
</select>
<input type="text" name="searchStr">
<input type="button" id="5__JsonStr_Json_Btn"  value="2.JsonStr-JSON">
</form>
<hr>

<form id="Json_Json_Form" action="/RestServlet" method="get">
<select name="searchGubun">
	<option value="title">제목</option>
	<option value="contents">내용</option>
	<option value="regid">글쓴이</option>
</select>
<input type="text" name="searchStr">
<input type="button" id="6__Json_Json_Btn"   value="3.Json-JSON">
</form>
<hr>


<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
	//$(function() {    });
		$("#AjaxBtn").click(  function(){
		//alert("초간단AJAX");
		var sg = $("#searchGubun").val();
		var ss = $("#searchStr").val();
		$.ajax({
			method      : "GET",
	        url         : "<%=request.getContextPath()%>/RestServlet?pagecode=R001",
	        data 		: "searchGubun="+sg+"&searchStr="+ss,  // key=val 형식은 get 
	  		error 	    : function(myval){ console.log("이건에러:" + myval);   }, // function() = 서버는 괄호 안에 넣어주세요!	// 아무 변수나 넣어주면 된다.
	  		success     : function(myval){ // callback 호출 방식.
	  										console.log("이건성공:" + myval);   
	  										$("#resultDIV").html("<b>"+myval+"</b>") // 특정 영역에다 html에 추가 처리한 것.
	  									 }
		});
	});

	// String_JsonStr_JSON * (String, JSON)  총 6가지 방법! 05/28 배울 예정.
	// searchGubun=contents&searchStr=%E3%85%81%E3%84%B4%E3%85%81%E3%84%B4 :: 한글 인코딩한 것. ==> 궁금하면 인코딩 디코딩 변환기로 볼 수 있다.(%E3%85%81%E3%84%B4%E3%85%81%E3%84%B4) , 인코딩 -> caractor set에 맞게 바이트로 변환하는것. 한글이 2byte라서 (아스키코드형태)1byte 처리한 것.
	// searchGubun=contents&searchStr=abc
	// 디코딩은 다시 원래대로 바꿔주는 것.
	$("#1__Str_Str_Btn").click(  function(){
		alert("1");
		var sendFormData = $("#Str_Str_Form1").serialize();	// .serialize() 는 key=value 처리를 해주기 때문에 하나하나 처리할 필요없다.
		console.log(sendFormData);
		$.ajax({
			method      : "GET",
	        url         : "<%=request.getContextPath()%>/RestServlet?pagecode=R001",
	        data 		: sendFormData,		// searchGubun=contents&searchStr=abc
	  		error 	    : function(myval){ console.log("이건에러:" + myval);   }, // function() = 서버는 괄호 안에 넣어주세요!	// 아무 변수나 넣어주면 된다.
	  		success     : function(myval){ 	
	  										// JSON 모양의 글자가 들어온 것. 
	  										console.log("성공 :" + myval);	// 정말 객체라면 토글로 표현됨. 그러나 그냥 출력. String처리 되어있음. => get으로 접속하려면 객체로 변형해야함.
	  										
	  										// : JSON.parse() == script꺼!
	  										// JSON모양의 글자를 객체 JSON으로 변환
	  										myval_obj = JSON.parse(myval);	// 글자를 JSON.parse(myval) 객체로 변경.
	  										console.log("변환 성공 : "+myval_obj);
	  										console.log("변환 성공 : "+myval_obj[0]["regid"])				
 	  										
	  										// https://api.jquery.com/jQuery.map/
											// [{"seq":0,"title":"aaa","regid":"kim"},{"seq":0,"title":"bbb","regid":"hong"}]
	  										// $.map( myval_obj, function( val, idx ) {	// 변수명은 아무거나! 내가 알아볼 수 있게!
											   //console.log(val + ", "+ idx);
	  											//console.log(val["title"] + ", "+ val["regid"] + idx);
											// }); 
	  										// 테이블로 보여질 문장을 만들어 내야함.
	  										var htmlStr = "<table border=1 width=50% ><tr><th>제목</th><th>글쓴이</th></tr>";
	  										$.map( myval_obj, function( val, idx ) {	// 변수명은 아무거나! 내가 알아볼 수 있게!
											   	htmlStr += "<tr><td>"+val["title"]+"</td><td>"+val["regid"]+"</td></tr>";
	  											//console.log(val["title"] + ", "+ val["regid"] + idx);
											});
	  										htmlStr += "</table>";
	  										$("#resultDIV").empty();	// data 있으면 없애기.
	  										$("#resultDIV").html("<b>"+htmlStr+"</b>")
  										}
		});
	});
	
	// JSON 모양의 String == { "name":"kim" , "pw":"123" }  -- 내부변환
	$("#2__JsonStr_Str_Btn").click(  function(){
		var objData = {"name":"kim","pw":"123"};
		console.log(objData);

		var strData  = JSON.stringify(objData)
		console.log(strData);
		
		$.ajax({
			method      : "POST",
	        url         : "<%=request.getContextPath()%>/RestServlet?pagecode=R002",
	        contentsType : "application/x-www-form-urlencoded; charset-UTF-8",
       		data 		: "MY_JSONKEY=" + JSON.stringify(objData),	// 	"{MY_JSONKEY={ "name":"kim" , "pw":"123" }}"
	  		error 	    : function(myval){ console.log("이건에러:" + myval);   }, 
	  		success     : function(myval){ 	console.log("성공 :" + myval);	}
		});
	});
	
	// 진짜 Object data 보낼 것. ==  { "name":"kim" , "pw":"123" }
	// 즉 키를 확인하고 value 값을 꺼낼수있어야해서 VO가 필요함. 즉, getter setter 필요함.
	// obj = JSON.stringify({"title":"이건제목" , "regid":"hong"});
	// str : "{"title":"이건제목" , "regid":"hong"}" --> 스트링으로 바꿔진 것을
	// fromjson : {"title":"이건제목" , "regid":"hong"} 최종으로 이렇게 바꿔짐.
	$("#3__Json_Str_Btn").click(  function(){
		var objData = {"title":"aaaaatitle","regid":"hong"};
		$.ajax({
			method      : "POST",
	        url         : "<%=request.getContextPath()%>/RestServlet?pagecode=R003",
	        contentType : "application/json; charset=UTF-8", 
       		data 		: JSON.stringify(objData),	// 객체는 그냥 못나가서 stringify로 감싸야함. 없을 땐,  key=val(쿼리스트링으로 나감)== 내부적으로 쿼리스트링으로 컴버팅됨, 꼭 stringigy로 감싸줘야 JSON타입파일로 나가진다.  // 	"{MY_JSONKEY={ "name":"kim", "pw":"123" }}"
       		error 	    : function(myval){ console.log("에러:" + myval);   },
	  		success     : function(myval){ console.log("성공:" + myval);   }
		});
	});
	
	$("#4__Str_Json_Btn").click(  function(){
		var sendFormData = $("#Str_Json_Form").serialize();
		$.ajax({
			method      : "POST",
	        url         : "<%=request.getContextPath()%>/RestServlet?pagecode=R004", 
       		data 		: sendFormData,
       		dataType	: "json",		// 생략가능. 
       		error 	    : function(myval){ console.log("에러:" + myval);   },  
	  		success     : function(myval){ console.log("성공:" + myval); 
									  		console.log(myval["status"] + ", " + myval["message"]);
									  		}		// myval == json 타입의 보낸 스트링 출력됨.
		});
	});
	
	$("#5__JsonStr_Json_Btn").click(  function(){
		var objData = {"name":"kim","pw":"123"};
		console.log(objData);

		var strData  = JSON.stringify(objData)
		console.log(strData);
		
		$.ajax({
			method      : "POST",
	        url         : "<%=request.getContextPath()%>/RestServlet?pagecode=R005",
	        contentsType : "application/x-www-form-urlencoded; charset-UTF-8",
       		data 		: "MY_JSONKEY=" + JSON.stringify(objData),	
       		dataType	: "json",		// 생략가능. 
       		error 	    : function(myval){ console.log("에러:" + myval);   },  
	  		success     : function(myval){ 
	  										console.log("성공:" + myval);
											console.log(myval["status"] + ", " + myval["message"]);
  										}
		});
	});
	
	
	$("#6__Json_Json_Btn").click(  function(){
		var objData = {"title":"aaaaatitle","regid":"hong"};
		$.ajax({
			method      : "POST",
	        url         : "<%=request.getContextPath()%>/RestServlet?pagecode=R006",
	        contentType : "application/json; charset=UTF-8", 
	        data		: JSON.stringify(objData),
	        dataType	: "json",		// 생략가능. 
       		error 	    : function(myval){ console.log("에러:" + myval);   },  
	  		success     : function(myval){ console.log("성공:" + myval); 
									  		console.log(myval["status"] + ", " + myval["message"]);
									  		}		// myval == json 타입의 보낸 스트링 출력됨.
		});
	});

</script>

</body>
</html>