package com.jk.certmon.display;

import java.io.File;
import javax.swing.*;

import com.jk.certmon.utility.Constants;
import com.jk.certmon.utility.GetCert;
import com.jk.certmon.utility.CertManager;

public class Execute {

	static void doit(String value) {

		switch(value){
			case "Exit": System.exit(0);
				break;
			case "Choose File": chooseFile();
				break;
			case "About":
				JOptionPane.showMessageDialog(null, "About is under construction");
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
