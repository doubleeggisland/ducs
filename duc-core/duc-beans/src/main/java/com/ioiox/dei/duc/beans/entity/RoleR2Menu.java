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
public class RoleR2Menu extends BaseDeiEntity {
    private Long roleSid;
    private Long menuSid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleR2Menu that = (RoleR2Menu) o;
        return Objects.equals(roleSid, that.roleSid) && Objects.equals(menuSid, that.menuSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleSid, menuSid);
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        ROLE_SID("roleSid", "角色ID"),
        MENU_SID("menuSid", "菜单ID"),
        ;

        private final String code;
        private final String desc;
    }
}
