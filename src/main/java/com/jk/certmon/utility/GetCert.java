package com.jk.certmon.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.*;
import java.util.Date;
import java.util.Iterator;
import com.jk.certmon.display.certmon;
import java.util.Base64;

import javax.net.ssl.*;

public class GetCert {
	
	private static String filename;
	private static KeyStore keystore;
	private static PKIXParameters params;
	private static Iterator<TrustAnchor> it;
	
	public static void setFileName(String inFileName){ filename = inFileName; }

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
            if(certmon.list != null){
                certmon.list.setSelectedIndex(0);
            }
        } catch (Exception e) { Logger.logit(e); }
	}

	public static void analyzeCerts(){
	    String result = "Here are the results of the expiration date audit:\n\n";
	    try{
	        KeyStore store = KeyStore.getInstance(KeyStore.getDefaultType());
	        store.load(new FileInputStream(certmon.fileField.getText()), certmon.pwField.getText().toCharArray());
	        PKIXParameters params = new PKIXParameters(keystore);
            for(TrustAnchor ta : params.getTrustAnchors()) {
                X509Certificate cert = ta.getTrustedCert();
                Date expDate = cert.getNotAfter();
                if (expDate.before(new Date())) {
                    result += keystore.getCertificateAlias(cert) + " has expired on " + expDate + "\n";
                }
            }
	        certmon.certDetailsArea.setText(result);
        }catch(Exception e){ Logger.logit(e); }
    }
	
	public static void getCertList(){
		getCertificate();
	}
	
	public static String getCertString(String alias){
		
		try {
            keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(new FileInputStream(certmon.fileField.getText()), certmon.pwField.getText().toCharArray());
            return keystore.getCertificate(alias).toString() + "\n\n" + getCertValue(alias);
        } catch (NullPointerException e) {
		    return "Certificate does not exist";
        } catch(KeyStoreException kse){
		    return "Something happened with the keystore: " + kse.toString();
        } catch(IOException fileException){
		    return "File does not exist or cannot be opened: " + fileException.toString();
        } catch(NoSuchAlgorithmException | CertificateException ioe){
		    return "Something happened with the certificate: " + ioe.toString();
        }
	}

	public static String getCertValue(String alias){
        try {
            keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(new FileInputStream(certmon.fileField.getText()), certmon.pwField.getText().toCharArray());
            String certValue = Base64.getEncoder().encodeToString(keystore.getCertificate(alias).getEncoded());
            return Constants.BEGIN_CERT + "\n" + certValue + Constants.END_CERT;
        } catch (NullPointerException e) {
            return "Certificate does not exist";
        } catch(KeyStoreException kse){
            return "Something happened with the keystore: " + kse.toString();
        } catch(IOException fileException){
            return "File does not exist or cannot be opened: " + fileException.toString();
        } catch(NoSuchAlgorithmException | CertificateException ioe){
            return "Something happened with the certificate: " + ioe.toString();
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

            return servercerts[0].toString() + "\n\n" + Constants.BEGIN_CERT + "\n" + Base64.getEncoder().encodeToString(servercerts[0].getEncoded()) + Constants.END_CERT;
        }catch(IOException e){
            return "Something happened connecting to " + url + ":: " + e.toString();
        } catch(NoSuchAlgorithmException | KeyManagementException kme){
	        return "Something happened with the key: " + kme.toString();
        }catch(CertificateEncodingException cee){
	        return "Something happened with the certificate encoding: " + cee;
        }
    }
}
