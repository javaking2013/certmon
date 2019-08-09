package com.jk.certmon.display;

import java.io.File;
import javax.swing.*;

import com.jk.certmon.utility.*;

public class Execute {

	static void doit(String value) {
		Logger.debug("User clicked: " + value);
		switch(value){
			case "Exit": System.exit(0);
				break;
			case "Choose File": chooseFile();
				break;
			case "About":
				String showit = "Created by Patrick Meadows aka JavaKing\n" +
						"Version is " + Constants.VERSION + "\n" +
						//"Click here to get the latest version: <a href='https://github.com/javaking2013/certmon'>asdf</a>" +
						"Go here to get the latest version: https://github.com/javaking2013/certmon";
				certmon.certDetailsArea.setText(showit);
				break;
			case "Get Remote Certificate":
				certmon.certDetailsArea.setText(GetCert.getRemoteCertificate(certmon.fileField.getText()));
				certmon.certDetailsArea.setCaretPosition(0);
				break;
			case "Populate Default Keystore":
				populateDefaultKeystore();
				break;
			case "Import Certificate":
				CertManager.importCert();
				break;
			case "Remove Certificate":
				CertManager.removeCert();
				break;
			case "Analyze Certs":
				GetCert.analyzeCerts();
				break;
			case "Show Change Log":
				certmon.certDetailsArea.setText(Constants.changelog);
				break;
			default: JOptionPane.showMessageDialog(null, "This feature is not implemented yet.");
		}
	}

	public static void populateDefaultKeystore(){
		certmon.fileField.setText(Constants.getDefaultKeystore());
		certmon.listModel.removeAllElements();
		GetCert.getCertList();
	}

	private static void chooseFile(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(null);
		File file = fileChooser.getSelectedFile();
		if(!(file == null)){
			certmon.fileField.setText(file.toString());
			certmon.listModel.removeAllElements();
			GetCert.getCertList();
		}
	}
}
