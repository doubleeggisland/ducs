package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.beans.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class MenuSysApiMapping extends BaseDeiEntity {
    private Long menuSid;
    private Long sysApiSid;
    private String interactForm;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuSysApiMapping that = (MenuSysApiMapping) o;
        return Objects.equals(menuSid, that.menuSid) && Objects.equals(sysApiSid, that.sysApiSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuSid, sysApiSid);
    }

    @Getter
    @AllArgsConstructor
    public enum RoleType implements BaseDeiEnum {
        BUTTON("btn", "按钮", "按钮"),
        LINK("link", "链接", "链接"),
        OTHER("other", "其他", "其他"),
        ;
        private final String code;
        private final String name;
        private final String desc;
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        MENU_SID("menuSid", "菜单主键ID"),
        SYS_API_SID("sysApiSid", "系统API的主键ID"),
        INTERACT_FORM("interactForm", "交互形式"),
        ;

        private final String code;
        private final String desc;
    }
}
