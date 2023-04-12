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

    public static List<SysResRoleR2Res> instances(final List<Long> sysResSids, final Long sysResRoleSid, final String operator, final Date operateTime) {
        final Date createdTime = Objects.isNull(operateTime) ? new Date(System.currentTimeMillis()) : operateTime;
        final List<SysResRoleR2Res> entities = new ArrayList<>(sysResSids.size());
        for (final Long sysResSid : sysResSids) {
            final SysResRoleR2Res entity = new SysResRoleR2Res();
            entity.setSysResRoleSid(sysResRoleSid);
            entity.setSysResSid(sysResSid);
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
        SYS_RES_ROLE_SID("sysResRoleSid", "系统资源角色ID"),
        SYS_RES_SID("sysResSid", "系统资源ID"),
        ;

        private final String code;
        private final String desc;
    }
}
