package com.ioiox.dei.duc.spring.core.model;

import com.ioiox.dei.core.constant.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public enum DUCSysApiInteractForm implements BaseDeiEnum {
    BUTTON("btn", "按钮", "按钮"),
    LINK("link", "链接", "链接"),
    OTHER("other", "其他", "其他"),
    ;
    private final String code;
    private final String name;
    private final String desc;

    public static DUCSysApiInteractForm getByCode(final String code) {
        for (final DUCSysApiInteractForm interactForm : values()) {
            if (StringUtils.equals(interactForm.code, code)) {
                return interactForm;
            }
        }
        return null;
    }
}
