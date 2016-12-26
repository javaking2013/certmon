package com.jk.certmon.utility;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.Iterator;

import com.jk.certmon.display.certmon;
import sun.misc.BASE64Encoder;
import sun.security.provider.X509Factory;

import javax.net.ssl.*;

public class GetCert {
	
	private static String filename;
	private static KeyStore keystore;
	private static PKIXParameters params;
	private static Iterator<TrustAnchor> it;
	
	public static void setFileName(String inFileName){filename = inFileName;}

	private static void getCertificate(){
		try {
            keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(new FileInputStream(certmon.fileField.getText()), certmon.pwField.getText().toCharArray());
            params = new PKIXParameters(keystore);
            
            // Get the set of trust anchors, which contain the most-trusted CA certificates
            it = params.getTrustAnchors().iterator();
            while( it.hasNext() ) {
                TrustAnchor ta = it.next();
                X509Certificate cert = ta.getTrustedCert();
                certmon.listModel.addElement(keystore.getCertificateAlias(cert));
            }
        } catch (Exception e) {
		    certmon.certDetailsArea.setText(e.toString());
        } 
	}
	
	public static void getCertList(){
		getCertificate();
	}
	
	public static String getCertString(String alias){
		
		try {
            keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(new FileInputStream(certmon.fileField.getText()), certmon.pwField.getText().toCharArray());
            return keystore.getCertificate(alias).toString() + "\n\n" + getCertValue(alias);
        } catch (Exception e) {
            return e.toString();
        } 
	}

	public static String getCertValue(String alias){
        try {
            keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(new FileInputStream(certmon.fileField.getText()), certmon.pwField.getText().toCharArray());
            String certValue = new BASE64Encoder().encodeBuffer(keystore.getCertificate(alias).getEncoded());
            return X509Factory.BEGIN_CERT + "\n" + certValue + X509Factory.END_CERT;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static String getRemoteCertificate(String url){
	    try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, null, null);
            SSLSocketFactory factory = sc.getSocketFactory();
            SSLSocket socket = (SSLSocket) factory.createSocket(url, 443);
            socket.startHandshake();
            SSLSession session = socket.getSession();
            Certificate[] servercerts = session.getPeerCertificates();
            socket.close();

            return servercerts[0].toString() + "\n\n" + X509Factory.BEGIN_CERT + "\n" + new BASE64Encoder().encodeBuffer(servercerts[0].getEncoded()) + X509Factory.END_CERT;
        }catch(Exception e){
	       return e.toString();
        }
    }
}
