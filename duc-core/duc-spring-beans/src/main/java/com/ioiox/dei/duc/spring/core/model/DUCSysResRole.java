package com.ioiox.dei.duc.spring.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class DUCSysResRole extends BaseDUCRole {
    /**
     * 分配的系统资源访问权限
     */
    private Map<DUCSysRes.UniqueKey, DUCSysRes> grantedSysResources;

    private DUCSysResRole(final Builder builder) {
        super(builder);
        grantedSysResources = builder.grantedSysResources;
    }

    public static class Builder
            extends BaseDUCRoleBuilder<DUCSysResRole> {
        private Map<DUCSysRes.UniqueKey, DUCSysRes> grantedSysResources;

        public Builder grantedSysResources(final Map<DUCSysRes.UniqueKey, DUCSysRes> grantedSysResources) {
            this.grantedSysResources = grantedSysResources;
            return this;
        }

        @Override
        public DUCSysResRole build() {
            return new DUCSysResRole(this);
        }
    }
}
