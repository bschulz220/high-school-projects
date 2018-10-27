package org.millardps.BubbleSort;

import java.util.ArrayList;
import java.util.Collections;

public class SelectionSort {
	private ArrayList<Integer> numbers = new ArrayList<Integer>();
	private ArrayList<Integer> sorted = new ArrayList<Integer>();
	
	public SelectionSort() {
		numbers = addNumbers();
		sorted = swap();
		System.out.println("The sorted list is: " + sorted);
	}
	
	public ArrayList<Integer> swap() {
		//Boolean first = true;
		//int lowestNum = 0;
		ArrayList<Integer> y = numbers;
		ArrayList<Integer> sortedTemp = new ArrayList<Integer>();
		int numSize = numbers.size();
		for (int i=0; i < numSize; i++) {
			System.out.println(numbers.size());
			int minimum = Collections.min(y);
			System.out.println("minimum = " + minimum);
			y.remove(y.indexOf(minimum));
			sortedTemp.add(minimum);
			System.out.println(sortedTemp);
			System.out.println("y = " + y);
			System.out.println("i = " + i);
//			for (int k=0; k < y.size()-1; k++) {
//				if (first) {
//					lowestNum = y.get(0);
//				}
//				int secNum = y.get(k);
//				int secNumIndex = y.indexOf(secNum);
//				if (secNum < lowestNum) {
//					lowestNum = secNum;
//					y.remove(secNumIndex);
//					sortedTemp.add(secNum);
//				}
//				first = false;
//				System.out.println(sortedTemp);
//			}
		}
		return sortedTemp;
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
