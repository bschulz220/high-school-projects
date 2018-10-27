package org.millardps.BubbleSort;

import java.util.ArrayList;
import java.util.Collections;

public class InsertSort {
	private ArrayList<Integer> numbers = new ArrayList<Integer>();
	private ArrayList<Integer> sorted = new ArrayList<Integer>();
	
	public InsertSort() {
		numbers = addNumbers();
		sorted = swap();
		System.out.println("The sorted list is: " + sorted);
	}
	
	public ArrayList<Integer> swap() {
//		Boolean first = true;
//		Boolean second = true;
		int num = 0;
		ArrayList<Integer> y = numbers;
//		ArrayList<Integer> sortedTemp = new ArrayList<Integer>();
		int numSize = numbers.size();
		for (int n: y) {
		for (int i=1; i < numSize; i++) {
			num = y.get(i);
			System.out.println("i = " + i);
			while (num < y.get(i-1) && i >= 0) {
				Collections.swap(y, i, i-1); 
			}
			System.out.println("y = " + y);
//			if (first) {
//				sortedTemp.add(y.get(0));
//				first = false;
//			}
//			else if (second) {
//				if (num < sortedTemp.get(0)) {
//					sortedTemp.add(0, num);
//				}
//				else {
//					sortedTemp.add(1, num);
//				}
//				second = false;
//			}
//			else {
//				Boolean frst = true;
//				Boolean scnd = true;
//				for (int n: y) {
////					if (frst) {
////						frst = false;
////					}
////					else if (scnd) {
////						scnd = false;
////					}
////					else {
//						//System.out.println(sortedTemp.get(sortedTemp.indexOf(n)-1) + ", " + n);
//						System.out.println("REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
////						if (num > sortedTemp.get(sortedTemp.indexOf(n)-1) && num < n) {
////							//sortedTemp.add(sortedTemp.indexOf(n), num);
////							
////						}
////						if (num > sortedTemp.get(y.indexOf(n)-1) && num < sortedTemp.get(y.indexOf(n))) {
////							
////						}
//						for (int x=y.size()-1; x>=0; x--) {
//							if (num < y.get(x)) {
//								Collections.swap(y, y.indexOf(num), x);
//							}
//						}
					//}
				//}
				
			}
		}
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
