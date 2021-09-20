package com.miu.minionlinemarkert.filter;

import com.miu.minionlinemarkert.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AuthorizationFilter extends BasicAuthenticationFilter {


    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        String header = request.getHeader("auth");
        if (header == null || Objects.equals(header, "") || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("auth");
        if (token == null || token.equals("")) {
            return null;
        }
        String JwtSecret = JwtUtil.JWT_SECRET;
        Jws<Claims> parsedToken = Jwts.parserBuilder()
                .setSigningKey(JwtSecret.getBytes()).build()
                .parseClaimsJws(token.replace("Bearer ", ""));

        String userName = parsedToken.getBody().getSubject();
        List<SimpleGrantedAuthority> authorities = ((List<?>) parsedToken.getBody()
                .get("role")).stream()
                .map(authority -> new SimpleGrantedAuthority((String) authority))
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userName, null, authorities);
    }

}
