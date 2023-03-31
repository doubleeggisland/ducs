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
public class SysResRoleR2Res extends BaseDeiRelationshipEntity<Long, Long> {
    private Long sysResRoleSid;
    private Long sysResSid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysResRoleR2Res that = (SysResRoleR2Res) o;
        return Objects.equals(sysResRoleSid, that.sysResRoleSid) && Objects.equals(sysResSid, that.sysResSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sysResRoleSid, sysResSid);
    }

    @Override
    public RelationshipItem<Long, Long> toRelationshipItem() {
        return new RelationshipItem<>(sysResRoleSid, sysResSid);
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        SYS_RES_ROLE_SID("sysResRoleSid", "系统资源角色ID"),
        SYS_RES_SID("sysResSid", "系统资源ID"),
        ;

        private final String code;
        private final String desc;
    }
}
