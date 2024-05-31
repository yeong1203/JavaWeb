package com.kosta.sample.quiz1;

import javax.security.auth.Subject;

public class Score {
	
	private int[] scores = new int[3];
	
	// by 윤 승님
	static int[][] sumScore = new int[2][3];
	private static int array_column = 0;
	
	
	// 기본 생성자
	public Score() { 
		System.out.println("국어\t 영어\t 수학");		
	};
	

	public Score(int k, int e, int m) {
//		this();  // 기본생성자 호출. title 출력.    // this(); == new Score();
		
		
		System.out.println(k+"\t"+e+"\t"+m);
		// this();는 기본생성자 출력. Score에 들어온 int값 syso에 그대로 대입 및 출력되어 나온다.
	}
	
//	public int SubjectSum(int[][] sumScore) {
//		System.out.println(sumScore[0][0]);
//	}
	
	
	public Score(int[] scores) {
		System.out.println(scores[0]+"\t"+scores[1]+"\t"+scores[2]);
	}

	public static void main(String[] args) {

		
	}
	
//	public static void main(String[] args) {
//		new Score(100,90,80);
//		new Score(88,77,66);
//	}

}
