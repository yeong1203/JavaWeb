package com.kosta.sample.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import javax.sql.DataSource;

import com.kosta.sample.common.MyOracleConnection;

public class BoardDAO {
	
	// 프로토타입 개발 : 리턴타입, 메서드명 작성 후 파라미터 받는 것 작성, 리턴 뱉어지게 하기. 
	// public Object add(Object a, Object b) {
		//return 0;
		// 호출했을 때, return 뱉어내기만 하면 된다. 
	// }
	//select a.*, b.* from board a, reply b where a.seq = 1 and a.seq = b.seq(+);
	
	// ---- board + reply 조인 사용해 가져오기
	public BoardVO boardReplySelect(int seq){
		// 프로토타입 선언 : 메서드 이름짓고, 파라미터 뭘보 받고 리턴리스트 뱉어지게. => 이렇게 작성하기.
		//ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		//return list;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DataSource ds = null;
		MyOracleConnection moc = new MyOracleConnection();
		BoardVO bvo = new BoardVO();
		try {
			ds = moc.myOracleDataSource();
			conn = ds.getConnection();

			String sql = "select b.seq, b.title, b.regid, b.regdate, r.rseq , r.reply, r.regid as rregid, r.regdate as rregdate from board b, reply r where b.seq=? and b.seq = r.seq(+) order by r.seq desc";	// 바인딩
			pstmt = conn.prepareStatement(sql);		// 바인딩해서 실행시점에 사용하겠다!  없으면 없는데로, 있으면 있는대로 사용이 가능함.
			pstmt.setInt(1, seq);					// 바인딩 하나 있어서 그 자리에 seq를 주는 것.
			rs = pstmt.executeQuery();
			
			List<ReplyVO> replyList = new ArrayList<ReplyVO>();		// 댓글은 ReplyVO에 있는데 그걸 불러
			ReplyVO rvo = new ReplyVO();
			while(rs.next()) {		// TRUE 일때까지 WHILE처리.
				bvo = null;
				if(bvo == null) {	// null일때, 한번만 담아라! 이런 용도로 하는것! 덮어서 한번만 실행되긴 하지만 굳이 그렇게 하지 않고 null 조건을 줘서 실행.
					bvo.setSeq(rs.getInt("seq"));		// set으로 받았기 때문에  get으로 꺼내기.
					bvo.setTitle(rs.getString("title"));
					bvo.setContents(rs.getString("contents"));
					bvo.setRegid(rs.getString("regid"));
					bvo.setRegate(rs.getString("regdate"));					
				}
				
//				ReplyVO rvo = new ReplyVO();	// 불러온 글의 댓글을 찾아서 bvo에 붙여 넣은 것. 
				rvo.setRseq(rs.getInt("rseq"));
				rvo.setReply(rs.getString("reply"));
				rvo.setRegid(rs.getString("rregid"));
				rvo.setRegdate(rs.getString("rregdate"));
				// BoardVO List<ReplyVO> replies;
				// List<ReplyVO> replyList
				replyList.add(rvo);		// 즉, board 1개에 rvo를 담아둠.
				// 리스트에 rvo를 추가요!해서 넣은 것.
			}
			bvo.setReplies(replyList);	// 루프 밖에서 한번에 담으면 되기 때문에 밖에 저장.
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			moc.oracleClose(conn, pstmt, rs);
		}		
		return bvo;// BoardVO에 reply가 있다.
		
	}
	
	// ---------------------------------------
//	public BoardVO	boardSelect(int seq) {
//		BoardVO bvo = new BoardVO();
//		// 1. 변수들 CPR
//		// 2. SQL 만들기
//		// 3. DB커넥션
//		// 1건 => next()
//		// ex. 게시판 댓글 가져오려면 while(next()) {
//		//	// 게시물 1건
//		//	// 리스트(댓글) -- 여러개
//		// }
//		// bvo.setReplies(리스트);
//		return bvo;
//	}
	
	// main 호출
	// BoardVO bvo = new boardSelectM();
	//-----------------------------------------
	
