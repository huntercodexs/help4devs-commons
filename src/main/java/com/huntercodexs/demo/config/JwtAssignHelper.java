package com.huntercodexs.demo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.Map;

@Component
public class JwtAssignHelper {
	
	@Autowired
	RSAPrivateKey rsaPrivateKey;

	@Autowired
	RSAPublicKey rsaPublicKey;
	
	@Value("${app.jwt.expiration-minutes}")
	int expireTimer;
	
	public String jwtCreator(String subject, Map<String, String> claims) {
		JWTCreator.Builder jwtBuilder = JWT.create().withSubject(subject);
		claims.forEach(jwtBuilder::withClaim);

		return jwtBuilder
				.withNotBefore(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60 * expireTimer))
				.sign(Algorithm.RSA512(rsaPublicKey, rsaPrivateKey));
	}
	
}