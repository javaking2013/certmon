package com.jk.certmon.display;

import java.io.File;
import javax.swing.*;

import com.jk.certmon.utility.Constants;
import com.jk.certmon.utility.GetCert;

class Execute {

	static void doit(String value) {

		switch(value){
			case "Exit": System.exit(0);
				break;
			case "Choose File": chooseFile();
				break;
			case "About":
				JOptionPane.showMessageDialog(null, "This feature is not implemented yet.");
				break;
			case "Get Remote Certificate":
				certmon.certDetailsArea.setText(GetCert.getRemoteCertificate(certmon.fileField.getText()));
				certmon.certDetailsArea.setCaretPosition(0);
				break;
			case "Populate Default Keystore":
				certmon.fileField.setText(Constants.getDefaultKeystore());
				certmon.listModel.removeAllElements();
				GetCert.getCertList();
				break;
			default: JOptionPane.showMessageDialog(null, "This feature is not implemented yet.");
		}
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
