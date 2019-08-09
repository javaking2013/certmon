package com.jk.certmon.utility;

import com.jk.certmon.display.certmon;
import javax.swing.*;
import java.lang.reflect.Field;

public class Settings {

    public static String logLocation = "app";
    public static boolean isDebug = false;

    public static void loadSettings(){
        // todo load all of these settings
        // check if settings file exists
        // if not then create file with default values
        // if so then load all the settings into the fields
    }

    public static void flipDebug(){
        if(isDebug){ isDebug = false; }
        else{ isDebug = true; }
        JOptionPane.showMessageDialog(null, "Debug is now " + isDebug);
    }

    public static void saveSetting(String key, String value){
        // todo append setting to file
        // determine if platform is windows, linux, or mac to get file location
        try {
            Settings.class.getDeclaredField(key).set(null, value);
        }catch(Exception e){ Logger.logit(e); }
    }

    public static void showSettings(){
        String result = "All settings and values\n\n";
        try {
            for (Field field : Settings.class.getDeclaredFields()) {
                result += field.getName() + ": " + field.get(null) + "\n";
            }
        }catch(Exception e){ Logger.logit(e); }
        certmon.certDetailsArea.setText(result);
    }

    public static JMenu getSettingsSubmenu(){
        JMenu settingsSubmMenu = new JMenu("Settings");
        settingsSubmMenu.add(getMenuItem("Show Settings"));
        settingsSubmMenu.add(getMenuItem("Turn Debug On/Off"));

        JMenu loggerMenu = new JMenu("Choose Logger");
        loggerMenu.add(getMenuItem("Change Logging to Console"));
        loggerMenu.add(getMenuItem("Change Logging to Application"));
        loggerMenu.add(getMenuItem("Change Logging to File"));

        settingsSubmMenu.add(loggerMenu);

        settingsSubmMenu.add(getMenuItem("Restore Defaults"));
        return settingsSubmMenu;
    }

    private static JMenuItem getMenuItem(String input){
        JMenuItem item = new JMenuItem(input);
        item.addActionListener(e -> executeCommand(input));
        return item;
    }

    private static void executeCommand(String input){
        switch(input){
            case "Show Settings": Settings.showSettings(); break;
            case "Turn Debug On/Off": flipDebug(); break;
            case "Change Logging to Console": logLocation = "console"; break;
            case "Change Logging to Application": logLocation = "app"; break;
            case "Change Logging to File": logLocation = "file"; break;
            case "Restore Defaults": restoreDefaults(); break;
            default: JOptionPane.showMessageDialog(null, "This setting does not exist.");
        }
    }

    private static void restoreDefaults(){
        if(JOptionPane.showConfirmDialog(null, "This will overwrite all settings, continue?") == JOptionPane.YES_OPTION){
            logLocation = "app";
            isDebug = false;
            executeCommand("Show Settings");
        }
    }

    public static void standupStandaloneSettingsFrame(){}
}
