package com.ioiox.dei.duc.springboot.jwt;

import com.ioiox.dei.core.beans.DeiStatus;
import com.ioiox.dei.core.orm.mybatis.EnumUtil;
import com.ioiox.dei.duc.spring.core.model.DUCUserInfo;
import com.ioiox.dei.duc.spring.core.model.DUCUserType;
import com.ioiox.dei.duc.springboot.helper.DUCJwtAuthCtxHelper;
import com.ioiox.dei.duc.springboot.jwt.config.DUCJwtSettings;
import com.ioiox.dei.duc.springboot.jwt.exception.JwtTokenInvalidatedException;
import com.ioiox.dei.duc.springboot.jwt.model.DUCJwtRefreshToken;
import com.ioiox.dei.duc.springboot.jwt.model.DUCJwtToken;
import com.ioiox.dei.duc.springboot.jwt.model.JwtToken;
import com.ioiox.dei.duc.springboot.rest.vo.ChgPwdReq;
import com.ioiox.dei.duc.springboot.rest.vo.LoginReq;
import com.ioiox.dei.duc.springboot.rest.vo.RefreshAccessTokenReq;
import com.ioiox.dei.duc.springboot.rest.vo.ResetPwdReq;
import com.ioiox.dei.duc.springboot.support.PwdEncoder;
import com.ioiox.dei.duc.springboot.support.PwdGenerator;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DefaultDUCJwtCoreSvc implements JwtCoreSvc {

    private final JwtAuthSvc jwtAuthSvc;
    private final JwtTokenFactory jwtTokenFactory;
    private final JwtTokenVerifier jwtTokenVerifier;
    private final DUCJwtSettings jwtSettings;
    private final PwdEncoder pwdEncoder;

    public DefaultDUCJwtCoreSvc(final JwtAuthSvc jwtAuthSvc,
                                final JwtTokenFactory jwtTokenFactory,
                                final JwtTokenVerifier jwtTokenVerifier,
                                final DUCJwtSettings jwtSettings,
                                final PwdEncoder pwdEncoder) {
        this.jwtAuthSvc = jwtAuthSvc;
        this.jwtTokenFactory = jwtTokenFactory;
        this.jwtTokenVerifier = jwtTokenVerifier;
        this.jwtSettings = jwtSettings;
        this.pwdEncoder = pwdEncoder;
    }

    /**
     * 校验用户信息
     * @param userInfo 用户基本信息
     */
    protected void checkUserInfo(final DUCUserInfo userInfo) {
        if (Objects.isNull(userInfo)) {
            throw new UsernameNotFoundException(AuthErr.USER_NOT_FOUND.getDesc());
        }
        final DeiStatus status = EnumUtil.codeOf(DeiStatus.class, userInfo.getStatus());
        if (DeiStatus.ENABLE != status) {
            throw new DisabledException(AuthErr.USER_DISABLED.getDesc());
        }
    }

    /**
     * 比对密码
     * @param pwd 用户输入的密码
     * @param userPwd 用户设置的密码
     */
    protected void comparePwd(final String pwd, final String userPwd) {
        if (pwdEncoder.pwdMatches(pwd, userPwd)) {
            throw new BadCredentialsException(AuthErr.BAD_CREDENTIAL.getDesc());
        }
    }

    @Override
    public Map<String, String> login(final LoginReq loginReq, final String userType) {
        final DUCUserType validUserType = DUCUserType.getByCode(userType);
        final DUCUserInfo userInfo = jwtAuthSvc.getUserInfo(loginReq.getUsername(), validUserType);
        checkUserInfo(userInfo);
        comparePwd(loginReq.getPwd(), userInfo.getPwd());

        final JwtToken accessToken = jwtTokenFactory.accessToken(userInfo.getUsername(), validUserType);
        final JwtToken refreshToken = jwtTokenFactory.refreshToken(userInfo.getUsername(), validUserType);
        final Map<String, String> result = new HashMap<>(5);
        result.put("token", accessToken.getRawToken());
        result.put("refreshToken", refreshToken.getRawToken());
        result.put("tokenPrefix",
                StringUtils.isBlank(jwtSettings.getTokenPrefix()) ? JwtTokenExtractor.DEFAULT_TOKEN_PREFIX : jwtSettings.getTokenPrefix());
        result.put("tokenSeparator",
                StringUtils.isBlank(jwtSettings.getTokenSeparator()) ? JwtTokenExtractor.DEFAULT_TOKEN_SEPARATOR : jwtSettings.getTokenSeparator());
        return result;
    }

    @Override
    public void chgPwd(final ChgPwdReq chgPwdReq) {
        final String username = DUCJwtAuthCtxHelper.getUsername();
        final String userType = DUCJwtAuthCtxHelper.getUserType();
        final DUCUserType validUserType = DUCUserType.getByCode(userType);
        final DUCUserInfo userInfo = jwtAuthSvc.getUserInfo(username, validUserType);
        checkUserInfo(userInfo);
        comparePwd(chgPwdReq.getOriginPwd(), userInfo.getPwd());
        jwtAuthSvc.updatePwd(userInfo.getUserId(), chgPwdReq.getNewPwd(), validUserType);
    }

    @Override
    public void resetPwd(final ResetPwdReq resetPwdReq, final String userType) {
        final DUCUserType validUserType = DUCUserType.getByCode(userType);
        final DUCUserInfo userInfo = jwtAuthSvc.getUserInfo(resetPwdReq.getUserId(), validUserType);
        checkUserInfo(userInfo);
        final String randomPwd = PwdGenerator.randomPassword(8);
        jwtAuthSvc.updatePwd(resetPwdReq.getUserId(), randomPwd, validUserType);
    }

    @Override
    public Map<String, String> renewAccessToken(final RefreshAccessTokenReq refreshAccessTokenReq) {
        final DUCJwtRefreshToken refreshToken = new DUCJwtRefreshToken(new DUCJwtToken(refreshAccessTokenReq.getRefreshToken()));

        final Claims claims = refreshToken.parse(jwtSettings.getRefreshTokenSigningKey());
        final String jti = claims.getId();
        if (jwtTokenVerifier.invalid(jti)) {
            throw new JwtTokenInvalidatedException(refreshToken, AuthErr.JWT_TOKEN_INVALID.getDesc());
        }

        final String username = claims.getSubject();
        final String userType = claims.get(JwtTokenFactory.CLAIMS_KEY_USER_TYPE, String.class);
        final DUCUserType validUserType = DUCUserType.getByCode(userType);
        final DUCUserInfo userInfo = jwtAuthSvc.getUserInfo(username, validUserType);
        checkUserInfo(userInfo);

        final JwtToken accessToken = jwtTokenFactory.accessToken(userInfo.getUsername(), validUserType);
        final Map<String, String> result = new HashMap<>(3);
        result.put("accessToken", accessToken.getRawToken());
        result.put("tokenPrefix",
                StringUtils.isBlank(jwtSettings.getTokenPrefix()) ? JwtTokenExtractor.DEFAULT_TOKEN_PREFIX : jwtSettings.getTokenPrefix());
        result.put("tokenSeparator",
                StringUtils.isBlank(jwtSettings.getTokenSeparator()) ? JwtTokenExtractor.DEFAULT_TOKEN_SEPARATOR : jwtSettings.getTokenSeparator());
        return result;
    }
}
