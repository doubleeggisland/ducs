package com.ioiox.dei.duc.spring.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class DUCSysRes {
    /**
     * 系统资源ID
     */
    private Long id;
    /**
     * 系统资源编号
     */
    private String code;
    /**
     * 系统资源名称
     */
    private String name;
    /**
     * 系统资源类型
     */
    private String type;
    /**
     * 系统资源状态
     */
    private String status;
    /**
     * 系统资源所属模块名称
     */
    private String sysPrjModuleName;
    /**
     * 系统资源所属模块编号
     */
    private String sysPrjModuleCode;
    /**
     * 所属项目ID
     */
    private Long sysPrjId;
    /**
     * 访问条件
     */
    private DUCTimeBasedAccessCondition accessCondition;

    private DUCSysRes(final Builder builder) {
        id = builder.id;
        code = builder.code;
        name = builder.name;
        type = builder.type;
        status = builder.status;
        sysPrjModuleName = builder.sysPrjModuleName;
        sysPrjModuleCode = builder.sysPrjModuleCode;
        sysPrjId = builder.sysPrjId;
        accessCondition = builder.accessCondition;
    }

    public UniqueKey uniqueKey() {
        return new UniqueKey(code, sysPrjId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DUCSysRes that = (DUCSysRes) o;
        return Objects.equals(uniqueKey(), that.uniqueKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueKey());
    }

    @Getter
    public static class UniqueKey {
        private final String code;
        private final Long sysPrjId;

        public UniqueKey(final String code, final Long sysPrjId) {
            this.code = code;
            this.sysPrjId = sysPrjId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UniqueKey uniqueKey = (UniqueKey) o;
            return Objects.equals(code, uniqueKey.code) && Objects.equals(sysPrjId, uniqueKey.sysPrjId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(code, sysPrjId);
        }
    }

    public static class Builder {
        private Long id;
        private String code;
        private String name;
        private String type;
        private String status;
        private String sysPrjModuleName;
        private String sysPrjModuleCode;
        private Long sysPrjId;
        private DUCTimeBasedAccessCondition accessCondition;

        public Builder id(final Long id) {
            this.id = id;
            return this;
        }
        public Builder code(final String code) {
            this.code = code;
            return this;
        }
        public Builder name(final String name) {
            this.name = name;
            return this;
        }
        public Builder type(final String type) {
            this.type = type;
            return this;
        }
        public Builder status(final String status) {
            this.status = status;
            return this;
        }
        public Builder sysPrjModuleName(final String sysPrjModuleName) {
            this.sysPrjModuleName = sysPrjModuleName;
            return this;
        }
        public Builder sysPrjModuleCode(final String sysPrjModuleCode) {
            this.sysPrjModuleCode = sysPrjModuleCode;
            return this;
        }
        public Builder sysPrjId(final Long sysPrjId) {
            this.sysPrjId = sysPrjId;
            return this;
        }
        public Builder accessCondition(final DUCTimeBasedAccessCondition accessCondition) {
            this.accessCondition = accessCondition;
            return this;
        }

        public DUCSysRes build() {
            return new DUCSysRes(this);
        }
    }
}
