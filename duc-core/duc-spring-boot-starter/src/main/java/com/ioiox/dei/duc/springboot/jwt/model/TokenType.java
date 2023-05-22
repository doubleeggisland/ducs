package com.ioiox.dei.duc.springboot.jwt.model;

import com.ioiox.dei.core.beans.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenType implements BaseDeiEnum {
    ACCESS_TOKEN("access", "访问令牌", "用户访问商旅管理后台"),
    REFRESH_TOKEN("refresh", "刷新令牌", "用于刷新访问令牌的有效时长"),
    ;
    private final String code;
    private final String name;
    private final String desc;
}
