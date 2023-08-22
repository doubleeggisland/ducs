package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.BaseDeiEnum;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.constant.DeiStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Tenant extends BaseDeiEntity {
    /**
     * 租户编号
     */
    private String code;
    /**
     * 租户名称
     */
    private String name;
    /**
     * 备注
     */
    private String memo;
    /**
     * 租户状态
     * @see com.ioiox.dei.core.constant.DeiStatus
     */
    private String status;
    /**
     * 租户层级
     */
    private Integer lvl;
    /**
     * 父租户主键ID
     */
    private Long pid;
    /**
     * 归属的顶级租户ID
     */
    private Long rid;

    public void setDefaultValueIfNeed() {
        if (StringUtils.isBlank(status)) {
            status = DeiStatus.ENABLE.getCode();
        }
        if (Objects.isNull(lvl)) {
            lvl = DeiGlobalConstant.ONE;
        }
        if (Objects.isNull(pid)) {
            pid = DeiGlobalConstant.DEFAULT_SID;
        }
        if (Objects.isNull(rid)) {
            rid = DeiGlobalConstant.DEFAULT_SID;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tenant tenant = (Tenant) o;
        return Objects.equals(code, tenant.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        CODE("code", "租户编号"),
        NAME("name", "租户名称"),
        MEMO("memo", "备注"),
        STATUS("status", "租户状态"),
        LEVEL("lvl", "租户层级"),
        PARENT_SID("pid", "父租户主键ID"),
        ROOT_SID("rid", "归属的顶级租户ID"),
        ;
        private final String code;
        private final String desc;
    }
}
