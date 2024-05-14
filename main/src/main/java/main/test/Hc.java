package main.test;

import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import javax.net.ssl.HttpsURLConnection;
import javax.security.cert.CertificateException;


public class Hc {
    public static void main(String[] args) throws IOException, CertificateEncodingException, CertificateException {
        if (args == null || args.length == 0) {
            System.err.println("url parameter not supplied.");
            System.exit(0);
        }

        URL url = new URL(args[0]);

        // 프록시를 사용하지 않도록 설정
        System.setProperty("java.net.useSystemProxies", "false");

        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        System.out.println("Response Code : " + con.getResponseCode());
        System.out.println("Cipher Suite : " + con.getCipherSuite());
        System.out.println("\n");
        Certificate[] certs = con.getServerCertificates();
        for (Certificate cert : certs) {
            javax.security.cert.X509Certificate c = javax.security.cert.X509Certificate.getInstance(cert.getEncoded());
            System.out.println("\tCert Dn : " + c.getSubjectDN());
            System.out.println("\tIssuer Dn : " + c.getIssuerDN());
            System.out.println("\n");
        }

        con.disconnect();
    }
}
