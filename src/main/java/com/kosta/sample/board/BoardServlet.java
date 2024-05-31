package com.kosta.sample.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardServlet() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html;charset=UTF-8");
	    BoardDAO dao = new BoardDAO();
	    
	    String pagecode = request.getParameter("pagecode");
	    // 게시물 목록보기
	    if(pagecode.equals("B001")) {
		    ArrayList<BoardVO> list = dao.boardSelect();
		    
		    request.setAttribute("KEY_BOARDLIST", list);
		    RequestDispatcher rd = request.getRequestDispatcher("tables.jsp");	// (링크를 패치해서), 객체를 jsp에 연결다리를 준다.
		    rd.forward(request, response);	// toGet 하고 있는 애를 forward로 두가지 키=벨류 전부 담아서 보내진다.

    	// 게시물 상세보기
	    } else if(pagecode.equals("B002")) {
	    	// wrapper?
	    	int seq = Integer.parseInt(request.getParameter("seq"));	// seq가 와야 selectOne 할 수 있음! 이게 제일 중요.
	    	BoardVO bvo = dao.boardSelectOne(seq);
	    	
	    	request.setAttribute("KEY_BOARDVO", bvo);
	    	RequestDispatcher rd = request.getRequestDispatcher("tables_detail.jsp");
	    	rd.forward(request, response);	    	
	    	
	    	System.out.println();
	    } else if(pagecode.equals("B003")) {
	    	// B003 수정버튼
	    	
	    	System.out.println("===== 수정 =====" );
	    } else if(pagecode.equals("B004")) {
	    	// B004 삭제
	    	
	    	System.out.println("===== 삭제 =====");
	    } else {
	    	response.sendRedirect("500.html");
	    }
	    
//	    
//	    // board 목록 DB에서 꺼내기
//	    BoardDAO dao = new BoardDAO();
//	    ArrayList<BoardVO> list = dao.boardSelect();
////	    결과를 가진 list를 tables 에 이제 넘겨줘야한다.
//	    
////	     response.sendRedirect("xx.jsp") : 글자 전송에 사용
////	    get방식으로 넘기는 방법은 문자열만 가능. 
////	    response.sendRedirect("table.jsp?UID=kim");
//	    
////	    객체 전송에 사용.
////	    request.setAttribute("", list);		// 요청자에게 ("무슨키", 무슨값)
//	    /**
//	     * <글자 전송에 사용하는 것. >
//	     *  - response.sendRedirect("xx.jsp");
//	     *  - response.sendRedirect("table.jsp?UID=kim");
//	     * 
//	     * <객체 전송에 사용.>
//	     * - 세션에 담아버리면 어딜가나 다 따라다님.
//	     * - page scrop : 가져온 값을 어느 범위까지 공유해서 사용할 것 인가?
//	     * - page < request < session < application
//	     * - request.getAttribute();   // 
//	     * 
//	    // application도 내장 객체. -- 톰캣 연결된 프로젝트들과 쉐어링할때 쓰는 것. 가장 크다!
//	    // jsp 안에서만 쓰는게 page  
//	    // 해당 jsp 요청한 서블릿까지 = request		    // 요청-> 포워딩 하면 끝.	 서블릿까지.!   
//	    // 해당 jsp 뿐만아니라 모든 페이지 = session
//	    // 해당 jsp 뿐만 아니라 모든 프로젝트까지 = application
//	     * 
////	    HttpSession session = request.getSession();
////	    session.setAttribute("KEY_SESS_USERID", list);
//	        	    	    
//	    //객체 전송을 위한 jsp 파일을 넘긴다.
//	     */
//	    request.setAttribute("KEY_BOARDLIST", list);
//	    RequestDispatcher rd = request.getRequestDispatcher("tables.jsp");	// (링크를 패치해서), 객체를 jsp에 연결다리를 준다.
//	    rd.forward(request, response);	// toGet 하고 있는 애를 forward로 두가지 키=벨류 전부 담아서 보내진다.
//	    // 객체를 가지고 가면 forward, 그냥 가면 send... 두개 다 열면 에러남.
		System.out.println("====request method : GET ====");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html;charset=UTF-8");

	    BoardDAO dao = new BoardDAO();
	    String pagecode = request.getParameter("pagecode");
//	    System.out.println(pagecode);
	    if(pagecode.equals("B003")) {
	    	// B003 수정버튼
	    	ArrayList<BoardVO> bvo = dao.boardSelect();
	    	System.out.println(request.getParameter("seq"));
	    	System.out.println(request.getParameter("regid"));
	    	System.out.println(request.getParameter("regate"));
	    	System.out.println(request.getParameter("title")); 
	    	System.out.println(request.getParameter("contents"));
	    	
	    	System.out.println("===== 수정 =====" );
	    } else if(pagecode.equals("B004")) {
	    	// B004 삭제
	    	
	    	
	    	System.out.println("===== 삭제 =====");
	    } else {
	    	response.sendRedirect("500.html");
	    }
	    
	    
	    
	    
		System.out.println("====request method : POST ====");
	}
	
	

}
