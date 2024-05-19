package com.huntercodexs.demo.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.huntercodexs.demo.config.JwtHelper;
import com.huntercodexs.demo.enumerator.TokenType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class Help4DevsJwtService {

    @Autowired
    JwtHelper jwtHelper;

    /**
     * @implNote Create a JWT Token
     */
    public String create(String subject) {
        Map<String, String> claims = new HashMap<>();
        claims.put("token_type", TokenType.TOKEN_TYPE_1.name());
        return jwtHelper.jwtCreator(subject, claims);
    }

    /**
     * @implNote Get the JWT Token from the HTTP Header Request HttpServletRequest
     */
    public String get(String bearerToken) {
        String jwt = bearerToken.replace("Bearer ","");
        if (jwt.isEmpty()) {
            log.info("JWT TOKEN NOT FOUND");
            return "";
        }
        return jwt;
    }

    /**
     * @implNote Get the JWT Token Type from type field in the Headers Request HttpServletRequest
     */
    public String type(String jwt) {
        DecodedJWT jwtDecode = JWT.decode(jwt);
        return jwtDecode.getClaim("token_type").asString();
    }

    /**
     * @implNote Get the Subject from type field in the Headers Request HttpServletRequest
     */
    public String subject(String jwt) {
        DecodedJWT jwtDecode = JWT.decode(jwt);
        return jwtDecode.getSubject();
    }

    /**
     * @implNote Check the JWT Token
     */
    public String identifier(String bearerToken, TokenType tokenType, String subject) {

        String token = get(bearerToken);
        String jwtType = type(token);

        if (!jwtType.equals(tokenType.name())) {
            log.info("INVALID JWT TOKEN TYPE");
            return null;
        }

        String identifier = subject(token);

        if (identifier == null || identifier.isEmpty() || !identifier.equals(subject)) {
            log.info("INVALID SUBJECT - NOT FOUND IN JWT TOKEN");
            return null;
        }

        return identifier;
    }

}
