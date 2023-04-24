package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.beans.BaseDeiEnum;
import com.ioiox.dei.core.beans.DeiStatus;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.duc.beans.constant.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public abstract class Role extends BaseDeiEntity {
    /**
     * 角色编号
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色类型
     * @see com.ioiox.dei.duc.beans.constant.RoleType
     */
    private String type;
    /**
     * 状态
     * @see com.ioiox.dei.core.beans.DeiStatus
     */
    private String status;
    /**
     * 备注
     */
    private String memo;
    /**
     * 所属系统ID
     */
    private Long sysPrjSid;

    public void setDefaultValueIfNeed() {
        if (StringUtils.isBlank(type)) {
            type = RoleType.CUSTOMIZED.getCode();
        }
        if (StringUtils.isBlank(status)) {
            status = DeiStatus.ENABLE.getCode();
        }
        if (Objects.isNull(sysPrjSid)) {
            sysPrjSid = DeiGlobalConstant.DEFAULT_SID;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        CODE("code", "角色编号"),
        NAME("name", "角色名称"),
        TYPE("type", "角色类型"),
        STATUS("status", "状态"),
        MEMO("memo", "备注"),
        SYS_PRJ_SID("sysPrjSid", "所属系统ID"),
        ;
        private final String code;
        private final String desc;
    }
}
