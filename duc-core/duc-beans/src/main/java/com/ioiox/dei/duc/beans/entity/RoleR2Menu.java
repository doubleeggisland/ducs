package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.beans.BaseDeiRelationshipEntity;
import com.ioiox.dei.core.constant.BaseDeiEnum;
import com.ioiox.dei.core.orm.mybatis.model.RelationshipItem;
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
public class RoleR2Menu extends BaseDeiRelationshipEntity<Long, Long> {
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

    @Override
    public RelationshipItem<Long, Long> toRelationshipItem() {
        return new RelationshipItem<>(roleSid, menuSid);
    }

    public static List<RoleR2Menu> instances(final List<Long> menuSids, final Long roleSid, final String operator, final Date operateTime) {
        final Date createdTime = Objects.isNull(operateTime) ? new Date(System.currentTimeMillis()) : operateTime;
        final List<RoleR2Menu> entities = new ArrayList<>(menuSids.size());
        for (final Long menuSid : menuSids) {
            final RoleR2Menu entity = new RoleR2Menu();
            entity.setRoleSid(roleSid);
            entity.setMenuSid(menuSid);
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
        MENU_SID("menuSid", "菜单ID"),
        ;

        private final String code;
        private final String desc;
    }
}
