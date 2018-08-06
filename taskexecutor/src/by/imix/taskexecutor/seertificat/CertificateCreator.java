package by.imix.taskexecutor.seertificat;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

/**
 * Class for creating sertificat for specific sight
 */
public class CertificateCreator {
    private static final Logger _log= LoggerFactory.getLogger(CertificateCreator.class);
    /**
     * Method for set up certificate for site urlSite
     * @param urlSite url Site
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws MalformedURLException
     * @throws IOException
     */
    public static void getCertificate(String urlSite) throws NoSuchAlgorithmException, KeyManagementException, MalformedURLException, IOException {
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
        SSLContext.setDefault(ctx);
        URL url = new URL(null, urlSite, new sun.net.www.protocol.https.Handler());
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.connect();
        Certificate[] certs = conn.getServerCertificates();
        for (Certificate cert : certs) {
            if (cert instanceof X509Certificate) {
                X509Certificate cert509 = (X509Certificate)cert;
                _log.debug("Certificate is: " + cert509.getSubjectDN());
            }
        }
        conn.disconnect();
    }
}
