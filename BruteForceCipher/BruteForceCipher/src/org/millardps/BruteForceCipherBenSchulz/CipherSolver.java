package org.millardps.BruteForceCipherBenSchulz;

import java.util.ArrayList;

public class CipherSolver {
	private ArrayList<String> fours = new ArrayList<String>();
	private ArrayList<String> cribs = new ArrayList<String>();
	private String[] messList;
	private ArrayList<int[]> diffList = new ArrayList<int[]>();
	private String mess;
	private String finalMess;
	
	public CipherSolver(String message) {
		mess = message;
		cribs = getCribs();
		messList = mess.split(" ");
		fours = findFours();
		diffList = findDiff();
		perfShift();
	}
	//methods
	
	//find four-letter words and make an arraylist of them
	public ArrayList<String> findFours() {
		ArrayList<String> temp = new ArrayList<String>();
		for (int i=0; i<messList.length; i++) {
			String word = messList[i];
			if (word.length() == 4) {
				temp.add(word);
			}
//					try {
//						if (!mess.substring(i + 1, i + 5).contains(" ") && mess.charAt(i+6) == ' ') {
//							temp.add(mess.substring(i + 1, i + 5));
//						}
//					}
//					catch (StringIndexOutOfBoundsException y) {
//						temp.add(mess.substring(i + 1, i + 5));
//					}
				//}
//			if (i == 0 && char1 != ' ') {
//				//for (String word: cribs) {
//				if (i+5 > mess.length()) {
//					temp.add(mess.substring(i, i + 4));
//				}
//				else {
//					if (!mess.substring(i, i + 4).contains(" ") && mess.charAt(i+5) == ' ') {
//						temp.add(mess.substring(i, i + 4));
//					}
//				}
////					try {
////						if (!mess.substring(i, i + 4).contains(" ") && mess.charAt(i+5) == ' ') {
////							temp.add(mess.substring(i, i + 4));
////						}
////					}
////					catch (StringIndexOutOfBoundsException y) {
////						temp.add(mess.substring(i, i + 4));
////					}
//				//}
//			}
		}
		System.out.println("Four letter words: "+temp);
		System.out.println("How many? "+temp.size());
		return temp;
	}
	
