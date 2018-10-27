package org.millardps.Roguelike;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.KeyListener;

public class Ear implements KeyListener{
	private char[][] map = new char[107][15];
	private int x = 39;
	private int y = 11;
	private char prevChar = '.';
	private String message = "You float slowly down to the bottom of the ocean. Apparently, trying to light a signal fire on top of your life raft wasn't the greatest of ideas. Luckily your fancy futuristic scuba gear can let you breathe underwater for at least a day, so you might not drown if you can get back to land fast enough. Eventually you land on what appears to be a tower of a castle. Which is weird. There are stairs on the tower leading down into the castle. You go down the stairs. As you walk down the stairs, the stairs crumble underneath you and you fall. The fall doesn't matter because you're underwater though, so you just float down. At the bottom of the staircase, you find two doors. One door is blocked by debris, and one looks like it's covered by some kind of opaque bubble.";
	private boolean nearSub = false;
	private boolean nearRocks = false;
	private boolean nearGold = false;
	private boolean nearBlue = false;
	private boolean nearRust = false;
	private boolean gameOver = false;
	private boolean abyssFirst = true;
	private boolean jumpFirst = true;
	private boolean boxFirst = true;
	private boolean nearBox = false;
	private boolean dark = false;
	private boolean noMoreDark = false;
	private ArrayList<String> inventory = new ArrayList<String>();
	private Random rand = new Random();
	private int luck = rand.nextInt(100);//random number between 0-99
	
	public Ear() {
		map = populateMap();
//		x = 83;
//		y = 11;
		map[x][y] = '@';
		printMap();
		System.out.println(message);
	}
	
	public void checkProx() {
		if (map[x-1][y] == '_' || map[x+1][y] == '_' || map[x][y-1] == '_' || map[x][y+1] == '_') {
			System.out.println("You see here a submarine. There's a door on it, but it's locked. If you have a key, you could try opening it.");
			String t = "";
			if (inventory.contains("rusty key")) {
				t += "Press '1' to use the rusty key. ";
			}
			if (inventory.contains("blue key")) {
				t += "Press '2' to use the blue key. ";
			}
			if (inventory.contains("gold key")) {
				t += "Press '3' to use the gold key. ";
			}
			System.out.println(t);
			nearSub = true;
		}
		else {
			nearSub = false;
		}
		if (map[x-1][y] == '*' || map[x+1][y] == '*' || map[x][y-1] == '*' || map[x][y+1] == '*') {
			System.out.println("There are some rocks here. It you have something to break through them, you can use it.");
			if (inventory.contains("pickaxe")) {
				System.out.println("You can press '1' to use your pickaxe to break through or '2' to use thermite.");
			}
			nearRocks = true;
		}
		else {
			nearRocks = false;
		}
		if ((map[x-1][y] == '#' || map[x+1][y] == '#' || map[x][y-1] == '#' || map[x][y+1] == '#') && boxFirst) {
			System.out.println("There is a very elegantly designed golden box here.");
			System.out.println("Press '1' to try opening the box, or press '2' to look around for something less likely to be a horrible trap.");
			nearBox = true;
		}
		else {
			nearBox = false;
		}
		if ((map[x-1][y] == ' ' || map[x+1][y] == ' ' || map[x][y-1] == ' ' || map[x][y+1] == ' ') && abyssFirst) {
			abyssFirst = false;
			System.out.println("There is what appears to be an extremely deep abyss in front of you. You can move towards it to try jumping over it, but it's dangerous because this room has no water in it and you can't just swim over it.");
		}
		if ((x == 83 && y == 11) || (x == 82 && y == 12) && !inventory.contains("gold key")) {
			System.out.println("You see here a golden key in the mouth of a statue of some lizard-looking thing. You can try taking the key with '1'");
			nearGold = true;
		}
		else {
			nearGold = false;
		}
		if ((x == 91 && y == 2) || (x == 90 && y == 1) || (x == 89 && y == 2) || (x == 90 && y == 3)  && !inventory.contains("rusty key")) {
			System.out.println("There's a rusty key on the floor right in front of your feet. You can try taking the key with '1'");
			nearRust = true;
		}
		else {
			nearRust = false;
		}
		if (x == 103 && y == 2  && !inventory.contains("blue key")) {
			System.out.println("You've stepped through the bubble and are glad that there isn't anything on this side that wants to kill you. You see here a blue key. You can try taking the key with '1'");
			nearBlue = true;
		}
		else {
			nearBlue = false;
		}
		if (map[x+1][y] == 'H') {
			System.out.println("You step on a rock and puncture your suit, but water doesn't rush in. There must be air on this side of the bubble. On the floor is a roll of waterproof duct tape, a pickaxe, and a grenade-looking thing that says 'Underwater Thermite' on it. You pick them up and use the duct tape to patch up your suit.");
			map[x+1][y] = '.';
			map[x+2][y] = '.';
			map[x+3][y] = '.';
			inventory.add("pickaxe");
			inventory.add("thermite");
		}
		if ((x >= 61 && x <= 79  && y >= 1 && y <= 5) && !noMoreDark) {//between 61x1y and 79x5y, dark room
			System.out.println("It's very dark in here and you can't seem to find so much as another door without any light. If you have a light source, you can use it in here.");
			String t = "";
			if (inventory.contains("thermite")) {
				t += "Press '1' to use thermite. ";
			}
			if (inventory.contains("glowsticks")) {
				t += "Press '2' to use a glowstick.";
			}
			System.out.println(t);
			dark = true;
		}
		else {
			dark = false;
		}
	}
	
