package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.StdDataDelParam;
import com.ioiox.dei.core.vo.StdDataDelParamBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class UserSysPrjPrivilegeDelParam
        extends StdDataDelParam {
    private List<Long> userIds;
    private List<Long> sysPrjIds;

    private UserSysPrjPrivilegeDelParam(final Builder builder) {
        super(builder);
        userIds = builder.userIds;
        sysPrjIds = builder.sysPrjIds;
    }

    @Override
    public Map<String, Object> deleteParams() {
        final Map<String, Object> deleteParams = super.deleteParams();
        if (DeiCollectionUtil.isNotEmpty(userIds)) {
            if (userIds.size() > 1) {
                deleteParams.put("userSids", userIds);
            } else {
                deleteParams.put("userSid", userIds.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(sysPrjIds)) {
            if (sysPrjIds.size() > 1) {
                deleteParams.put("sysPrjSids", sysPrjIds);
            } else {
                deleteParams.put("sysPrjSid", sysPrjIds.get(0));
            }
        }
        return deleteParams;
    }

    public static class Builder
            extends StdDataDelParamBuilder<UserSysPrjPrivilegeDelParam> {
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
        public UserSysPrjPrivilegeDelParam build() {
            return new UserSysPrjPrivilegeDelParam(this);
        }
    }
}
