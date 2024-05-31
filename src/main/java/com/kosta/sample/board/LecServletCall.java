package com.kosta.sample.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//   Serializable = 직렬화 => (무엇)이다. == 인코딩(객체를 바이트로 변환하는것)하는 것.
// Deserializable == 역직렬화 == 디코딩(바이트->객체 변환)

//public abstract class HttpServlet {
//
//}
// 원형은 그대로 가져가야해서 오버라이딩해서 상속받아서 사용해야함.

//HttpServlet 부모꺼에는 http 전송방식이 들어있다. 상수로!
// 들어온 해당 클래스를 정의해서 들어온게 httpServlet.class라면  이렇게 처리해라! 해서 얘들은 내부꺼라서 prate

// 초록색 => public, 노란색 => 상속만 가능 (protected), 빨간색 => private
// 맘에 안들어도 goGet
// 서블릿이 개념적 abstract 구성!!
// 모든 서블릿은 httpServlet으로 들어가야함! 오버라이딩은 가능하지만 상속받아서 꼭 사용해야함.


// HttpServletRequest  == 요청
// 있으면 getSession, 없으면 getRequestedSessionId() 세션아이디 요청함. 세션 살아있는지 물어볼 수 있음= isRequestedSessionValid()

// 서버는 세션 빈 파일 만들고, 필요한 정보를 담고, 안중요한(loginChecked같은것)것은 브라우저에 저장하는 것. 덜 중요한건 브라우저로 내리는 것. 중요한건 세션이 가지고 간다.
// RedirectURL = 로그인 성공 시, 어디로 가라! 보내는 것.
// HttpServletResponse == 응답 => 쿠키로 보내진다.

// 서버가 쿠키 보내ㄱ
// 응답이 쿠키 정보를 꺼내본다던지, 만든 세션이나 쿠키정보는 꺼내서 볼 수는 있다.  
/// 200 (서버 클라이언트 제대로 붙은 것) / 401 (200인 통과, 인증안되었을때) / 403(auth할때 만날것! / 404 / 500 (java코딩처리하다 만나는 것, 백엔드 문제! )



@WebServlet("/board_servlet_url")
public class LecServletCall extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 기본생성자.
    public LecServletCall() { }

    // 웹에서 보내는 것 => default : get
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
							// 추가하는것 .append
//		response.getWriter().append("Hello zzzzz Served at: ")		
//							.append(request.getContextPath());	// /web 내 웹프로젝 접근 주소 => getContextPath()
//		지우는 이유? path 경로 삭제함. 계속 web_prj만 사용할 것이 아니기 때문에 경로를 삭제.
//		response.sendRedirect("index.html");	// 응답.보낼경로줘라("보여줄파일");
		
//		response.sendRedirect("index.html");
		String vUserid = request.getParameter("userid");
		response.getWriter().append("GET : "+vUserid);
		System.out.println("====request method : GET ====");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// request.getParameter("userid") => request를 통해서 getter parameter을 주세요. 하는 것.
		// request.getParameter("userid") 를 숫자로 받아야한다면 캐스팅하면됨.
		String vUserid = request.getParameter("userid");
		String vUserpw = request.getParameter("userpw");
		String vGen = request.getParameter("gen");
		String vSubject = request.getParameter("subject");
		String vUserfile = request.getParameter("userfile");
		String vSsn = request.getParameter("ssn");
		String vContents = request.getParameter("contents");
		// 멀티선택
		String[] vHabbit = request.getParameterValues("habbit");
//		response.getWriter().append("POST");
		response.getWriter().append("POST "+vUserid);
		
		// 위에 값을 한번에 객체에 담으려면 VO에 담으면 된다.
		System.out.println(vUserid);
		System.out.println(vUserpw);
		System.out.println(vGen);
		System.out.println(vSubject);
		System.out.println(vUserfile);
		System.out.println(vSsn);
		System.err.println(vContents);
		if(vHabbit != null) {			// vHabbit이 없을 경우를 위해 is not null 설정.
			for(String vh : vHabbit) {
				System.out.println(vh);
			}			
		}
		
		System.out.println("====request method : POST ====");
	}
	
	

}
