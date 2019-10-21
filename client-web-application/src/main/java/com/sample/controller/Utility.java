package com.sample.controller;

import java.time.Duration;
import java.util.Map;

import com.okta.jwt.AccessTokenVerifier;
import com.okta.jwt.Jwt;
import com.okta.jwt.JwtVerifiers;

public class Utility {

	private boolean verifyJwt(String jwtString) {

		if (jwtString == null && jwtString.isEmpty()) {
			return false;
		}

		AccessTokenVerifier jwtVerifier = JwtVerifiers.accessTokenVerifierBuilder()
				.setIssuer("https://YOUR-DOMAIN/oauth2/default").setAudience("api://default") 
				.setConnectionTimeout(Duration.ofSeconds(1))
				.setReadTimeout(Duration.ofSeconds(1))
				.build();
		try {
			jwtString = jwtString.substring(jwtString.indexOf(" ") + 1);
			Jwt jwt = jwtVerifier.decode(jwtString.trim());

			for (Map.Entry<String, Object> entry : jwt.getClaims().entrySet()) {
				System.out.println("Key = " + entry.getKey() + " Value = " + entry.getValue());
			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
