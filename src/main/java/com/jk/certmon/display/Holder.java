package com.jk.certmon.display;

import java.io.File;

public class Holder {
	
	private static String title = "certmon";
	
	public static String getDefaultFileName(){
		return System.getProperty("java.home") + "/lib/security/cacerts".replace('/', File.separatorChar);
	}
	
	public static void setTitle(String inTitle){title = inTitle;}
	public static String getTitle(){return title;}
}
