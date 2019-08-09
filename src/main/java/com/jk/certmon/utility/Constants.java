package com.jk.certmon.utility;

import java.io.File;

public class Constants {
    public static String TITLE = "certmon";
    public static String VERSION = "v1.0";
    public static String FILENAME = "";
    public static String CURRENT_CERT = "";
    public static String CERT_PASSWORD = "changeit";

    public static final String BEGIN_CERT = "-----BEGIN CERTIFICATE-----";
    public static final String END_CERT = "-----END CERTIFICATE-----";

    public static String getDefaultKeystore(){
        return System.getProperty("java.home") + "/lib/security/cacerts".replace('/', File.separatorChar);
    }

    public static String changelog = "Changes and Features:\n\n" +
            "-- Version 1.0 --\n\n" +
            "- Added feature to analyze the expiration dates on each certificate in the keystore\n" +
            "- Added feature to change logging to app, console, or file\n" +
            "- Added logging framework\n" +
            "- Added settings framework for future development\n" +
            "\n" +
            "-- Version 0.3 --\n\n" +
            "- Added feature to remove a certificate from the keystore\n" +
            "\n" +
            "-- Version 0.2 --\n\n" +
            "- certmon now works with Java 10\n";
}
