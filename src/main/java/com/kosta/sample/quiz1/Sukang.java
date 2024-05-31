package com.kosta.sample.quiz1;


public class Sukang extends Score {

	// 배열 변수
	private int[] scores = new int[3];
//	private int[] scores = new int[] {0,0,0};
//	public Sukang(int[] sss) {
//		super(new int[] {0,0,0}); 		// 변수를 잡아다 넣으면 실행기가 인식을 못함. 그래서 값을 그대로 넣어야 함.
////		super(sss);
//	}
	
	
	//생성자 : 기본생성자
	public Sukang() {
		// super();	// 숨어있는 키워드
	}
//	
	// 추가 생성자 ... 부모꺼 호출 => 매개변수가 있는 생성자
	public Sukang(int k, int e, int m) { 
//		super(k, e, m);	// 부모의 해당 생성자에 대입.
//		super();		// 숨은코드
		scores[0] = k;
		scores[1] = e;
		scores[2] = m;
		new Score(scores);		// 위에 값을 받으면서 초기화하기 때문에 앞에 super 사용불가능.
		
	}
	
	public Sukang(int[] ss) {
		super(ss);
//		for(int i=0; i<ss.lenght; i++) {
//			System.out.println(ss[i]+"\t");
//		}
	}
	
//	public static int[] getSukang(int aaa) {
//		Sukang sks = new Sukang();
//		return sks.scores;
//	};
	
	public static void main(String[] a) {
		new Sukang();
		
//		Sukang s = new Sukang();
		new Sukang(100,90,80);	// public Sukang(int k, int e, int m) 생성자에 대입.
		new Sukang(88,77,66);
		
		System.out.println();
		
//		new Sukang(new int[] {100,90,80});	// 배열채로 넘겨준다.

//		int[] temp = new int[] {100,90,80};
//		new Sukang(temp);
//		
//		new Sukang(new int[] {88,77,66});
		
//		int[] sss = new int[] {0,0,0};	// new 인스턴스 초기화 해서 넣어주기.
//		Sukang su = new Sukang(ss);	// 생성자가 받는다고 하면, 위에 생성자에서 전달해주면 됨.
////		Sukang su = new Sukang();	// Sukang() 부를 때, 아무것도 안줌.
	}

}
