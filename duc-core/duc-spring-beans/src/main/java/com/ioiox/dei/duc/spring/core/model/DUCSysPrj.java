package com.ioiox.dei.duc.spring.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class DUCSysPrj {
    /**
     * 项目ID
     */
    private Long id;
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
     */
    private String status;

    private DUCSysPrj(final Builder builder) {
        id = builder.id;
        code = builder.code;
        name = builder.name;
        status = builder.status;
    }

    public UniqueKey uniqueKey() {
        return new UniqueKey(code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DUCSysPrj that = (DUCSysPrj) o;
        return Objects.equals(uniqueKey(), that.uniqueKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueKey());
    }

    @Getter
    public static class UniqueKey {
        private final String code;

        public UniqueKey(final String code) {
            this.code = code;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UniqueKey uniqueKey = (UniqueKey) o;
            return Objects.equals(code, uniqueKey.code);
        }

        @Override
        public int hashCode() {
            return Objects.hash(code);
        }
    }

    public static class Builder {
        private Long id;
        private String code;
        private String name;
        private String status;

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
        public Builder status(final String status) {
            this.status = status;
            return this;
        }

        public DUCSysPrj build() {
            return new DUCSysPrj(this);
        }
    }
}
