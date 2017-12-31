package com.charlesasilva61.testjwtjava.jjwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SuppressWarnings("restriction")
public final class EncodeJJWT {

	private EncodeJJWT(){}
	
	public static JwtBuilder encodeHS256(String secret, long timeToExpire) {
		if (secret == null || secret.isEmpty()) {
			throw new IllegalArgumentException("The secret is required.");
		}
		
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		return Jwts.builder().signWith(signatureAlgorithm, signingKey)
				.setExpiration(generateTimeToExpire(timeToExpire)).setIssuedAt(new Date());
	}

	private static Date generateTimeToExpire(long timeToExpire) {
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + timeToExpire;
		return new Date(expMillis);
	}
}
