package com.project.marketapi.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    //En esta constante se define la clave
    private static final String KEY = "projectmarket";

    //Metodo genera token
    public String generateToken(UserDetails userDetails){
        //En una secuencia de metodos vamos a construir el jwt
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

    //Metodo validacion si el token es correcto
    public boolean validateToken(String token, UserDetails userDetails){
        return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
    }

    //Metodo que extrae el usuario
    public String extractUsername(String token){
        return getClaims(token).getSubject(); //Subject=Es donde está el Usuario de la peticion
    }

    //Metodo que verifique si el token ya expiró
    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    //Metodo auxiliar que retorne los objetos dentro del jwt
    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        //Tengo un parser, le añado la llave de la firma, y cuando se verifique la firma correcta
        //Va a obtener el cuerpo del jwt separado por cada uno de los objetos
    }

}
