package com.charlesasilva61.testjwtjava.jjwt;

import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@SuppressWarnings("restriction")
public final class DecodeJJWT {

	private DecodeJJWT(){}
	
	public static Claims decodeHS256(String jwt, String secret) {
		if (isBlank(jwt) || isBlank(secret)) {
			throw new IllegalArgumentException("Jwt and Secret are required.");
		}

	    return Jwts.parser()         
	       .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
	       .parseClaimsJws(jwt).getBody();
	}
	
	private static boolean isBlank(String value) {
		return value == null || value.isEmpty();
	}

}
