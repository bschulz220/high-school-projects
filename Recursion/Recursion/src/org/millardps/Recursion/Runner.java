package org.millardps.Recursion;

public class Runner {
	
	public static void recur(int num) {
		if (num <= 0) {
			return;
		}
		else {
			System.out.println(num);
			recur(--num);
		}
	}
	
	public static void recur2(int num) {
		if (num >= 0) {
			System.out.println(num);
			recur2(--num);
		}
	}
	
	public static void main(String[] args) {
		//Star star = new Star();
		//ArrayListStar s = new ArrayListStar();
		Star2 star2 = new Star2();
	}
	
}
