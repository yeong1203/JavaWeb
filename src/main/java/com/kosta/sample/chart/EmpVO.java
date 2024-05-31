package com.kosta.sample.chart;

public class EmpVO {
	//프로퍼티(property) --> 값을 다루는 변수(파란색 변수)
	// 롬복은 이 private 빼고는 다 없어질 것이다. 그런데도 돌아갈 것이다.
	private int empno;
	private String ename;
	private int sal;
	
	public EmpVO() { }	// DTO 메모리에 안올라가는 경우, 이 기본생성자가 없을 때, 
	
	//마우스우클릭>source>Generate constructor using fields ..
	public EmpVO(int empno, String ename, int sal) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.sal = sal;
	}
	
	//마우스우클릭>source>Generate getter and setter ..
	public int getEmpno() {
		return empno;
	}
	public String getEname() {
		return ename;
	}
	public int getSal() {
		return sal;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}

	//Object toString()을 오버라이딩
	@Override
	public String toString() {
		return "EmpVO [empno=" + this.empno + ", ename=" + this.ename + ", sal=" + sal + "]";
	}
	
	
	
	
//	public String getEname() {
//		return this.ename;
//	}
//	
//	public void setEname(String prmEname){
//		this.ename = prmEname;
//	}
//	public int getEmpno() {
//		return this.empno;
//	}
//	public void setEmpno(int prmEmpno) {
//		this.empno = prmEmpno;
//	}
}
