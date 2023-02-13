package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.beans.BaseDeiEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class SysPrj extends BaseDeiEntity {
    /**
     * 项目编号
     */
    private String code;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 项目状态
     * @see com.ioiox.dei.core.beans.DeiStatus
     */
    private String status;
    /**
     * 项目备注
     */
    private String memo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysPrj that = (SysPrj) o;
        if (Objects.nonNull(getSid())
                && Objects.nonNull(that.getSid())) {
            return Objects.equals(getSid(), that.getSid());
        } else {
            return Objects.equals(code, that.code);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        CODE("code", "项目编号"),
        NAME("name", "项目名称"),
        STATUS("status", "项目状态"),
        MEMO("memo", "备注"),
        ;
        private final String code;
        private final String desc;
    }
}
