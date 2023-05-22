package com.ioiox.dei.duc.springboot.jwt.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DUCJwtSettings {
    /**
     * 访问令牌的前缀
     */
    private String tokenPrefix;
    /**
     * 访问令牌前缀与令牌间的分隔符
     */
    private String tokenSeparator;
    /**
     * 令牌签发者
     */
    private String tokenIssuer;
    /**
     * 访问令牌签名密钥
     */
    private String accessTokenSigningKey;
    /**
     * 刷新令牌签名密钥
     */
    private String refreshTokenSigningKey;

    private Long accessTokenLifetime;
    private String accessTokenLifetimeUnit;

    private Long refreshTokenLifetime;
    private String refreshTokenLifetimeUnit;
}
