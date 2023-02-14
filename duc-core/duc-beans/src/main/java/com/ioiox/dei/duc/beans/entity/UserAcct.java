package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class UserAcct extends Acct {
    private Long tenantSid;
    private Long corpSid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAcct that = (UserAcct) o;
        if (Objects.nonNull(getSid())
                && Objects.nonNull(that.getSid())) {
            return Objects.equals(getSid(), that.getSid());
        } else if (StringUtils.isNotBlank(getUsername())
                && StringUtils.isNotBlank(that.getUsername())) {
            return StringUtils.equals(getUsername(), that.getUsername());
        } else {
            if (Objects.equals(corpSid, that.corpSid)) {
                if (StringUtils.isNotBlank(getMobile())
                        && StringUtils.isNotBlank(that.getMobile())) {
                    return StringUtils.equals(getUsername(), that.getUsername());
                } else {
                    return StringUtils.equals(getEmail(), that.getEmail());
                }
            } else {
                return false;
            }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getMobile(), getEmail(), tenantSid);
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
