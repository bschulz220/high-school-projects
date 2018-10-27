package org.millardps.ConnectFour;

import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		String[][] str = new String[10][10];
		Scanner textScanner = new Scanner(System.in);
		
		for (int i=0; i<str.length; i++) {
			for (int z = 0; z<str[i].length; z++) {
				str[i][z] = "[" + i + " " + z + "]";
				System.out.print(str[i][z] + " ");
			}
			System.out.println();
		}
		System.out.println("What item do you want to change?");
		System.out.println("X: ");
		int x = textScanner.nextInt();
		System.out.println("Y: ");
		int y = textScanner.nextInt();
		str[x][y] = "[ X ]";
		
		for (int i=0; i<str.length; i++) {
			for (int z = 0; z<str[i].length; z++) {
				System.out.print(str[i][z] + " ");
			}
			System.out.println();
		}
	}
	
}
