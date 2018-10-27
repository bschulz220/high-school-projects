package org.millardps.BubbleSort;

import java.util.ArrayList;

public class BubbleSort {
	private boolean swapped;
	private ArrayList<Integer> numbers = new ArrayList<Integer>();
	private ArrayList<Integer> sorted = new ArrayList<Integer>();
	
	public BubbleSort() {
		numbers = addNumbers();
		sorted = swap();
		System.out.println("The sorted list is: " + sorted);
	}
	
	public ArrayList<Integer> swap() {
		ArrayList<Integer> y = numbers;
		do {
			swapped = false;
			for (int n: y) {
				int firstNum = n;
				int firstNumIndex = y.indexOf(firstNum);
				if (firstNumIndex != y.size()-1) {
					int secNum = y.get(y.indexOf(n) + 1);
					int secNumIndex = y.indexOf(secNum);
					if (firstNum > secNum) {
						y.set(firstNumIndex, secNum);
						y.set(secNumIndex, firstNum);
						swapped = true;
					}
				}
				System.out.println(y);
			}
		} while (swapped);
		return y;
	}
	
	public ArrayList<Integer> addNumbers() {
		ArrayList<Integer> temp = numbers;
		temp.add(930);
		temp.add(345);
		temp.add(653);
		temp.add(45);
		temp.add(65);
		temp.add(12);
		temp.add(456);
		temp.add(123);
		return temp;
	}
	
}
