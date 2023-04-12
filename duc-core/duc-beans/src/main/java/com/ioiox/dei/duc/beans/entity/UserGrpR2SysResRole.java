package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.beans.BaseDeiEnum;
import com.ioiox.dei.core.beans.BaseDeiRelationshipEntity;

import com.ioiox.dei.core.beans.RelationshipItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public static List<UserGrpR2SysResRole> instances(final List<Long> sysResRoleSids, final Long userGrpSid, final String operator, final Date operateTime) {
        final Date createdTime = Objects.isNull(operateTime) ? new Date(System.currentTimeMillis()) : operateTime;
        final List<UserGrpR2SysResRole> entities = new ArrayList<>(sysResRoleSids.size());
        for (final Long sysResRoleSid : sysResRoleSids) {
            final UserGrpR2SysResRole entity = new UserGrpR2SysResRole();
            entity.setUserGrpSid(userGrpSid);
            entity.setSysResRoleSid(sysResRoleSid);
            entity.setCreatedBy(operator);
            entity.setCreatedTime(createdTime);
            entity.setVersionNum(BaseDeiEntity.INIT_VERSION_NUM);
            entities.add(entity);
        }
        return entities;
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
