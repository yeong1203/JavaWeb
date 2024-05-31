package com.kosta.sample.shop;
//import java.lang.Object;	// 자동으로 추가가 된다. 없을 경우에만! object는


/** Quiz
 * 패키지 생성 : com.kosa.sample.shop
 * 부모 클래스 : User 
 * 부모 메서드 : getUserName(String uid)
 * 
 * 공통 변수 : orderPoint = 100   //값 변경 불가, 직접 접근 불가
 * 공통 메서드 : getCompName(), order(int price)
 * 
 * 자식 클래스 : Member   Guest
 * Member : order(int price) 시 구매 금액의 10% 추가 적립
 * Guest  : order(int price) 시 구매 금액 적립 불필요
 * 
 */


class User {
	public String getUserName(String uid) {
		// DB 조회 select ename from emp where userid = 'uid';
		return "홍길동";
	}
	
	public int orderByUser(Order o, int price) {	// composite 단계 --> order클래스로 분류단계 
		// 런타임시 타입체크 : isInstance() 		o.isInstance(MemberOrder)
		// 런파일 시 타입체크  : instanceOf		o.instanceOf MemberOrder
		int res = o.order(price);	// MemberOrder == o
		return res;
	}
}



// --------------------------------------------
//class Member extends User { 
//	// 메서드
//}
////----------------------------------------------
//class Guest extends User { 
//	// 메서드
//}



class Order {
	private final int ORDER_POINT = 100; // 값 변경 불가, 직접 접근 불가.
	public int getOrderPoint() {	// 직접 접근 불가한 애를 가져다 사용해야해서 만듬.
		return this.ORDER_POINT;
	}
	public int order(int price) {
		// 부모 order를 불러서 여기에 쓰고 그 개별 값을 member에 부모 order 불러쓰고 추가만 하기.
		int res = 0;
		return res;
	}
}
class MemberOrder extends Order{ 
	@Override
	public int order(int price) {
		super.order(price);	// 부모꺼 가지고 오기
		int res = price + getOrderPoint() + (int)(price+0.1);
		return res;
	}
}
//------------------------------------
class GuestOrder extends Order { 
	@Override
	public int order(int price) {
		super.order(price);	// 부모꺼 가지고 오기
		int res = price + getOrderPoint();
		return res;
	}
}
// -------------------------------------

public class ShopCallTest2 {
	public static void main(String[] args) {
		User u = new User();
		int mRes = u.orderByUser(new MemberOrder(), 1000);
		System.out.println("회원구매 : "+mRes);
		
		int gRes = u.orderByUser(new GuestOrder(), 1000);
		System.out.println("비회원구매 : " + gRes);	
	}
	
}
