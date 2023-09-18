package com.ioiox.dei.duc.beans.model.master.user;

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
public class BaseUserRoleDelParam extends BaseRoleDelParam {
    private List<Long> corpIds;
    private List<Long> tenantIds;

    public BaseUserRoleDelParam(final BaseUserRoleDelParamBuilder<? extends BaseUserRoleDelParam> builder) {
        super(builder);
        corpIds = builder.corpIds();
        tenantIds = builder.tenantIds();
    }

    public Map<String, Object> deleteParams() {
        final Map<String, Object> deleteParams = super.deleteParams();
        if (DeiCollectionUtil.isNotEmpty(corpIds)) {
            if (corpIds.size() > 1) {
                deleteParams.put("corpSids", corpIds);
            } else {
                deleteParams.put("corpSid", corpIds.get(0));
            }
        }
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
