package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.beans.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class UserGrpR2Role extends BaseDeiEntity {
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
