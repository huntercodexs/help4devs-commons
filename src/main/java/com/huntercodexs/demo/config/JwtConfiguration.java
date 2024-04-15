package com.huntercodexs.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Slf4j
@Configuration
public class JwtConfiguration {
	
	@Value("${app.jwt.keystore-location}")
	String keyStorePath;

	@Value("${app.jwt.keystore-password}")
	String keyStorePassword;

	@Value("${app.jwt.key-alias}")
	String keyAlias;

	@Value("${app.jwt.private-key-passphrase}")
	String privateKeyPassphrase;
	
	@Bean
	public KeyStore keyStore() {
		try {

	    	KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
	    	keyStore.load(new FileInputStream(keyStorePath.toString()), keyStorePassword.toCharArray());
	    	return keyStore;

		} catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException e) {
			log.error("Keystore not loaded correctly: " + e.getMessage());
		}
		
		throw new IllegalArgumentException("Keystore Error: Unable to load it");
	}
	
	@Bean
	public RSAPrivateKey jwtSigningKey(KeyStore keyStore) {
		try {

			Key key = keyStore.getKey(keyAlias, privateKeyPassphrase.toCharArray());

			if (key instanceof RSAPrivateKey) {
				return (RSAPrivateKey) key;
			}

		} catch (UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e) {
			log.error("Keystore not loaded correctly: " + e.getMessage());
		}
		
		throw new IllegalArgumentException("Keystore Error: Unable to load it");
	}
	
	@Bean
	public RSAPublicKey jwtValidationKey(KeyStore keyStore) {
		try {

			Certificate certificate = keyStore.getCertificate(keyAlias);
			PublicKey publicKey = certificate.getPublicKey();
			
			if (publicKey instanceof RSAPublicKey) {
				return (RSAPublicKey) publicKey;
			}

		} catch (KeyStoreException e) {
			log.error("Keystore not loaded correctly: " + e.getMessage());
		}
		
		throw new IllegalArgumentException("Keystore Error: Unable to load it");
	}
	
}
