package com.kosta.other;

public class Lec07접근제어자Other {
	public int otherPublicPoint = 100;
	protected int otherProtectedPoint = 222222;

	protected static int otherProtectedPoint_static = 222222;
	
				int otherDefaultPoint = 333;

	private int otherPrivatePoint = 444;
	
	public void dum() {
		System.out.println(otherPrivatePoint);		// 
	}
}
