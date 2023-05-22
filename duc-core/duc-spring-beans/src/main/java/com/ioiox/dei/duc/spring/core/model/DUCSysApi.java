package com.ioiox.dei.duc.spring.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class DUCSysApi {
    /**
     * 系统接口ID
     */
    private Long id;
    /**
     * 系统接口编号
     */
    private String code;
    /**
     * 系统接口名称
     */
    private String name;
    /**
     * 系统接口类型
     * @see DUCSysApiType
     */
    private String type;
    /**
     * 系统接口地址
     */
    private String url;
    /**
     * 系统接口的请求方式
     */
    private String httpMethod;
    /**
     * 所属的项目模块名称
     */
    private String sysPrjModuleName;
    /**
     * 所属的项目模块编号
     */
    private String sysPrjModuleCode;
    /**
     * 所属的项目ID
     */
    private Long sysPrjId;
    /**
     * 系统接口状态
     */
    private String status;
    /**
     * 访问条件
     */
    private DUCTimeBasedAccessCondition accessCondition;

    private DUCSysApi(final Builder builder) {
        id = builder.id;
        code = builder.code;
        name = builder.name;
        type = builder.type;
        url = builder.url;
        httpMethod = builder.httpMethod;
        sysPrjModuleName = builder.sysPrjModuleName;
        sysPrjModuleCode = builder.sysPrjModuleCode;
        sysPrjId = builder.sysPrjId;
        status = builder.status;
        accessCondition = builder.accessCondition;
    }

    public UniqueKey uniqueKey() {
        return new UniqueKey(url, httpMethod, sysPrjId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DUCSysApi that = (DUCSysApi) o;
        return Objects.equals(uniqueKey(), that.uniqueKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueKey());
    }

    @Getter
    public static class UniqueKey {
        private final String url;
        private final String httpMethod;
        private final Long sysPrjId;

        public UniqueKey(final String url, final String httpMethod, final Long sysPrjId) {
            this.url = url;
            this.httpMethod = httpMethod;
            this.sysPrjId = sysPrjId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UniqueKey uniqueKey = (UniqueKey) o;
            return Objects.equals(url, uniqueKey.url) && Objects.equals(httpMethod, uniqueKey.httpMethod) && Objects.equals(sysPrjId, uniqueKey.sysPrjId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(url, httpMethod, sysPrjId);
        }
    }

    public static class Builder {
        private Long id;
        private String code;
        private String name;
        private String type;
        private String url;
        private String httpMethod;
        private String sysPrjModuleName;
        private String sysPrjModuleCode;
        private Long sysPrjId;
        private String status;
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
        public Builder url(final String url) {
            this.url = url;
            return this;
        }
        public Builder httpMethod(final String httpMethod) {
            this.httpMethod = httpMethod;
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
        public Builder status(final String status) {
            this.status = status;
            return this;
        }
        public Builder accessCondition(final DUCTimeBasedAccessCondition accessCondition) {
            this.accessCondition = accessCondition;
            return this;
        }

        public DUCSysApi build() {
            return new DUCSysApi(this);
        }
    }
}
