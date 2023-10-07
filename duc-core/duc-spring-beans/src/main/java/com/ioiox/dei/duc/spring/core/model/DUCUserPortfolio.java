package com.ioiox.dei.duc.spring.core.model;

import com.ioiox.dei.core.utils.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class DUCUserPortfolio {
    /**
     * 用户基础信息
     */
    private DUCUserInfo userInfo;
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
    /**
     * 分配的系统资源访问权限
     */
    private Map<DUCSysRes.UniqueKey, DUCSysRes> grantedSysResMap;
    /**
     * 分配的系统权限信息
     */
    private Map<DUCSysPrj.UniqueKey, DUCUserSysPrjPrivilege> grantedSysPrjPrivileges;
    /**
     * 分配的用户组
     */
    private List<DUCUserGrp> grantedUserGrps;
    /**
     * 分配的角色
     */
    private List<DUCRole> grantedRoles;
    /**
     * 分配的临时角色
     */
    private List<DUCRole> grantedTmpRoles;
    /**
     * 分配的系统资源角色
     */
    private List<DUCSysResRole> grantedSysResRoles;
    /**
     * 分配的临时系统资源角色
     */
    private List<DUCSysResRole> grantedTmpSysResRoles;

    public String toString() {
        return JsonUtil.toJsonStr(this);
    }

    private DUCUserPortfolio(final Builder builder) {
        userInfo = builder.userInfo;
        grantedMenus = builder.grantedMenus;
        grantedMenuSysApiMappings = builder.grantedMenuSysApiMappings;
        grantedMenuSysApis = builder.grantedMenuSysApis;
        grantedSysApis = builder.grantedSysApis;
        grantedSysResMap = builder.grantedSysResources;
        grantedSysPrjPrivileges = builder.grantedSysPrjPrivileges;
        grantedUserGrps = builder.grantedUserGrps;
        grantedRoles = builder.grantedRoles;
        grantedTmpRoles = builder.grantedTmpRoles;
        grantedSysResRoles = builder.grantedSysResRoles;
        grantedTmpSysResRoles = builder.grantedTmpSysResRoles;
    }

    public static class Builder {
        private DUCUserInfo userInfo;
        private List<DUCMenu> grantedMenus;
        private Map<DUCMenu.UniqueKey, List<DUCMenuSysApiMapping>> grantedMenuSysApiMappings;
        private Map<DUCSysApi.UniqueKey, DUCSysApi> grantedMenuSysApis;
        private Map<DUCSysApi.UniqueKey, DUCSysApi> grantedSysApis;
        private Map<DUCSysRes.UniqueKey, DUCSysRes> grantedSysResources;
        private Map<DUCSysPrj.UniqueKey, DUCUserSysPrjPrivilege> grantedSysPrjPrivileges;
        private List<DUCUserGrp> grantedUserGrps;
        private List<DUCRole> grantedRoles;
        private List<DUCRole> grantedTmpRoles;
        private List<DUCSysResRole> grantedSysResRoles;
        private List<DUCSysResRole> grantedTmpSysResRoles;

        public Builder userInfo(final DUCUserInfo userInfo) {
            this.userInfo = userInfo;
            return this;
        }
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
        public Builder grantedSysResources(final Map<DUCSysRes.UniqueKey, DUCSysRes> grantedSysResources) {
            this.grantedSysResources = grantedSysResources;
            return this;
        }
        public Builder grantedSysPrjPrivileges(final Map<DUCSysPrj.UniqueKey, DUCUserSysPrjPrivilege> grantedSysPrjPrivileges) {
            this.grantedSysPrjPrivileges = grantedSysPrjPrivileges;
            return this;
        }
        public Builder grantedUserGrps(final List<DUCUserGrp> grantedUserGrps) {
            this.grantedUserGrps = grantedUserGrps;
            return this;
        }
        public Builder grantedRoles(final List<DUCRole> grantedRoles) {
            this.grantedRoles = grantedRoles;
            return this;
        }
        public Builder grantedTmpRoles(final List<DUCRole> grantedTmpRoles) {
            this.grantedTmpRoles = grantedTmpRoles;
            return this;
        }
        public Builder grantedSysResRoles(final List<DUCSysResRole> grantedSysResRoles) {
            this.grantedSysResRoles = grantedSysResRoles;
            return this;
        }
        public Builder grantedTmpSysResRoles(final List<DUCSysResRole> grantedTmpSysResRoles) {
            this.grantedTmpSysResRoles = grantedTmpSysResRoles;
            return this;
        }

        public DUCUserPortfolio build() {
            return new DUCUserPortfolio(this);
        }
    }
}
