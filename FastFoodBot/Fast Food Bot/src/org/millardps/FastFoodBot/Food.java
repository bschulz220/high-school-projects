package org.millardps.FastFoodBot;

import java.util.ArrayList;

public class Food {
	//attributes
	private String item;
	private double price;
	private ArrayList<String> toppings;
	//constructor
	public Food(String order, double cost, ArrayList<String> top) {
		item = order;
		price = cost;
		toppings = top;
	}
	//methods
	public String getItem() {
		return item;
	}
	public ArrayList<String> getToppings() {
		return toppings;
	}
	public double getPrice() {
		return price;
	}
	public void printString() {
		System.out.println(item);
		if (toppings.size() == 1) {
			System.out.println("    only");
			System.out.println("        " + toppings.get(0));
		}
		else if (toppings.size() == 0) {
			
		}
		else {
			System.out.println("    with");
			for(String t: toppings) {
				System.out.println("        " + t);
			}
		}
	}
}
