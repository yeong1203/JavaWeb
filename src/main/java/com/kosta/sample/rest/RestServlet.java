package com.kosta.sample.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.kosta.sample.board.BoardVO;


@WebServlet("/RestServlet")
public class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String pagecode = request.getParameter("pagecode");
		Gson gson = new Gson();
		
		// -------------- #AjaxBtn or #i_Str_str_Btn ------------
		if(pagecode.equals("R001")) {
			String searchGubun = request.getParameter("searchGubun");
			String searchStr   = request.getParameter("searchStr");
			
			// 초간단 AJAX
			System.out.println(searchGubun + "," + searchStr); 
			
			//String responseStr = " 이건 서버가 보낸 메세지" ;	// 서버로 부터의 응답 String 
			ArrayList<BoardVO> list = new ArrayList<BoardVO>();
			BoardVO bvo = new BoardVO();
			bvo.setTitle("aaa");
			bvo.setRegid("kim"); // [{"title"="aaa", "regid"="kim"} ]
			list.add(bvo);
			
			bvo = new BoardVO();
			bvo.setTitle("bbb");
			bvo.setRegid("hong");	// [{"title"="aaa", "regid"="kim"}, {"title":"bbb", "regid":"hong"} ]
			list.add(bvo);	

			// ------- 객체를 글자로 변경 : Jackson
//			ObjectMapper mapper = new ObjectMapper();
//			String jsonString = mapper.writeValueAsString(list);
			// jackson 바인더 쓰려니 거창해서 간단하게 toJson으로 해결가능해서 Gson 사용.
			
			// ------- 객체를 글자로 변경 : Gson
			// =을 :으로 교체해줄 수 있는 Gson() 사용!
			// map은 =으로 전송되는데, 우리는 : 로 가져야 하는데 안됨. 그래서 사용한 것이 Gson 
			// json 모양으로 변경하기.
			
			String gsonString = gson.toJson(list);  //(toJSON = String) => "[ {"title":"aaa" , "regid":"kim"}, {"title":"bbb" , "regid":"hong"} ]"		
			System.out.println(gsonString+", "+ gsonString.getClass());
			
			PrintWriter out = response.getWriter();		
			out.print(gsonString);
			// 객체로 변경해서 key 값으로 꺼내서 사용.
			
			

//			// 응답은 서버에서 오는 응답 == dataType
//			PrintWriter out = response.getWriter();		// 무슨 응답을 주라고 했음.
//			// 데이터를 보내줘라 그랬기 때문에 포워드 X
//			out.print("이건 서버가 보낸 메세지");
			
			/*
			System.out.println(list.toString());
			// 보낼 데이터를 JSON형태로 변경하려면, key=val 형태 처리해야함.
			String responseStr = "{\"MYKEY\": }";
			
			// 응답은 서버에서 오는 응답 == dataType
			PrintWriter out = response.getWriter();
			//out.print(list);	// list는 원래 메모리 주소가 찍힘. 그러나 sucess로 넘어가서 알아서 처리해서 모양은 리스트지만 글자로 찍힘
			out.print(responseStr);
			*/
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// key 없이 객체 채로 넣음.
		String pagecode = request.getParameter("pagecode");
		Gson gson = new Gson();
		
		// -------------- or #2_Str_str_Btn ------------
		//data : "MY_JSONKEY=" + JSON.stringify(objData) ,
		if(pagecode.equals("R002")) {
			String jsonStr = request.getParameter("MY_JSONKEY");	// key 값으로 불러와서
			System.out.println(jsonStr + ", " + jsonStr.getClass());
			
			PrintWriter out = response.getWriter();		//  보내는 타입이 다르기 때문에 쓰는 것은 다 따로 가지고 갈 것.
			out.print("R002 응답 확인");
			
			
		//-------------------- #3__Json_Str_Btn --------------------
        //contentType : "application/json; charset=UTF-8",			
		//data 		  : JSON.stringify(objData)
		} else if (pagecode.equals("R003")) {
			// key 가 JSON안에 들어있어서 직접 들어가서 꺼내야함. // 라인으로 읽어서 하나하나 따와서 조합하는 방법.
			String jsonStr = request.getReader().lines().collect(Collectors.joining());
			System.out.println(jsonStr + "," + jsonStr.getClass());
			
			// obj : {"title":"이건제목" , "regid":"hong"}
			// obj : title=이건제목&regid=hog
			//str -> obj ==> hashmap에서 = 때문에, 그래서 객체로 바꿔서 get.parameter()로 꺼내서 사용함.
			// 이름 떼어내고 값 떼어내고
			
			// fromjson을 사용해서 객체로 변형.
			// obj  : JSON.stringify({"title":"aaaaatitle","regid":"hong"});
			// str  : "{"title":"aaaaatitle","regid":"hong"}"
			// fromjson : {"title":"aaaaatitle","regid":"hong"}
			BoardVO bvo = gson.fromJson(jsonStr, BoardVO.class);
			System.out.println(bvo.getTitle());
			
			PrintWriter out = response.getWriter();
			out.print("R003 응답 ok");
			
			
		//----- Str -> Json
		//-------------------- #4__Str_Json_Btn --------------------
		} else if (pagecode.equals("R004")) {
			response.setContentType("application/json; charset=UTF-8");	// 응답을 json으로 주는 것. 
			
			//String jsonStr = "{\"status\":\"200\", \"message\":\"R004 응답 OK\"}"; 
			// 위 코드가 아래와 같다. map사용
			HashMap<String, String> map = new HashMap<String, String>();	// 아래처럼 생긴 map을 json모양의 string으로 만들어진다.
			map.put("status", "200");
			map.put("message", "R004 응답 ok");
			String jsonStr = gson.toJson(map);
			
			PrintWriter out = response.getWriter();		
			out.print(jsonStr);
			
		//  ---- JsonStr -> Json
		//-------------------- #5__JsonStr_Json_Btn --------------------
		} else if (pagecode.equals("R005")) {
			response.setContentType("application/json; charset=UTF-8");
			String jsonStrParam = request.getParameter("MY_JSONKEY");
			System.out.println(jsonStrParam + "," + jsonStrParam.getClass());
			
			HashMap<String, String> map = new HashMap<String, String>();	// 아래처럼 생긴 map을 json모양의 string으로 만들어진다.
			map.put("status", "200");
			map.put("message", "R005 응답 ok");
			String jsonStr = gson.toJson(map);
			
			PrintWriter out = response.getWriter();		
			out.print(jsonStr);
			
		// --- Json -> Json
		//-------------------- #6__Json_Json_Btn --------------------
		} else if (pagecode.equals("R006")) {
			response.setContentType("application/json; charset=UTF-8");	
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("status", "200");
			map.put("message", "R006 응답 ok");
			String jsonStr = gson.toJson(map);
			//String jsonStr = "{\"status\":\"200\", \"message\":\"R004 응답 ok\"}";
			PrintWriter out = response.getWriter();
			out.print(jsonStr);
		} 
	}

}
