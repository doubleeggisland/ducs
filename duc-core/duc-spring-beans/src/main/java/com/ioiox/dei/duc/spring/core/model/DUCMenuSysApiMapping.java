package com.ioiox.dei.duc.spring.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DUCMenuSysApiMapping {
    /**
     * 菜单ID
     */
    private Long menuId;
    /**
     * 菜单关联的系统接口
     */
    private DUCSysApi sysApi;
    /**
     * 交互形式
     * @see DUCSysApiInteractForm
     */
    private String interactForm;

    private DUCMenuSysApiMapping(final Builder builder) {
        menuId = builder.menuId;
        sysApi = builder.sysApi;
        interactForm = builder.interactForm;
    }

    public static class Builder {
        private Long menuId;
        private DUCSysApi sysApi;
        private String interactForm;

        public Builder menuId(final Long menuId) {
            this.menuId = menuId;
            return this;
        }
        public Builder sysApi(final DUCSysApi sysApi) {
            this.sysApi = sysApi;
            return this;
        }
        public Builder interactForm(final String interactForm) {
            this.interactForm = interactForm;
            return this;
        }

        public DUCMenuSysApiMapping build() {
            return new DUCMenuSysApiMapping(this);
        }
    }
}
