package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.vo.UpdatableAttr;
import com.ioiox.dei.core.vo.UpdatableDateAttr;
import com.ioiox.dei.core.vo.UpdatableTimeAttr;
import com.ioiox.dei.duc.beans.entity.Role;
import com.ioiox.dei.duc.beans.entity.TmpRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseTmpRoleUpdatableObj
        extends BaseRoleUpdatableObj {

    private UpdatableAttr<String> unlimitedDateRangeFlag;

    private UpdatableDateAttr effectiveStartDate;

    private UpdatableDateAttr effectiveEndDate;

    private UpdatableAttr<String> unlimitedTimeRangeFlag;

    private UpdatableTimeAttr effectiveStartTime;

    private UpdatableTimeAttr effectiveEndTime;

    @Override
    public Map<String, String> updateSummary() {
        final Map<String, String> updateSummary = new HashMap<>(Role.ShowColumn.values().length + TmpRole.ShowColumn.values().length);
        updateSummary.putAll(super.updateSummary());
        if (Objects.nonNull(unlimitedDateRangeFlag)) {
            updateSummary.put(unlimitedDateRangeFlag.getAttrName(), String.valueOf(unlimitedDateRangeFlag));
        }
        if (Objects.nonNull(effectiveStartDate)) {
            updateSummary.put(effectiveStartDate.getAttrName(), String.valueOf(effectiveStartDate));
        }
        if (Objects.nonNull(effectiveEndDate)) {
            updateSummary.put(effectiveEndDate.getAttrName(), String.valueOf(effectiveEndDate));
        }
        if (Objects.nonNull(unlimitedTimeRangeFlag)) {
            updateSummary.put(unlimitedTimeRangeFlag.getAttrName(), String.valueOf(unlimitedTimeRangeFlag));
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
