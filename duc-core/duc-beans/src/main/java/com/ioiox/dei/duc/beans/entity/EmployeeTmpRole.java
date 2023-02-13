package com.ioiox.dei.duc.beans.entity;

import java.util.Objects;

public class EmployeeTmpRole extends TmpRole {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeTmpRole that = (EmployeeTmpRole) o;
        if (Objects.nonNull(getSid()) && Objects.nonNull(that.getSid())) {
            return Objects.equals(getSid(), that.getSid());
        } else {
            return Objects.equals(getCode(), that.getCode());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }
}
