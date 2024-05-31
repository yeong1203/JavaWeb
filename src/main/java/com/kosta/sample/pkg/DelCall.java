package com.kosta.sample.pkg;

// 패키지 = 폴더
// 유사한 기능을 하는 클래스들의 묶음
// import com.kosta.pkg.DelDel;		// 3단 권고.
// 예약어로 패키지명 사용 불가.
// import 패키지.패키지.*(*은 클래스명)	// 다른 폴더 파일을 내 폴더에 복사해서 넣는것과 동일하다.

// 동일한 패키지 내 클래스는 import 하지 않아도 된다.
import com.kosta.sample.pkg.DelDel;

//----------------------------------------------------------------------------

class Parent {
	public Parent() {
		System.out.println("Parent() 기본생성자 콜");
	}
	public Parent(String name) {
		System.out.println("Parent(String name) 생성자 콜");
	}
}

//----------------------------------------------------------------------------

public class DelCall extends Parent {
	public DelCall() {
		// super(); // 숨어있는 키워드! 그래서 부모한테 제일 먼저 간다.
		super("ABC");
		System.out.println("DelCall() 기본생성자 콜");
	}
	public DelCall(int a) { // 오버로딩
		/**
		 * 자식의 생성자를 호출할 때, 그 안에서 부모 상속은 되어 있는데
		 * 부모의 생성자 호출이 없을 경우( super() 없을 경우, )
		 * 컴파일러가 기본부모생성자 super(); 키워드를 붙여준다.
		 */
//		super();	// extends Parent()  --> 부모의 기본생성자
		System.out.println("Delcall () 생성자 콜");
	}
	public static void main(String[] args) {
		DelCall me = new DelCall();		// Parent() 생성자 콜
										// Delcall() 기본생성자 콜
		
		DelCall me2 = new DelCall(5);	// Parent() 생성자 콜
										// Delcall(int a) 생성자 콜 
		/**
		 * 남의 클래스를 가져다 사용할 경우
		 * 1. 패키지가 다른 경우: import 패키지.패키지.DelDel;
		 * 2. 메모리에 올려야 실행 가능 == DelDel 클래스의 생성자부터 확인.
		 * 3. DelDel 클래스의 생성자로 d = new 생성자(); -->  d.메서드() 사용
		 */
		DelDel d = new DelDel();
		d.myPrint();					// ddd

	}

}

//----------------------------------------------------------------------------




