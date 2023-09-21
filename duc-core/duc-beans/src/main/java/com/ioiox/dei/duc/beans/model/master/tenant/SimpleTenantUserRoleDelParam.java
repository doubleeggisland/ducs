package com.ioiox.dei.duc.beans.model.master.tenant;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.model.master.SimpleRoleDelParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public abstract class SimpleTenantUserRoleDelParam extends SimpleRoleDelParam {
    private List<Long> tenantIds;

    public SimpleTenantUserRoleDelParam(final SimpleTenantUserRoleDelParamBuilder<? extends SimpleTenantUserRoleDelParam> builder) {
        super(builder);
        tenantIds = builder.tenantIds();
    }

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
}
