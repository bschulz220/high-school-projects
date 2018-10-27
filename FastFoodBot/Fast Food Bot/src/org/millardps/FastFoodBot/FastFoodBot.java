package org.millardps.FastFoodBot;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FastFoodBot {
	//Attributes
	private Scanner textScanner;
	private String name;
	private ArrayList<String> menu;
	private ArrayList<String> toppings;
	private ArrayList<String> resp;//responses
	private ArrayList<Food> order;
	private ArrayList<Double> price;
	private Random rand;
	private double total;
	
	//Constructor
	public FastFoodBot(String n) {
		name = n;
		textScanner = new Scanner(System.in);
		menu = getMenu();
		toppings = getToppings();
		//resp = getResp();
		order = new ArrayList<Food>();
		price = setPrice();
	}
	//methods

	public ArrayList<String> getMenu() {
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("hamburger");
		temp.add("cheese curds");
		temp.add("fries");
		temp.add("bepis");
		temp.add("chicken salad");
		temp.add("milkshake");
		temp.add("hot dog");
		return temp;
	}
	
	public ArrayList<Double> setPrice() {
		ArrayList<Double> temp = new ArrayList<Double>();
		temp.add(.99);
		temp.add(1.07);
		temp.add(1.03);
		temp.add(.99);
		temp.add(1.03);
		temp.add(2.00);
		temp.add(1.15);
		return temp;
	}
	
	public void printMenu() {
		int count = 0;
		for (String s: menu) {
			System.out.println(s + "    " + price.get(count));
			count++;
		}
	}
	public void runBot() {
		System.out.println("Welcome to Good Burger, home of the Good Burger. May I take your order?");
		boolean first = true;
		breakpoint:
		while(true) {
			while(true) {
				if (!first) {
					System.out.println(resp.get(rand.nextInt(resp.size()-1)));
					System.out.println("What else would you like?");
				}
				Food thing = processOrder(textScanner.nextLine().toLowerCase());
				if(thing != null) {
					order.add(thing);
				}
				else {
					System.out.println("What did you say? Repeat yourself.");
				}
				first = false;
				break;
			}
			printCurrentOrder();
			System.out.println("Will that be all for you today?");
			String yesNo = textScanner.nextLine().toLowerCase();
			if (yesNo.contains("yes")) {
				break;
			}
			
		}
		printPrice();
		System.out.println("Please pull up to the twentieth century fox window.");
	}
	
	public void printCurrentOrder() {
		if (order.size() > 0) {
			for (Food f: order) {
				f.printString();
			}
		}
	}

	private Food processOrder(String nextLine) {
		for (String menuItem: menu) {
			if(nextLine.contains(menuItem)) {
				Food f = new Food(menuItem, price.get(menu.indexOf(menuItem)), figureToppings(nextLine));
				return f;
			}
		}
		return null;
	}

	private ArrayList<String> figureToppings(String nextLine) {
		boolean special = false;
		ArrayList<String> theseTops = new ArrayList<String>();
		for (String t: toppings) {
			if (nextLine.contains(t)){
				special = true;
				break;
			}
		}
		if (!special) {
			return theseTops;
		}
		else {
			if(nextLine.contains("only")) {
				String [] words = nextLine.split(" ");
				String [] words2 = new String[2];
				for (int i=1; i<words.length; i++) {
					if (words[i].equals("only")) {
						words2[0] = words[i-1];
						if (words.length < i+2) {
							words2[1] = words[i+1];
						}
						else{
							words2[1] = "";
						}
					}
				}
				//String [] words2 = {words[words.indexOf("only") + 1], words[words.indexOf("only") - 1]};
				for (String top: toppings) {
					for (String top2: words2) {
						if(top.equals(top2)) {
							theseTops.add(top);
						}
					}
				}
			}
			else {
				for (String t: toppings) {
					if (nextLine.contains(t)){
						theseTops.add(t);
					}
				}
			}
			return theseTops;
		}
	}

	private void printPrice() {
		for(Food f: order) {
			total+=f.getPrice();
		}
		System.out.println("Your total is " + total + ".");
	}
	public ArrayList<String> getToppings() {
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("ketchup");
		temp.add("mustard");
		temp.add("onions");
		temp.add("mayo");
		temp.add("pickles");
		temp.add("salt");
		return temp;
	}
	
	private ArrayList<String> getResp() {
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("Have you tried our fries? They are expertly crafted by fry artisans.");
		temp.add("You should get a shake with that.");
		temp.add("Our hot dogs are made with real horse now!");
		temp.add("We have the largest selection of Bepis products in town.");
		temp.add("We got rid of the rats yesterday, so now we can sell you our chicken salad again.");
		return temp;
	}
}