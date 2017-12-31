package com.charlesasilva61.testjwtjava.jjwt;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.jsonwebtoken.JwtBuilder;

public class EncodeJJWTTest {

	@Test(expected=IllegalArgumentException.class)
	public void ifSecretIsNullThrowExceptino() {
		EncodeJJWT.encodeHS256(null, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void ifSecretIsEmptyThrowExceptino() {
		EncodeJJWT.encodeHS256("", 0);
	}
	
	@Test
	public void generateTokenWithSuccess() {
		JwtBuilder jwtBuilder = EncodeJJWT.encodeHS256("my-secret", 100);
		jwtBuilder.setId("my-id");
		jwtBuilder.setExpiration(null);
		jwtBuilder.setIssuedAt(new GregorianCalendar(2017, Calendar.DECEMBER, 31).getTime());
		
		String expectedToken = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MTQ2ODU2MDAsImp0aSI6Im15LWlkIn0.EiasFBmFVfS3ystNbrl8vVcH4zZmzO25QBF2nvGdv-Y";
		
		Assert.assertThat(jwtBuilder.compact(),  Matchers.equalTo(expectedToken)); 
	}
}
