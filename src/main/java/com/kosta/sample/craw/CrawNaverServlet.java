package com.kosta.sample.craw;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CrawNaverServlet")
public class CrawNaverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CrawNaverServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		crawTest ct = new crawTest();
		ArrayList<NewsVO> list = ct.getNaverNews();		// list에 crawTest의 getNaverNews()를 담고
		System.out.println(list.size());
		
		// 화면 출력. == jsp 화면 출력.
		PrintWriter out = response.getWriter();		// 응답화면에 사용하겠다!
		for(NewsVO nvo : list) {
			out.println(nvo.getTitle() + "<br>");		// 타이틀 찍히는지 확인.
		}
	}


//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
