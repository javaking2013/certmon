package com.jk.certmon.display;

import java.io.File;
import javax.swing.*;

import com.jk.certmon.utility.GetCert;

public class Execute {

	public static void doit(String value) {
		
		if(value.equals("Exit")){
			System.exit(0);
		}else if(value.equals("Choose File")){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showOpenDialog(null);
			File file = fileChooser.getSelectedFile();
			if(!(file == null)){
				certmon.fileField.setText(file.toString());
				certmon.listModel.removeAllElements();
				GetCert.getCertList();
			}
			
		}else if(value.equals("About")){
			
		}else if(value.equals("Get Cert Value")){
			JOptionPane.showMessageDialog(null, GetCert.getCertValue(Holder.getCurrentCert()));
		}else{
			JOptionPane.showMessageDialog(null, "This doesn't work yet: " + value);
		}
	}
}
