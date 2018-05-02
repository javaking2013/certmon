package com.jk.certmon.utility;

import java.io.File;

public class Constants {
    public static String TITLE = "certmon";
    public static String VERSION = "v0.2";
    public static String FILENAME = "";
    public static String CURRENT_CERT = "";
    public static String CERT_PASSWORD = "changeit";

    public static final String BEGIN_CERT = "-----BEGIN CERTIFICATE-----";
    public static final String END_CERT = "-----END CERTIFICATE-----";

    public static String getDefaultKeystore(){
        return System.getProperty("java.home") + "/lib/security/cacerts".replace('/', File.separatorChar);
    }
}
