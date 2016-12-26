package com.jk.certmon.utility;

import java.io.File;

public class Constants {
    public static String TITLE = "certmon";
    public static String VERSION = "v0.1";
    public static String FILENAME = "";
    public static String CURRENT_CERT = "";
    public static String CERT_PASSWORD = "changeit";

    public static String getDefaultKeystore(){
        return System.getProperty("java.home") + "/lib/security/cacerts".replace('/', File.separatorChar);
    }
}
