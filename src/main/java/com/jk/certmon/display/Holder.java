package com.jk.certmon.display;

import java.io.File;

public class Holder {
	
	private static String title = "certmon";
	private static String version = "v0.1";
	private static String filename = "";
	private static String currentCert = "";
	
	public static String getDefaultFileName(){
		return System.getProperty("java.home") + "/lib/security/cacerts".replace('/', File.separatorChar);
	}
	
	public static void setTitle(String inTitle){title = inTitle;}
	public static String getTitle(){return title + " - " + version;}
	
	public static void setFilename(String inFile){filename = inFile;}
	public static String getFilename(){return filename;}
	
	public static String getVersion(){return version;}

	public static void setCurrentCert(String certName){currentCert = certName;}
	public static String getCurrentCert(){return currentCert;}
}
