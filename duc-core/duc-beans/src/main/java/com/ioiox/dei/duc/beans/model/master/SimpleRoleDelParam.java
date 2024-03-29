package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataDelParam;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public abstract class SimpleRoleDelParam extends StdDataDelParam {
    private List<Long> sysPrjIds;
    private List<String> statuses;

    public SimpleRoleDelParam(final SimpleRoleDelParamBuilder<? extends SimpleRoleDelParam> builder) {
        super(builder);
        sysPrjIds = builder.sysPrjIds();
        statuses = builder.statuses();
    }

    @Override
    public Map<String, Object> deleteParams() {
        final Map<String, Object> deleteParams = super.deleteParams();
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
}
