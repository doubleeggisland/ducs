package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.beans.BaseDeiEnum;
import com.ioiox.dei.core.beans.DeiStatus;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Menu extends BaseDeiEntity {
    private String code;
    private String name;
    private Long pid;
    private Integer lvl;
    private String routePath;
    private String componentUrl;
    private String redirectPath;
    private String isHidden;
    private String isCache;
    private String icon;
    private String status;
    private Long sysPrjSid;

    public void setDefaultValueIfNeed() {
        if (Objects.isNull(pid)) {
            pid = DeiGlobalConstant.DEFAULT_SID;
        }
        if (Objects.isNull(lvl)) {
            lvl = DeiGlobalConstant.ONE;
        }
        if (StringUtils.isBlank(routePath)) {
            routePath = DeiGlobalConstant.NONE;
        }
        if (StringUtils.isBlank(componentUrl)) {
            componentUrl = DeiGlobalConstant.NONE;
        }
        if (StringUtils.isBlank(redirectPath)) {
            redirectPath = DeiGlobalConstant.NONE;
        }
        if (StringUtils.isBlank(isHidden)) {
            isHidden = DeiGlobalConstant.FLAG_NO;
        }
        if (StringUtils.isBlank(isCache)) {
            isCache = DeiGlobalConstant.FLAG_NO;
        }
        if (StringUtils.isBlank(icon)) {
            icon = DeiGlobalConstant.NONE;
        }
        if (StringUtils.isBlank(status)) {
            status = DeiStatus.ENABLE.getCode();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu that = (Menu) o;
        if (Objects.nonNull(getSid())
                && Objects.nonNull(that.getSid())) {
            return Objects.equals(getSid(), that.getSid());
        } else {
            return StringUtils.equals(code, that.code)
                    && Objects.equals(sysPrjSid, that.sysPrjSid);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Getter
    @AllArgsConstructor
    public enum ShowColumn implements BaseDeiEnum {
        CODE("code", "菜单编号"),
        NAME("name", "菜单名称"),
        PARENT_SID("pid", "父菜单主键ID"),
        LEVEL("lvl", "菜单层级"),
        ROUTE_PATH("routePath", "菜单路由路径"),
        COMPONENT_URL("componentUrl", "菜单对应前端组件URL"),
        REDIRECT_PATH("redirectPath", "菜单跳转路由路径"),
        IS_HIDDEN("isHidden", "是否隐藏标记(Y: 是, N: 否)"),
        IS_CACHE("isCache", "是否缓存标记(Y: 是, N: 否)"),
        ICON("icon", "菜单图标"),
        STATUS("status", "菜单状态"),
        SYS_PRJ_SID("sysPrjSid", "菜单所属项目主键ID"),
        ;
        private final String code;
        private final String desc;
    }
}
