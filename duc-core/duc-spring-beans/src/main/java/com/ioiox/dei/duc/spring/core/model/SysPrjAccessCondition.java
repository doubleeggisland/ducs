package com.ioiox.dei.duc.spring.core.model;

import com.ioiox.dei.core.constant.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SysPrjAccessCondition implements BaseDeiEnum {
    PERMANENT("permanent", "永久", "永久访问"),
    REJECT("reject", "拒绝", "拒绝访问"),
    TEMPORARY("temporary", "临时", "在指定的日期和时间内访问"),
    ;
    private final String code;
    private final String name;
    private final String desc;
}
