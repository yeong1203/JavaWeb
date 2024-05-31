package com.kosta.sample.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import javax.sql.DataSource;

import com.kosta.sample.common.MyOracleConnection;

// TODO: Data Access Object : DML
public class UserDAO {	
	// 파라미터가 잘 넘어왔는지 확인용 코드
	
	
	public int userInsert(UserVO uvo) {
		MyOracleConnection moc = new MyOracleConnection();
		DataSource ds = null;
		Connection conn = null;
		
		PreparedStatement pstmt = null;
		int insertRows = 0;
//		ResultSet rs = null;
		
		System.out.println(uvo.toString());
		
		try {
//			conn= moc.oracleConn();
			ds = moc.myOracleDataSource();
			conn = ds.getConnection();
			if(conn != null) { 
				System.out.println("conn ok"); 
			} else {
				System.out.println("conn fail");
			}
			
			String sql = "insert into users values(users_seq.nextval,?,?,?,?,sysdate,'u')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uvo.getUserid());
			pstmt.setString(2, uvo.getUname());
			pstmt.setString(3, uvo.getEmail());
			pstmt.setString(4, uvo.getPasswd());
			
			insertRows = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return insertRows;
	}
	// 서블릿에 있는 거쳐오는 라이브러리를 못읽어와서 
	
	
	
	//TODO: 로그인 필요. 이건 VO 말고 따로 받는 걸 연습위한 것. => true, false만 체크
//	public boolean userLogin(String userid, String passwd) {
	public UserVO userLogin(String userid, String passwd) {
		
		MyOracleConnection moc = new MyOracleConnection();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DataSource ds = null;
		
//		UserVO uvo = new UserVO();
		boolean userCheck = false;
		UserVO uvo = new UserVO();
		
		try {
			ds = moc.myOracleDataSource();
			conn = ds.getConnection();
//			userCheck = true;
			
			// userid,uname, grade 등 어디서 사용하려고 꺼내는데, 
			String sql = "select userid, uname, grade from users where userid=? and passwd=?";
			// sql문에서 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			
//			if(rs.next()) { 
//				return true; 
//			} else { 
//				return false;
//			}
			
//			int num = 10;
//			if(true) {	// true / false는 이렇게 넣던지 true가 될만한 조건을(수식을) 넣던지 하는 
//				System.out.println("dddd");
//			}
			
			if(rs.next()) {
//				userid = rs.getString("userid");
//				String uname = rs.getString("uname");
//				String grade = rs.getString("grade");
//				System.out.println(userid+"\t"+uname+"\t"+grade);
				
				//String[] 사용하면 안됨. 찾기 힘들다.
				
				// session에서 한가지만 가져와야해서. 
				
				uvo.setUserid(rs.getString("userid"));
				uvo.setUname(rs.getString("uname"));
				uvo.setGrade(rs.getString("grade"));
//				userCheck = true;		// true, false 필요 없다.
				uvo.setLoginCheck(true);
//				userCheck = true;
//				uvo.setLoginCheck(userCheck);
			}
			// while에서 값이 있으면 들어와. uvo오에 모든걸 담아.. 그리고 userCheck true로 변경해! 
			
			// rs.next() 값이 있다고 하면 true == rs.next()
//			while(rs.next()) {	// rs.next() 가져올 것이 있으면. => 결과 집합을 소모했습니다! re.set이 끝에(다 소모)도달했다. 아무것도 안나오면 읽어올게 없다라는 뜻.
//				System.out.print("여기까지 들어옴");
//				uvo.setUserid(rs.getString("userid"));
//				System.out.print("여기까지 들어옴 - id");
//
//				uvo.setPasswd(rs.getString("passwd"));
//				System.out.print("여기까지 들어옴 - pw");
//			}
//			userCheck = true;
				
				
				
//			if(conn != null) {
//				System.out.println("conn ok"); 
//			} else {
//				System.out.println("conn fail");
//			}			

//			uvo = null;
//			while(userCheck == true) {
//				if(uvo == null) {
//					uvo.setUserid(rs.getString("userid"));
//					uvo.setPasswd(rs.getString("passwd"));	
//				}
//			}
//			boolean userCheck = true; // rs.next(); , default를 true로 준 것.					
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return uvo;
	}
}
