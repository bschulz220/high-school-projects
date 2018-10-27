package org.millardps.Roguelike;

import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Runner {

	public static void main(String[] args) {
		//Game game = new Game();
		
		JTextField textField = new JTextField();
	    textField.addKeyListener(new Ear());
	    JFrame jframe = new JFrame();
	    jframe.add(textField);
//	    jframe.setSize(400, 350);
	    jframe.setVisible(true);
	}
	    

}
