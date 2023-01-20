package com.project.marketapi.web.controller;

import com.project.marketapi.domain.dto.AuthenticationRequest;
import com.project.marketapi.domain.dto.AuthenticationResponse;
import com.project.marketapi.domain.service.MarketUserDetailsService;
import com.project.marketapi.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth", method = RequestMethod.GET)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MarketUserDetailsService marketUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    //Metodo recibe peticiones post
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request){
        try {
            //Verificacion de usuario y contraseña correctos
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            //Obtener los detalles del usuario desde el servicio
            UserDetails userDetails = marketUserDetailsService.loadUserByUsername(request.getUsername());
            //Generar jwt
            String jwt = jwtUtil.generateToken(userDetails);


            //Verificacion de que se hizo bien
            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);

        } catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
