package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableDateAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObj;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableTimeAttr;
import com.ioiox.dei.duc.beans.entity.SimpleRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public abstract class SimpleRoleUpdatableObj extends UpdatableObj {

    private UpdatableAttr<String> name;

    private UpdatableAttr<String> type;

    private UpdatableAttr<String> memo;

    private UpdatableAttr<String> status;

    private UpdatableAttr<String> unlimitedDateRange;

    private UpdatableDateAttr effectiveStartDate;

    private UpdatableDateAttr effectiveEndDate;

    private UpdatableAttr<String> unlimitedTimeRange;

    private UpdatableTimeAttr effectiveStartTime;

    private UpdatableTimeAttr effectiveEndTime;

    @Override
    public Map<String, String> updateSummary() {
        final Map<String, String> updateSummary = new HashMap<>(SimpleRole.ShowColumn.values().length);
        if (Objects.nonNull(name)) {
            updateSummary.put(name.getAttrName(), String.valueOf(name));
        }
        if (Objects.nonNull(type)) {
            updateSummary.put(type.getAttrName(), String.valueOf(type));
        }
        if (Objects.nonNull(memo)) {
            updateSummary.put(memo.getAttrName(), String.valueOf(memo));
        }
        if (Objects.nonNull(status)) {
            updateSummary.put(status.getAttrName(), String.valueOf(status));
        }
        if (Objects.nonNull(unlimitedDateRange)) {
            updateSummary.put(unlimitedDateRange.getAttrName(), String.valueOf(unlimitedDateRange));
        }
        if (Objects.nonNull(effectiveStartDate)) {
            updateSummary.put(effectiveStartDate.getAttrName(), String.valueOf(effectiveStartDate));
        }
        if (Objects.nonNull(effectiveEndDate)) {
            updateSummary.put(effectiveEndDate.getAttrName(), String.valueOf(effectiveEndDate));
        }
        if (Objects.nonNull(unlimitedTimeRange)) {
            updateSummary.put(unlimitedTimeRange.getAttrName(), String.valueOf(unlimitedTimeRange));
        }
        if (Objects.nonNull(effectiveStartTime)) {
            updateSummary.put(effectiveStartTime.getAttrName(), String.valueOf(effectiveStartTime));
        }
        if (Objects.nonNull(effectiveEndTime)) {
            updateSummary.put(effectiveEndTime.getAttrName(), String.valueOf(effectiveEndTime));
        }
        return updateSummary;
    }
}