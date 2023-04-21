package com.ioiox.dei.duc.beans.vo.std.master.user;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.vo.std.master.BaseRoleDelParam;
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

    public BaseUserRoleDelParam(final BaseUserRoleDelParamBuilder<? extends BaseUserRoleDelParam> builder) {
        super(builder);
        corpIds = builder.corpIds();
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
        return deleteParams;
    }
}
