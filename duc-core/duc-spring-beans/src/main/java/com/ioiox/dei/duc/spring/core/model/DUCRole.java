package com.ioiox.dei.duc.spring.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class DUCRole
        extends BaseDUCRole {
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
}
