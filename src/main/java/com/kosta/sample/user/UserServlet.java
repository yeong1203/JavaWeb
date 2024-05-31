package com.kosta.sample.user;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosta.sample.common.MyOracleConnection;		// Oracle 커넥션

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		// TODO : 로그아웃
		HttpSession session = request.getSession();
//		session.removeAttribute("KEY_SESS_USERID");
//		session.removeAttribute("KEY_SESS_UNAME");
//		session.removeAttribute("KEY_SESS_UGEDE");
		session.invalidate();					// 일괄 지우기
		session.setMaxInactiveInterval(0);		// 세션 유효타임 0초 : 또 적은 이유? 한번 더 안전장치.
		
		response.sendRedirect("index.jsp");
	}

	// doPost 이런 이름들을 나중에 바꿀 수 있다. 스프링에서는! 주소를 맨 위에 하나만 줬다면, 메서드 마다 하나씩 따로 @WebServlet()을 사용할 수 있다.
	// 하지만 서블릿에서는 서블릿단 하나가져가고 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: login, register
		// 페이지 구분을 위한 input type="hidden"의 값(P001 or P002)
		String pagecode = request.getParameter("pagecode");				
		UserDAO dao = new UserDAO();	
		
		if(pagecode.equals("P001")) {
			// TODO: 레지스터처리 =>
//			UserVO uvo = new UserVO();
//			uvo.setUname("홍길동");
			String userid = request.getParameter("userid");
			String uname = request.getParameter("uname");
			String passwd = request.getParameter("passwd");
			String email = request.getParameter("email");
			
			UserVO uvo = new UserVO();
			uvo.setUserid(userid);
			uvo.setUname(uname);
			uvo.setPasswd(passwd);
			uvo.setEmail(email);
//			uvo.setUserid(request.getParameter("userid"));
//			uvo.setUname(request.getParameter("uname"));
//			uvo.setPasswd(request.getParameter("passwd"));
//			uvo.setEmail(request.getParameter("email"));
			
			int insertRows = dao.userInsert(uvo);	
			System.out.println(uvo);
			if(insertRows == 1) {							
				// 회원가입 정상적 -> 로그인 또는 메인화면으로 이동.
//				dao.userInsert(uvo);
//				uvo.setRegdate(request.getParameter("regdate"));
				
//				dao.equals(uvo);
				response.sendRedirect("index.jsp");
			} else {
				// 가입 중 문제 생기면
				response.sendRedirect("500.html");
			}
			
		} else if(pagecode.equals("P002")) {
			// TODO: 로그인처리
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			
//			String sql = "select userid, name, emil from users where userid=? and passwd=?";
			
			UserVO uvo = new UserVO();
			uvo.setUserid(userid);
			uvo.setPasswd(passwd);
//			boolean loginCheck = dao.userLogin(userid, passwd);
			uvo = dao.userLogin(userid, passwd);
			
			//if(loginCheck == true) {
			// if(uvo != null) {
			if(uvo.getLoginCheck()) {
				// 로그인 성공
				
//				session.add("",grade);
				System.out.println("---- session 할당 ----"+ uvo.getGrade());
				// userid, uname, grade
				HttpSession session = request.getSession();
				session.setAttribute("KEY_SESS_USERID", uvo.getUserid());
				session.setAttribute("KEY_SESS_UNAME", uvo.getUname());
				session.setAttribute("KEY_SESS_GRADE", uvo.getGrade());
				
				response.sendRedirect("index.jsp");
			} else {
				response.sendRedirect("500.html");
			}
			
		} else {
			// TODO: 기타 처리
			response.sendRedirect("500.html");
		}
	}

}
