package com.project.marketapi.web.security.filter;

import com.project.marketapi.domain.service.MarketUserDetailsService;
import com.project.marketapi.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//OncePerRequestFilter = Este filtro se ejecuta cada que exista una peticion
@Component
public class JwtFilterRequest extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private MarketUserDetailsService marketUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Verifica si lo que viene en el header de la peticion es un token y si es correcto
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            String jwt = authorizationHeader.substring(7);
            //Verificar el usuario del jwt
            String username = jwtUtil.extractUsername(jwt);

            //Verificar que aun no exista ninguna autenticacion para ese usuario
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //Obtener los detaller del usuario para ver si existe
                UserDetails userDetails = marketUserDetailsService.loadUserByUsername(username);

                //Preguntar si el jwt es correcto
                if (jwtUtil.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    //Esto funciona para que una segunda vez no tenga que pasar nuevamente por toda la validacion
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
