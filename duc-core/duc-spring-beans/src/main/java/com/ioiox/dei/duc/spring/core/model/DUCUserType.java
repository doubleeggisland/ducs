package com.ioiox.dei.duc.spring.core.model;

import com.ioiox.dei.core.beans.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum DUCUserType
        implements BaseDeiEnum {
    EMPLOYEE("employee", "雇员"),
    TENANT_USER("tenant-user", "租户用户"),
    CORP_MEMBER("corp-member", "企业用户"),
    USER("user", "个人用户"),
    ;
    private final String code;
    private final String desc;

    public static DUCUserType getByCode(final String code) {
        for (final DUCUserType userType : values()) {
            if (StringUtils.equals(userType.code, code)) {
                return userType;
            }
        }
        return null;
    }
}
