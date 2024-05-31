package com.kosta.sample.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kosta.sample.json.EmpVO;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import javax.sql.DataSource;

//import oracle.jdbc.datasource.OracleDataSource;
import javax.sql.ConnectionPoolDataSource;


// JDBC는 자바의 꽃!

public class BoardJDBCTest {
		
	static final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	static final String DB_ID = "it";
	static final String DB_PW = "0000";

	/**
	 * DBCP(DataBase Connection Pool) : DB connecrion을 미리 만들어 Pool로 관리는 서비스 => Database connection pooling services. 
	 * DataSource
	 * 	- application과 Database의 인터페이스
	 * 	-  DataSource를 이용해서 DBCP(pool)에 있는 connection을 사용.
	 * 	- javax.sql.DataSource
	 * -------------------------------------------------------------
	 * java.lang.Object
	 * 	-- [i] implements javax.sql.DataSource
	 * 		-- [c] OracleDataSource
	 * 			--[c] OracleConnectionPoolDataSource
	 * -------------------------------------------------------------
	 */
	public DataSource myOracleDataSource() {
		OracleConnectionPoolDataSource ds = null;
		try {
			ds = new OracleConnectionPoolDataSource();
			ds.setURL(DB_URL);
			ds.setUser(DB_ID);
			ds.setPassword(DB_PW);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;			
	}
	// connection pool에 한번만 접속하여 15개 중에 하나씩 땡겨서 사용할 수 있게 해준다.
	
	
	
	
	
	// 서버 사양과 해당 서비스에 맞게 개수를 
	// 인프라가 pool 개수를 잡아 준다.  => DBCPool
	public Connection oracleConn() {
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);	
			if(conn != null) {
				System.out.println("conn OK");
			} else {
				System.out.println("Faild");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
			
	}
//	public DataSource getOracleDataSource() {
//		conn = OracleConnectionPoolDataSource();
//		getPooledConnection();
//	}
	

	

	public void oracleClose(Connection conn, PreparedStatement pstmt,ResultSet rs) {
		try {
			if(rs !=null) { rs.close(); }
			if(pstmt !=null) { pstmt.close(); }
			if(conn !=null) { conn.close(); }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void oracleClose(Connection conn, PreparedStatement pstmt) {
		try {
			if(pstmt !=null) { pstmt.close(); }
			if(conn !=null) { conn.close(); }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void oracleClose(Connection conn, Statement stmt) {
		try {
			if(stmt !=null) { stmt.close(); }
			if(conn !=null) { conn.close(); }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	public ArrayList<EmpVO> boardSelect(Connection conn) {	// connection 을 받아서 사용하는 것.
//		Connection conn = null;		// DB 여러개이면 안에서 만들어서 사용. 
	public ArrayList<EmpVO> boardSelect() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmpVO> arrmap = new ArrayList<EmpVO>();
		
		DataSource ds = null;
		
		try {
			
//			conn = oracleConn();		// ----- 메서드 호출을 통해 conn 받아오기. 
			
			//------- DBCP를 사용한  DB 연결 ----------------------------
			ds = myOracleDataSource();	// javax.sql.DataSource 사용 => 다형성으로 javax에 있는 것 사용.
			ds.getConnection();			// OracleConnectionPoolDataSource.getPooledConnection() 사용 => 다형성으로 javax에 있는 것 사용.
			
			
			String sql = "select * from emp";
			pstmt = conn.prepareStatement(sql);	// PreparedStatement 받기 때문에 pstmt 에 저장.	// Oracle DB 연결되어 Oracle에서 sql 확인함.
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				vo.setEmpno(rs.getInt("empno"));	
				vo.setEname(rs.getString("ename"));
				arrmap.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			oracleClose(conn, pstmt, rs);
		}
		return arrmap;
	}
// ----------------------------------------------------------
//  PreparedStatement preparedStatement => 리턴 차이가 있다.
// -----------------------------------------------------------
	// 삭제 = PreparedStatement
	public int boradDelete(int empno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int delCount = 0;
		
		try {
			conn = oracleConn();	
			/**
			 * java.sql.InterfaceConnection
			 * public interface PreparedStatement {
			 * 		public abstract prepareStatement prepareStatement(String sql);  ****
			 * 		public int						 executeUpdate();  ***
			 * 
			 * 		pulbic abstract Statement createStatement();
			 * 		public int 		executeUpdate(String sql);
			 * }
			 * 
			 *  Statement는 값이 바뀔 때 마다 또다른 메모리에 올라가는 것. => 정적인 경우에 적합하다!! 값이 바뀌지 않아야 하는 것. 한번 생성후 끝! => DDL 문 같은 딱 한번 생성하면 끝인것.
			 *  PrepareStatement => 동적인 경우 적합. where문이 자주 바뀌는 것. DML과같은 애
			 *  
			 *  
			 *  
			 */
			
			// String sql = "delete from emp where empno" + empno ;	// 하드코딩... 값이 바뀔때마다 계속 새로운 메모리 사용. 그래서 바인딩처리!
			//  바인딩 한 것 => "delete from emp where empno" + empno :: 변경된 값만 바꿔서 받기때문에 하드코딩된 sql(string) 부분은 한번만 메모리에 올라가기 때문에 딱 4바이트만 사용한다.
			String sql = "delete from emp where empno=? and ename=?";
			pstmt = conn.prepareStatement(sql);	
			// 실행 전 바인딩.
			pstmt.setInt(1, empno);			// (인덱스, 넣을 값)	=> preparedStatement 장점! ----- 런타임시, (동적) 바인딩
											// setInt[=set바인딩값타입] (1번째 물음표에 , 넣을 값[바인딩될값]);
			//pstmt.setString(2, ename);
			delCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			oracleClose(conn, pstmt);
		}
		return delCount;
	}
// ----------------------------------------------------------
//  Statement createStatement()
// -----------------------------------------------------------

//	public int boradDelete(int empno) {
//		Connection conn = null;
//		Statement stmt = null;
//		int delCount = 0;
//		
//		try {
//			conn = oracleConn();	
//			
//			/**
//			 * java.sql.InterfaceConnection
//			 * public interface PreparedStatement {
//			 * 		public abstract prepareStatement prepareStatement(String sql);  ****
//			 * 		public int						 executeUpdate();  ***
//			 * 
//			 * 		pulbic abstract Statement createStatement();
//			 * 		public int 		executeUpdate(String sql);
//			 * }
//			 */
//			
//			// String sql = "delete from emp where empno" + empno ;	// 하드코딩... 값이 바뀔때마다 계속 새로운 메모리 사용. 그래서 바인딩처리!
//			//  바인딩 한 것 => "delete from emp where empno" + empno :: 변경된 값만 바꿔서 받기때문에 하드코딩된 sql(string) 부분은 한번만 메모리에 올라가기 때문에 딱 4바이트만 사용한다.
//			// String sql= "delete from emp where empno=?";		// ?에 맞게 값을 끼워줘야하낟.
//			String sql= "delete from emp where empno="+empno;	// statement는 하드코딩해야함.
//			stmt = conn.createStatement();
//			delCount = stmt.executeUpdate(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			oracleClose(conn, pstmt);
//		}
//		return delCount;
//	}
	
/** 			Statement createStatement()  		vs.  PreparedStatement prepareStatement()
 * 
 * 파라미터		"select * from emp where empno="+no		"select * from emp where empno=?"
 * 컴파일			실행시점에 컴파일								사전컴파일(DB는 컴파일 없이 바로 실행) == 캐시기능. 즉, 재사용가능.
 * 속도													효율적 ( 건수가 많다면 압도적 차이가 날 것이다)
 * 보안			보안에 상당히 취약(SQL Injection공격)			
 * 바이너리데이터	사용 불가									사용가능
 * 주사용처		SQL문의 변경이 거의 없는 경우(DDL)				SQL문의 변경이 빈번한 경우(DML -> ex. DML의 where조건절 등...)
 * 문법			stmt = conn.createStatement();			pstmt = conn.prepareStatement(sql);	
														pstmt.setInt(1, empno);			// (인덱스, 넣을 값)
				delCount = stmt.executeUpdate(sql);		delCount = pstmt.executeUpdate();
				평소에 이거 사용하지 말기.						바인딩 필요할 때 사용하는것. 대체적으로 이것을 많이 사용하자!
 * 			
 * => 타입은 바인딩할때 하는것.
 * => 컴파일 시점 다르다 -> 실행 시점에 컴파일(state)  /  (사전컴파일) 사전에 미리 컴파일 --> DB가 컴파일 필요없고 바로 실행하면된다)
 * => 사전 컴파일 하는 것이 효율적이고, 작업 수가 많을 수록 압도적으로 차이가 날 것이다.
 * => SQL Injection공격 => 사용자가 원치 않는 코드를 넣어 의도치 않게 만드는 것.
 * 
 * :: execute -- select, insert, delete,update, DDL 문까지.
 * 
 */
	
	public static void main(String[] args) {
		BoardJDBCTest obj = new BoardJDBCTest();
		// Connection conn = b.oracleConn();	// 커넥션을 줄 필요 없음. 얘는 주겠다는 놈이 
		ArrayList<EmpVO> list = obj.boardSelect();
		
		System.out.println("총: "+ list.size());		 
		
		for(int i=0; i<list.size(); i++) {
			int emp = list.get(i).getEmpno();
			String en = list.get(i).getEname();
			System.out.println(emp+"\t"+en);
		}
		
//		int delCount = obj.boardDelete(7357);
//		System.out.println("총 삭제 :" + delCount);
		
	}
}
