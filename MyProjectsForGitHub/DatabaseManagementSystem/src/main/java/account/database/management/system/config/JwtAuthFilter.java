package account.database.management.system.config;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthenticationProvider userAuthenticationProvider;

    public JwtAuthFilter(UserAuthenticationProvider userAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null) {
            String[] authElements = header.split(" ");

            if (authElements.length == 2 && "Bearer".equals(authElements[0])) {
                try {
                    if (request.getRequestURI().startsWith("/job/seeker")) {
                        authenticateJobSeeker(request, authElements[1]);
                    } else if (request.getRequestURI().startsWith("/company")) {
                        authenticateCompany(request, authElements[1]);
                    }
                } catch (RuntimeException e) {
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private void authenticateJobSeeker(HttpServletRequest request, String token) {
        if ("GET".equals(request.getMethod())) {
            SecurityContextHolder.getContext().setAuthentication(
                    userAuthenticationProvider.validateJobSeekerToken(token));
        } else {
            SecurityContextHolder.getContext().setAuthentication(
                    userAuthenticationProvider.validateJobSeekerTokenStrongly(token));
        }
    }

    private void authenticateCompany(HttpServletRequest request, String token) {
        if ("GET".equals(request.getMethod())) {
            SecurityContextHolder.getContext().setAuthentication(
                    userAuthenticationProvider.validateCompanyToken(token));
        } else {
            SecurityContextHolder.getContext().setAuthentication(
                    userAuthenticationProvider.validateCompanyTokenStrongly(token));
        }
    }
}
