package com.ioiox.dei.duc.beans.vo.std.master;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.StdDataDelParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public abstract class UserGrpDelParam
        extends StdDataDelParam {
    private List<String> statuses;

    public UserGrpDelParam(UserGrpDelParamBuilder<? extends UserGrpDelParam> builder) {
        super(builder);
        statuses = builder.statuses();
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
}