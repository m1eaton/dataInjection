package repositoryapp.data.connection.vplex;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Certs
{

    /**
     * Sets the https connection to trust all certifications.
     * 
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    public static void ignoreCertifications() throws KeyManagementException,
            NoSuchAlgorithmException
    {
        System.setProperty("javax.net.debug", "true");
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session)
            {
                return true;
            }
        });

        trustAllHttpsCertificates();
    }

    private static void trustAllHttpsCertificates()
            throws KeyManagementException, NoSuchAlgorithmException
    {
        // Create a trust manager that does not validate certificate chains:
        TrustManager[] trustAllCerts = new TrustManager[1];

        TrustManager tm = new AllTM();
        trustAllCerts[0] = tm;

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    /**
     * This is a trust manager that trusts all certificates
     */
    private static class AllTM implements TrustManager, X509TrustManager
    {
        public X509Certificate[] getAcceptedIssuers()
        {
            return null;
        }

        @SuppressWarnings("unused")
        public boolean isServerTrusted(X509Certificate[] certs)
        {
            return true;
        }

        @SuppressWarnings("unused")
        public boolean isClientTrusted(X509Certificate[] certs)
        {
            return true;
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType)
                throws CertificateException
        {
            return;
        }

        public void checkClientTrusted(X509Certificate[] certs, String authType)
                throws CertificateException
        {
            return;
        }
    }

}
