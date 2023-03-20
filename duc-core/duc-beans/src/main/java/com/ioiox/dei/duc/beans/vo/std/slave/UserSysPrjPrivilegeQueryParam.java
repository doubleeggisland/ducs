package com.ioiox.dei.duc.beans.vo.std.slave;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.StdDataQueryCfgBuilder;
import com.ioiox.dei.core.vo.StdDataQueryParam;
import com.ioiox.dei.core.vo.StdDataQueryParamBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSysPrjPrivilegeQueryParam
        extends StdDataQueryParam {

    private List<Long> userIds;
    private List<Long> sysPrjIds;

    private UserSysPrjPrivilegeQueryParam(final Builder builder) {
        super(builder);
        userIds = builder.userIds;
        sysPrjIds = builder.sysPrjIds;
    }

    @Override
    public Map<String, Object> queryParams() {
        final Map<String, Object> queryParams = super.queryParams();
        if (DeiCollectionUtil.isNotEmpty(this.userIds)) {
            if (this.userIds.size() > 1) {
                queryParams.put("userSids", this.userIds);
            } else {
                queryParams.put("userSid", this.userIds.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(this.sysPrjIds)) {
            if (this.sysPrjIds.size() > 1) {
                queryParams.put("sysPrjSids", this.sysPrjIds);
            } else {
                queryParams.put("sysPrjSid", this.sysPrjIds.get(0));
            }
        }
        return queryParams;
    }

    public static class Builder
            extends StdDataQueryParamBuilder<UserSysPrjPrivilegeQueryParam> {
        private List<Long> userIds;
        private List<Long> sysPrjIds;

        public Builder userIds(final List<Long> userIds) {
            this.userIds = userIds;
            return this;
        }
        public Builder sysPrjIds(final List<Long> sysPrjIds) {
            this.sysPrjIds = sysPrjIds;
            return this;
        }

        @Override
        public UserSysPrjPrivilegeQueryParam build() {
            return new UserSysPrjPrivilegeQueryParam(this);
        }
    }
}
