package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 用户账号系统资源角色
 */
@Getter
@Setter
@NoArgsConstructor
public class UserAcctSysResRole
        extends SysResRole {

    /**
     * 所属租户ID
     */
    private Long tenantSid;
    /**
     * 所属企业ID
     */
    private Long corpSid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserAcctSysResRole that = (UserAcctSysResRole) o;
        if (Objects.nonNull(getSid()) && Objects.nonNull(that.getSid())) {
            return Objects.equals(getSid(), that.getSid());
        } else {
            return StringUtils.equals(getCode(), that.getCode())
                    && Objects.equals(corpSid, that.corpSid);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), corpSid);
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        TENANT_SID("tenantSid", "所属租户主键ID"),
        CORP_SID("corpSid", "所属企业主键ID"),
        ;

        private final String code;
        private final String desc;
    }
}
