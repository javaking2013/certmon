package com.jk.certmon.display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;

public class Items {
	
	public Items(){
		
	}
	
	public static JMenuItem getMenuItem(final String value){
		JMenuItem item = new JMenuItem(value);
		
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Execute.doit(value);
			}
		});
		return item;
	}
	
	public static JButton getButton(final String value){
		JButton but = new JButton(value);
		
		but.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Execute.doit(value);
			}
		});
		return but;
	}
}
