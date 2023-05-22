package com.ioiox.dei.duc.spring.core.model;

import com.ioiox.dei.core.beans.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum DUCSysResType implements BaseDeiEnum {
    UNDER_CONTROL("under-ctrl", "管控资源"),
    SHARED("shared", "共享资源"),
    ;
    private final String code;
    private final String desc;

    public static DUCSysResType getByCode(final String code) {
        for (final DUCSysResType type : values()) {
            if (StringUtils.equals(type.code, code)) {
                return type;
            }
        }
        return null;
    }
}
