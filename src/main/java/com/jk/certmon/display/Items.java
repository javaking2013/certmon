package com.jk.certmon.display;

import javax.swing.JButton;
import javax.swing.JMenuItem;

public class Items {
	
	public Items(){ } // no-args constructor
	
	static JMenuItem getMenuItem(final String value){
		JMenuItem item = new JMenuItem(value);
		item.addActionListener(e -> Execute.doit(value));
		return item;
	}
	
	static JButton getButton(final String value){
		JButton but = new JButton(value);
		but.addActionListener(e -> Execute.doit(value));
		return but;
	}
}
