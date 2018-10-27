package org.millardps.Recursion;

import java.util.Scanner;

public class Star {
	private Scanner intScanner = new Scanner(System.in);
	private int size = 0;
	private int v = 0;
	private int n = 0;
	private int l = 0;
	private int count = 0;
	private int j = 0;
	public Star() {
		size = 0;
		System.out.println("What size would you like your star to be?");
		setSize();
		l = 1;
		makeTop(size, 0, 0);
		makeMiddleTop();
		System.out.println();
		v = size;
		makeMiddle(0);
		count = 0;
		makeMiddleRight();
		makeBottomLeft(size, 0);
	}
	
	public void makeMiddleRight() {
		
	}
	
	public void makeMiddle(int k) {
		if (k < size/2) {
			//leftSpaces(size);
			v--;
			if (n < size) {
				if (count >= 1) {
					System.out.print("**");
				}
				else {
					System.out.print(" *");
					count++;
				}
				n++;
				makeMiddle(k);
			}
			else {
				n = k;
				k = v;
				v = 0;
				n++;
				if (n < size/2) {
					System.out.println();
					leftSpaces(l);
				}
				l++;
				v = k;
				k = n;
				n = 0;
				makeMiddle(k);
			}
		}
	}
	
	public void makeMiddleTop() {
		if (v <= size*7) {
			System.out.print("*");
			v++;
			makeMiddleTop();
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
			System.out.print("  ");//this is what's causing the line between the middle and bottom
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
