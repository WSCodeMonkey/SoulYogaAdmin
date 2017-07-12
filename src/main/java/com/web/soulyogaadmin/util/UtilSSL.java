package com.web.soulyogaadmin.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;

public class UtilSSL {
	
	/*keystore and  truststore for ctrip gateway usage*/
	public static SSLConnectionSocketFactory generateSSL(String localCert,
			String keystoreFile, String certPassword,
			String keyStorePassword) throws KeyStoreException,
			IOException, NoSuchAlgorithmException,
			KeyManagementException, UnrecoverableKeyException {
		
		
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		
		
	       InputStream instream = UtilProperties.class.getClassLoader()
				.getResourceAsStream(localCert);//加载本地的证书进行https加密传输
	        try {
	            keyStore.load(instream, certPassword.toCharArray());//设置证书密码
	        } catch (CertificateException e) {
	            e.printStackTrace();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } finally {
	            instream.close();
	        }

	                // 设置truststore
	                KeyStore trustStore = KeyStore.getInstance("jks");
	                InputStream instream2 = UtilProperties.class.getClassLoader()
					.getResourceAsStream(keystoreFile);
	                try {
				trustStore.load(instream2, keyStorePassword.toCharArray());
			} catch (CertificateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	                } finally {
	                    try {
	                	    instream2.close();
	                    } catch (Exception ignore) {
	                    }
	                }

	        // Trust own CA and all self-signed certs
	        @SuppressWarnings("deprecation")
			SSLContext sslcontext = SSLContexts.custom()
			        .loadKeyMaterial(keyStore, certPassword.toCharArray())
			       // .loadTrustMaterial(trustStore)
			        .loadTrustMaterial(null, (certificate, authType) -> true)
			        .build();

	        // Allow TLSv1 protocol only
	        @SuppressWarnings("deprecation")
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	                sslcontext,
	                new String[]{"TLSv1"},
	                null,
	                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		return sslsf;
	}
	
	/*only truststore for Liberty usage*/
	public static SSLConnectionSocketFactory generateSSL(
			String keystoreFile,
			String keyStorePassword) throws KeyStoreException,
			IOException, NoSuchAlgorithmException,
			KeyManagementException, UnrecoverableKeyException {
		
	                // 设置truststore
	                KeyStore trustStore = KeyStore.getInstance("jks");
	                InputStream instream2 = UtilProperties.class.getClassLoader()
					.getResourceAsStream(keystoreFile);
	                try {
				trustStore.load(instream2, keyStorePassword.toCharArray());
			} catch (CertificateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	                } finally {
	                    try {
	                	    instream2.close();
	                    } catch (Exception ignore) {
	                    }
	                }

	        // Trust own CA and all self-signed certs
	        @SuppressWarnings("deprecation")
			SSLContext sslcontext = SSLContexts.custom()
			        //.loadTrustMaterial(trustStore)
				.loadTrustMaterial(null, (certificate, authType) -> true)
			        .build();

	        // Allow TLSv1 protocol only
	        @SuppressWarnings("deprecation")
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	                sslcontext,
	                new String[]{"TLSv1"},
	                null,
	                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		return sslsf;
	}
	
	public static SSLSocketFactory generateSSLFactory(String localCert,
			String keystoreFile, String certPassword,
			String keyStorePassword) throws KeyStoreException,
			IOException, NoSuchAlgorithmException,
			KeyManagementException, UnrecoverableKeyException {
		
		
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		
		
	       InputStream instream = UtilProperties.class.getClassLoader()
				.getResourceAsStream(localCert);//加载本地的证书进行https加密传输
	        try {
	            keyStore.load(instream, certPassword.toCharArray());//设置证书密码
	        } catch (CertificateException e) {
	            e.printStackTrace();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } finally {
	            instream.close();
	        }

	                // 设置truststore
	                KeyStore trustStore = KeyStore.getInstance("jks");
	                InputStream instream2 = UtilProperties.class.getClassLoader()
					.getResourceAsStream(keystoreFile);
	                try {
				trustStore.load(instream2, keyStorePassword.toCharArray());
			} catch (CertificateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	                } finally {
	                    try {
	                	    instream2.close();
	                    } catch (Exception ignore) {
	                    }
	                }

	        // Trust own CA and all self-signed certs
	        @SuppressWarnings("deprecation")
			SSLContext sslcontext = SSLContexts.custom()
			        .loadKeyMaterial(keyStore, certPassword.toCharArray())
			        //.loadTrustMaterial(trustStore)
			       .loadTrustMaterial(null, (certificate, authType) -> true)
			        .build();

	        
		return sslcontext.getSocketFactory();
	}
	
	public static SSLSocketFactory generateSSLFactory(
			String keystoreFile, 
			String keyStorePassword) throws KeyStoreException,
			IOException, NoSuchAlgorithmException,
			KeyManagementException, UnrecoverableKeyException {
		
		
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		

	                // 设置truststore
	                KeyStore trustStore = KeyStore.getInstance("jks");
	                InputStream instream2 = UtilProperties.class.getClassLoader()
					.getResourceAsStream(keystoreFile);
	                try {
				trustStore.load(instream2, keyStorePassword.toCharArray());
			} catch (CertificateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	                } finally {
	                    try {
	                	    instream2.close();
	                    } catch (Exception ignore) {
	                    }
	                }

	        // Trust own CA and all self-signed certs
	        @SuppressWarnings("deprecation")
			SSLContext sslcontext = SSLContexts.custom()
			        //.loadTrustMaterial(trustStore)
			        .loadTrustMaterial(null, (certificate, authType) -> true)
			        .build();

	        
		return sslcontext.getSocketFactory();
	}
	
}
