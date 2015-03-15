package com.jk.certmon.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.Iterator;

import com.jk.certmon.display.certmon;

public class GetCert {
	
	private static String filename;
	private static String password;
	private static KeyStore keystore;
	private static PKIXParameters params;
	private static Iterator it;
	
	public static void setFileName(String inFileName){filename = inFileName;}

	private static void getCertificate(){
		try {
            // Load the JDK's cacerts keystore file
            filename = certmon.fileField.getText();
            FileInputStream is = new FileInputStream(filename);
            keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            password = "changeit";
            keystore.load(is, password.toCharArray());
            
            // This class retrieves the most-trusted CAs from the keystore
            params = new PKIXParameters(keystore);
            
            // Get the set of trust anchors, which contain the most-trusted CA certificates
            it = params.getTrustAnchors().iterator();
            while( it.hasNext() ) {
                TrustAnchor ta = (TrustAnchor)it.next();
                X509Certificate cert = ta.getTrustedCert();
                certmon.listModel.addElement(keystore.getCertificateAlias(cert));
            }
            is.close();
        } catch (CertificateException e) {
        } catch (KeyStoreException e) {
        } catch (NoSuchAlgorithmException e) {
        } catch (InvalidAlgorithmParameterException e) {
        } catch (IOException e) {
        } 
	}
	
	public static void getCertList(){
		getCertificate();
	}
	
	public static String getCertString(String alias){
		
		try {
            // Load the JDK's cacerts keystore file
            filename = certmon.fileField.getText();
            
            FileInputStream is = new FileInputStream(filename);
            keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            password = "changeit";
            keystore.load(is, password.toCharArray());

            // This class retrieves the most-trusted CAs from the keystore
            params = new PKIXParameters(keystore);

            // Get the set of trust anchors, which contain the most-trusted CA certificates
            it = params.getTrustAnchors().iterator();
            while( it.hasNext() ) {
                TrustAnchor ta = (TrustAnchor)it.next();
                X509Certificate cert = ta.getTrustedCert();
                if(keystore.getCertificateAlias(cert).equals(alias)){
                	return cert.toString();
                }
            }
            is.close();
        } catch (CertificateException e) {
        } catch (KeyStoreException e) {
        } catch (NoSuchAlgorithmException e) {
        } catch (InvalidAlgorithmParameterException e) {
        } catch (IOException e) {
        } 
		
		return "";
	}
}
