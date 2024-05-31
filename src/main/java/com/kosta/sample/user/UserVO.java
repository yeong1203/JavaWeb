package com.kosta.sample.user;

// Entity : getter / setteer
public class UserVO {
	private int seq;
	private String userid;
	private String uname;
	private String email;
	private String passwd;
	private String regdate;
	private String grade;
	
	private boolean loginCheck;		// 로그인 체크
	
	public UserVO() { }	// PK
	
	public UserVO(int seq, String userid, String uname, String email, String passwd, String regdate, String grade) {
		this.seq = seq;
		this.userid = userid;
		this.uname = uname;
		this.email = email;
		this.passwd = passwd;
		this.regdate = regdate;
		this.grade = grade;
	}
	
	// 
	public UserVO(String userid, String passwd) {
		this.userid = userid;
		this.passwd = passwd;
	}
	

	
//	public boolean isLoginCheck() {		// booleanType은 get 아닌 is 이다.
	public boolean getLoginCheck() {
		return loginCheck;
	}

	public void setLoginCheck(boolean loginCheck) {
		this.loginCheck = loginCheck;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getUserid() {
		return userid;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getRegdate() {
		return regdate;
	} 

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	
	@Override
	public String toString() {
		return "UserVO [seq=" + seq + ", userid=" + userid + ", uname=" + uname + ", email=" + email + ", passwd="
				+ passwd + ", regdate=" + regdate + ", grade=" + grade + ", loginCheck=" + loginCheck + "]";
	}

	

//	
//	public String toString() {
//		return "userid="+userid+"\t"+"name="+uname+"\t"+"email="+email+"\t";
//	}

	
}
