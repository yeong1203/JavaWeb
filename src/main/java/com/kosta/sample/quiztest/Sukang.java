package com.kosta.sample.quiztest;

/** Q. 문제
 *  패키지명 com.kosta.sample.quiz1
 *  다음과 같은 정보를 관리하는 Sukang 클래스가 있다.
 *   이름	 국어	 영어	 수학
 *  ----------------
 *  홍길동 100 90 80
 *  아무개  88 77 66
 *  ####################################
 *  1. Sukang 클래스에 국어, 영어, 수학 점수를 담을 수 있는 빈 배열 변수 score를 생성하세요.
 *  	배열변수 scores : 인스턴스변수(접근제어자 private)
 *  	생성자 : 기본 생성자
 *  
 *  2. Sukang 클래스의 부모 클래스 Score를 생성하세요.
 *  	생성자 1. "국어 영어 수학" 이라는 타이틀을 출력하는 함수
 *  		결과) 국어 영어 수학
 *  	생성자 2. 국어 영어 수학 점수를 매개변수로 받아 출력하는 함수
 *  		+ 생성자1 함수 사용하기
 *  		결과 ) 국어 영어 수학
 *  			 100  90  80
 *  			 국어  영어  수학
 *  			 88   77   66
 *  3. Score 클래스에 각 과목의 "과목명 평균점수"를 계산해서 리턴해주는 subjectAverage()함수 생성.
 *  	==> 평균 점수 대신 sum(총합) 구하기
 *  
 *  4. Score 클래스에 전체응시인원 수를 리턴해주는 userCount()함수를 생성하세요.
 *  
 *  5. Score 클래스에서 subjectAverage()함수를 재 사용해 사용자 이름도 같이 출력되는 함수를 생성하세요.
 *  
 */

public class Sukang extends Score {
	
	private int[] scores = new int[3];
	
	public Sukang() {
		// 기본 생성자
	}
	
	public Sukang(int k, int e, int m) {
//		super(k,e,m);	// 부모의 Score() 생성자 사용.
		Score.kSum = k;
		Score.eSum = e;
		Score.mSum = m;		
	}

	public int getkSum() {
		return Score.kSum;
	}
	public int geteSum() {
		return Score.eSum;
	}
	public int getmSum() {
		return Score.mSum;
	}

	
	public static void main(String[] arg) {
		// 동일값 : Score Score = new Score(88,77,66);
		Sukang[] ca = { new Sukang(100,90,80)
						, new Sukang(88,77,66) } ;
		String res = Score.subjectSum(ca);
		System.out.println(res);
		
	}

}
