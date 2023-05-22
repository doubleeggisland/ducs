package com.ioiox.dei.duc.spring.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DUCUserSysPrjPrivilege {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 授权给用户的项目权限
     */
    private DUCSysPrj sysPrj;
    /**
     * 访问条件
     */
    private String accessCondition;

    private DUCUserSysPrjPrivilege(final Builder builder) {
        userId = builder.userId;
        sysPrj = builder.sysPrj;
        accessCondition = builder.accessCondition;
    }

    public static class Builder {
        private Long userId;
        private DUCSysPrj sysPrj;
        private String accessCondition;

        public Builder userId(final Long userId) {
            this.userId = userId;
            return this;
        }
        public Builder sysPrj(final DUCSysPrj sysPrj) {
            this.sysPrj = sysPrj;
            return this;
        }
        public Builder accessCondition(final String accessCondition) {
            this.accessCondition = accessCondition;
            return this;
        }

        public DUCUserSysPrjPrivilege build() {
            return new DUCUserSysPrjPrivilege(this);
        }
    }
}
