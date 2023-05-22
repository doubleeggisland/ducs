package com.ioiox.dei.duc.spring.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DUCUserGrp {
    /**
     * 用户组ID
     */
    private Long id;
    /**
     * 用户组编号
     */
    private String code;
    /**
     * 用户组名称
     */
    private String name;
    /**
     * 分配的角色
     */
    private List<DUCRole> grantedRoles;
    /**
     * 分配的系统资源角色
     */
    private List<DUCSysResRole> grantedSysResRoles;

    private DUCUserGrp(final Builder builder) {
        id = builder.id;
        code = builder.code;
        name = builder.name;
        grantedRoles = builder.grantedRoles;
        grantedSysResRoles = builder.grantedSysResRoles;
    }

    public static class Builder {
        private Long id;
        private String code;
        private String name;
        private List<DUCRole> grantedRoles;
        private List<DUCSysResRole> grantedSysResRoles;

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
        public Builder grantedRoles(final List<DUCRole> grantedRoles) {
            this.grantedRoles = grantedRoles;
            return this;
        }
        public Builder grantedSysResRoles(final List<DUCSysResRole> grantedSysResRoles) {
            this.grantedSysResRoles = grantedSysResRoles;
            return this;
        }

        public DUCUserGrp build() {
            return new DUCUserGrp(this);
        }
    }
}
