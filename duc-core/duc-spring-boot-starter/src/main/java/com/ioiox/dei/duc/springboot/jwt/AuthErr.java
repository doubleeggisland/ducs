package com.ioiox.dei.duc.springboot.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErr {
    AUTHENTICATION(10, "认证失败"),
    BAD_CREDENTIAL(99, "用户名或密码错误"),
    JWT_TOKEN_EXPIRED(11, "TOKEN已过期"),
    JWT_TOKEN_BAD(44, "无效的TOKEN"),
    JWT_TOKEN_INVALID(148, "TOKEN已失效"),
    JWT_TOKEN_NOT_EXIST(14, "TOKEN不存在"),
    USER_NOT_FOUND(12, "用户不存在"),
    USER_DISABLED(20, "用户已禁用"),
    USER_TYPE_NOT_SUPPORTED(36, "不支持的用户类型");

    private final int code;
    private final String desc;
}
