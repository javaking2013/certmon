package org.jk.testing;

import com.jk.certmon.utility.GetCert;

public class testit {
    public static void main(String args[]){
        System.out.println(GetCert.getRemoteCertificate("www.google.com"));
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
            public void run(){
                System.out.println("End of testing.");
            }
        }));
    }
}
