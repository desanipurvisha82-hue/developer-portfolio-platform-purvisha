package com.jaimin.portfolio_backend.security;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jaimin.portfolio_backend.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String email;

        System.out.println("[JwtAuthenticationFilter] Incoming request: " + request.getMethod() + " " + request.getRequestURI());
        System.out.println("[JwtAuthenticationFilter] Auth Header: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("[JwtAuthenticationFilter] No Bearer token found. Skipping filter.");
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);

        try {
            email = jwtService.extractUsername(jwt);
            System.out.println("[JwtAuthenticationFilter] Extracted email: " + email);
        } catch (Exception e) {
            System.err.println("[JwtAuthenticationFilter] Failed to extract username: " + e.getMessage());
            filterChain.doFilter(request, response);
            return;
        }

        if (email != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            boolean isValid = jwtService.isTokenValid(jwt, userDetails.getUsername());
            System.out.println("[JwtAuthenticationFilter] Is token valid for " + userDetails.getUsername() + "? " + isValid);

            if (isValid) {

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request));

                SecurityContextHolder.getContext()
                        .setAuthentication(authToken);
                System.out.println("[JwtAuthenticationFilter] User authenticated successfully: " + email);
            }
        }

        filterChain.doFilter(request, response);
    }
}
