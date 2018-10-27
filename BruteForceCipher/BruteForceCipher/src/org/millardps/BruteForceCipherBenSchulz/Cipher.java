package org.millardps.BruteForceCipherBenSchulz;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Cipher {
	//attributes
	private String message;
	private String finalEncrypt;
	private int[] shift = new int[4];
	private Scanner textScanner = new Scanner(System.in);
	private Scanner numScanner = new Scanner(System.in);
	
	//constructor
	public Cipher() {
		message = getMessage();
		shift = getShift();
		finalEncrypt = "";
		performShift();
	}
	
	//methods
	/**
	 * Asks the user for a message and makes sure that message is only characters or spaces.
	 * @return A String message that we can encrypt
	 */
	public String getMessage() {
		String myMessage = "";
		while (true) {
			System.out.println("What message would you like to encrypt?");
			myMessage = this.textScanner.nextLine().toLowerCase();
			boolean useIt = true;
			for (int i=0; i<myMessage.length(); i++) {
				if (!((int)myMessage.charAt(i)>=97) && ((int)myMessage.charAt(i)<=122) && myMessage.charAt(i) != ' '){
					 useIt = false;
				}
			}
			if (useIt) {
				break;
			}
			System.out.println("The message must only contain letters and spaces.");
		}
		return myMessage;
	}
	
	/**
	 * Gets four numbers from the user to use as shifts in the cipher.
	 * Protected against input mismatch.
	 * @return int[] of shifts to use in the cipher
	 */
	public int[] getShift() {
		int[] temp = new int[4];
		while (true) {
			try {
				for (int i = 0; i < temp.length; i++) {
					System.out.println("Shift #" + (i+1) + "/4?");
					temp[i] = this.numScanner.nextInt();
				}
				break;
			}
			catch (InputMismatchException e) {
				System.out.println("Numbers only!");
				numScanner.next();
			}
		}
		return temp;
	}
	
	/**
	 * Shifts the message by the shift array amount
	 */
	public void performShift() {
		for (int i=0, count=0; i<this.message.length(); i++) {
			if (this.message.charAt(i) == ' ') {
				this.finalEncrypt += " ";
			}
			else {
				int temp = (int)message.charAt(i);
				temp += this.shift[count];
				count++;
				if (count > 3) {
					count = 0;
				}
				if (temp > 122) {
					temp -= 97;
					temp %= 26;
					temp += 97;
				}
				char next = (char)temp;
				finalEncrypt += next;
			}
		}
		System.out.println("The new message is...");
		System.out.println(finalEncrypt);
	}
	
	public String getEncrypted() {
		return this.finalEncrypt;
	}
}
