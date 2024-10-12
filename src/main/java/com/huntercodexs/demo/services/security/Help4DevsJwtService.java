package com.huntercodexs.demo.services.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.huntercodexs.demo.config.JwtAssignHelper;
import com.huntercodexs.demo.config.JwtHelper;
import com.huntercodexs.demo.dto.JwtResponseDto;
import com.huntercodexs.demo.enumerator.TokenType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class Help4DevsJwtService {

    @Autowired
    JwtHelper jwtHelper;

    @Autowired
    JwtAssignHelper jwtAssignHelper;

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">jwt</h6>
     *
     * <p style="color: #CDCDCD">Create a JWT Token</p>
     *
     * @return String (JWT)
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public JwtResponseDto jwt() {
        JwtResponseDto responseDto = new JwtResponseDto();
        Map<String, Object> claims = new HashMap<>();
        claims.put("token_type", TokenType.TOKEN_TYPE_1.name());
        String jwt = jwtHelper.jwtCreator("sub_"+UUID.randomUUID(), claims);
        responseDto.setJwt(jwt);
        return responseDto;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">jwtCheck</h6>
     *
     * <p style="color: #CDCDCD">Check the JWT Token</p>
     *
     * @return boolean
     * @author huntercodexs (powered by jereelton-devel)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     */
    public boolean jwtCheck(String header) {
        String jwt = jwtHelper.jwtVerifier(header);
        return (jwt != null);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">jwtAssign</h6>
     *
     * <p style="color: #CDCDCD">Create a Assigned JWT Token</p>

     * @return String (JWT)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public JwtResponseDto jwtAssign() {
        JwtResponseDto responseDto = new JwtResponseDto();
        Map<String, String> claims = new HashMap<>();
        claims.put("token_type", TokenType.TOKEN_TYPE_1.name());
        String jwt = jwtAssignHelper.jwtCreator("sub_"+UUID.randomUUID(), claims);
        responseDto.setJwt(jwt);
        return responseDto;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">jwtAssignCheck</h6>
     *
     * <p style="color: #CDCDCD">Check the JWT Assigned Token</p>

     * @return String (JWT)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public boolean jwtAssignCheck(String header) {
        String jwt = get(header);
        String subject = subject(jwt);
        String identify = identifier(jwt, TokenType.TOKEN_TYPE_1, subject);
        System.out.println("===================> "+identify);
        return (identify != null);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">create</h6>
     *
     * <p style="color: #CDCDCD">Create a JWT Token</p>
     *
     * @param subject (String)
     * @return String (JWT)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public String create(String subject) {
        Map<String, String> claims = new HashMap<>();
        claims.put("token_type", TokenType.TOKEN_TYPE_1.name());
        return jwtAssignHelper.jwtCreator(subject, claims);
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">get</h6>
     *
     * <p style="color: #CDCDCD">Get the JWT Token from the HTTP Header Request HttpServletRequest</p>
     *
     * @param bearerToken (String)
     * @return String (JWT Token)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
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
     * <h6 style="color: #FFFF00; font-size: 11px">type</h6>
     *
     * <p style="color: #CDCDCD">Get the JWT Token Type from type field in the Headers Request HttpServletRequest</p>
     *
     * @param jwt (String)
     * @return String (JWT Token)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public String type(String jwt) {
        DecodedJWT jwtDecode = JWT.decode(jwt);
        return jwtDecode.getClaim("token_type").asString();
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">subject</h6>
     *
     * <p style="color: #CDCDCD">Get the Subject from type field in the Headers Request HttpServletRequest</p>
     *
     * @param jwt (String)
     * @return String (JWT Token)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public String subject(String jwt) {
        DecodedJWT jwtDecode = JWT.decode(jwt);
        return jwtDecode.getSubject();
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">identifier</h6>
     *
     * <p style="color: #CDCDCD">Check the JWT Token</p>
     *
     * @param bearerToken (String)
     * @param tokenType (TokenType)
     * @param subject (String)
     * @return String (JWT Token)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
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
