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
