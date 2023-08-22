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
public class TenantUserGrp extends UserGrp {
    /**
     * 属所租户ID
     */
    private Long tenantSid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TenantUserGrp that = (TenantUserGrp) o;
        if (Objects.nonNull(getSid()) && Objects.nonNull(that.getSid())) {
            return Objects.equals(getSid(), that.getSid());
        } else {
            return StringUtils.equals(getCode(), that.getCode())
                    && Objects.equals(tenantSid, that.tenantSid);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getTenantSid());
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        TENANT_SID("tenantSid", "所属租户主键ID"),
        ;
        private final String code;
        private final String desc;
    }
}
