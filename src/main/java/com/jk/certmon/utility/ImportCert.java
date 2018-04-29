package com.jk.certmon.utility;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import com.jk.certmon.display.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;

public class ImportCert {

    public static void doit(){

        String clipboardData;
        String certText;

        try {
            //Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboardData = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            if(clipboardData.contains("-----BEGIN CERTIFICATE-----")){
                //JOptionPane.showMessageDialog(null, "Positive");
                certText = clipboardData;
            }

            char[] password = certmon.pwField.getText().toCharArray();
            String alias = JOptionPane.showInputDialog("Alias of new cert:");

            String certfile = JOptionPane.showInputDialog("Path and filename of certificate:");
            FileInputStream is = new FileInputStream(certmon.fileField.getText());

            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(is, password);

            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream certstream = fullStream(certfile);
            Certificate certs =  cf.generateCertificate(certstream);

            File keystoreFile = new File(certmon.fileField.getText());
            FileInputStream in = new FileInputStream(keystoreFile);
            keystore.load(in, password);
            in.close();

            keystore.setCertificateEntry(alias, certs);

            FileOutputStream out = new FileOutputStream(keystoreFile);
            keystore.store(out, password);
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static InputStream fullStream( String fname ) throws IOException {
        FileInputStream fis = new FileInputStream(fname);
        DataInputStream dis = new DataInputStream(fis);
        byte[] bytes = new byte[dis.available()];
        dis.readFully(bytes);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        return bais;
    }
}
