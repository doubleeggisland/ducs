package com.ioiox.dei.duc.springboot.jwt;


import com.ioiox.dei.duc.spring.core.model.DUCUserInfo;
import com.ioiox.dei.duc.spring.core.model.DUCUserPortfolio;
import com.ioiox.dei.duc.spring.core.model.DUCUserType;

public class DefaultDUCJwtAuthSvc implements JwtAuthSvc {

    @Override
    public DUCUserPortfolio getUserPortfolio(final String username, final DUCUserType userType) {
        return null;
    }

    @Override
    public DUCUserInfo fastLogin(final String mobile, final String dynamicCode, final DUCUserType userType) {
        return null;
    }

    @Override
    public DUCUserInfo getUserInfo(final Long userId, final DUCUserType userType) {
        return null;
    }

    @Override
    public DUCUserInfo getUserInfo(final String username, final DUCUserType userType) {
        return null;
    }

    @Override
    public void updatePwd(final Long userId, final String newPwd, final DUCUserType userType) {

    }
}
