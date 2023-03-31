package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEnum;
import com.ioiox.dei.core.beans.BaseDeiRelationshipEntity;

import com.ioiox.dei.core.beans.RelationshipItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class RoleR2SysApi extends BaseDeiRelationshipEntity<Long, Long> {
    private Long roleSid;
    private Long sysApiSid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleR2SysApi that = (RoleR2SysApi) o;
        return Objects.equals(roleSid, that.roleSid) && Objects.equals(sysApiSid, that.sysApiSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleSid, sysApiSid);
    }

    @Override
    public RelationshipItem<Long, Long> toRelationshipItem() {
        return new RelationshipItem<>(roleSid, sysApiSid);
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        ROLE_SID("roleSid", "角色ID"),
        SYS_API_SID("sysApiSid", "系统API的ID"),
        ;

        private final String code;
        private final String desc;
    }
}
