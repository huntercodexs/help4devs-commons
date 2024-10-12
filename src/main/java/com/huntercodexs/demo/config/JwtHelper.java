package com.huntercodexs.demo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.huntercodexs.demo.enumerator.TokenType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Map;

@Component
public class JwtHelper {

	@Value("${jwt.secret-key.token:AAA-BBB-CCC-DDD}")
	String jwtSecret;

	@Value("${app.jwt.expiration-time:15}")
	int expireTime;
	
	public String jwtCreator(String subject, Map<String, Object> claims) {

		Date expirationDate = Date.from(
				LocalDateTime
						.now()
						.plusMinutes(expireTime)
						.atZone(ZoneOffset.systemDefault()).toInstant());

        return Jwts.builder()
                .setSubject(subject)
				.addClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS384, jwtSecret)
                .compact();
	}

	public String jwtVerifier(String header) {
		String token = header.replace("Bearer ","");
		DecodedJWT decode = JWT.decode(token);

		if (!decode.getClaim("token_type").asString().equals(TokenType.TOKEN_TYPE_1.name())) {
			return null;
		}

		if (!decode.getSubject().matches("^(sub_.*)$")) {
			return null;
		}

        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
	}
	
}