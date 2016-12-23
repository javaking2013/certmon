package com.jk.certmon.display;

import java.io.File;

public class Holder {
	
	public static String getDefaultFileName(){
		return System.getProperty("java.home") + "/lib/security/cacerts".replace('/', File.separatorChar);
	}
}
