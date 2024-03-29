package com.ioiox.dei.duc.beans.model.master.user;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.model.master.UserDelParam;
import com.ioiox.dei.duc.beans.model.master.UserDelParamBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class UserAcctDelParam extends UserDelParam {
    private List<Long> tenantIds;
    private List<Long> corpIds;

    private UserAcctDelParam(final Builder builder) {
        super(builder);
        tenantIds = builder.tenantIds;
        corpIds = builder.corpIds;
    }

    @Override
    public Map<String, Object> deleteParams() {
        final Map<String, Object> deleteParams = super.deleteParams();
        if (DeiCollectionUtil.isNotEmpty(tenantIds)) {
            if (tenantIds.size() > 1) {
                deleteParams.put("tenantSids", tenantIds);
            } else {
                deleteParams.put("tenantSid", tenantIds.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(corpIds)) {
            if (corpIds.size() > 1) {
                deleteParams.put("corpSids", corpIds);
            } else {
                deleteParams.put("corpSid", corpIds.get(0));
            }
        }
        return deleteParams;
    }

    public static class Builder
            extends UserDelParamBuilder<UserAcctDelParam> {
        private List<Long> tenantIds;
        private List<Long> corpIds;

        public Builder tenantIds(final List<Long> tenantIds) {
            this.tenantIds = tenantIds;
            return this;
        }
        public Builder corpIds(final List<Long> corpIds) {
            this.corpIds = corpIds;
            return this;
        }

        @Override
        public UserAcctDelParam build() {
            return new UserAcctDelParam(this);
        }
    }
}
