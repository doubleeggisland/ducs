package com.ioiox.dei.duc.spring.core.model;

import com.ioiox.dei.core.beans.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum DUCSysApiType implements BaseDeiEnum {
    AUTH_NEEDED("auth-needed", "需授权的接口"),
    AUTH_IGNORED("auth-ignored", "忽略授权的接口 (需要access token才能访问, 不需要授权的接口)"),
    PUBLIC("public", "公共接口 (不需要access token就可以访问的接口)"),
    ;

    private final String code;
    private final String desc;

    public static DUCSysApiType getByCode(final String code) {
        for (final DUCSysApiType type : values()) {
            if (StringUtils.equals(type.code, code)) {
                return type;
            }
        }
        return null;
    }
}
