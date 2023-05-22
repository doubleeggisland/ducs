package com.ioiox.dei.duc.springboot.jwt.model;


import com.ioiox.dei.duc.springboot.jwt.JwtTokenFactory;
import com.ioiox.dei.duc.springboot.jwt.exception.BadJwtTokenException;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;

public class DUCJwtRefreshToken implements JwtToken {

    private final DUCJwtToken jwtToken;

    public DUCJwtRefreshToken(final DUCJwtToken jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Claims parse(final String signingKey) {
        return jwtToken.parse(signingKey);
    }

    public static DUCJwtRefreshToken generate(final DUCJwtAccessToken token,
                                              final String signingKey) {
        final Claims claims = token.parse(signingKey);
        final String tokenType = claims.get(JwtTokenFactory.CLAIMS_KEY_TOKEN_TYPE, String.class);
        if (StringUtils.equals(TokenType.REFRESH_TOKEN.getCode(), tokenType)) {
            return new DUCJwtRefreshToken(new DUCJwtToken(token.getRawToken()));
        }
        throw new BadJwtTokenException(token, "无效的令牌");
    }


    @Override
    public String getRawToken() {
        return jwtToken.getRawToken();
    }
}
