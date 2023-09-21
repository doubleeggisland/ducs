package com.ioiox.dei.duc.beans.model.slave.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.model.slave.SimpleRoleQueryParam;
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
public abstract class SimpleUserRoleQueryParam
        extends SimpleRoleQueryParam {
    private List<Long> corpIds;
    private List<Long> tenantIds;

    public SimpleUserRoleQueryParam(final SimpleUserRoleQueryParamBuilder<? extends SimpleUserRoleQueryParam> builder) {
        super(builder);
        corpIds = builder.corpIds();
        tenantIds = builder.tenantIds();
    }

    @Override
    public Map<String, Object> queryParams() {
        final Map<String, Object> queryParams = super.queryParams();
        if (DeiCollectionUtil.isNotEmpty(this.corpIds)) {
            if (this.corpIds.size() > 1) {
                queryParams.put("corpSids", this.corpIds);
            } else {
                queryParams.put("corpSid", this.corpIds.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(this.tenantIds)) {
            if (this.tenantIds.size() > 1) {
                queryParams.put("tenantSids", this.tenantIds);
            } else {
                queryParams.put("tenantSid", this.tenantIds.get(0));
            }
        }
        return queryParams;
    }
}
