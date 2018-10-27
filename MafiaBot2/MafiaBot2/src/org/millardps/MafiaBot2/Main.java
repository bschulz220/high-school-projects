package org.millardps.MafiaBot2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {
	private int playerNum = 0;
	private int mafiaNum = 0;
	private int killIndex = 0;
	private int saveIndex = 0;
	private int investigateIndex = 0;
	private int execIndex = 0;
	private boolean go = true;
	private boolean docAlive = true;
	private boolean sheriffAlive = true;
	private Scanner intScanner;
	private ArrayList<String> players;
	private ArrayList<String> roles;
	private ArrayList<String> alive;
	private ArrayList<Integer> targets;
	private ArrayList<String> resp;//responses
	
	public Main() {
		intScanner = new Scanner(System.in);
		setPlayerNum();
		roles = getRoles();
		play();
	}
	
	/**
	 * Runs wake() until the game is supposed to end.
	 */
	public void play() {
		while (go) {
			wake();
		}
	}
	
	/**
	 * Runs all of the main parts of the game.
	 */
	public void wake() {
		checkAlive();
		debug();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		boolean mafiaBool = true;
		boolean docBool = true;
		boolean sheriffBool = true;
		boolean execBool = true;
		for(String role: roles){
			if(role == "doctor" && alive.get(roles.indexOf(role)) == "dead"){
				docAlive = false;
			}
		}
		for(String role: roles){
			if(role == "sheriff" && alive.get(roles.indexOf(role)) == "dead"){
				sheriffAlive = false;
			}
		}
		System.out.println("Mafia, wake up! Who would you like to kill? Please use player numbers. There are " + playerNum + " players.");
		while (mafiaBool) {
			if (intScanner.hasNextInt()) {
				int z = intScanner.nextInt();
				if (z <= playerNum && z > 0 && alive.get(z-1) != "dead") {
					temp.add(z);
					killIndex = z;
					mafiaBool = false;
				}
				else {
					System.out.println("That's not a valid player number.");
				}
			}
			else {
				System.out.println("Please use numbers only.");
				intScanner.next();
			}
		}
		System.out.println("Doctor, wake up! Who would you like to save? Please use player numbers. There are " + playerNum + " players.");
		if (docAlive) {
			while (docBool) {
				if (intScanner.hasNextInt()) {
					int z = intScanner.nextInt();
					if (z <= playerNum && z > 0 && alive.get(z-1) != "dead") {
						temp.add(z);
						saveIndex = z;
						docBool = false;
					}
					else {
						System.out.println("That's not a valid player number.");
					}
				}
				else {
					System.out.println("Please use numbers only.");
					intScanner.next();
				}
			}
		}
		System.out.println("Sheriff, wake up! Who would you like to investigate? Please use player numbers. There are " + playerNum + " players.");
		if (sheriffAlive) {
			while (sheriffBool) {
				if (intScanner.hasNextInt()) {
					int z = intScanner.nextInt();
					if (z <= playerNum && z > 0 && alive.get(z-1) != "dead") {
						temp.add(z);
						investigateIndex = z;
						sheriffBool = false;
					}
					else {
						System.out.println("That's not a valid player number.");
					}
				}
				else {
					System.out.println("Please use numbers only.");
					intScanner.next();
				}
			}
			if (roles.get(investigateIndex - 1).equals("mafia")) {
				System.out.println("Player " + investigateIndex + " is a member of the mafia!");
			}
			else {
				System.out.println("Player " + investigateIndex + " is not a member of the mafia.");
			}
		}
		System.out.println("Villagers, wake up!");
		explainDeath();
		if (killIndex == saveIndex) {
			System.out.println("Luckily, the town doctor was able to make it to player " + killIndex + " in time to save them.");
		}
		else {
			System.out.println("The doctor was unable to save them, so player " + killIndex + " is now dead.");
			alive.set(killIndex-1, "dead");
		}
		System.out.println("Townspeople, who would you like to execute for the crime? Please use player numbers. There are " + playerNum + " players.");
		while (execBool) {
			if (intScanner.hasNextInt()) {
				int z = intScanner.nextInt();
				if (z <= playerNum && z > 0 && alive.get(z-1) != "dead") {
					temp.add(z);
					execIndex = z;
					execBool = false;
				}
				else {
					System.out.println("That's not a valid player number.");
				}
			}
			else {
				System.out.println("Please use numbers only.");
				intScanner.next();
			}
		}
		alive.set(execIndex-1, "dead");
		System.out.println("Player " + execIndex + " has been put to death for what may or may not have been their crime.");
//		while (targets.size() < mafiaNum) {
//			for (String p: roles) {
//				if (p == "mafia") {
//					int mafiaIndex = roles.indexOf(p);
//					System.out.println(mafiaIndex);
//					String name = players.get(mafiaIndex);
//					System.out.println(name + ", who would you like to kill? Please use player numbers. There are " + playerNum + " players.");
//					if (intScanner.hasNextInt()) {
//						int z = intScanner.nextInt();
//						if (z <= playerNum) {
//							temp.add(z);
//						}
//					}
//					else {
//						System.out.println("Please use numbers only.");
//						intScanner.next();
//					}
//				}
//			}
//		}
		targets = temp;
		System.out.println(targets);
		checkAlive();
		//go = false;
	}
	
	/**
	 * Prints everything of importance out to see what's going on in the code.
	 */
	public void debug() {
		System.out.println("docAlive = " + docAlive);
		System.out.println("sheriffAlive = " + sheriffAlive);
		System.out.println(players);
		System.out.println(roles);
		System.out.println(alive);
		System.out.println();
	}
	
	/**
	 * Checks if all of the mafia or all of the townspeople are dead and ends the game if either group is completely dead.
	 */
	public void checkAlive() {
		int deadNum = 0;
		int deadMafia = 0;
		for (String p: alive) {
			if (!roles.get(alive.indexOf(p)).equals("mafia")) {
				if (p.equals("dead")) {
					deadNum++;
				}
			}
			else {//doesn't end when mafia should win and executed docs/sheriffs aren't recognized as dead
				if (p.equals("dead")) {
					deadMafia++;
				}
			}
			if (roles.get(alive.indexOf(p)).equals("doctor")) {
				if (p.equals("dead")) {
					docAlive = false;
				}
			}
			if (roles.get(alive.indexOf(p)).equals("sheriff")) {
				if (p.equals("dead")) {
					sheriffAlive = false;
				}
			}
		}
		if (deadNum >= playerNum - mafiaNum) {
			System.out.println("Game over! The mafia has eliminated all of the townspeople.");
			go = false;
		}
		if (deadMafia >= mafiaNum) {
			System.out.println("Game over! The townspeople have eliminated all of the mafia.");
			go = false;
		}
	}
	
	/**
	 * Picks a random explanation for a player's death and prints it.
	 */
	public void explainDeath() {
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("Unfortunately, last night, player " + killIndex + "'s candle selling party went horribly wrong, and them and their house were both terribly burned.");
		temp.add("Unfortunately, last night, player " + killIndex + " was killed in a freak nailgun accident while working on their roof.");
		temp.add("Unfortunately, last night, player " + killIndex + " was run over by an unidentified semi while they were working on a highway.");
		temp.add("Unfortunately, last night, player " + killIndex + " was picked up by a tornado and thrown across town into the courthouse.");
		temp.add("Unfortunately, last night, player " + killIndex + " got mugged and knocked out in the freezing rain. When they woke up, they slipped on the ice and cracked their head open.");
		resp = temp;
		int rand = new Random().nextInt(resp.size());
		System.out.println(resp.get(rand));
	}
	
	/**
	 * Asks how many players there will be and sets the playerNum to the user's input if the input is an int.
	 */
	public void setPlayerNum() {
		ArrayList<String> temp = new ArrayList<String>();
		System.out.println("How many players will be playing?");
		int x = 0;
		while (playerNum < 6) {
			if (intScanner.hasNextInt()) {
				x = intScanner.nextInt();
				if (x > 5) {
					playerNum = x;
					System.out.println(playerNum);
					getPlayers();
				}
				else {
					System.out.println("Please pick a number greater than 5.");
				}
			}
			else {
				System.out.println("Please enter only numbers.");
				intScanner.next();
			}
		}
		for (int y=0; y<playerNum; y++) {
			temp.add("alive");
		}
		alive = temp;
	}
	
	/**
	 * Adds the players to an ArrayList with a number for each player (player1, player2, ...)
	 * @return An ArrayList of player id's
	 */
	public void getPlayers() {
		ArrayList<String> temp = new ArrayList<String>();
		for (int e=0; e<playerNum; e++) {
			temp.add("Player " + (e + 1));
		}
		this.players = temp;
		System.out.println(players);
	}
	
	/**
	 * Adds all of the roles for the players to an ArrayList and randomizes it.
	 * @return A randomized ArrayList of roles.
	 */
	public ArrayList<String> getRoles() {
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("doctor");
		temp.add("sheriff");
		mafiaNum = playerNum / 4;
		if (playerNum > 12) {
			for (int y=0; y<mafiaNum; y++) {
				temp.add("mafia");
			}
		}
		else {
			temp.add("mafia");
			temp.add("mafia");
		}
		while (temp.size() < playerNum) {
			temp.add("villager");
		}
		Collections.shuffle(temp);
		System.out.println(temp);
		System.out.println(temp.size());
		return temp;
	}
}
