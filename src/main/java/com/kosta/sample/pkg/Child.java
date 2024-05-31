package com.kosta.sample.pkg;

/**
 * 오버라이딩(Over riding)
 * : 상속관계에서 부모 메서드를 재사용 하는 것. // 재사용: 선언부는 같고 바디부는 다를 수 있다.
 * : 접근 제어자 : protected 부모   <=   자식 public 또는 protected  
 * : 예외      : 부모 예외  >=  자식 예외
 */

class MyParent{
//	public int myPrint(String name, int age) {	// {} 블럭부터 바디 시작. 그래서 비어도 바디 있다고 하면 됨.
//		System.out.println("부모 "+name);
//		return  age;
//	}
	
//	protected int myPrint(String name, int age) {	// {} 블럭부터 바디 시작. 그래서 비어도 바디 있다고 하면 됨.
	protected int myPrint(String name, int age) throws RuntimeException {
		System.out.println("부모 "+name);
		return  age;
	}
	
//	public abstract void div(); // abstract 추가 한 것.
}

public class Child extends MyParent {
	// 굳이 복붙해서 오버라이딩 하는 이유?  --> 바디부를 바꿔서 쓰겠다!
	/** 중요!!****
	 * 아래 myPrint 메서드를 주석처리 하면 부모 myPrint 호출!!
	 */
//	public int myPrint(String name, int age) {
//		System.out.println("자식 "+name);
//		System.out.println("***");
//		return  age;
//	}
	
//	public int myPrint(String name, int age) {		// 가능(접근제어자가 크다)
//	protected int myPrint(String name, int age) {   // 가능(접근제어자가 같다)
//	private int myPrint(String name, int age) {     // -> 에러(접근제어자가 작다)
//	public int myPrint(String name, int age) throws RuntimeException {	// 가능 동일한 예외
//	public int myPrint(String name, int age) throws NullPointerException { // 가능(더 적은 예외)
	public int myPrint(String name, int age) throws RuntimeException {
		System.out.println("자식 "+name);
		System.out.println("***");
		return  age;
	}

	public static void main(String[] args) {
		Child c = new Child();
		c.myPrint("홍길동", 20);

	}

}
