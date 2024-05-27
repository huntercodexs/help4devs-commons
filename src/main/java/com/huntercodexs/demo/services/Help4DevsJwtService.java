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
     * @param subject (String)
     * @return String (JWT)
     * @implNote Create a JWT Token
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public String create(String subject) {
        Map<String, String> claims = new HashMap<>();
        claims.put("token_type", TokenType.TOKEN_TYPE_1.name());
        return jwtHelper.jwtCreator(subject, claims);
    }

    /**
     * @param bearerToken (String)
     * @return String (JWT Token)
     * @implNote Get the JWT Token from the HTTP Header Request HttpServletRequest
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public String get(String bearerToken) {
        String jwt = bearerToken.replace("Bearer ","");
        if (jwt.isEmpty()) {
            log.info("JWT TOKEN NOT FOUND");
            return "";
        }
        return jwt;
    }

    /**
     * @param jwt (String)
     * @return String (JWT Token)
     * @implNote Get the JWT Token Type from type field in the Headers Request HttpServletRequest
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public String type(String jwt) {
        DecodedJWT jwtDecode = JWT.decode(jwt);
        return jwtDecode.getClaim("token_type").asString();
    }

    /**
     * @param jwt (String)
     * @return String (JWT Token)
     * @implNote Get the Subject from type field in the Headers Request HttpServletRequest
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public String subject(String jwt) {
        DecodedJWT jwtDecode = JWT.decode(jwt);
        return jwtDecode.getSubject();
    }

    /**
     * @param bearerToken (String)
     * @param tokenType (TokenType)
     * @param subject (String)
     * @return String (JWT Token)
     * @implNote Check the JWT Token
     * @see <a href="https://github.com/huntercodexs/help4devs">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
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
