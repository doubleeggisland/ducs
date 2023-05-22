package com.ioiox.dei.duc.springboot.jwt;

import com.ioiox.dei.duc.springboot.rest.vo.ChgPwdReq;
import com.ioiox.dei.duc.springboot.rest.vo.LoginReq;
import com.ioiox.dei.duc.springboot.rest.vo.RefreshAccessTokenReq;
import com.ioiox.dei.duc.springboot.rest.vo.ResetPwdReq;

import java.util.Map;

public interface JwtCoreSvc {
    Map<String, String> login(final LoginReq loginReq, final String userType);
    void chgPwd(final ChgPwdReq chgPwdReq);
    void resetPwd(final ResetPwdReq resetPwdReq, final String userType);
    Map<String, String> renewAccessToken(final RefreshAccessTokenReq refreshAccessTokenReq);
}
