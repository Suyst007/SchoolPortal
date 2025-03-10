package com.example.school.school.Service;




import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmConstraints;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.key}")
    private String algorithmkey;
    @Value("${jwt.expiration}")
    private int  expirationTime;
    @Value("${jwt.issuer}")
    private String issuer;


    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct() throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(algorithmkey);

    }
    public String genrateToken(String name){
        return JWT.create().withClaim("name",name)
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + expirationTime))
                .withIssuer(issuer)
                .sign(algorithm);

       }
    public String verifytoken(String token){
        DecodedJWT decodedJWT = JWT.require(algorithm)

                .build()
                .verify(token);
        return decodedJWT.getClaim("name").asString();
    }









}
