package com.ioiox.dei.duc.beans.model.master.tenant;

import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataDelParam;
import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataDelParamBuilder;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class TenantDelParam
        extends StdDataDelParam {
    private List<Long> pids;
    private List<Long> rids;
    private List<String> statuses;

    private TenantDelParam(final Builder builder) {
        super(builder);
        pids = builder.pids;
        rids = builder.rids;
        statuses = builder.statuses;
    }

    @Override
    public Map<String, Object> deleteParams() {
        final Map<String, Object> deleteParams = super.deleteParams();
        if (DeiCollectionUtil.isNotEmpty(pids)) {
            if (pids.size() > 1) {
                deleteParams.put("pids", pids);
            } else {
                deleteParams.put("pid", pids.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(rids)) {
            if (rids.size() > 1) {
                deleteParams.put("rids", rids);
            } else {
                deleteParams.put("rid", rids.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(statuses)) {
            if (statuses.size() > 1) {
                deleteParams.put("statuses", statuses);
            } else {
                deleteParams.put("status", statuses.get(0));
            }
        }
        return deleteParams;
    }

    public static class Builder
            extends StdDataDelParamBuilder<TenantDelParam> {
        private List<Long> pids;
        private List<Long> rids;
        private List<String> statuses;

        public Builder pids(final List<Long> pids) {
            this.pids = pids;
            return this;
        }
        public Builder rids(final List<Long> rids) {
            this.rids = rids;
            return this;
        }
        public Builder statuses(final List<String> statuses) {
            this.statuses = statuses;
            return this;
        }

        @Override
        public TenantDelParam build() {
            return new TenantDelParam(this);
        }
    }
}
