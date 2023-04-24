package com.ioiox.dei.duc.beans.model;

import com.ioiox.dei.core.vo.UpdatableAttr;
import com.ioiox.dei.core.vo.UpdatableVO;
import com.ioiox.dei.duc.beans.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseRoleUpdatableObj extends UpdatableVO {

    private UpdatableAttr<String> name;

    private UpdatableAttr<String> type;

    private UpdatableAttr<String> status;

    private UpdatableAttr<String> memo;

    @Override
    public Map<String, String> updateSummary() {
        final Map<String, String> updateSummary = new HashMap<>(Role.ShowColumn.values().length);
        if (Objects.nonNull(name)) {
            updateSummary.put(name.getAttrName(), String.valueOf(name));
        }
        if (Objects.nonNull(type)) {
            updateSummary.put(type.getAttrName(), String.valueOf(type));
        }
        if (Objects.nonNull(status)) {
            updateSummary.put(status.getAttrName(), String.valueOf(status));
        }
        if (Objects.nonNull(memo)) {
            updateSummary.put(memo.getAttrName(), String.valueOf(memo));
        }
        return updateSummary;
    }
}