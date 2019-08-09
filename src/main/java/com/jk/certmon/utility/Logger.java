package com.jk.certmon.utility;

import com.jk.certmon.display.certmon;
import javax.swing.*;
import java.util.Date;

public class Logger {

    public static void logit(Object input){
        switch(Settings.logLocation){
            case "app": logitToApp(input); break;
            case "console": logitToConsole(input); break;
            case "file": logitToFile(input); break;
            default: JOptionPane.showMessageDialog(null, "Something happened, log location not valid: " + Settings.logLocation);
        }
    }

    public static void debug(Object input){
        if(Settings.isDebug){
            logitToDebugFile(input);
        }else{
            logit(input);
        }
    }

    private static void logitToDebugFile(Object input){

    }

    private static void logitToApp(Object input){
        String s = String.valueOf(input);
        certmon.certDetailsArea.setText(new Date() + "::" + s);
    }

    private static void logitToConsole(Object input){
        String s = String.valueOf(input);
        System.out.println(new Date() + "::" + s);
    }

    private static void logitToFile(Object input){
        String s = String.valueOf(input);
        String logEntry = new Date() + "::" + s;
    }
}
