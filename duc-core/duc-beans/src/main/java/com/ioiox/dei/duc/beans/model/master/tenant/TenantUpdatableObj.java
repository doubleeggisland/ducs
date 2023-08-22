package com.ioiox.dei.duc.beans.model.master.tenant;


import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObj;
import com.ioiox.dei.duc.beans.entity.Tenant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class TenantUpdatableObj
        extends UpdatableObj {

    private UpdatableAttr<String> code;

    private UpdatableAttr<String> name;

    private UpdatableAttr<String> memo;

    private UpdatableAttr<String> status;

    private UpdatableAttr<Integer> lvl;

    private UpdatableAttr<Long> pid;

    private UpdatableAttr<Long> rid;

    @Override
    public Map<String, String> updateSummary() {
        final Map<String, String> updateSummary = new HashMap<>(Tenant.ShowColumn.values().length);
        if (Objects.nonNull(code)) {
            updateSummary.put(code.getAttrName(), String.valueOf(code));
        }
        if (Objects.nonNull(name)) {
            updateSummary.put(name.getAttrName(), String.valueOf(name));
        }
        if (Objects.nonNull(memo)) {
            updateSummary.put(memo.getAttrName(), String.valueOf(memo));
        }
        if (Objects.nonNull(status)) {
            updateSummary.put(status.getAttrName(), String.valueOf(status));
        }
        if (Objects.nonNull(lvl)) {
            updateSummary.put(lvl.getAttrName(), String.valueOf(lvl));
        }
        if (Objects.nonNull(pid)) {
            updateSummary.put(pid.getAttrName(), String.valueOf(pid));
        }
        if (Objects.nonNull(rid)) {
            updateSummary.put(rid.getAttrName(), String.valueOf(rid));
        }
        return updateSummary;
    }
}