	// 조인 없이 가져오기 -- 게시글
	// boardSelectOne(int seq)
	public BoardVO boardSelectOne(int seq) {
		// 한 것만 가져올 것이기 때문에 그냥 BoardVO 를 담아 가져오는 것.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DataSource ds = null;
		MyOracleConnection moc = new MyOracleConnection();
		
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO bvo = new BoardVO();
		try {
			ds = moc.myOracleDataSource();
			conn = ds.getConnection();
			String sql = "select seq, title, contents, regid, regdate from board where seq=?";	// 바인딩
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			rs.next(); 	// while 필요 없다. 한 줄만 읽으면 된다. 그래서 next()만 필요해.
				
			bvo = new BoardVO();
			bvo.setSeq(rs.getInt("seq"));
			bvo.setTitle(rs.getString("title"));
			bvo.setContents(rs.getString("contents"));
			bvo.setRegid(rs.getString("regid"));
			bvo.setRegate(rs.getString("regdate"));
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			moc.oracleClose(conn, pstmt, rs);
		}		
		return bvo;
	}
	
	// 조인 없이 가져오기. --댓글
	public List<ReplyVO> replySelect(int seq) {
		ArrayList<ReplyVO> list = new ArrayList<ReplyVO>();
		// 변수들
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// DB
		DataSource ds = null;
		MyOracleConnection moc = new MyOracleConnection();
		try {
			ds = moc.myOracleDataSource();
			conn = ds.getConnection();
			// sql
			String sql = "select rseq, reply, regid, regdate from reply where seq=? order by seq desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);				// 1번째 값에 seq 넣어주세요.
			rs = pstmt.executeQuery();
			// while -> 
			while(rs.next()) {		// TRUE 일때까지 WHILE처리. => rseq, reply, regid, regdate 
				ReplyVO rvo = new ReplyVO();
				rvo.setRseq(rs.getInt("rseq"));
				rvo.setReply(rs.getString("reply"));
				rvo.setRegid(rs.getString("regid"));
				rvo.setRegdate(rs.getString("regdate"));
				list.add(rvo);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			moc.oracleClose(conn, pstmt, rs);
		}		
		return list;
	}
	
	
//	public List<ReplyVO> replySelect(int seq) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
//		DataSource ds = null;
//		MyOracleConnection moc = new MyOracleConnection();	// 클래스 분리 -> 인스턴스 생성 후 사용
//		
//		try {
//			//---------------DBCP를 사용한 DB 연결 -----------------------
//			// conn.moc.oracleConn();	// 일반연결
//			ds = moc.myOracleDataSource();
//			conn = ds.getConnection(); 
//			String sql = "select * from reply where seq=? order by rseq desc";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, seq);		
//			rs = pstmt.executeQuery();
//			rs.next(); 	// while 필요 없다. 한 줄만 읽으면 된다. 그래서 next()만 필요해.
//			while(rs.next()) {		// TRUE 일때까지 WHILE처리.
//				BoardVO bvo = new BoardVO();
//				bvo.setSeq(rs.getInt("seq"));		// set으로 받았기 때문에  get으로 꺼내기.
//				bvo.setTitle(rs.getString("title"));
//				bvo.setContents(rs.getString("contents"));
//				bvo.setRegid(rs.getString("regid"));
//				bvo.setRegate(rs.getString("regdate"));
//				list.add(bvo);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			moc.oracleClose(conn, pstmt, rs);
//		}		
//		return list;
//	}
	
//	// boardSelectOne(int seq)
//	public BoardVO boardSelectOne(int seq) {
//		// 한 것만 가져올 것이기 때문에 그냥 BoardVO 를 담아 가져오는 것.
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		DataSource ds = null;
//		MyOracleConnection moc = new MyOracleConnection();
//		
//		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
//		BoardVO bvo = null;		
//		try {
//			ds = moc.myOracleDataSource();
//			conn = ds.getConnection();
//			String sql = "select seq, title, contents, regid, regdate from board where seq=?";	// 바인딩
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, seq);
//			rs = pstmt.executeQuery();
//			rs.next(); 	// while 필요 없다. 한 줄만 읽으면 된다. 그래서 next()만 필요해.
//				
//			System.out.println(rs.toString());
//			bvo = new BoardVO();
//			bvo.setSeq(rs.getInt("seq"));
//			bvo.setTitle(rs.getString("title"));
//			bvo.setContents(rs.getString("contents"));
//			bvo.setRegid(rs.getString("regid"));
//			bvo.setRegate(rs.getString("regdate"));
//			
//		} catch(SQLException e) {
//			e.printStackTrace();
//		} finally {
//			moc.oracleClose(conn, pstmt, rs);
//		}		
//		return bvo;
//	}
	
