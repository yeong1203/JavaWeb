package com.kosta.sample.chart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmpServlet() {
        super();        
    }

    // button에서 주는 값 없이 sevlet 이동이기 때문에 그냥 get 처리함.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html;charset=UTF-8");
		
   
		EmpDAO dao = new EmpDAO();
		ArrayList<EmpVO> list = dao.empSelect();
//		System.out.println("총:" + list.size());
//		for(EmpVO evo  : list) {
//			System.out.println(evo.getSal());
//		}
	
		// 서블릿 객체 전달 방법
		// 1. 화면 전환 보여도
		request.setAttribute("KEY_EMPLIST", list); // 어떤 객체를 담을 것인가.
	    request.getRequestDispatcher("lec_chart_rest.jsp").forward(request, response);
	}
	
	// 객체로 받으려면 로 받아야함.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    
//	    -------- 서버로 부터 응답이 str로 오는 경우.
//	    response.setContentType("text/html;charset=UTF-8");
//
//	    EmpDAO dao = new EmpDAO();
//	    ArrayList<EmpVO> list = dao.empSelect();
//		
//	    Gson gson = new Gson();
//	    String jsonStr = gson.toJson(list);
//	    PrintWriter out = response.getWriter();
//	    out.println(jsonStr);
	    
//	   -------- 서버로 부터 응답이 Json로 오는 경우.
	    response.setContentType("application/json;charset=UTF-8");

	    EmpDAO dao = new EmpDAO();
	    ArrayList<EmpVO> list = dao.empSelect();
		
	    Gson gson = new Gson();
	    String jsonStr = gson.toJson(list);
	    System.out.println(jsonStr);
	    
	    PrintWriter out = response.getWriter();
	    out.println(jsonStr);
	}

}
