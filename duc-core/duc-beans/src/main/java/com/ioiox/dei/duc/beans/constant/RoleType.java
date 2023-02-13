package com.ioiox.dei.duc.beans.constant;


import com.ioiox.dei.core.beans.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType implements BaseDeiEnum {
    PRE_DEFINED("pre-defined", "预定义", "预定义角色, 不可以修改"),
    CUSTOMIZED("customized", "自定义", "自定义角色, 用户自己创建"),
    ;
    private final String code;
    private final String name;
    private final String desc;
}
