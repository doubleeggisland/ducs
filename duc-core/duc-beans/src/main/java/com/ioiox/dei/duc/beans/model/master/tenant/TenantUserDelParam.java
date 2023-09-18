package com.ioiox.dei.duc.beans.model.master.tenant;

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
public class TenantUserDelParam extends UserDelParam {
    private List<Long> tenantIds;

    private TenantUserDelParam(final Builder builder) {
        super(builder);
        tenantIds = builder.tenantIds;
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
        return deleteParams;
    }

    public static class Builder
            extends UserDelParamBuilder<TenantUserDelParam> {
        private List<Long> tenantIds;

        public Builder tenantIds(final List<Long> tenantIds) {
            this.tenantIds = tenantIds;
            return this;
        }

        @Override
        public TenantUserDelParam build() {
            return new TenantUserDelParam(this);
        }
    }
}
