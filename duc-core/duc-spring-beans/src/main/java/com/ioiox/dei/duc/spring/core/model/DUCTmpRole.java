package com.ioiox.dei.duc.spring.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class DUCTmpRole
        extends BaseDUCTmpRole {
    /**
     * 分配的菜单
     */
    private List<DUCMenu> grantedMenus;
    /**
     * 分配的菜单系统接口映射信息
     */
    private Map<DUCMenu.UniqueKey, List<DUCMenuSysApiMapping>> grantedMenuSysApiMappings;
    /**
     * 分配的菜单相关的系统接口
     */
    private Map<DUCSysApi.UniqueKey, DUCSysApi> grantedMenuSysApis;
    /**
     * 分配的系统接口
     */
    private Map<DUCSysApi.UniqueKey, DUCSysApi> grantedSysApis;

    private DUCTmpRole(final Builder builder) {
        super(builder);
        grantedMenus = builder.grantedMenus;
        grantedMenuSysApiMappings = builder.grantedMenuSysApiMappings;
        grantedMenuSysApis = builder.grantedMenuSysApis;
        grantedSysApis = builder.grantedSysApis;
    }

    public static class Builder
            extends BaseDUCTmpRoleBuilder<DUCTmpRole> {
        private List<DUCMenu> grantedMenus;
        private Map<DUCMenu.UniqueKey, List<DUCMenuSysApiMapping>> grantedMenuSysApiMappings;
        private Map<DUCSysApi.UniqueKey, DUCSysApi> grantedMenuSysApis;
        private Map<DUCSysApi.UniqueKey, DUCSysApi> grantedSysApis;

        public Builder grantedMenus(final List<DUCMenu> grantedMenus) {
            this.grantedMenus = grantedMenus;
            return this;
        }
        public Builder grantedMenuSysApiMappings(final Map<DUCMenu.UniqueKey, List<DUCMenuSysApiMapping>> grantedMenuSysApiMappings) {
            this.grantedMenuSysApiMappings = grantedMenuSysApiMappings;
            return this;
        }
        public Builder grantedMenuSysApis(final Map<DUCSysApi.UniqueKey, DUCSysApi> grantedMenuSysApis) {
            this.grantedMenuSysApis = grantedMenuSysApis;
            return this;
        }
        public Builder grantedSysApis(final Map<DUCSysApi.UniqueKey, DUCSysApi> grantedSysApis) {
            this.grantedSysApis = grantedSysApis;
            return this;
        }

        @Override
        public DUCTmpRole build() {
            return new DUCTmpRole(this);
        }
    }
}
