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
public class UserR2SysResRole extends BaseDeiRelationshipEntity<Long, Long> {
    private Long userSid;
    private Long sysResRoleSid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserR2SysResRole that = (UserR2SysResRole) o;
        return Objects.equals(userSid, that.userSid)
                && Objects.equals(sysResRoleSid, that.sysResRoleSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userSid, sysResRoleSid);
    }

    @Override
    public RelationshipItem<Long, Long> toRelationshipItem() {
        return new RelationshipItem<>(userSid, sysResRoleSid);
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        USER_SID("userSid", "用户ID"),
        SYS_RES_ROLE_SID("sysResRoleSid", "系统资源角色ID"),
        ;

        private final String code;
        private final String desc;
    }
}
