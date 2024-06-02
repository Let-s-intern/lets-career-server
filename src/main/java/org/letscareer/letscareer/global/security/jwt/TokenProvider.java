package org.letscareer.letscareer.global.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.letscareer.letscareer.domain.user.dto.request.TokenReissueRequestDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.letscareer.letscareer.global.common.utils.RedisUtils;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;
import org.letscareer.letscareer.global.security.user.PrincipalDetails;
import org.letscareer.letscareer.global.security.user.PrincipalDetailsService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import static org.letscareer.letscareer.global.error.GlobalErrorCode.INVALID_TOKEN;
import static org.letscareer.letscareer.global.error.GlobalErrorCode.NOT_REFRESH_TOKEN;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider implements InitializingBean {
    private static final String AUTHORITIES_KEY = "auth";
    private static final String ACCESS_KEY = "access";
    private static final String REFRESH_KEY = "refresh";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private final PrincipalDetailsService principalDetailsService;
    private final UserHelper userHelper;
    private final RedisUtils redisUtils;

    @Value("${spring.jwt.secret}")
    private String secret;

    @Value("${spring.jwt.access-token-validity-in-seconds}")
    private int accessTokenExpirationTime;

    @Value("${spring.jwt.refresh-token-validity-in-seconds}")
    private int refreshTokenExpirationTime;

    private Key key;

    @Override
    public void afterPropertiesSet() {
        byte keyBytes[] = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(Long id, Authentication authentication) {
        return createToken(id, ACCESS_KEY, accessTokenExpirationTime, authentication);
    }

    public String createOAuth2AccessToken(Authentication authentication) {
        return createToken(((PrincipalDetails) authentication.getPrincipal()).getId(), ACCESS_KEY, accessTokenExpirationTime, authentication);
    }

    public String createRefreshToken(Long id, Authentication authentication) {
        return createToken(id, REFRESH_KEY, refreshTokenExpirationTime, authentication);
    }

    public String createOAuth2RefreshToken(Authentication authentication) {
        return createToken(((PrincipalDetails) authentication.getPrincipal()).getId(), ACCESS_KEY, refreshTokenExpirationTime, authentication);
    }

    private String createToken(Long id, String type, int expirationTime, Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, expirationTime);

        final Date issuedAt = new Date();
        final Date validity = new Date(calendar.getTimeInMillis());

        String token = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(id.toString())
                .claim(AUTHORITIES_KEY, authorities)
                .claim("type", type)
                .setIssuedAt(issuedAt)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        if(type.equals(REFRESH_KEY)) {
            redisUtils.setRefreshToken(id, token, expirationTime);
        }

        return token;
    }

    public String reissueAccessToken(TokenReissueRequestDto tokenReissueRequestDto) {
        String refreshToken = tokenReissueRequestDto.refreshToken();
        validateRefreshToken(refreshToken);
        User user = userHelper.findUserByIdOrThrow(Long.parseLong(getTokenUserId(refreshToken)));
        Authentication authentication = userHelper.userAuthorizationInput(user);
        return createAccessToken(user.getId(), authentication);
    }

    public void deleteRefreshToken(Long id) {
        redisUtils.delete(id.toString());
    }

    public String getTokenUserId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Authentication getAuthentication(String token) {
        PrincipalDetails principalDetails = (PrincipalDetails) principalDetailsService.loadUserByUserId(Long.parseLong(getTokenUserId(token)));
        return new UsernamePasswordAuthenticationToken(principalDetails, token, principalDetails.getAuthorities());
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다");
        }
        return false;
    }

    public String resolveAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if(StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public void validateRefreshToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            String typeValue = claims.get("type", String.class);
            if(!typeValue.equals(REFRESH_KEY)) {
                throw new InvalidValueException(NOT_REFRESH_TOKEN);
            }
        }
        catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다");
        }
    }
}
