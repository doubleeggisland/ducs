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
public class SysPrjDelParam
        extends StdDataDelParam {
    private List<String> statuses;

    private SysPrjDelParam(final Builder builder) {
        super(builder);
        statuses = builder.statuses;
    }

    @Override
    public Map<String, Object> deleteParams() {
        final Map<String, Object> deleteParams = super.deleteParams();
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
            extends StdDataDelParamBuilder<SysPrjDelParam> {
        private List<String> statuses;

        public Builder statuses(final List<String> statuses) {
            this.statuses = statuses;
            return this;
        }

        @Override
        public SysPrjDelParam build() {
            return new SysPrjDelParam(this);
        }
    }
}
