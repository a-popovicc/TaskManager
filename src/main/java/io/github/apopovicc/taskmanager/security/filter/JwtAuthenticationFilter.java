package io.github.apopovicc.taskmanager.security.filter;

import io.github.apopovicc.taskmanager.security.jwt.JwtService;
import io.github.apopovicc.taskmanager.security.user.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService,
                                   CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Uzmi Authorization header
        String authHeader = request.getHeader("Authorization");

        // 2. Ako nema tokena → pusti request dalje
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Izvuci JWT token
        String token = authHeader.substring(7);

        // 4. Izvuci subject (u tvom slučaju → userId)
        UUID userId = jwtService.extractUserId(token);

        // 5. Ako već postoji authentication → nemoj duplirati
        if (userId == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 6. Validacija tokena
        boolean isValid = jwtService.isTokenValid(token);
        if (!isValid) {
            filterChain.doFilter(request, response);
            return;
        }

        // 7. Učitaj user-a iz baze preko ID-a
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId.toString());

        // 8. Kreiraj Authentication objekat
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        // 9. Postavi u SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 10. Nastavi dalje kroz filter chain
        filterChain.doFilter(request, response);
    }
}

