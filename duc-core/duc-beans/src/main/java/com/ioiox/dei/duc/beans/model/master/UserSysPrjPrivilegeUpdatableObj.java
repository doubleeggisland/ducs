package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObj;
import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class UserSysPrjPrivilegeUpdatableObj
        extends UpdatableObj {

    private UpdatableAttr<String> accessCondition;

    @Override
    public Map<String, String> updateSummary() {
        final Map<String, String> updateSummary = new HashMap<>(UserSysPrjPrivilege.ShowColumn.values().length);
        if (Objects.nonNull(accessCondition)) {
            updateSummary.put(accessCondition.getAttrName(), String.valueOf(accessCondition));
        }
        return updateSummary;
    }
}
