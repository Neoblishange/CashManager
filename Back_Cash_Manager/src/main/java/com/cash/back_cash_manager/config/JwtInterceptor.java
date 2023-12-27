package com.cash.back_cash_manager.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.security.Key;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private final Key secretKey;

    public JwtInterceptor(@Value("${MY_SECRET_KEY}") String secretKeyString) {
        this.secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Ignore /login and /register
        if ("/login".equals(request.getRequestURI()) || "/register".equals(request.getRequestURI())) {
            return true;
        }

        // Get the token from the request header
        String authorizationToken = request.getHeader("Authorization");

        // Validate and parse the token
        if (authorizationToken != null && authorizationToken.startsWith("Bearer ")) {
            authorizationToken = authorizationToken.substring(7); // Remove "Bearer "
            try {
                Jws<Claims> jws = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(authorizationToken);

                Claims claims = jws.getBody();
                // You can now access the claims and use them as needed
                request.setAttribute("claims", claims);
                return true;
            } catch (Exception e) {
                // Handle token validation failure
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }

        // No token or invalid token
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // This method can be used for additional processing after the request has been handled by the controller
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // This method is called after the response has been sent to the client
    }
}

