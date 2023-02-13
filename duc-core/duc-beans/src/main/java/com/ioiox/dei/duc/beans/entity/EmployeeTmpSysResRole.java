package com.ioiox.dei.duc.beans.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeTmpSysResRole
        extends TmpSysResRole {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeTmpSysResRole that = (EmployeeTmpSysResRole) o;
        return Objects.equals(getSysPrjSid(), that.getSysPrjSid())
                && StringUtils.equals(getCode(), that.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSysPrjSid(), getCode());
    }
}
