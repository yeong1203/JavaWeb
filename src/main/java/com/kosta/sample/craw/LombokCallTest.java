package com.kosta.sample.craw;

public class LombokCallTest {

	public static void main(String[] args) {
		NewsVO nvo = new NewsVO();
		nvo.setTitle("zzz");
		
		String title = nvo.getTitle();
		System.out.println(title);
		
	}

}