	public void end(String key) {
		if (key.equals("gold key")) {
			if (luck > 75) {//best ending
				System.out.println("You put the key in the lock and turn it, but the sub's door doesn't open. Instead, the wall at the front of the room rolls down, revealing a giant robotic manta ray. You get into it and pilot it back to the coast, wondering why it and all the other weird stuff was in there.");
				System.out.println("           /\\		 _    _ _     ");
				System.out.println("         ./  |		| |  | (_)     ");
				System.out.println("        /     \\	 	| |  | |_ _ __  ");
				System.out.println("------<      :|		| |/\\| | | '_ \\ ");
				System.out.println("        \\     /		\\  /\\  / | | | |");
				System.out.println("         '\\  |		 \\/  \\/|_|_| |_|");
				System.out.println("           \\/");
				gameOver = true;
				//insert Bourne theme here
			}
			else {//good ending
				System.out.println("You put the key in the lock and turn it, opening the door to an airlock. You get in, close the door, empty out the airlock of water, and go through to the controls of the submarine. You turn on the sub and drive it up to the surface and back to the coast. It's good to finally be back.");
				System.out.println("o   o ~ ____						   ___");
				System.out.println("o O   ~|    \\					        __/   |__");
				System.out.println("O  o  ~|     \\ 					  	 /    |");
				System.out.println("o   o ~|      \\_________________________________________/     |_____");
				System.out.println("O  o  ~|			 _    _ _       		     \\");
				System.out.println("o O   ~|		        | |  | (_)      		      \\");
				System.out.println("o   o ~|			| |  | |_ _ __  		       \\");
				System.out.println("o   o ~|		      	| |/\\| | | '_ \\ 			)");
				System.out.println("o O   ~|	            	\\  /\\  / | | | |		       /");
				System.out.println("O  o  ~|	              	 \\/  \\/|_|_| |_|		      /");
				System.out.println("o O   ~|       _____________________________________________________/");
				System.out.println("O  o  ~|      /");
				System.out.println("o O   ~|     /");
				System.out.println("O  o  ~|____/");
				gameOver = true;
			}
		}
		else if (key.equals("blue key")) {
			if (luck > 25) {//good ending
				System.out.println("You put the key in the lock and turn it, opening the door to an airlock. You get in, close the door, empty out the airlock of water, and go through to the controls of the submarine. You turn on the sub and drive it up to the surface and back to the coast. It's good to finally be back.");
				System.out.println("o   o ~ ____						   ___");
				System.out.println("o O   ~|    \\					        __/   |__");
				System.out.println("O  o  ~|     \\ 					  	 /    |");
				System.out.println("o   o ~|      \\_________________________________________/     |_____");
				System.out.println("O  o  ~|			 _    _ _       		     \\");
				System.out.println("o O   ~|		        | |  | (_)      		      \\");
				System.out.println("o   o ~|			| |  | |_ _ __  		       \\");
				System.out.println("o   o ~|		      	| |/\\| | | '_ \\ 			)");
				System.out.println("o O   ~|	            	\\  /\\  / | | | |		       /");
				System.out.println("O  o  ~|	              	 \\/  \\/|_|_| |_|		      /");
				System.out.println("o O   ~|       _____________________________________________________/");
				System.out.println("O  o  ~|      /");
				System.out.println("o O   ~|     /");
				System.out.println("O  o  ~|____/");
				gameOver = true;
			}
			else {//key breaks; bad ending
				System.out.println("You put the key in the lock and turn it, only for it to snap, leaving parts of it inside the keyhole so that you can't try another key. You've ruined your only chance of making it back to the surface.");
				System.out.println("   ____                         ___                 ");
				System.out.println("  / ___| __ _ _ __ ___   ___   / _ \\__   _____ _ __ ");
				System.out.println(" | |  _ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__|");
				System.out.println(" | |_| | (_| | | | | | |  __/ | |_| |\\ V /  __/ |  ");
				System.out.println("  \\____|\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|   ");
				gameOver = true;
			}
		}
		else if (key.equals("rusty key")){
			if (luck > 75) {//good ending
				System.out.println("You put the key in the lock and turn it, opening the door to an airlock. You get in, close the door, empty out the airlock of water, and go through to the controls of the submarine. You turn on the sub and drive it up to the surface and back to the coast. It's good to finally be back.");
				System.out.println("o   o ~ ____						   ___");
				System.out.println("o O   ~|    \\					        __/   |__");
				System.out.println("O  o  ~|     \\ 					  	 /    |");
				System.out.println("o   o ~|      \\_________________________________________/     |_____");
				System.out.println("O  o  ~|			 _    _ _       		     \\");
				System.out.println("o O   ~|		        | |  | (_)      		      \\");
				System.out.println("o   o ~|			| |  | |_ _ __  		       \\");
				System.out.println("o   o ~|		      	| |/\\| | | '_ \\ 			)");
				System.out.println("o O   ~|	            	\\  /\\  / | | | |		       /");
				System.out.println("O  o  ~|	              	 \\/  \\/|_|_| |_|		      /");
				System.out.println("o O   ~|       _____________________________________________________/");
				System.out.println("O  o  ~|      /");
				System.out.println("o O   ~|     /");
				System.out.println("O  o  ~|____/");
				gameOver = true;
			}
			else {//key breaks; bad ending
				System.out.println("You put the key in the lock and turn it, only for it to snap, leaving parts of it inside the keyhole so that you can't try another key. You've ruined your only chance of making it back to the surface.");
				System.out.println("   ____                         ___                 ");
				System.out.println("  / ___| __ _ _ __ ___   ___   / _ \\__   _____ _ __ ");
				System.out.println(" | |  _ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__|");
				System.out.println(" | |_| | (_| | | | | | |  __/ | |_| |\\ V /  __/ |  ");
				System.out.println("  \\____|\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|   ");
				gameOver = true;
			}
		}
		else {//eel
			System.out.println("You open the golden box to find nothing. Nothing but the floor shaking, the bubble on the door popping, water filling the room, and a giant eel coming through the abyss eating you, that is.");
			System.out.println("   ____                         ___                 ");
			System.out.println("  / ___| __ _ _ __ ___   ___   / _ \\__   _____ _ __ ");
			System.out.println(" | |  _ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__|");
			System.out.println(" | |_| | (_| | | | | | |  __/ | |_| |\\ V /  __/ |  ");
			System.out.println("  \\____|\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|   ");
			gameOver = true;
		}
	}
	
