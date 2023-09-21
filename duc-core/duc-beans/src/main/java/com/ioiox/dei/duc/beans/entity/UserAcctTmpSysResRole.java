package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.constant.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class UserAcctTmpSysResRole
        extends BaseSysResRole {
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
        UserAcctTmpSysResRole that = (UserAcctTmpSysResRole) o;
        if (Objects.nonNull(getSid()) && Objects.nonNull(that.getSid())) {
            return Objects.equals(getSid(), that.getSid());
        } else {
            return StringUtils.equals(getCode(), that.getCode())
                    && Objects.equals(getSysPrjSid(), that.getSysPrjSid())
                    && Objects.equals(corpSid, that.corpSid);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getSysPrjSid(), corpSid);
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
