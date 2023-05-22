package com.ioiox.dei.duc.springboot.jwt.model;

import com.ioiox.dei.core.beans.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum TokenLifetimeUnit implements BaseDeiEnum {
    SECOND("sec", "秒"),
    MINUTE("min", "分"),
    HOUR("hour", "小时"),
    DAY("day", "天"),
    ;
    private final String code;
    private final String desc;

    public static TokenLifetimeUnit getByCode(final String code) {
        for (final TokenLifetimeUnit timeUnit : values()) {
            if (StringUtils.equals(timeUnit.code, code)) {
                return timeUnit;
            }
        }
        return null;
    }
}
