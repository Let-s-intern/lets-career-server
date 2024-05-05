package org.letscareer.letscareer.global.config;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.common.filter.AccessDeniedFilter;
import org.letscareer.letscareer.global.security.jwt.JwtAccessDeniedHandler;
import org.letscareer.letscareer.global.security.jwt.JwtAuthenticationEntryPoint;
import org.letscareer.letscareer.global.security.jwt.JwtAuthenticationFilter;
import org.letscareer.letscareer.global.security.jwt.JwtExceptionHandlerFilter;
import org.letscareer.letscareer.global.security.oauth2.CookieAuthorizationRequestRepository;
import org.letscareer.letscareer.global.security.oauth2.CustomOAuth2UserService;
import org.letscareer.letscareer.global.security.oauth2.handler.OAuth2AuthenticationFailureHandler;
import org.letscareer.letscareer.global.security.oauth2.handler.OAuth2AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@Configuration
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtExceptionHandlerFilter jwtExceptionHandlerFilter;
    private final AccessDeniedFilter accessDeniedFilter;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    @Value("${server.url}")
    private String SERVER_URL;

    private final String[] SwaggerPatterns = {
            "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html"
    };

    private final String[] AdminGetPatterns = {
    };

    private final String[] AdminPostPatterns = {
    };

    private final String[] AdminPatchPatterns = {
    };

    private final String[] AdminDeletePatterns = {
    };

    private final String[] UserGetPatterns = {
    };

    private final String[] UserPostPatterns = {
    };

    private final String[] UserPatchPatterns = {
    };

    private final String[] UserDeletePatterns = {
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(configurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> {
                    headers.defaultsDisabled().frameOptions(Customizer.withDefaults());
                })
                .sessionManagement(sessionManagement -> {
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authorizeHttpRequests(request -> {
                    request.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                            .requestMatchers(SwaggerPatterns).permitAll()
                            .requestMatchers(HttpMethod.GET, UserGetPatterns).hasAnyRole("USER","ADMIN")
                            .requestMatchers(HttpMethod.POST, UserPostPatterns).hasAnyRole("USER", "ADMIN")
                            .requestMatchers(HttpMethod.PATCH, UserPatchPatterns).hasAnyRole("USER", "ADMIN")
                            .requestMatchers(HttpMethod.DELETE, UserDeletePatterns).hasAnyRole("USER", "ADMIN")
                            .requestMatchers(HttpMethod.GET, AdminGetPatterns).hasAnyRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, AdminPostPatterns).hasAnyRole("ADMIN")
                            .requestMatchers(HttpMethod.PATCH, AdminPatchPatterns).hasAnyRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, AdminDeletePatterns).hasAnyRole("ADMIN")
                            .requestMatchers("/oauth2/**").permitAll()
                            .anyRequest().permitAll();
                })
                .oauth2Login(oauth2 -> {
                    oauth2.authorizationEndpoint(auth -> auth.baseUri("/oauth2/authorize")
                                    .authorizationRequestRepository(cookieAuthorizationRequestRepository))
                            .redirectionEndpoint(redirect -> redirect.baseUri("/oauth2/callback/*"))
                            .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                            .successHandler(oAuth2AuthenticationSuccessHandler)
                            .failureHandler(oAuth2AuthenticationFailureHandler);
                })
                .logout(logout -> {
                    logout.clearAuthentication(true)
                            .deleteCookies("JSESSIONID");
                })
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling.accessDeniedHandler(jwtAccessDeniedHandler)
                            .authenticationEntryPoint(jwtAuthenticationEntryPoint);
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionHandlerFilter, JwtAuthenticationFilter.class)
                .addFilterBefore(accessDeniedFilter, AuthorizationFilter.class);

        return http.build();
    }

    protected CorsConfigurationSource configurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", getDefaultCorsConfiguration());
        return source;
    }

    private CorsConfiguration getDefaultCorsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(
                Arrays.asList(
                        "http://localhost:8080",
                        "http://localhost:3000",
                        "https://lets-intern.vercel.app",
                        "https://lets-intern-client-test.vercel.app",
                        "https://letsintern.co.kr",
                        "https://www.letsintern.co.kr",
                        SERVER_URL
                )
        );
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        return configuration;
    }
}
