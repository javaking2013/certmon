package com.jk.certmon.display;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class certmon {
	
	public static void main(String[] args) {
		new certmon();
	}
	
	public static JFrame f;
	
	
	public certmon(){
		showit();
		btnAction();
	}
	
	public static void showit(){
		f = new JFrame("CertMon");
		JPanel p = new JPanel();
		
		f.add(p);
		
	}
	
	public static void btnAction(){
		
	}
}
