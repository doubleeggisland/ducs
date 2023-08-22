package com.ioiox.dei.duc.spring.core.model;

import com.ioiox.dei.core.constant.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum DUCRoleType implements BaseDeiEnum {
    PRE_DEFINED("pre-defined", "预定义", "预定义角色, 不可以修改"),
    CUSTOMIZED("customized", "自定义", "自定义角色, 用户自己创建"),
    ;
    private final String code;
    private final String name;
    private final String desc;

    public DUCRoleType getByCode(final String code) {
        for (final DUCRoleType roleType : values()) {
            if (StringUtils.equals(roleType.code, code)) {
                return roleType;
            }
        }
        return null;
    }
}