	public void choice1() {
		if (nearSub && inventory.contains("rusty key")) {
			end("rusty key");
		}
		if (nearGold) {
			System.out.println("You grab the key and pocket it without anything horrible happening.");
			map[82][11] = '.';
			inventory.add("gold key");
		}
		if (nearRust) {
			System.out.println("You pick up the rusty key.");
			map[90][2] = '.';
			inventory.add("rusty key");
		}
		if (nearBlue) {
			System.out.println("You pick up the blue key.");
			map[104][2] = '.';
			inventory.add("blue key");
		}
		if (nearBox && boxFirst) {
			end("eels");
			boxFirst = false;
		}
		if (nearRocks && inventory.contains("pickaxe")) {
			System.out.println("You spend at least a solid hour digging through the debris, eventually breaking through.");
			map[38][6] = '.';
			map[39][6] = '.';
			map[40][6] = '.';
			map[41][6] = '.';
			map[42][6] = '.';
		}
		if (dark && inventory.contains("thermite")) {
			System.out.println("You set off the thermite and watch its warm glow reveal a new doorway.");
			map[80][2] = '(';
			noMoreDark = true;
			dark = false;
		}
	}
	public void choice2() {
		if (nearSub && inventory.contains("blue key")) {
			end("blue key");
		}
		if (nearBox && boxFirst) {
			System.out.println("You look behind the box and find some unused glowsticks lying around. You pick them up.");
			inventory.add("glowsticks");
			boxFirst = false;
		}
		if (nearRocks && inventory.contains("thermite")) {
			System.out.println("You lodge the thermite into the debris, activate it, and watch the debris be completely demolished.");
			map[38][6] = '.';
			map[39][6] = '.';
			map[40][6] = '.';
			map[41][6] = '.';
			map[42][6] = '.';
			int thermIndex = inventory.indexOf("thermite");
			inventory.remove(thermIndex);
		}
		if (dark && inventory.contains("glowsticks")) {
			System.out.println("You turn activate the glowstick and watch its green glow reveal a new doorway.");
			map[80][2] = '(';
			noMoreDark = true;
			dark = false;
		}
	}
	public void choice3() {
		if (nearSub && inventory.contains("gold key")) {
			end("gold key");
		}
	}
	
