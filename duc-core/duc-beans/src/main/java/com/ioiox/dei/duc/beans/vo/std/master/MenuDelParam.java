package com.ioiox.dei.duc.beans.vo.std.master;

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
public class MenuDelParam
        extends StdDataDelParam {
    private List<Long> pids;
    private List<Long> sysPrjIds;
    private List<String> statuses;

    private MenuDelParam(final Builder builder) {
        super(builder);
        pids = builder.pids;
        sysPrjIds = builder.sysPrjIds;
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
        if (DeiCollectionUtil.isNotEmpty(sysPrjIds)) {
            if (sysPrjIds.size() > 1) {
                deleteParams.put("sysPrjSids", sysPrjIds);
            } else {
                deleteParams.put("sysPrjSid", sysPrjIds.get(0));
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
            extends StdDataDelParamBuilder<MenuDelParam> {
        private List<Long> pids;
        private List<Long> sysPrjIds;
        private List<String> statuses;

        public Builder pids(final List<Long> pids) {
            this.pids = pids;
            return this;
        }
        public Builder sysPrjIds(final List<Long> sysPrjIds) {
            this.sysPrjIds = sysPrjIds;
            return this;
        }
        public Builder statuses(final List<String> statuses) {
            this.statuses = statuses;
            return this;
        }

        @Override
        public MenuDelParam build() {
            return new MenuDelParam(this);
        }
    }
}
