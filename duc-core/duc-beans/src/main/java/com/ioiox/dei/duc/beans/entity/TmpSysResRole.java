package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class TmpSysResRole extends TmpRole {
    private Long sysPrjSid;

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        SYS_PRJ_SID("sysPrjSid", "临时系统资源角色所属项目主键ID"),
        ;
        private final String code;
        private final String desc;
    }
}
