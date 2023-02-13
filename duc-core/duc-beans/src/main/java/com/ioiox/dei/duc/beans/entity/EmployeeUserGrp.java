package com.ioiox.dei.duc.beans.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeUserGrp extends UserGrp {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeUserGrp that = (EmployeeUserGrp) o;
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
