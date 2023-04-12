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
public class RoleR2SysApi extends BaseDeiRelationshipEntity<Long, Long> {
    private Long roleSid;
    private Long mappingSid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleR2SysApi that = (RoleR2SysApi) o;
        return Objects.equals(roleSid, that.roleSid) && Objects.equals(mappingSid, that.mappingSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleSid, mappingSid);
    }

    @Override
    public RelationshipItem<Long, Long> toRelationshipItem() {
        return new RelationshipItem<>(roleSid, mappingSid);
    }

    public static List<RoleR2SysApi> instances(final List<Long> mappingSids, final Long roleSid, final String operator, final Date operateTime) {
        final Date createdTime = Objects.isNull(operateTime) ? new Date(System.currentTimeMillis()) : operateTime;
        final List<RoleR2SysApi> entities = new ArrayList<>(mappingSids.size());
        for (final Long mappingSid : mappingSids) {
            final RoleR2SysApi entity = new RoleR2SysApi();
            entity.setRoleSid(roleSid);
            entity.setMappingSid(mappingSid);
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
        ROLE_SID("roleSid", "角色ID"),
        MAPPING_SID("mappingSid", "菜单与系统接口映射ID"),
        ;

        private final String code;
        private final String desc;
    }
}
