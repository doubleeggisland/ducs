package com.ioiox.dei.duc.springboot.jwt;

import com.ioiox.dei.duc.spring.core.model.DUCUserType;
import com.ioiox.dei.duc.springboot.jwt.config.DUCJwtSettings;
import com.ioiox.dei.duc.springboot.jwt.model.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;


public class JwtTokenFactory {
    public static final String CLAIMS_KEY_USER_TYPE = "user-type";
    public static final String CLAIMS_KEY_TOKEN_TYPE = "token-type";
    private final DUCJwtSettings jwtSettings;

    public JwtTokenFactory(final DUCJwtSettings jwtSettings) {
        this.jwtSettings = jwtSettings;
    }

    /**
     * 创建访问令牌
     */
    public JwtToken accessToken(final String username, final DUCUserType userType) {
        final TokenLifetimeUnit timeUnit = TokenLifetimeUnit.getByCode(jwtSettings.getAccessTokenLifetimeUnit());
        return createToken(username, userType, jwtSettings.getAccessTokenLifetime(), timeUnit, TokenType.ACCESS_TOKEN);
    }

    /**
     * 创建用于刷新访问令牌的令牌
     */
    public JwtToken refreshToken(final String username, final DUCUserType userType) {
        final TokenLifetimeUnit timeUnit = TokenLifetimeUnit.getByCode(jwtSettings.getRefreshTokenLifetimeUnit());
        return createToken(username, userType, jwtSettings.getRefreshTokenLifetime(), timeUnit, TokenType.REFRESH_TOKEN);
    }

    /**
     * @param username 用户名
     * @param userType 用户类型
     * @param lifetime token有效时长
     * @param timeUnit 时间单位
     * @param tokenType token类型
     * @return JwtToken 访问令牌
     *
     * @see com.ioiox.dei.duc.spring.core.model.DUCUserType
     * @see com.ioiox.dei.duc.springboot.jwt.model.TokenLifetimeUnit
     * @see com.ioiox.dei.duc.springboot.jwt.model.TokenType
     */
    private JwtToken createToken(final String username,
                                 final DUCUserType userType,
                                 final long lifetime,
                                 final TokenLifetimeUnit timeUnit,
                                 final TokenType tokenType) {
        final Claims claims =
                Jwts.claims().setSubject(username);
        claims.put(CLAIMS_KEY_USER_TYPE, userType.getCode());
        final String signingKey;
        if (TokenType.REFRESH_TOKEN == tokenType) {
            signingKey = jwtSettings.getRefreshTokenSigningKey();
            claims.put(CLAIMS_KEY_TOKEN_TYPE, TokenType.REFRESH_TOKEN.getCode());
        } else {
            signingKey = jwtSettings.getAccessTokenSigningKey();
            claims.put(CLAIMS_KEY_TOKEN_TYPE, TokenType.ACCESS_TOKEN.getCode());
        }

        final LocalDateTime currentTime = LocalDateTime.now();
        final LocalDateTime expireTime = currentTime.plus(lifetime, convertTimeUnit(timeUnit));

        final JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(claims)
                .setIssuer(jwtSettings.getTokenIssuer())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, signingKey);

        if (TokenType.REFRESH_TOKEN == tokenType) {
            jwtBuilder.setId(UUID.randomUUID().toString());
        }

        if (TokenType.REFRESH_TOKEN == tokenType) {
            return new DUCJwtRefreshToken(new DUCJwtToken(jwtBuilder.compact()));
        } else {
            return new DUCJwtAccessToken(new DUCJwtToken(jwtBuilder.compact()));
        }
    }

    private ChronoUnit convertTimeUnit(final TokenLifetimeUnit timeUnit) {
        if (TokenLifetimeUnit.SECOND == timeUnit) {
            return ChronoUnit.SECONDS;
        } else if (TokenLifetimeUnit.MINUTE == timeUnit) {
            return ChronoUnit.MINUTES;
        } else if (TokenLifetimeUnit.HOUR == timeUnit) {
            return ChronoUnit.HOURS;
        } else if (TokenLifetimeUnit.DAY == timeUnit) {
            return ChronoUnit.DAYS;
        }
        return ChronoUnit.SECONDS;
    }
}
