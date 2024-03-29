package com.ioiox.dei.duc.beans.model.master.user;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.model.master.UserGrpDelParam;
import com.ioiox.dei.duc.beans.model.master.UserGrpDelParamBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class AcctUserGrpDelParam
        extends UserGrpDelParam {
    private List<Long> corpIds;
    private List<Long> tenantIds;

    private AcctUserGrpDelParam(final Builder builder) {
        super(builder);
        corpIds = builder.corpIds;
        tenantIds = builder.tenantIds;
    }

    @Override
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

    public static class Builder
            extends UserGrpDelParamBuilder<AcctUserGrpDelParam> {
        private List<Long> corpIds;
        private List<Long> tenantIds;

        public Builder corpIds(final List<Long> corpIds) {
            this.corpIds = corpIds;
            return this;
        }
        public Builder tenantIds(final List<Long> tenantIds) {
            this.tenantIds = tenantIds;
            return this;
        }

        @Override
        public AcctUserGrpDelParam build() {
            return new AcctUserGrpDelParam(this);
        }
    }
}
