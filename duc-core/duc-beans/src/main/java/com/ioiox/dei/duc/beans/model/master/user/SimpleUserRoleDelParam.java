package com.ioiox.dei.duc.beans.model.master.user;

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
public abstract class SimpleUserRoleDelParam extends SimpleRoleDelParam {
    private List<Long> corpIds;
    private List<Long> tenantIds;

    public SimpleUserRoleDelParam(final SimpleUserRoleDelParamBuilder<? extends SimpleUserRoleDelParam> builder) {
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
