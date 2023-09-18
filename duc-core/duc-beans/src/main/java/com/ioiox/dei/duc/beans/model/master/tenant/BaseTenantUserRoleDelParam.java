package com.ioiox.dei.duc.beans.model.master.tenant;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.model.master.BaseRoleDelParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class BaseTenantUserRoleDelParam extends BaseRoleDelParam {
    private List<Long> tenantIds;

    public BaseTenantUserRoleDelParam(final BaseTenantUserRoleDelParamBuilder<? extends BaseTenantUserRoleDelParam> builder) {
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
