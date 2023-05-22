package com.ioiox.dei.duc.springboot.jwt;


import com.ioiox.dei.duc.spring.core.model.DUCUserInfo;
import com.ioiox.dei.duc.spring.core.model.DUCUserPortfolio;
import com.ioiox.dei.duc.spring.core.model.DUCUserType;

public interface JwtAuthSvc {
    DUCUserPortfolio getUserPortfolio(final String username, final DUCUserType userType);
    DUCUserInfo fastLogin(final String mobile, final String dynamicCode, final DUCUserType userType);
    DUCUserInfo getUserInfo(final Long userId, final DUCUserType userType);
    DUCUserInfo getUserInfo(final String username, final DUCUserType userType);
    void updatePwd(final Long userId, final String newPwd, final DUCUserType userType);
}
