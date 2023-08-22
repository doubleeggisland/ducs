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
public class UserGrpR2Role extends BaseDeiRelationshipEntity<Long, Long> {
    private Long userGrpSid;
    private Long roleSid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGrpR2Role that = (UserGrpR2Role) o;
        return Objects.equals(userGrpSid, that.userGrpSid)
                && Objects.equals(roleSid, that.roleSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userGrpSid, roleSid);
    }

    @Override
    public RelationshipItem<Long, Long> toRelationshipItem() {
        return new RelationshipItem<>(userGrpSid, roleSid);
    }

    public static List<UserGrpR2Role> instances(final List<Long> roleSids, final Long userGrpSid, final String operator, final Date operateTime) {
        final Date createdTime = Objects.isNull(operateTime) ? new Date(System.currentTimeMillis()) : operateTime;
        final List<UserGrpR2Role> entities = new ArrayList<>(roleSids.size());
        for (final Long roleSid : roleSids) {
            final UserGrpR2Role entity = new UserGrpR2Role();
            entity.setUserGrpSid(userGrpSid);
            entity.setRoleSid(roleSid);
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
        ROLE_SID("roleSid", "角色ID"),
        ;

        private final String code;
        private final String desc;
    }
}
