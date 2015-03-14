package com.jk.certmon.display;

import com.jk.certmon.utility.GetCert;

public class Execute {

	public static void doit(String value) {
		
		if(value.equals("Exit")){
			System.exit(0);
		}else if(value.equals("List Cert")){
			certmon.certDetailsArea.setText(GetCert.getCertString((String)certmon.list.getSelectedValue()));
			certmon.certDetailsArea.setCaretPosition(0);
		}else if(value.equals("Choose File")){
			// file chooser dialog box
			// set filename in holder and title
			// updates list and clears details
		}else if(value.equals("About")){
			
		}
	}
}
