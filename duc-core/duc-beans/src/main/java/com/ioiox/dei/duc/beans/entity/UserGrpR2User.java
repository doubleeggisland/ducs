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
public class UserGrpR2User
        extends BaseDeiRelationshipEntity<Long, Long> {
    private Long userSid;
    private Long userGrpSid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGrpR2User that = (UserGrpR2User) o;
        return Objects.equals(userSid, that.userSid) && Objects.equals(userGrpSid, that.userGrpSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userSid, userGrpSid);
    }

    @Override
    public RelationshipItem<Long, Long> toRelationshipItem() {
        return new RelationshipItem<>(userSid, userGrpSid);
    }

    public static List<UserGrpR2User> instances(final List<Long> userGrpSids, final Long userSid, final String operator, final Date operateTime) {
        final Date createdTime = Objects.isNull(operateTime) ? new Date(System.currentTimeMillis()) : operateTime;
        final List<UserGrpR2User> entities = new ArrayList<>(userGrpSids.size());
        for (final Long userGrpSid : userGrpSids) {
            final UserGrpR2User entity = new UserGrpR2User();
            entity.setUserSid(userSid);
            entity.setUserGrpSid(userGrpSid);
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
        USER_SID("userSid", "用户ID"),
        USER_GROUP_SID("userGrpSid", "用户组ID"),
        ;

        private final String code;
        private final String desc;
    }
}
