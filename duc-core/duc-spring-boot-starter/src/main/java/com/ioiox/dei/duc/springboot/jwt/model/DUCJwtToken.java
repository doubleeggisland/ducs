package com.ioiox.dei.duc.springboot.jwt.model;

import com.ioiox.dei.duc.springboot.jwt.AuthErr;
import com.ioiox.dei.duc.springboot.jwt.exception.BadJwtTokenException;
import com.ioiox.dei.duc.springboot.jwt.exception.JwtTokenExpiredException;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;

public class DUCJwtToken implements JwtToken {

    private final String rawToken;

    public DUCJwtToken(final String rawToken) {
        this.rawToken = rawToken;
    }

    public Claims parse(final String signingKey) {
        if (StringUtils.isBlank(rawToken)) {
            throw new BadJwtTokenException(this, AuthErr.JWT_TOKEN_BAD.getDesc());
        }
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.rawToken).getBody();
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            throw new BadJwtTokenException(this, AuthErr.JWT_TOKEN_BAD.getDesc(), ex);
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenExpiredException(this, AuthErr.JWT_TOKEN_EXPIRED.getDesc(), ex);
        }
    }

    public String getRawToken() {
        return this.rawToken;
    }
}
