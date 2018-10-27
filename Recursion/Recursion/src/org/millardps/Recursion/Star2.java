package org.millardps.Recursion;

import java.util.Scanner;

public class Star2 {
	private Scanner intScanner = new Scanner(System.in);
	private int size = 0;
	private int v = 0;
	private int middleDotCount = 0;
	private int l = 1;
	private int count = 0;
	private int j = 0;
	private int u = 0;
	private int h = 0;
	private int g = 0;
	private int permL = 0;
	private int p = 0;
	private int w = 0;
	private int midSpace = 1;
	private int r = 0;
	private int b = 0;
	public Star2() {
		size = 0;
		System.out.println("What size would you like your star to be?");
		setSize();
		//l = 1;
		makeTop(size, 0, 0);
		makeMiddleTop();
		v = 0;
		makeMiddleTopMid();
		v = 0;
		makeMiddleTop();
		System.out.println();
		v = size;
		makeMiddle(/*0*/);
		permL = l;
		//makeLegSpaces();
		v = (middleDotCount/3)-1;
		makeLegs();
		//System.out.println(middleDotCount);
//		count = 0;
//		makeMiddleRight();
//		makeBottomLeft(size, 0);
	}
	
	public void makeBtwnLegSpace() {
		if (r < midSpace) {
			System.out.print(" ");
			r++;
			makeBtwnLegSpace();
		}
		else {
			midSpace+=6;
		}
	}
	
	public void printLegStuff() {
		if (w < v) {
			System.out.print("*");
			w++;
			printLegStuff();
		}
		else {
			makeBtwnLegSpace();
			printLegStuff2();
		}
	}
	public void printLegStuff2() {
		if (b < v) {
			System.out.print("*");
			b++;
			printLegStuff2();
		}
	}
	
	public void makeLegs() {
		if (p < size*3) {
			makeLegSpaces();
			l = --permL;
			p++;
			//System.out.print(p);
			printLegStuff();
			w = 0;
			b = 0;
			r = 0;
			v--;
			v--;
			System.out.println();
			makeLegs();
		}
	}
	
	public void makeLegSpaces() {
		if (g < l) {
			System.out.print(" ");
			l--;
			makeLegSpaces();
		}
	}
	
	public void makeMiddleSpaces() {
		if (v < l) {// <- that's an L, not a one
			System.out.print(" ");
			v++;
			makeMiddleSpaces();
		}
	}
	
	public void doStuff() {
		//middleDotCount--;
		if (u <= middleDotCount-l-l+1) {
			System.out.print("*");
			u++;
			doStuff();
		}
	}
	public void makeMiddle(/*int k*/) {//bottom of top is always size*2+1
		if (h <= size) {
			v = 0;
			makeMiddleSpaces();
			l++;
			doStuff();
			u = 0;
			v = 0;
			h++;
			//System.out.print(l);
			System.out.println();
			makeMiddle();
		}
//		if (k < size) {
//			//leftSpaces(size);
//			v--;
//			if (n < size) {
//				if (count >= 1) {
//					System.out.print("**");
//				}
//				else {
//					System.out.print(" *");
//					count++;
//				}
//				n++;
//				makeMiddle(k);
//			}
//			else {
//				n = k;
//				k = v;
//				v = 0;
//				n++;
//				if (n < size) {
//					System.out.println();
//					leftSpaces(l);
//				}
//				l++;
//				v = k;
//				k = n;
//				n = 0;
//				makeMiddle(k);
//			}
//		}
	}
	
	public void makeMiddleTop() {
		if (v <= (size*2+1)) {
			System.out.print("*");
			v++;
			middleDotCount++;
			makeMiddleTop();
		}
	}
	
	public void makeMiddleTopMid() {
		if (v < (size*2+1)) {
			System.out.print("*");
			v++;
			middleDotCount++;
			makeMiddleTopMid();
		}
	}
	
	public void makeTop(int s, int howMany, int sStart) {
		leftSpaces(size);
		if (s > 0) {
			System.out.print(" ");
			s--;
			makeTop(s, howMany, sStart);
		}
		else {
			System.out.print("*");
			makeRightStuff(sStart, howMany);
			sStart++;
//			if (s != size) {
//				//System.out.println(size-howMany);
//				makeBottomLeftRight(size-howMany);
//			}
			if (howMany < size) {
				howMany++;
				makeTop(size-howMany, howMany, sStart);
			}
		}
	}
	
	public void leftSpaces(int f) {
		if (v <= f) {
			System.out.print("  ");//this is what was causing the line between the middle and bottom
			v++;
			leftSpaces(f);
		}
	}
	
	public void makeRightStuff(int x, int howMany) {
//		System.out.println("this ran");
//		System.out.println(x);
		if (x > 0) {
			System.out.print("**");
			x--;
			makeRightStuff(x, howMany);
		}
		else {
			System.out.println();
			v = 0;
			//y++;
		}
	}
	
	public void makeBottomLeft(int s, int howMany) {
		if (s > 0) {
//			System.out.print(" ");
			s--;
			makeBottomLeft(s, howMany);
		}
		else {
			//System.out.println("*");
			System.out.println();
			if (s != size) {
				//System.out.println(size-howMany);
				
				makeBottomLeftRight(size-howMany);
			}
			count++;
			if (howMany < size) {
				
				howMany++;
				makeBottomLeft(size-howMany, howMany);
			}
		}
	}
	
	public void makeBottomLeftRight(int s) {
		if (s > 0) {
			System.out.print(" ");
			s--;
			makeBottomLeftRight(s);
		}
		System.out.print("*");
	}
	
	/**
	 * Sets the size variable to user input.
	 */
	public void setSize() {
		if (intScanner.hasNextInt()) {
			int trySize = intScanner.nextInt();
			if (trySize > 0) {
				size = trySize;
				System.out.println("The size will be " + size);
			}
			else {
				System.out.println("Please use a number greater than zero.");
				setSize();
			}
		}
		else {
			System.out.println("Please use a number.");
			intScanner.next();
			setSize();
		}
	}
	
	
}
