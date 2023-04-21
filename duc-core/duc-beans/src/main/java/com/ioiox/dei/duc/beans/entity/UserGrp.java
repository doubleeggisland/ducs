package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.beans.BaseDeiEnum;
import com.ioiox.dei.core.beans.DeiStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户组, 用于管理用户的权限
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class UserGrp extends BaseDeiEntity {
    private String code;
    private String name;
    private String memo;
    private String status;

    public void setDefaultValueIfNeed() {
        if (StringUtils.isBlank(status)) {
            status = DeiStatus.ENABLE.getCode();
        }
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        CODE("code", "用户组编号"),
        NAME("name", "用户组名称"),
        MEMO("memo", "用户组备注"),
        STATUS("status", "用户组状态"),
        ;
        private final String code;
        private final String desc;
    }
}
