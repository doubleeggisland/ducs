package com.ioiox.dei.duc.spring.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class DUCSysResRole
        extends DUCSimpleRole {
    /**
     * 分配的系统资源访问权限
     */
    private Map<DUCSysRes.UniqueKey, DUCSysRes> grantedSysResMap;

    private DUCSysResRole(final Builder builder) {
        super(builder);
        grantedSysResMap = builder.grantedSysResMap;
    }

    public static class Builder
            extends DUCSimpleRoleBuilder<DUCSysResRole> {
        private Map<DUCSysRes.UniqueKey, DUCSysRes> grantedSysResMap;

        public Builder grantedSysResMap(final Map<DUCSysRes.UniqueKey, DUCSysRes> grantedSysResMap) {
            this.grantedSysResMap = grantedSysResMap;
            return this;
        }

        @Override
        public DUCSysResRole build() {
            return new DUCSysResRole(this);
        }
    }
}
