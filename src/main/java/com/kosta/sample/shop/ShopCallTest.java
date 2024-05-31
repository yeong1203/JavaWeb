//package com.kosta.sample.shop;
///** Quiz
// * 패키지 생성 : com.kosa.sample.shop
// * 부모 클래스 : User 
// * 부모 메서드 : getUserName(String uid)
// * 
// * 공통 변수 : orderPoint = 100   //값 변경 불가, 직접 접근 불가
// * 공통 메서드 : getCompName(), order(int price)
// * 
// * 자식 클래스 : Member   Guest
// * Member : order(int price) 시 구매 금액의 10% 추가 적립
// * Guest     : order(int price) 시 구매 금액 적립 불필요
// * 
// */
//
//class User {
//	
//	private final int ORDER_POINT = 100; // 값 변경 불가, 직접 접근 불가.
//	public int getOrderPoint() {	// 직접 접근 불가한 애를 가져다 사용해야해서 만듬.
//		return this.ORDER_POINT;
//	}
//		
//	public String getUserName(String uid) {
//		// DB 조회  select ename from emp where userid = 'uid';
//		return "김말이";
//	}
//	
//	public int order(int price) {
//		// 1111
//		return 0;
//	}
//
//	public int orderByUser(GuestOrder guestOrder, int i) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//}
//
//class Member extends User {
//	/** 아래 식 가져온 것과 동일함.
//		public int getOrderPoint() {	// 직접 접근 불가한 애를 가져다 사용해야해서 만듬.
//			return this.ORDER_POINT;
//		} 
//	 */
//	
//	// order(int price) 오버라이딩 한 메소드
//	public int order(int price) {
//		int res = price + getOrderPoint() +(int)(price*0.1);
//		return res;
//	}
//	
//	public String getCompName() {
//		return "KOSTA";
//	}
//}
//
//class Guest extends User {
//	public int order(int price) {
//		int res = price + getOrderPoint();
//		return res;
//	}
//
//}
//
//public class ShopCallTest {
//	public static void main(String[] args) {
//		Member m = new Member();
//		int res = m.order(1000);
//		System.out.println("회원구매 : "+res );
//		
//		Guest g = new Guest();
//		int gRes = g.order(1000);
//		System.out.println("비회원구매: "+ gRes);
//	}
//}