	public void left() {
		System.out.println("left");
		if (map[x-1][y] == '.' || map[x-1][y] == ')' || map[x-1][y] == '(' || map[x-1][y] == ' ') {
			if (map[x-1][y] == ' ' && jumpFirst) {
				jumpFirst = false;
				System.out.println("You take a leap towards the other side of the abyss and fail, but you don't fall. There seems to be some sort of invisible floor above the gorge. Add that to the list of strange things you've encountered today.");
			}
			map[x][y] = prevChar;
			x--;
			prevChar = map[x][y];
			map[x][y] = '@';
			printMap();
		}
	}
	public void right() {
		System.out.println("right");
		if (map[x+1][y] == '.' || map[x+1][y] == ')' || map[x+1][y] == '(' || map[x+1][y] == ' ') {
			map[x][y] = prevChar;
			x++;
			prevChar = map[x][y];
			map[x][y] = '@';
			printMap();
		}
	}
	public void up() {
		System.out.println("up");
		if (map[x][y-1] == '.' || map[x][y-1] == ')' || map[x][y-1] == '(' || map[x][y-1] == ' ') {
			map[x][y] = prevChar;
			y--;
			prevChar = map[x][y];
			map[x][y] = '@';
			printMap();
		}
	}
	public void down() {
		System.out.println("down");
		if (map[x][y+1] == '.' || map[x][y+1] == ')' || map[x][y+1] == '(' || map[x][y+1] == ' ') {
			map[x][y] = prevChar;
			y++;
			prevChar = map[x][y];
			map[x][y] = '@';
			printMap();
		}
		
	}
	
