package org.millardps.ConnectFour;

import java.util.Random;
import java.util.Scanner;

public class GameRunner {

	public static void main(String[] args) {
		Scanner textScanner = new Scanner(System.in);
		Scanner intScanner = new Scanner(System.in);
		int xWins = 0;
		int oWins = 0;
		Boolean keepGoing = true;
		boolean firstVertLine = true;
		boolean aiGame = false;
		boolean aiOff = false;
		boolean aiDef = false;
		boolean aiMix = false;
		Random rand = new Random();
		while (keepGoing) {
			Boolean gameOver = false;
			System.out.println("What size would you like your board?");
			int size = 0;
			while (true) {
				if (intScanner.hasNextInt()) {
					int x = intScanner.nextInt();
					if (x > 0) {
						size = x;
						//System.out.println(size);
						break;
					}
					else {
						System.out.println("Please enter positive numbers.");
					}
				}
				else {
					System.out.println("Please enter only numbers.");
					intScanner.next();
				}
			}
			System.out.println("Would you like to play against AI or a player? (respond with 'AI' or 'player')");
			while (true) {
				if (textScanner.hasNextLine()) {
					String x = textScanner.nextLine();
					if (x.toLowerCase().equals("ai")) {
						aiGame = true;
						break;
					}
					else if (x.toLowerCase().equals("player")) {
						aiGame = false;
						break;
					}
					else {
						System.out.println("Please enter 'AI' or 'player'.");
					}
				}
				else {
					System.out.println("Please enter only strings.");
					textScanner.next();
				}
			}
			if (aiGame) {
				System.out.println("Would you like the AI to be offensive, defensive, or a mix? (respond with 'offensive', 'defensive', or 'mix')");
				while (true) {
					if (textScanner.hasNextLine()) {
						String x = textScanner.nextLine();
						if (x.toLowerCase().equals("offensive")) {
							aiOff = true;
							aiDef = false;
							aiMix = false;
							break;
						}
						else if (x.toLowerCase().equals("defensive")) {
							aiDef = true;
							aiMix = false;
							aiOff = false;
							break;
						}
						else if (x.toLowerCase().equals("mix")) {
							aiMix = true;
							aiOff = false;
							aiDef = false;
							break;
						}
						else {
							System.out.println("That's not an option.");
						}
					}
					else {
						System.out.println("Please enter only strings.");
						textScanner.next();
					}
				}
			}
			Coordinate[][] board = new Coordinate[size][size];
			for (int h = 0; h < size; h++) {
				if (size < 10) {
					System.out.print("|-----|");
				}
				else {
					System.out.print("|-------|");
				}
			}
			System.out.println();
			for (int row = 0; row < board.length; row++) {
				for (int col = 0; col < board[row].length; col++) {
					board[row][col] = new Coordinate(row, col, size);
					if (firstVertLine) {
						System.out.print("|");
						firstVertLine = false;
					}
					else {
						System.out.print("||");
					}
					board[row][col].printCoord();
				}
				firstVertLine = true;
				System.out.println("|");
				for (int h = 0; h < size; h++) {
					if (size < 10) {
						System.out.print("|-----|");
					}
					else {
						System.out.print("|-------|");
					}
				}
				System.out.println();
			}
			Boolean first = true;
			//board[x][4].makeMove(true);
				int playerNum = 2;
			//while(playerNum < 100) {
			while (!gameOver) {
				int x = 0;
				int checkNum = 0;
				Boolean moved = false;
				if (playerNum % 2 == 0) {
					System.out.println("Player X, what column would you like to place a piece in?");
					while (true) {
						if (intScanner.hasNextInt()) {
							x = intScanner.nextInt() - 1;
							//System.out.println("This is working" + x);
							if (x > size-1 || x < 0) {
								System.out.println("That's not an option.");
							}
							else if (board[0][x].getCoord().contains("X") || board[0][x].getCoord().contains("O")) {
								System.out.println(board[0][x].getCoord());
								System.out.println("That column is already full.");
							}
							else {
								break;
							}
						}
						else {
							System.out.println("That's not a number.");
							intScanner.next();
						}
					}
					for (int i = 0; i < size; i++) {
						if (!moved) {
							if (board[i][x].getCoord().contains("X") || board[i][x].getCoord().contains("O")) {
								if (!board[i-1][x].getCoord().contains("X") && !board[i-1][x].getCoord().contains("O")) {
									board[i-1][x].makeMove(true);
									checkNum = i-1;
									moved = true;
								}
							}
						}
						if (!moved && i == size-1) {
//							System.out.println("size = " + size);
//							System.out.println("i = " + i);
							if(board[size-1][x].getCoord().contains("X") || board[size-1][x].getCoord().contains("O")){
								board[size-2][x].makeMove(true);
							}
							else{
								board[size-1][x].makeMove(true);
							}
							checkNum = size-1;
							moved = true;
						}
					}
				}
				else {
					if (!aiGame) {
						System.out.println("Player O, what column would you like to place a piece in?");
						while (true) {
							if (intScanner.hasNextInt()) {
								x = intScanner.nextInt() - 1;
								if (x > size-1 || x < 0) {
									System.out.println("That's not an option.");
								}
								else if (board[0][x].getCoord().contains("X") || board[0][x].getCoord().contains("O")) {
									System.out.println(board[0][x].getCoord());
									System.out.println("That column is already full.");
								}
								else {
									break;
								}
							}
							else {
								System.out.println("That's not a number.");
								intScanner.next();
							}
						}
						for (int i = 0; i < size; i++) {
							if (!moved) {
								if (board[i][x].getCoord().contains("X") || board[i][x].getCoord().contains("O")) {
									//System.out.println(board[0][x].getCoord());
									if (!board[i-1][x].getCoord().contains("X") && !board[i-1][x].getCoord().contains("O")) {
										board[i-1][x].makeMove(false);
										checkNum = i-1;
										moved = true;
									}
								}
							}
							if (!moved && i == size-1) {
	//							System.out.println("size = " + size);
	//							System.out.println("i = " + i);
								if(board[size-1][x].getCoord().contains("X") || board[size-1][x].getCoord().contains("O")){
									board[size-2][x].makeMove(false);
								}
								else{
									board[size-1][x].makeMove(false);
								}
								checkNum = size-1;
								moved = true;
							}
						}
					}
					else { //ai pick a move ***************************************************************************************************
						if (aiOff) {
							for (int row = 0; row < board.length; row++) {
								for (int col = 0; col < board.length; col++) {
									if (board[row][col].getCoord().contains("O") && !moved) {//found an O
										try {
											if (!board[row-1][col].getCoord().contains("O") && !board[row-1][col].getCoord().contains("X")) {//check above it
												board[row-1][col].makeMove(false);
												x = col;
												moved = true;
											}
											else { //try left and right of piece
												if (!board[row][col+1].getCoord().contains("O") && !board[row][col+1].getCoord().contains("X")) {
													for (int i = 0; i < size; i++) {
														if (!moved) {//check right
															try {
																if (board[i][col+1].getCoord().contains("X") || board[i][col+1].getCoord().contains("O")) {
																	if (!board[i-1][col+1].getCoord().contains("X") && !board[i-1][col+1].getCoord().contains("O")) {
																		board[i-1][col+1].makeMove(false);
																		checkNum = i-1;
																		x = col+1;
																		moved = true;
																	}
																}
															}
															catch (ArrayIndexOutOfBoundsException e) {
																System.out.println("Can't check right 2." + e);
															}
														}
														if (!moved && i == size-1) {//still check right
															if (board[size-1][col+1].getCoord().contains("X") || board[size-1][col+1].getCoord().contains("O")){
																board[size-2][col+1].makeMove(false);
															}
															else {
																board[size-1][col+1].makeMove(false);
															}
															x = col+1;
															checkNum = size-1;
															moved = true;
														}
														if (!moved) {//check left
															try {
																if (board[i][col-1].getCoord().contains("X") || board[i][col-1].getCoord().contains("O")) {
																	if (!board[i-1][col-1].getCoord().contains("X") && !board[i-1][col-1].getCoord().contains("O")) {
																		board[i-1][col-1].makeMove(false);
																		checkNum = i-1;
																		x = col-1;
																		moved = true;
																	}
																}
															}
															catch (ArrayIndexOutOfBoundsException e) {
																System.out.println("Can't check left.");
															}
														}
														if (!moved && i == size-1) {//still check left
															if (board[size-1][col-1].getCoord().contains("X") || board[size-1][col-1].getCoord().contains("O")){
																board[size-2][col-1].makeMove(false);
															}
															else {
																board[size-1][col-1].makeMove(false);
															}
															x = col-1;
															checkNum = size-1;
															moved = true;
														}
													}
												}
											}
										}
										catch (ArrayIndexOutOfBoundsException e) {//error for up, so check sides
											if (!board[row][col+1].getCoord().contains("O") && !board[row][col+1].getCoord().contains("X")) {
												for (int i = 0; i < size; i++) {
													if (!moved) {//check right
														try {
															if (board[i][col+1].getCoord().contains("X") || board[i][col+1].getCoord().contains("O")) {
																if (!board[i-1][col+1].getCoord().contains("X") && !board[i-1][col+1].getCoord().contains("O")) {
																	board[i-1][col+1].makeMove(false);
																	checkNum = i-1;
																	x = col+1;
																	moved = true;
																}
															}
														}
														catch (ArrayIndexOutOfBoundsException v) {
															System.out.println("Can't check right." + v);
														}
													}
													if (!moved && i == size-1) {//still check right
														if (board[size-1][col+1].getCoord().contains("X") || board[size-1][col+1].getCoord().contains("O")){
															board[size-2][col+1].makeMove(false);
														}
														else {
															board[size-1][col+1].makeMove(false);
														}
														x = col+1;
														checkNum = size-1;
														moved = true;
													}
													if (!moved) {//check left
														try {
															if (board[i][col-1].getCoord().contains("X") || board[i][col-1].getCoord().contains("O")) {
																if (!board[i-1][col-1].getCoord().contains("X") && !board[i-1][col-1].getCoord().contains("O")) {
																	board[i-1][col-1].makeMove(false);
																	checkNum = i-1;
																	x = col-1;
																	moved = true;
																}
															}
														}
														catch (ArrayIndexOutOfBoundsException v) {
															System.out.println("Can't check left.");
														}
													}
													if (!moved && i == size-1) {//still check left
														if (board[size-1][col-1].getCoord().contains("X") || board[size-1][col-1].getCoord().contains("O")){
															board[size-2][col-1].makeMove(false);
														}
														else {
															board[size-1][col-1].makeMove(false);
														}
														x = col-1;
														checkNum = size-1;
														moved = true;
													}
												}
											}
										}
									}
								}
							}
							if (!moved) {
								System.out.println("no usable O yet");
								for (int u = 0; u < size; u++) {//column
									for (int i = 0; i < size; i++) {//row
										if (!moved) {
											try { 
												if (i != 0) {
													if (board[i][u].getCoord().contains("X") || board[i][u].getCoord().contains("O")) {
														if (!board[i-1][u].getCoord().contains("X") && !board[i-1][u].getCoord().contains("O")) {
															board[i-1][u].makeMove(false);
															x = u;
															checkNum = i-1;
															moved = true;
														}
													}
												}
												else {
													System.out.println("i = " + i + " right now.");
												}
											}
											catch (ArrayIndexOutOfBoundsException e) {
												System.out.println("Well, this sure ain't working." + e);
											}
										}
										if (!moved && i == size-1) {
											if (board[size-1][u].getCoord().contains("X") || board[size-1][u].getCoord().contains("O")){
												try {
												board[size-2][u].makeMove(false);
												}
												catch (ArrayIndexOutOfBoundsException bleh) {
													gameOver = true;
													System.out.println("It's a draw!");
												}
											}
											else {
												board[size-1][u].makeMove(false);
											}
											x = u;
											checkNum = size-1;
											moved = true;
										}
									}
								}
							}
						}
						else if (aiDef) {//same as offense, but it targets opponents' pieces instead of its own
							for (int row = 0; row < board.length; row++) {
								for (int col = 0; col < board.length; col++) {
									if (board[row][col].getCoord().contains("X") && !moved) {//found an X
										try {
											if (!board[row-1][col].getCoord().contains("O") && !board[row-1][col].getCoord().contains("X")) {//check above it
												board[row-1][col].makeMove(false);
												x = col;
												moved = true;
											}
											else { //try left and right of piece
												if (!board[row][col+1].getCoord().contains("O") && !board[row][col+1].getCoord().contains("X")) {
													for (int i = 0; i < size; i++) {
														if (!moved) {//check right
															try {
																if (board[i][col+1].getCoord().contains("X") || board[i][col+1].getCoord().contains("O")) {
																	if (!board[i-1][col+1].getCoord().contains("X") && !board[i-1][col+1].getCoord().contains("O")) {
																		board[i-1][col+1].makeMove(false);
																		checkNum = i-1;
																		x = col+1;
																		moved = true;
																	}
																}
															}
															catch (ArrayIndexOutOfBoundsException e) {
																System.out.println("Can't check right 2." + e);
															}
														}
														if (!moved && i == size-1) {//still check right
															if (board[size-1][col+1].getCoord().contains("X") || board[size-1][col+1].getCoord().contains("O")){
																board[size-2][col+1].makeMove(false);
															}
															else {
																board[size-1][col+1].makeMove(false);
															}
															x = col+1;
															checkNum = size-1;
															moved = true;
														}
														if (!moved) {//check left
															try {
																if (board[i][col-1].getCoord().contains("X") || board[i][col-1].getCoord().contains("O")) {
																	if (!board[i-1][col-1].getCoord().contains("X") && !board[i-1][col-1].getCoord().contains("O")) {
																		board[i-1][col-1].makeMove(false);
																		checkNum = i-1;
																		x = col-1;
																		moved = true;
																	}
																}
															}
															catch (ArrayIndexOutOfBoundsException e) {
																System.out.println("Can't check left.");
															}
														}
														if (!moved && i == size-1) {//still check left
															if (board[size-1][col-1].getCoord().contains("X") || board[size-1][col-1].getCoord().contains("O")){
																board[size-2][col-1].makeMove(false);
															}
															else {
																board[size-1][col-1].makeMove(false);
															}
															x = col-1;
															checkNum = size-1;
															moved = true;
														}
													}
												}
											}
										}
										catch (ArrayIndexOutOfBoundsException e) {//error for up, so check sides
											try {//to protect when the board is full
												if (!board[row][col+1].getCoord().contains("O") && !board[row][col+1].getCoord().contains("X")) {
													for (int i = 0; i < size; i++) {
														if (!moved) {//check right
															try {
																if (board[i][col+1].getCoord().contains("X") || board[i][col+1].getCoord().contains("O")) {
																	if (!board[i-1][col+1].getCoord().contains("X") && !board[i-1][col+1].getCoord().contains("O")) {
																		board[i-1][col+1].makeMove(false);
																		checkNum = i-1;
																		x = col+1;
																		moved = true;
																	}
																}
															}
															catch (ArrayIndexOutOfBoundsException v) {
																System.out.println("Can't check right." + v);
															}
														}
														if (!moved && i == size-1) {//still check right
															if (board[size-1][col+1].getCoord().contains("X") || board[size-1][col+1].getCoord().contains("O")){
																board[size-2][col+1].makeMove(false);
															}
															else {
																board[size-1][col+1].makeMove(false);
															}
															x = col+1;
															checkNum = size-1;
															moved = true;
														}
														if (!moved) {//check left
															try {
																if (board[i][col-1].getCoord().contains("X") || board[i][col-1].getCoord().contains("O")) {
																	if (!board[i-1][col-1].getCoord().contains("X") && !board[i-1][col-1].getCoord().contains("O")) {
																		board[i-1][col-1].makeMove(false);
																		checkNum = i-1;
																		x = col-1;
																		moved = true;
																	}
																}
															}
															catch (ArrayIndexOutOfBoundsException v) {
																System.out.println("Can't check left.");
															}
														}
														if (!moved && i == size-1) {//still check left
															if (board[size-1][col-1].getCoord().contains("X") || board[size-1][col-1].getCoord().contains("O")){
																board[size-2][col-1].makeMove(false);
															}
															else {
																board[size-1][col-1].makeMove(false);
															}
															x = col-1;
															checkNum = size-1;
															moved = true;
														}
													}
												}
											}
											catch (ArrayIndexOutOfBoundsException bleh) {
												gameOver = true;
												System.out.println("It's a draw!");
											}
										}
									}
								}
							}
							if (!moved) {
								System.out.println("no usable X yet");
								for (int u = 0; u < size; u++) {//column
									for (int i = 0; i < size; i++) {//row
										if (!moved) {
											try { 
												if (i != 0) {
													if (board[i][u].getCoord().contains("X") || board[i][u].getCoord().contains("O")) {
														if (!board[i-1][u].getCoord().contains("X") && !board[i-1][u].getCoord().contains("O")) {
															board[i-1][u].makeMove(false);
															x = u;
															checkNum = i-1;
															moved = true;
														}
													}
												}
												else {
													System.out.println("i = " + i + " right now.");
												}
											}
											catch (ArrayIndexOutOfBoundsException e) {
												System.out.println("Well, this sure ain't working." + e);
											}
										}
										if (!moved && i == size-1) {
											if (board[size-1][u].getCoord().contains("X") || board[size-1][u].getCoord().contains("O")){
												try {
													board[size-2][u].makeMove(false);
												}
												catch (ArrayIndexOutOfBoundsException bleh) {
													gameOver = true;
													System.out.println("It's a draw!");
												}
											}
											else {
												board[size-1][u].makeMove(false);
											}
											x = u;
											checkNum = size-1;
											moved = true;
										}
									}
								}
							}
						}
						else if (aiMix) {//randomly pick between offensive move and defensive move
							if (rand.nextInt(100) < 50) {//attack
								for (int row = 0; row < board.length; row++) {
									for (int col = 0; col < board.length; col++) {
										if (board[row][col].getCoord().contains("O") && !moved) {//found an O
											try {
												if (!board[row-1][col].getCoord().contains("O") && !board[row-1][col].getCoord().contains("X")) {//check above it
													board[row-1][col].makeMove(false);
													x = col;
													moved = true;
												}
												else { //try left and right of piece
													if (!board[row][col+1].getCoord().contains("O") && !board[row][col+1].getCoord().contains("X")) {
														for (int i = 0; i < size; i++) {
															if (!moved) {//check right
																try {
																	if (board[i][col+1].getCoord().contains("X") || board[i][col+1].getCoord().contains("O")) {
																		if (!board[i-1][col+1].getCoord().contains("X") && !board[i-1][col+1].getCoord().contains("O")) {
																			board[i-1][col+1].makeMove(false);
																			checkNum = i-1;
																			x = col+1;
																			moved = true;
																		}
																	}
																}
																catch (ArrayIndexOutOfBoundsException e) {
																	System.out.println("Can't check right 2." + e);
																}
															}
															if (!moved && i == size-1) {//still check right
																if (board[size-1][col+1].getCoord().contains("X") || board[size-1][col+1].getCoord().contains("O")){
																	board[size-2][col+1].makeMove(false);
																}
																else {
																	board[size-1][col+1].makeMove(false);
																}
																x = col+1;
																checkNum = size-1;
																moved = true;
															}
															if (!moved) {//check left
																try {
																	if (board[i][col-1].getCoord().contains("X") || board[i][col-1].getCoord().contains("O")) {
																		if (!board[i-1][col-1].getCoord().contains("X") && !board[i-1][col-1].getCoord().contains("O")) {
																			board[i-1][col-1].makeMove(false);
																			checkNum = i-1;
																			x = col-1;
																			moved = true;
																		}
																	}
																}
																catch (ArrayIndexOutOfBoundsException e) {
																	System.out.println("Can't check left.");
																}
															}
															if (!moved && i == size-1) {//still check left
																if (board[size-1][col-1].getCoord().contains("X") || board[size-1][col-1].getCoord().contains("O")){
																	board[size-2][col-1].makeMove(false);
																}
																else {
																	board[size-1][col-1].makeMove(false);
																}
																x = col-1;
																checkNum = size-1;
																moved = true;
															}
														}
													}
												}
											}
											catch (ArrayIndexOutOfBoundsException e) {//error for up, so check sides
												if (!board[row][col+1].getCoord().contains("O") && !board[row][col+1].getCoord().contains("X")) {
													for (int i = 0; i < size; i++) {
														if (!moved) {//check right
															try {
																if (board[i][col+1].getCoord().contains("X") || board[i][col+1].getCoord().contains("O")) {
																	if (!board[i-1][col+1].getCoord().contains("X") && !board[i-1][col+1].getCoord().contains("O")) {
																		board[i-1][col+1].makeMove(false);
																		checkNum = i-1;
																		x = col+1;
																		moved = true;
																	}
																}
															}
															catch (ArrayIndexOutOfBoundsException v) {
																System.out.println("Can't check right." + v);
															}
														}
														if (!moved && i == size-1) {//still check right
															if (board[size-1][col+1].getCoord().contains("X") || board[size-1][col+1].getCoord().contains("O")){
																board[size-2][col+1].makeMove(false);
															}
															else {
																board[size-1][col+1].makeMove(false);
															}
															x = col+1;
															checkNum = size-1;
															moved = true;
														}
														if (!moved) {//check left
															try {
																if (board[i][col-1].getCoord().contains("X") || board[i][col-1].getCoord().contains("O")) {
																	if (!board[i-1][col-1].getCoord().contains("X") && !board[i-1][col-1].getCoord().contains("O")) {
																		board[i-1][col-1].makeMove(false);
																		checkNum = i-1;
																		x = col-1;
																		moved = true;
																	}
																}
															}
															catch (ArrayIndexOutOfBoundsException v) {
																System.out.println("Can't check left.");
															}
														}
														if (!moved && i == size-1) {//still check left
															if (board[size-1][col-1].getCoord().contains("X") || board[size-1][col-1].getCoord().contains("O")){
																board[size-2][col-1].makeMove(false);
															}
															else {
																board[size-1][col-1].makeMove(false);
															}
															x = col-1;
															checkNum = size-1;
															moved = true;
														}
													}
												}
											}
										}
									}
								}
								if (!moved) {
									System.out.println("no usable O yet");
									for (int u = 0; u < size; u++) {//column
										for (int i = 0; i < size; i++) {//row
											if (!moved) {
												try { 
													if (i != 0) {
														if (board[i][u].getCoord().contains("X") || board[i][u].getCoord().contains("O")) {
															if (!board[i-1][u].getCoord().contains("X") && !board[i-1][u].getCoord().contains("O")) {
																board[i-1][u].makeMove(false);
																x = u;
																checkNum = i-1;
																moved = true;
															}
														}
													}
													else {
														System.out.println("i = " + i + " right now.");
													}
												}
												catch (ArrayIndexOutOfBoundsException e) {
													System.out.println("Well, this sure ain't working." + e);
												}
											}
											if (!moved && i == size-1) {
												if (board[size-1][u].getCoord().contains("X") || board[size-1][u].getCoord().contains("O")){
													try {
													board[size-2][u].makeMove(false);
													}
													catch (ArrayIndexOutOfBoundsException bleh) {
														gameOver = true;
														System.out.println("It's a draw!");
													}
												}
												else {
													board[size-1][u].makeMove(false);
												}
												x = u;
												checkNum = size-1;
												moved = true;
											}
										}
									}
								}
							}
							else {//defend
								for (int row = 0; row < board.length; row++) {
									for (int col = 0; col < board.length; col++) {
										if (board[row][col].getCoord().contains("X") && !moved) {//found an X
											try {
												if (!board[row-1][col].getCoord().contains("O") && !board[row-1][col].getCoord().contains("X")) {//check above it
													board[row-1][col].makeMove(false);
													x = col;
													moved = true;
												}
												else { //try left and right of piece
													if (!board[row][col+1].getCoord().contains("O") && !board[row][col+1].getCoord().contains("X")) {
														for (int i = 0; i < size; i++) {
															if (!moved) {//check right
																try {
																	if (board[i][col+1].getCoord().contains("X") || board[i][col+1].getCoord().contains("O")) {
																		if (!board[i-1][col+1].getCoord().contains("X") && !board[i-1][col+1].getCoord().contains("O")) {
																			board[i-1][col+1].makeMove(false);
																			checkNum = i-1;
																			x = col+1;
																			moved = true;
																		}
																	}
																}
																catch (ArrayIndexOutOfBoundsException e) {
																	System.out.println("Can't check right 2." + e);
																}
															}
															if (!moved && i == size-1) {//still check right
																if (board[size-1][col+1].getCoord().contains("X") || board[size-1][col+1].getCoord().contains("O")){
																	board[size-2][col+1].makeMove(false);
																}
																else {
																	board[size-1][col+1].makeMove(false);
																}
																x = col+1;
																checkNum = size-1;
																moved = true;
															}
															if (!moved) {//check left
																try {
																	if (board[i][col-1].getCoord().contains("X") || board[i][col-1].getCoord().contains("O")) {
																		if (!board[i-1][col-1].getCoord().contains("X") && !board[i-1][col-1].getCoord().contains("O")) {
																			board[i-1][col-1].makeMove(false);
																			checkNum = i-1;
																			x = col-1;
																			moved = true;
																		}
																	}
																}
																catch (ArrayIndexOutOfBoundsException e) {
																	System.out.println("Can't check left.");
																}
															}
															if (!moved && i == size-1) {//still check left
																if (board[size-1][col-1].getCoord().contains("X") || board[size-1][col-1].getCoord().contains("O")){
																	board[size-2][col-1].makeMove(false);
																}
																else {
																	board[size-1][col-1].makeMove(false);
																}
																x = col-1;
																checkNum = size-1;
																moved = true;
															}
														}
													}
												}
											}
											catch (ArrayIndexOutOfBoundsException e) {//error for up, so check sides
												try {//to protect when the board is full
													if (!board[row][col+1].getCoord().contains("O") && !board[row][col+1].getCoord().contains("X")) {
														for (int i = 0; i < size; i++) {
															if (!moved) {//check right
																try {
																	if (board[i][col+1].getCoord().contains("X") || board[i][col+1].getCoord().contains("O")) {
																		if (!board[i-1][col+1].getCoord().contains("X") && !board[i-1][col+1].getCoord().contains("O")) {
																			board[i-1][col+1].makeMove(false);
																			checkNum = i-1;
																			x = col+1;
																			moved = true;
																		}
																	}
																}
																catch (ArrayIndexOutOfBoundsException v) {
																	System.out.println("Can't check right." + v);
																}
															}
															if (!moved && i == size-1) {//still check right
																if (board[size-1][col+1].getCoord().contains("X") || board[size-1][col+1].getCoord().contains("O")){
																	board[size-2][col+1].makeMove(false);
																}
																else {
																	board[size-1][col+1].makeMove(false);
																}
																x = col+1;
																checkNum = size-1;
																moved = true;
															}
															if (!moved) {//check left
																try {
																	if (board[i][col-1].getCoord().contains("X") || board[i][col-1].getCoord().contains("O")) {
																		if (!board[i-1][col-1].getCoord().contains("X") && !board[i-1][col-1].getCoord().contains("O")) {
																			board[i-1][col-1].makeMove(false);
																			checkNum = i-1;
																			x = col-1;
																			moved = true;
																		}
																	}
																}
																catch (ArrayIndexOutOfBoundsException v) {
																	System.out.println("Can't check left.");
																}
															}
															if (!moved && i == size-1) {//still check left
																if (board[size-1][col-1].getCoord().contains("X") || board[size-1][col-1].getCoord().contains("O")){
																	board[size-2][col-1].makeMove(false);
																}
																else {
																	board[size-1][col-1].makeMove(false);
																}
																x = col-1;
																checkNum = size-1;
																moved = true;
															}
														}
													}
												}
												catch (ArrayIndexOutOfBoundsException bleh) {
													gameOver = true;
													System.out.println("It's a draw!");
												}
											}
										}
									}
								}
								if (!moved) {
									System.out.println("no usable X yet");
									for (int u = 0; u < size; u++) {//column
										for (int i = 0; i < size; i++) {//row
											if (!moved) {
												try { 
													if (i != 0) {
														if (board[i][u].getCoord().contains("X") || board[i][u].getCoord().contains("O")) {
															if (!board[i-1][u].getCoord().contains("X") && !board[i-1][u].getCoord().contains("O")) {
																board[i-1][u].makeMove(false);
																x = u;
																checkNum = i-1;
																moved = true;
															}
														}
													}
													else {
														System.out.println("i = " + i + " right now.");
													}
												}
												catch (ArrayIndexOutOfBoundsException e) {
													System.out.println("Well, this sure ain't working." + e);
												}
											}
											if (!moved && i == size-1) {
												if (board[size-1][u].getCoord().contains("X") || board[size-1][u].getCoord().contains("O")){
													try {
														board[size-2][u].makeMove(false);
													}
													catch (ArrayIndexOutOfBoundsException bleh) {
														gameOver = true;
														System.out.println("It's a draw!");
													}
												}
												else {
													board[size-1][u].makeMove(false);
												}
												x = u;
												checkNum = size-1;
												moved = true;
											}
										}
									}
								}
							}
						}
					}
				}
				
				for (int h = 0; h < size; h++) {
					if (size < 10) {
						System.out.print("|-----|");
					}
					else {
						System.out.print("|-------|");
					}
				}
				System.out.println();
				for (int row = 0; row < board.length; row++) {
					for (int col = 0; col < board[row].length; col++) {
						if (firstVertLine) {
							System.out.print("|");
							firstVertLine = false;
						}
						else {
							System.out.print("||");
						}
						board[row][col].printCoord();
					}
					firstVertLine = true;
					System.out.println("|");
					for (int h = 0; h < size; h++) {
						if (size < 10) {
							System.out.print("|-----|");
						}
						else {
							System.out.print("|-------|");
						}
					}
					System.out.println();
				}
				System.out.println();
				//checking
				boolean checkBool = true;
				boolean checkAgain = true;
				
				if (playerNum % 2 == 0) {
					if (size <= 4) { //3 to win ----------------------------------X 3 to win----------------------------------
						if (checkBool) {
							boolean thinkAgain = true;
							for (int i = 0; i < 3; i++) {//up
								try {
									if (board[checkNum-i][x].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left and right
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x-1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left
							boolean thinkAgain = true;
							for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x-i].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//up-left
							boolean thinkAgain = true;
							for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-i][x-i].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-right to u-left middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-1][x-1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+1][x+1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-left to u-right middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+1][x-1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-1][x+1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//down
							boolean thinkAgain = true;
							for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+i][x].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//right
							boolean thinkAgain = true;
							for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x+i].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//up-right
							boolean thinkAgain = true;
							for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-i][x+i].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//down-right
							boolean thinkAgain = true;
							for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+i][x+i].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//down-left
							boolean thinkAgain = true;
							for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+i][x-i].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
					}
					else if (size <= 7) { //4 to win ------------------------------------X 4 to win----------------------------
						if (checkBool) {
							boolean thinkAgain = true;
							for (int i = 0; i < 4; i++) {//up
								try {
									if (board[checkNum-i][x].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left2 and right1
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x-1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x-2].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left1 and right2
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x-1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+2].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-right1 to u-left2 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-1][x-1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-2][x-2].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+1][x+1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-right2 to u-left1 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-1][x-1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+2][x+2].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+1][x+1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-left2 to u-right1 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+1][x-1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+2][x-2].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-1][x+1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-left1 to u-right2 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+1][x-1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-2][x+2].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-1][x+1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left
							boolean thinkAgain = true;
							for (int i = 0; i < 4; i++) {
								try {
									if (board[checkNum][x-i].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//up-left
							boolean thinkAgain = true;
							for (int i = 0; i < 4; i++) {
								try {
									if (board[checkNum-i][x-i].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//down
							boolean thinkAgain = true;
							for (int i = 0; i < 4; i++) {
								try {
									if (board[checkNum+i][x].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//right
							boolean thinkAgain = true;
							for (int i = 0; i < 4; i++) {
								try {
									if (board[checkNum][x+i].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//up-right
							boolean thinkAgain = true;
							for (int i = 0; i < 4; i++) {
								try {
									if (board[checkNum-i][x+i].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//down-right
							boolean thinkAgain = true;
							for (int i = 0; i < 4; i++) {
								try {
									if (board[checkNum+i][x+i].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//down-left
							boolean thinkAgain = true;
							for (int i = 0; i < 4; i++) {
								try {
									if (board[checkNum+i][x-i].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
					}
					else { //5 to win --------------------------------------X 5 to win-----------------------------------------
						if (checkBool) {
							boolean thinkAgain = true;
							for (int i = 0; i < 5; i++) {//up
								try {
									if (board[checkNum-i][x].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left2 and right2
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x-1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x-2].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+2].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left3 and right1
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x-1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x-2].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x-3].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left1 and right3
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x-1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+3].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+2].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-right1 to u-left3 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-1][x-1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-2][x-2].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-3][x-3].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+1][x+1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-right3 to u-left1 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-1][x-1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+2][x+2].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+3][x+3].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+1][x+1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-right2 to u-left2 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-1][x-1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+2][x+2].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-2][x-2].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+1][x+1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-left3 to u-right1 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+1][x-1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+2][x-2].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+3][x-3].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-1][x+1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-left1 to u-right3 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+1][x-1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-3][x+3].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-2][x+2].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-1][x+1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-left2 to u-right2 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+1][x-1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+2][x-2].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-2][x+2].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-1][x+1].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left
							boolean thinkAgain = true;
							for (int i = 0; i < 5; i++) {
								try {
									if (board[checkNum][x-i].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//up-left
							boolean thinkAgain = true;
							for (int i = 0; i < 5; i++) {
								try {
									if (board[checkNum-i][x-i].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//down
							boolean thinkAgain = true;
							for (int i = 0; i < 5; i++) {
								try {
									if (board[checkNum+i][x].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//right
							boolean thinkAgain = true;
							for (int i = 0; i < 5; i++) {
								try {
									if (board[checkNum][x+i].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//up-right
							boolean thinkAgain = true;
							for (int i = 0; i < 5; i++) {
								try {
									if (board[checkNum-i][x+i].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//down-right
							boolean thinkAgain = true;
							for (int i = 0; i < 5; i++) {
								try {
									if (board[checkNum+i][x+i].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//down-left
							boolean thinkAgain = true;
							for (int i = 0; i < 5; i++) {
								try {
									if (board[checkNum+i][x-i].getCoord().contains("X") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player X has won!");
								xWins++;
								gameOver = true;
							}
						}
					}
				}
				else { //-----------------------------------check O----------------------------------------------------------------
					if (size <= 4) { //3 to win
						if (checkBool) {
							boolean thinkAgain = true;
							for (int i = 0; i < 3; i++) {//up
								try {
									if (board[checkNum-i][x].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left and right
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-right to u-left middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-1][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+1][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-left to u-right middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
							try {
								if (board[checkNum+1][x-1].getCoord().contains("O") && thinkAgain) {
									checkBool = true;
								}
								else {
									checkBool = false;
									thinkAgain = false;
								}
							}
							catch(ArrayIndexOutOfBoundsException e) {
								checkBool = false;
								thinkAgain = false;
							}
							try {
								if (board[checkNum-1][x+1].getCoord().contains("O") && thinkAgain) {
									checkBool = true;
								}
								else {
									checkBool = false;
									thinkAgain = false;
								}
							}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left
							boolean thinkAgain = true;
							for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x-i].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//up-left
							boolean thinkAgain = true;
							for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-i][x-i].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//down
							boolean thinkAgain = true;
							for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+i][x].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//right
							boolean thinkAgain = true;
							for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x+i].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//up-right
							boolean thinkAgain = true;
							for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-i][x+i].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//down-right
							boolean thinkAgain = true;
							for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+i][x+i].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//down-left
							boolean thinkAgain = true;
							for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+i][x-i].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
					}
					else if (size <= 7) { //4 to win------------------------------------0 4 to win----------------------------
						if (checkBool) {
							boolean thinkAgain = true;
							for (int i = 0; i < 4; i++) {//up
								try {
									if (board[checkNum-i][x].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left2 and right1
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x-2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left1 and right2
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-right1 to u-left2 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-1][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-2][x-2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+1][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-right2 to u-left1 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-1][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+2][x+2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+1][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-left2 to u-right1 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+1][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+2][x-2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-1][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-left1 to u-right2 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+1][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-2][x+2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-1][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left
							boolean thinkAgain = true;
							for (int i = 0; i < 4; i++) {
								try {
									if (board[checkNum][x-i].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//up-left
							boolean thinkAgain = true;
							for (int i = 0; i < 4; i++) {
								try {
									if (board[checkNum-i][x-i].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//down
							boolean thinkAgain = true;
							for (int i = 0; i < 4; i++) {
								try {
									if (board[checkNum+i][x].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//right
							boolean thinkAgain = true;
							for (int i = 0; i < 4; i++) {
								try {
									if (board[checkNum][x+i].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//up-right
							boolean thinkAgain = true;
							for (int i = 0; i < 4; i++) {
								try {
									if (board[checkNum-i][x+i].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//down-right
							boolean thinkAgain = true;
							for (int i = 0; i < 4; i++) {
								try {
									if (board[checkNum+i][x+i].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//down-left
							boolean thinkAgain = true;
							for (int i = 0; i < 4; i++) {
								try {
									if (board[checkNum+i][x-i].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
					}
					else { //5 to win ---------------------------------O 5 to win----------------------------------------------
						if (checkBool) {
							boolean thinkAgain = true;
							for (int i = 0; i < 5; i++) {//up
								try {
									if (board[checkNum-i][x].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left2 and right2
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x-2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left3 and right1
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x-2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x-3].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left1 and right3
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+3].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-right1 to u-left3 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-1][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-2][x-2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-3][x-3].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+1][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-right3 to u-left1 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-1][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+2][x+2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+3][x+3].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+1][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-right2 to u-left2 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-1][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+2][x+2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-2][x-2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+1][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-left3 to u-right1 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+1][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+2][x-2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+3][x-3].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-1][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-left1 to u-right3 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+1][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-3][x+3].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-2][x+2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-1][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-left2 to u-right2 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+1][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+2][x-2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-2][x+2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-1][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}if (!checkBool) {//left2 and right2
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x-2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left3 and right1
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x-2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x-3].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left1 and right3
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+3].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-right1 to u-left3 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-1][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-2][x-2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-3][x-3].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+1][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-right3 to u-left1 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-1][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+2][x+2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+3][x+3].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+1][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-right2 to u-left2 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum-1][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+2][x+2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-2][x-2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+1][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-left3 to u-right1 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+1][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+2][x-2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+3][x-3].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-1][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-left1 to u-right3 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+1][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-3][x+3].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-2][x+2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-1][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//d-left2 to u-right2 middle check
							boolean thinkAgain = true;
							//for (int i = 0; i < 3; i++) {
								try {
									if (board[checkNum+1][x-1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum+2][x-2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-2][x+2].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
								try {
									if (board[checkNum-1][x+1].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							//}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//left
							boolean thinkAgain = true;
							for (int i = 0; i < 5; i++) {
								try {
									if (board[checkNum][x-i].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//up-left
							boolean thinkAgain = true;
							for (int i = 0; i < 5; i++) {
								try {
									if (board[checkNum-i][x-i].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//down
							boolean thinkAgain = true;
							for (int i = 0; i < 5; i++) {
								try {
									if (board[checkNum+i][x].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//right
							boolean thinkAgain = true;
							for (int i = 0; i < 5; i++) {
								try {
									if (board[checkNum][x+i].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//up-right
							boolean thinkAgain = true;
							for (int i = 0; i < 5; i++) {
								try {
									if (board[checkNum-i][x+i].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//down-right
							boolean thinkAgain = true;
							for (int i = 0; i < 5; i++) {
								try {
									if (board[checkNum+i][x+i].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
						if (!checkBool) {//down-left
							boolean thinkAgain = true;
							for (int i = 0; i < 5; i++) {
								try {
									if (board[checkNum+i][x-i].getCoord().contains("O") && thinkAgain) {
										checkBool = true;
									}
									else {
										checkBool = false;
										thinkAgain = false;
									}
								}
								catch(ArrayIndexOutOfBoundsException e) {
									checkBool = false;
									thinkAgain = false;
								}
							}
							if (checkBool) {
								System.out.println("Player O has won!");
								oWins++;
								gameOver = true;
							}
						}
					}
				}
				playerNum++;
				if (gameOver) {
					System.out.println("X wins: " + xWins + " | O wins: " + oWins);
					System.out.println("Would you like to play again?");
					while (true) {
						String response = textScanner.nextLine().toLowerCase();
						if (response.equals("yes")) {
							keepGoing = true;
							System.out.println("Onward, then!");
							break;
						}
						else if (response.equals("no")){
							keepGoing = false;
							System.out.println("See you later.");
							break;
						}
						else {
							System.out.println("Please enter only 'yes' or 'no'.");
						}
					}
				}
				if (!gameOver) { //Check each space to see if the board is full. If it is, the game is a draw.
					boolean foundAnEmpty = false;
					for (int row = 0; row < board.length; row++) {
						for (int col = 0; col < board.length; col++) {
							if (!board[row][col].getCoord().contains("X") && !board[row][col].getCoord().contains("O") && !foundAnEmpty) {
								foundAnEmpty = true;
							}
						}
					}
					if (!foundAnEmpty) {
						gameOver = true;
						System.out.println("It's a draw!");
						System.out.println("X wins: " + xWins + " | O wins: " + oWins);
						System.out.println("Would you like to play again?");
						while (true) {
							String response = textScanner.nextLine().toLowerCase();
							if (response.equals("yes")) {
								keepGoing = true;
								System.out.println("Onward, then!");
								break;
							}
							else if (response.equals("no")){
								keepGoing = false;
								System.out.println("See you later.");
								break;
							}
							else {
								System.out.println("Please enter only 'yes' or 'no'.");
							}
						}
					}
				}
			}
		}//end of while (keepGoing)
	}//end of public static void

}