	public ArrayList<BoardVO> boardSelect() {	// 파라미터 없음. 원하는 값을 주기위해서 List처리한 것.
		// boardVO를 통해 get해서 데이터 꺼내기.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		DataSource ds = null;
		MyOracleConnection moc = new MyOracleConnection();	// 클래스 분리 -> 인스턴스 생성 후 사용
		
		try {
			//---------------DBCP를 사용한 DB 연결 -----------------------
			// conn.moc.oracleConn();	// 일반연결
			ds = moc.myOracleDataSource();
			conn = ds.getConnection(); 
			String sql = "select * from board order by seq desc";
			pstmt = conn.prepareStatement(sql);		// 바인딩해서 실행시점에 사용하겠다!  없으면 없는데로, 있으면 있는대로 사용이 가능함.
			rs = pstmt.executeQuery();
			
			while(rs.next()) {		// TRUE 일때까지 WHILE처리.
				BoardVO bvo = new BoardVO();
				bvo.setSeq(rs.getInt("seq"));		// set으로 받았기 때문에  get으로 꺼내기.
				bvo.setTitle(rs.getString("title"));
				bvo.setContents(rs.getString("contents"));
				bvo.setRegid(rs.getString("regid"));
				bvo.setRegate(rs.getString("regdate"));
				list.add(bvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			moc.oracleClose(conn, pstmt, rs);
		}		
		return list;
	}		
	
	// 추가 컬럼이 생겼을 때, 추가만 하고 다른 곳은 수정하지 않아도 된다. 
	public int boardInsert(BoardVO bvo) {	// 개별컬럼이 아닌 다 들어 있는 vo를 넣어주면 된다. 
		// seq		*title	*contents	*regid				regdate
		// nextval						session, cookies	sysdate
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertRows = 0;
		ResultSet rs = null;
		DataSource ds = null;
		
		MyOracleConnection moc = new MyOracleConnection();	//클래스 분리시켜놓아서 인스턴스 생성해서 사용.
//		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			moc.myOracleDataSource();
			conn = moc.oracleConn();
			// String sql = 
			String sql ="insert into board values (board_seq.nextval, ?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bvo.getTitle());
			pstmt.setString(2, bvo.getContents());   	
			pstmt.setString(3, bvo.getRegid());
			insertRows =  pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			moc.oracleClose(conn, pstmt);
		}
		return insertRows;
	}
	
	// 수정
	//public int boardUpdate(String title, String contetns, String regdate ) {
	public int boardUpdate(BoardVO bvo) {	// BoardVO를 받으면 여기 있는 모든 값을 수정가능하다.
		Connection conn = null;
		PreparedStatement pstmt  = null;
		int updateRows = 0;	// 제일 먼저 적기.
		DataSource ds = null;
		MyOracleConnection moc = new MyOracleConnection();  //클래스 분리시켜놓아서 인스턴스 생성해서 사용
		try {
			//---------------DBCP를 사용한 DB 연결 -----------------------
			//conn = moc.oracleConn();
			ds = moc.myOracleDataSource();
			conn = ds.getConnection();  

			String sql = "update board set title=?, contents=? where seq=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bvo.getTitle());
			pstmt.setString(2, bvo.getContents());   	
			pstmt.setInt(3, bvo.getSeq());   	
			updateRows =  pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			moc.oracleClose(conn, pstmt);
		}
		return updateRows;	// 제일 먼저 적기.
	}

	// 삭제
	public int boardDelete(int seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int delRows = 0;
		DataSource ds = null;
		
		MyOracleConnection moc = new MyOracleConnection();	//클래스 분리시켜놓아서 인스턴스 생성해서 사용.
		
		try {
			//---------------DBCP를 사용한 DB 연결 -----------------------
			//conn = moc.oracleConn();
			ds = moc.myOracleDataSource();
			conn = ds.getConnection();  
			
			String sql = "delete from emp where seq=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, delRows);
			delRows =  pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			moc.oracleClose(conn, pstmt);
		}
		return delRows;	
	}
}
