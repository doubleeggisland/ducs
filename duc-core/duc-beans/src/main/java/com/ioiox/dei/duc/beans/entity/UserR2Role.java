package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiRelationshipEntity;
import com.ioiox.dei.core.constant.BaseDeiEnum;
import com.ioiox.dei.core.orm.mybatis.model.RelationshipItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public abstract class UserR2Role
        extends BaseDeiRelationshipEntity<Long, Long> {
    private Long userSid;
    private Long roleSid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserR2Role that = (UserR2Role) o;
        return Objects.equals(userSid, that.userSid)
                && Objects.equals(roleSid, that.roleSid);
    }

    @Override
    public RelationshipItem<Long, Long> toRelationshipItem() {
        return new RelationshipItem<>(userSid, roleSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userSid, roleSid);
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        USER_SID("userSid", "用户ID"),
        ROLE_SID("roleSid", "角色ID"),
        ;

        private final String code;
        private final String desc;
    }
}
