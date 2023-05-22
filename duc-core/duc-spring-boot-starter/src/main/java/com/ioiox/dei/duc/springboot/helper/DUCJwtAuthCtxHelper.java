package com.ioiox.dei.duc.springboot.helper;

import com.ioiox.dei.duc.spring.core.model.DUCUserInfo;
import com.ioiox.dei.duc.spring.core.model.DUCUserPortfolio;
import org.springframework.security.core.context.SecurityContextHolder;

public class DUCJwtAuthCtxHelper {
    public static DUCUserPortfolio getUserPortfolio() {
        return (DUCUserPortfolio) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static DUCUserInfo getUserInfo() {
        return getUserPortfolio().getUserInfo();
    }

    public static Long getUserId() {
        return getUserInfo().getUserId();
    }

    public static String getUsername() {
        return getUserInfo().getUsername();
    }

    public static String getRealName() {
        return getUserInfo().getRealName();
    }

    public static String getEmail() {
        return getUserInfo().getEmail();
    }

    public static String getUserType() {
        return getUserInfo().getUserType();
    }

    public static Long getCorpId() {
        return getUserInfo().getCorpId();
    }

    public static Long getTenantId() {
        return getUserInfo().getTenantId();
    }
}
