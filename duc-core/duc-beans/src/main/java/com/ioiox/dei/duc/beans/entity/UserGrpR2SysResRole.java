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
public class UserGrpR2SysResRole extends BaseDeiRelationshipEntity<Long, Long> {
    private Long userGrpSid;
    private Long sysResRoleSid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGrpR2SysResRole that = (UserGrpR2SysResRole) o;
        return Objects.equals(userGrpSid, that.userGrpSid)
                && Objects.equals(sysResRoleSid, that.sysResRoleSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userGrpSid, sysResRoleSid);
    }

    @Override
    public RelationshipItem<Long, Long> toRelationshipItem() {
        return new RelationshipItem<>(userGrpSid, sysResRoleSid);
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        USER_GROUP_SID("userGrpSid", "用户组ID"),
        SYS_RES_ROLE_SID("roleSid", "系统资源角色ID"),
        ;

        private final String code;
        private final String desc;
    }
}
