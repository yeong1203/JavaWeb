package com.kosta.sample.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import oracle.jdbc.datasource.OracleDataSource;

public class MyOracleConnection {
	
	static final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	static final String DB_ID = "it";
	static final String DB_PW = "0000";
	
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
	
	
	
	
	
	
	
}
