package com.easemob.server.example.comm;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class MyX509TrustManager implements X509TrustManager
{
    
    X509TrustManager myTrustManager;
    
    public MyX509TrustManager(String cacertFile, String password) throws Exception
    {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(cacertFile);
        keyStore.load(inputStream, password.toCharArray());
        
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        
        TrustManager trustManagers[] = trustManagerFactory.getTrustManagers();
        
        for(TrustManager trustManager : trustManagers)
        {
            if(trustManager instanceof X509TrustManager)
            {
                myTrustManager = (X509TrustManager) trustManager;
                return;
            }
        }
        
        throw new Exception("Couldn't initialize");
    }
    
    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException
    {
        
    }
    
    @Override
    public void checkServerTrusted(X509Certificate[] certificates, String authType) throws CertificateException
    {
        if((certificates != null) && (certificates.length == 1))
        {
            certificates[0].checkValidity();
        }
        else
        {
            myTrustManager.checkServerTrusted(certificates, authType);
        }
    }
    
    @Override
    public X509Certificate[] getAcceptedIssuers()
    {
        return myTrustManager.getAcceptedIssuers();
    }
}