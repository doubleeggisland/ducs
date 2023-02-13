package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.beans.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Getter
    @AllArgsConstructor
    public enum AccessCondition implements BaseDeiEnum {
        PERMANENT("permanent", "永久", "永久访问"),
        REJECT("reject", "拒绝", "拒绝访问"),
        TEMPORARY("temporary", "临时", "在指定的日期和时间内访问"),
        ;
        private final String code;
        private final String name;
        private final String desc;
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
}
