package com.ioiox.dei.duc.beans.vo.std.slave;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ioiox.dei.core.vo.SlaveStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MenuSlaveStdVO extends SlaveStdDataVO {
    /**
     * 菜单编号
     */
    private String code;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 父菜单ID
     */
    private Long pid;
    /**
     * 菜单层级
     */
    private Integer lvl;
    /**
     * 菜单路由路径
     */
    private String routePath;
    /**
     * 菜单对应前端组件的URL
     */
    private String componentUrl;
    /**
     * 菜单跳转路由地址 (当菜单没有对应的前端组件时，通常情况下指的是父菜单的跳转路由地址)
     */
    private String redirectPath;
    /**
     * 是否隐藏(Y: 是, N: 否)
     */
    private String isHidden;
    /**
     * 是否缓存(Y: 是, N: 否)
     */
    private String isCache;
    /**
     * 菜单对应的图标
     */
    private String icon;
    /**
     * 菜单状态
     */
    private String status;
    /**
     * 所属项目ID
     */
    private Long sysPrjId;

    /**
     * 所属的系统
     */
    @JsonIgnore
    private SysPrjSlaveStdVO sysPrj;
    /**
     * 父菜单
     */
    @JsonIgnore
    private MenuSlaveStdVO parent;
    /**
     * 子菜单
     */
    private List<MenuSlaveStdVO> children;
    /**
     * 菜单对应的系统接口信息
     */
    private List<MenuSysApiMappingSlaveStdVO> sysApiMappings;
}
