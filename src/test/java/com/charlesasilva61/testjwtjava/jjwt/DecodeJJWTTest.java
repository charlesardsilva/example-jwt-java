package com.charlesasilva61.testjwtjava.jjwt;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;

public class DecodeJJWTTest {

	@Test(expected = IllegalArgumentException.class)
	public void ifJwtIsNullThrowException() {
		DecodeJJWT.decodeHS256(null, "secret");
	}

	@Test(expected = IllegalArgumentException.class)
	public void ifJwtIsEmptyThrowException() {
		DecodeJJWT.decodeHS256("", "secret");
	}

	@Test(expected = IllegalArgumentException.class)
	public void ifSecretIsNullThrowException() {
		DecodeJJWT.decodeHS256("token", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void ifSecretIsEmptyThrowException() {
		DecodeJJWT.decodeHS256("token", "");
	}

	@Test(expected=SignatureException.class)
	public void ifSecretIsWrongThrowException() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MTQ2ODU2MDAsImp0aSI6Im15LWlkIn0.EiasFBmFVfS3ystNbrl8vVcH4zZmzO25QBF2nvGdv-Y";
		DecodeJJWT.decodeHS256(token, "my-wrong-secret");
	}

	@Test
	public void mustReturnCorrectValues() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MTQ2ODU2MDAsImp0aSI6Im15LWlkIn0.EiasFBmFVfS3ystNbrl8vVcH4zZmzO25QBF2nvGdv-Y";

		Claims claims = DecodeJJWT.decodeHS256(token, "my-secret");
		Assert.assertThat(claims.getId(), Matchers.equalTo("my-id"));
	}
}
