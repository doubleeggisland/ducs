package com.ioiox.dei.duc.spring.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class DUCTmpSysResRole extends BaseDUCTmpRole {
    /**
     * 分配的系统资源访问权限
     */
    private Map<DUCSysRes.UniqueKey, DUCSysRes> grantedSysResources;

    private DUCTmpSysResRole(final Builder builder) {
        super(builder);
        grantedSysResources = builder.grantedSysResources;
    }

    public static class Builder
            extends BaseDUCTmpRoleBuilder<DUCTmpSysResRole> {
        private Map<DUCSysRes.UniqueKey, DUCSysRes> grantedSysResources;

        public Builder grantedSysResources(final Map<DUCSysRes.UniqueKey, DUCSysRes> grantedSysResources) {
            this.grantedSysResources = grantedSysResources;
            return this;
        }

        @Override
        public DUCTmpSysResRole build() {
            return new DUCTmpSysResRole(this);
        }
    }
}