	public ArrayList<String> getCribs() {
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("they");
		temp.add("that");
		temp.add("this");
		temp.add("meat");
		temp.add("quip");
		temp.add("foot");
		temp.add("pack");
		temp.add("tick");
		temp.add("sick");
		temp.add("trip");
		temp.add("meet");
		temp.add("rant");
		temp.add("tent");
		temp.add("cent");
		temp.add("ogre");
		temp.add("lamp");
		temp.add("lair");
		temp.add("fear");
		temp.add("tear");
		temp.add("near");
		temp.add("ware");
		temp.add("scar");
		temp.add("lake");
		temp.add("make");
		return temp;
	}
	//find the numeric difference between the four letter words and a crib
	public ArrayList<int[]> findDiff() {
		ArrayList<int[]> temp = new ArrayList<int[]>();
//		for (String w: fours) {
//			for (String c: cribs) {
//				int[] temp2 = new int[4];
//				for (int i = 0; i < 4; i++) {
//					int z = (int)c.charAt(i) - (int)w.charAt(i);
////					System.out.println(i);
////					System.out.println(z);
//					temp2[i] = z;
//					if (temp2[i] < 0) {
//						temp2[i] += 26;
//					}
//				}
//				temp.add(temp2);
//			}
//		}
		for (String c: cribs) {
			int[] t = new int[4];
			for (int i = 0; i < 4; i++) {
				int z = (int)c.charAt(i) - (int)fours.get(0).charAt(i);
				t[i] = z;
				if (t[i] < 0) {
					t[i] += 26;
				}
			}
			temp.add(t);
		}
//		for (int x = 0;x < temp.size(); x++){
//			for (int y = 0;y < temp.get(0).length; y++){
//				System.out.print(cribs.get(x)+" "+temp.get(x)[y] + " ");
//			}
//			System.out.println();
//		}
		
		return temp;
	}
	//using those differences you will perform a shift
	public void perfShift() { 
		int count = 0;
		System.out.println("diffList size: " + diffList.size());
		System.out.println("cribs size: " + cribs.size());
		/*
		for(int [] x: diffList){  //first way
//			for(int y: x){
//			System.out.print("diffList = " + y);
			//for (char c: mess.toCharArray()) {
			for (String w: messList) {
				
				String fin = "";
				//if (c != ' '){
				//if (w.length() == 4) {
				for (int h = 0; h < w.length(); h++) {
					if (count < cribs.size()) {
						int l = (int)w.charAt(h);
						if (w.length() > 4) {
							while (h > 4) {
								h -= 4;
							}
							l += diffList.get(count)[h];
						}
						else {
							l += diffList.get(count)[h];
						}
						if (l > 122) {
							l -= 97;
							l %= 26;
							l += 97;
						}
						char next = (char)l;
						fin += next;
					}
				}
				
				fin += " ";
				System.out.print(fin);
				//}
//				else {
//					fin += " ";
//				}
				if (w.equals(messList[messList.length-1])) {
				//if (fin.length() == mess.length()) {
					count++;
					System.out.println();
				}
				
			}
				//else {
				
				//}
				
		} //end of first way */
	
		//ArrayList<String> strings = new ArrayList<String>();
//		for (String v: messList) {
//			if (v.length() > 4) {
//				
//			}
//		}
		for (int[] x: diffList) { //alt way
			int g = 0;
			String fin = "";
			for (String w: messList) {
				count++;
				
				for (char c: w.toCharArray()) {
					int l = (int)c;
					l += x[g];
					if (l > 122) {
						l -= 97;
						l %= 26;
						l += 97;
					}
					char next = (char)l;
					fin += next;
					g++;
					if (g > 3) {
						g = 0;
					}
				}
				fin += " ";
			}
			
			System.out.print(fin);
			if (count == messList.length) {
				count = 0;
				System.out.println();
			} //souffle, hooray.
		}
		/*
		for(int [] x: diffList){ //second way
			String fin = "";
			for (char c: mess.toCharArray()) {
				for (int h = 0; h < 4; h++) {
					if (count < cribs.size()) {
						if (c != ' '){
							int l = (int)c;
							l += x[h];
							if (l > 122) {
								l -= 97;
								l %= 26;
								l += 97;
							}
							char next = (char)l;
							fin += next;
						}
						else {
							char next = ' ';
							fin += next;
						}
					}
				}
				fin += " ";
				System.out.print(fin);
				if (fin.length() == mess.length()) {
					count++;
					System.out.println();
				}
				
			}
		} //end of second way */
		/*
		int change = 0;
		for (int[] x: diffList) { //third way
			for (String w: messList) {
				String fin = "";
				for (int h = 0; h < w.length(); h++) {
					if (count < cribs.size()) {
						if (w.length() < 4) {
							change = w.length();
							int l = (int)w.charAt(h);
							l += diffList.get(count)[h];
							if (l > 122) {
								l -= 97;
								l %= 26;
								l += 97;
							}
							char next = (char)l;
							fin += next;
						}
						else if (w.length() > 4) {
							change = w.length();
							while (change > 4) {
								change -= 4;
							}
							h = change;
							int l = (int)w.charAt(h);
							l += diffList.get(count)[h];
							if (l > 122) {
								l -= 97;
								l %= 26;
								l += 97;
							}
							char next = (char)l;
							fin += next;
						}
						else {
							int l = (int)w.charAt(h);
							l += diffList.get(count)[h];
							if (l > 122) {
								l -= 97;
								l %= 26;
								l += 97;
							}
							char next = (char)l;
							fin += next;
						}
					}
				}
				fin += " ";
				System.out.print(fin);
				if (w.equals(messList[messList.length-1])) {
					count++;
					System.out.println();
				}
			}
		} */ //end of third way
	
	}
	//print all possible solutions
	public void printSol() {
		
	}
	
}
