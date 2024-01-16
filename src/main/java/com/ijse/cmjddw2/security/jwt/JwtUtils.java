package com.ijse.cmjddw2.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${app.secret}")
    private String jwtSecret;

    @Value("${app.jwtExpiration}")
    private int jwtExpiration;

//    private Key key(){
//        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
//    }

    private KeyPair generateEcKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            keyPairGenerator.initialize(256); // You can adjust the key size as needed
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new SignatureException("Error generating EC key pair", e);
        }
    }

    private PrivateKey getEcPrivateKey() {
        return generateEcKeyPair().getPrivate();
    }

    private Key key() {
        return getEcPrivateKey();
    }

    public String generateJwtToken(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpiration))
                .signWith(key(), SignatureAlgorithm.ES256)
                .compact();
    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        }catch (MalformedJwtException e){
            System.out.println("Invalid Token");
        }catch (ExpiredJwtException e){
            System.out.println("Expired Token");
        }catch (UnsupportedJwtException e){
            System.out.println("Unsupported token format");
        }catch (IllegalArgumentException e){
            System.out.println("Token blank");
        }
        return false;
    }

    public String getUsernameFromJwtToken(String authToken){
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJwt(authToken).getBody().getSubject();
    }

}
