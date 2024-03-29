package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.BaseDeiEnum;
import com.ioiox.dei.duc.spring.core.model.SysPrjAccessCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 用户系统项目权限表实体类, 用来配置用户能够访问的系统
 */
@Getter
@Setter
@NoArgsConstructor
public class UserSysPrjPrivilege extends BaseDeiEntity {
    private Long userSid;
    private Long sysPrjSid;
    private String accessCondition;

    public void setDefaultValueIfNeed() {
        if (StringUtils.isBlank(accessCondition)) {
            accessCondition = SysPrjAccessCondition.PERMANENT.getCode();
        }
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        USER_SID("userSid", "用户ID"),
        SYS_PRJ_SID("sysPrjSid", "系统项目ID"),
        ACCESS_CONDITION("accessCondition", "访问条件"),
        ;
        private final String code;
        private final String desc;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class UniqueKey {
        private Long userSid;
        private Long sysPrjSid;

        public UniqueKey(final Long userSid, final Long sysPrjSid) {
            this.userSid = userSid;
            this.sysPrjSid = sysPrjSid;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UniqueKey uniqueKey = (UniqueKey) o;
            return Objects.equals(userSid, uniqueKey.userSid)
                    && Objects.equals(sysPrjSid, uniqueKey.sysPrjSid);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userSid, sysPrjSid);
        }

        @Override
        public String toString() {
            return String.format("%s-%s",
                    userSid, sysPrjSid);
        }
    }
}
