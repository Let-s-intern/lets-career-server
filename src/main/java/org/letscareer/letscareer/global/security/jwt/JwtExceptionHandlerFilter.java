package org.letscareer.letscareer.global.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.error.ErrorCode;
import org.letscareer.letscareer.global.error.entity.ErrorResponse;
import org.letscareer.letscareer.global.error.exception.UnauthorizedException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtExceptionHandlerFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (UnauthorizedException e) {
            responseToClient(response, getErrorResponse(e.getErrorCode()));
        }
    }

    private ErrorResponse getErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.of(errorCode);
    }

    private void responseToClient(HttpServletResponse response, ErrorResponse errorResponse) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(errorResponse.getStatus());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
