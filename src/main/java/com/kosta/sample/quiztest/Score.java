package com.kosta.sample.quiztest;

public class Score {
	private int[] scores= new int[3];
	
	// 3. 점수를 위해 static
	static int kSum;
	static int eSum;
	static int mSum;
	

	public Score() {	// 기본생성자
//		System.out.println("국어\t 영어\t 수학");	// for문 때문에 주석처리. 도는만큼 출력되어서.
	}
	
	public Score(int k, int e, int m) {
		this();	// 기본 생성자 호출 == 생성자1 호출	
		System.out.println(k+"\t"+e+"\t"+m);
	}
	
	public static String subjectSum(Sukang[] ca) {
		int kSum = 0;
		int eSum = 0;
		int mSum = 0;	// 초기화
		// 행 1개씩 묶어서 부르기.
		for(int i=0; i<ca.length; i++) {
			kSum += ca[i].getkSum();
			eSum += ca[i].geteSum();
			mSum += ca[i].getmSum();
		}
		String result = "국어 " + kSum+ "\t 영어" + eSum + "\t 수학 " + mSum;		
		return result;
	}

	

	
	
}