	public char[][] populateMap() {
		char[][] temp = new char[107][15];//[x][y]
		for (int i = 0; i < temp[0].length; i++) {
			for (int z = 0; z < temp.length; z++) {
				temp[z][i] = '.';
			}
		}
		for (int e = 0; e < 100; e++) {
			temp[e][0] = '-';
		}
		for (int e = 0; e < 20; e++) {
			temp[e][6] = '-';
		}
		for (int e = 0; e < 7; e++) {
			temp[0][e] = '|';
		}
		for (int e = 0; e < 7; e++) {
			if (e != 3) {
				temp[20][e] = '|';
			}
		}
		for (int e = 0; e < 7; e++) {
			temp[60][e] = '|';
		}
		for (int e = 0; e < 7; e++) {
			temp[80][e] = '|';
		}
		for (int e = 0; e < 19; e++) {
			temp[e+61][6] = '-';
		}
		for (int e = 0; e < 39; e++) {
			temp[e+21][6] = '-';
		}
		for (int e = 0; e < 7; e++) {
			temp[29][e+7] = '|';
		}
		for (int e = 0; e < 7; e++) {
			temp[51][e+7] = '|';
		}
		for (int e = 0; e < 21; e++) {
			temp[e+30][13] = '-';
		}
		for (int e = 0; e < 19; e++) {
			temp[e+81][4] = '-';
		}
		for (int e = 0; e < 5; e++) {
			temp[100][e] = '|';
		}
		for (int e = 0; e < 4; e++) {
			temp[e+101][1] = '-';
			temp[e+101][3] = '-';
		}
		for (int e = 1; e < 4; e++) {
			temp[105][e] = '|';
		}
		for (int e = 4; e < 9; e++) {
			temp[94][e] = '|';
		}
		for (int e = 4; e < 15; e++) {
			temp[96][e] = '|';
		}
		for (int e = 8; e < 15; e++) {
			temp[80][e] = '|';
		}
		for (int e = 0; e < 15; e++) {
			if (e < 13) {
				temp[e+81][8] = '-';
			}
			temp[e+81][14] = '-';
		}
		for (int e = 1; e < 6; e++) {
			temp[7][e] = ' ';
			temp[8][e] = ' ';
			temp[9][e] = ' ';
			temp[10][e] = ' ';
			temp[11][e] = ' ';
		}
		for (int e = 0; e < 6; e++) {
			temp[e+52][9] = '-';
			temp[e+52][11] = '-';
		}
		
		temp[20][3] = ')';
		temp[60][3] = '.';
		
		temp[40][6] = '*';
		temp[39][6] = '*';
		temp[41][6] = '*';
		temp[42][6] = '*';
		temp[38][6] = '*';
		
		temp[40][1] = '_';
		temp[39][1] = '_';
		temp[41][1] = 'f';
		temp[42][1] = '_';
		temp[38][1] = '_';
		temp[37][2] = '/';
		temp[43][2] = '\\';
		temp[37][3] = '\\';
		temp[40][3] = '_';
		temp[39][3] = '_';
		temp[41][3] = '_';
		temp[42][3] = '_';
		temp[38][3] = '_';
		temp[43][3] = '/';
		temp[42][2] = 'O';
		
		//temp[80][2] = '('; //puts the door into the wall of the dark room
		temp[90][2] = ';';
		temp[100][2] = ')';
		
		temp[104][2] = ';';
		
		temp[95][4] = '.';
		
		temp[81][9] = '\\';
		temp[81][10] = 'O';
		temp[82][10] = '\\';
		temp[81][11] = '~';
		temp[82][11] = ';';
		temp[81][12] = '^';
		
		temp[2][3] = '#';
		
		temp[51][10] = '(';
		temp[58][11] = '|';
		temp[58][10] = '|';
		temp[58][9] = '|';
		temp[57][10] = 'o';
		temp[56][10] = 'T';
		temp[55][10] = 'H';
		
		return temp;
	}
	
	public void printMap() {
		for (int i = 0; i < map[0].length; i++) {
			for (int z = 0; z < map.length; z++) {
				System.out.print(map[z][i]);
			}
			System.out.println();
			
		}
		checkProx();
		System.out.println(x + " " + y);
		//System.out.print(message);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("key listener");
//		int x = a.getLocation()[0];
//		int y = a.getLocation()[1];
//		int newX = x;
//		int newY = y;
//		System.out.println(x + " this is in keylistender " + y);
        int key = e.getKeyCode();
        switch(key){
        case (KeyEvent.VK_LEFT):
        	//y--;
//        	System.out.println(" left ");
        	left();
        	break;
        case (KeyEvent.VK_RIGHT):
        	//y++;
//        	System.out.println(" right ");
        	right();
       		break;
        case (KeyEvent.VK_UP):
        	//x--;
//        	System.out.println(" up ");
        	up();
        	break;
        case (KeyEvent.VK_DOWN):
        	//x++;
//        	System.out.println(" down ");
        	down();
        	break;
        case (KeyEvent.VK_1):
        	choice1();
        	break;
        case (KeyEvent.VK_2):
        	choice2();
        	break;
        case (KeyEvent.VK_3):
        	choice3();
        	break;
        }
        
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
