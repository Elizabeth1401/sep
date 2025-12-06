package com.findmyclub.security;

import com.findmyclub.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class JWTService {

    // usually we cannot commit the secret key into gitHub, and we need to hide it and load from the safe place
    private static final String SECRET_KEY = "yourSecretKeyHerexmgxdghcfhmfcgyhcdfmhfjcgjcgmdcfhmf";
    public String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("username", user.getUsername())
                .claim("email", user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ 3600000)) // 1 hour
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
