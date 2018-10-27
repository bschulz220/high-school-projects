package org.millardps.Recursion;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListStar {
	private Scanner intScanner = new Scanner(System.in);
	private int size = 0;
	private int count = 0;
	private int k = 0;
	private int f = 0;
	private ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
	
	public ArrayListStar() {
		System.out.println("What size would you like your star to be?");
		setSize();
		//size *= size;
		k = 0;
		count = 0;
		makeArrayLists();
		k = 0;
		count = 0;
		printStar();
		System.out.println("lines = " + lines.size());
//		for (ArrayList<String> w: lines) {
//			System.out.println("w = " + w.size());
//			for (String s: w) {
//				System.out.println(s);
//			}
//		}
		//System.out.println(lines.get(1).get(1));
	}
	
	public void makeTop() {
		if (f < size) {
			
		}
	}
	
	public void printStar() {
		if (k < lines.size()) {
			System.out.println();
			printLines(lines.get(k));
			k++;
			count = 0;
			printStar();
		}
	}
	
	private void printLines(ArrayList<String> a) {
		if (count < a.size()) {
			System.out.print(a.get(count));
			count++;
			printLines(a);
		}
	}

	public ArrayList<String> populateArr(ArrayList<String> temp) {
		if (k < size*2/**size*/) {
			temp.add("k");
			k++;
			//System.out.println("skfjlskdf" + k);
			populateArr(temp);
		}
		//System.out.println("this rannnnnn" + k);
		k = 0;
		return temp;
	}
	
	public void makeArrayLists() {
		if (count < size) {
			ArrayList<String> temp = new ArrayList<String>();
			temp = populateArr(temp);
			lines.add(temp);
			count++;
			//System.out.println("this ran" + count);
			makeArrayLists();
		}
		k = 0;
	}
	
	/**
	 * Sets the size variable to user input.
	 */
	public void setSize() {
		if (intScanner.hasNextInt()) {
			int trySize = intScanner.nextInt();
			if (trySize > 0) {
				size = trySize;
				System.out.println("The size shall be " + size);
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
