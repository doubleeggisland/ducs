package com.ioiox.dei.duc.spring.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class DUCMenu {
    /**
     * 菜单ID
     */
    private Long id;
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
     * 菜单的路由路径
     */
    private String routePath;
    /**
     * 菜单对应的前端组件访问路径
     */
    private String componentUrl;
    /**
     * 菜单的跳转路由路径
     */
    private String redirectPath;
    /**
     * 菜单是否隐藏(Y: 是, N: 否)
     */
    private String isHidden;
    /**
     * 菜单是否缓存(Y: 是, N: 否)
     */
    private String isCache;
    /**
     * 菜单对应的图标
     */
    private String icon;
    /**
     * 菜单所属的项目ID
     */
    private Long sysPrjId;
    /**
     * 基于日期范围和时间范围的授权规则
     */
    private DUCDatetimeRangeBasedAuthRule authRule;
    /**
     * 子菜单
     */
    private List<DUCMenu> subMenus = new LinkedList<>();

    public void addSubMenu(final DUCMenu subMenu) {
        subMenus.add(subMenu);
    }

    public UniqueKey uniqueKey() {
        return new UniqueKey(code, sysPrjId);
    }

    private DUCMenu(final Builder builder) {
        id = builder.id;
        code = builder.code;
        name = builder.name;
        pid = builder.pid;
        lvl = builder.lvl;
        routePath = builder.routePath;
        componentUrl = builder.componentUrl;
        redirectPath = builder.redirectPath;
        isHidden = builder.isHidden;
        isCache = builder.isCache;
        icon = builder.icon;
        sysPrjId = builder.sysPrjId;
        authRule = builder.authRule;
        subMenus = builder.subMenus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DUCMenu that = (DUCMenu) o;
        return Objects.equals(uniqueKey(), that.uniqueKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueKey());
    }

    @Getter
    public static class UniqueKey {
        private final String code;
        private final Long sysPrjId;

        public UniqueKey(final String code, final Long sysPrjId) {
            this.code = code;
            this.sysPrjId = sysPrjId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UniqueKey uniqueKey = (UniqueKey) o;
            return Objects.equals(code, uniqueKey.code) && Objects.equals(sysPrjId, uniqueKey.sysPrjId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(code, sysPrjId);
        }
    }

    public static class Builder {
        private Long id;
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
        private Long sysPrjId;
        private DUCDatetimeRangeBasedAuthRule authRule;
        private List<DUCMenu> subMenus;

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
        public Builder pid(final Long pid) {
            this.pid = pid;
            return this;
        }
        public Builder lvl(final Integer lvl) {
            this.lvl = lvl;
            return this;
        }
        public Builder routePath(final String routePath) {
            this.routePath = routePath;
            return this;
        }
        public Builder componentUrl(final String componentUrl) {
            this.componentUrl = componentUrl;
            return this;
        }
        public Builder redirectPath(final String redirectPath) {
            this.redirectPath = redirectPath;
            return this;
        }
        public Builder isHidden(final String isHidden) {
            this.isHidden = isHidden;
            return this;
        }
        public Builder isCache(final String isCache) {
            this.isCache = isCache;
            return this;
        }
        public Builder icon(final String icon) {
            this.icon = icon;
            return this;
        }
        public Builder sysPrjId(final Long sysPrjId) {
            this.sysPrjId = sysPrjId;
            return this;
        }
        public Builder authRule(final DUCDatetimeRangeBasedAuthRule authRule) {
            this.authRule = authRule;
            return this;
        }
        public Builder subMenus(final List<DUCMenu> subMenus) {
            this.subMenus = subMenus;
            return this;
        }

        public DUCMenu build() {
            return new DUCMenu(this);
        }
    }
}
