package com.ioiox.dei.duc.beans.model;

import com.ioiox.dei.core.vo.UpdatableAttr;
import com.ioiox.dei.core.vo.UpdatableDateAttr;
import com.ioiox.dei.core.vo.UpdatableTimeAttr;
import com.ioiox.dei.core.vo.UpdatableVO;
import com.ioiox.dei.duc.beans.entity.TmpRole;
import com.ioiox.dei.duc.beans.vo.std.master.BaseTmpRoleMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.BaseTmpRoleSlaveStdVO;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public abstract class BaseTmpRoleUpdatableAttrsAnalyser<
        M extends BaseTmpRoleMasterStdVO,
        S extends BaseTmpRoleSlaveStdVO,
        O extends BaseTmpRoleUpdatableObj,
        C extends BaseTmpRoleUpdateCtx<O>>
        extends BaseRoleUpdatableAttrsAnalyser<M, S, O, C> {

    @Override
    protected void analyseUpdatedAttrs(final M tmpRole, final S existingTmpRole, final C updateCtx) {
        super.analyseUpdatedAttrs(tmpRole, existingTmpRole, updateCtx);
        if (UpdatableVO.modified(existingTmpRole.getUnlimitedDateRangeFlag(), tmpRole.getUnlimitedDateRangeFlag())) {
            updateCtx.getUpdatableObj().setUnlimitedDateRangeFlag(new UpdatableAttr<>(TmpRole.ShowColumn.UNLIMITED_DATE_RANGE.getCode(), existingTmpRole.getUnlimitedDateRangeFlag(), tmpRole.getUnlimitedDateRangeFlag()));
        }
        if (UpdatableVO.modified(existingTmpRole.getEffectiveStartDate(), tmpRole.getEffectiveStartDate())) {
            final Date oldEffectiveStartDate = Objects.isNull(existingTmpRole.getEffectiveStartDate()) ? null : new Date(existingTmpRole.getEffectiveStartDate());
            final Date newEffectiveStartDate = Objects.isNull(tmpRole.getEffectiveStartDate()) ? null : new Date(tmpRole.getEffectiveStartDate());
            updateCtx.getUpdatableObj().setEffectiveStartDate(new UpdatableDateAttr(TmpRole.ShowColumn.EFFECTIVE_START_DATE.getCode(), oldEffectiveStartDate, newEffectiveStartDate));
        }
        if (UpdatableVO.modified(existingTmpRole.getEffectiveEndDate(), tmpRole.getEffectiveEndDate())) {
            final Date oldEffectiveEndDate = Objects.isNull(existingTmpRole.getEffectiveEndDate()) ? null : new Date(existingTmpRole.getEffectiveEndDate());
            final Date newEffectiveEndDate = Objects.isNull(tmpRole.getEffectiveEndDate()) ? null : new Date(tmpRole.getEffectiveEndDate());
            updateCtx.getUpdatableObj().setEffectiveStartDate(new UpdatableDateAttr(TmpRole.ShowColumn.EFFECTIVE_END_DATE.getCode(), oldEffectiveEndDate, newEffectiveEndDate));
        }
        if (UpdatableVO.modified(existingTmpRole.getUnlimitedTimeRangeFlag(), tmpRole.getUnlimitedTimeRangeFlag())) {
            updateCtx.getUpdatableObj().setUnlimitedDateRangeFlag(new UpdatableAttr<>(TmpRole.ShowColumn.UNLIMITED_TIME_RANGE.getCode(), existingTmpRole.getUnlimitedTimeRangeFlag(), tmpRole.getUnlimitedTimeRangeFlag()));
        }
        if (UpdatableVO.modified(existingTmpRole.getEffectiveStartTime(), tmpRole.getEffectiveStartTime())) {
            final Time oldEffectiveStartTime = Objects.isNull(existingTmpRole.getEffectiveStartTime()) ? null : new Time(existingTmpRole.getEffectiveStartTime());
            final Time newEffectiveStartTime = Objects.isNull(tmpRole.getEffectiveStartTime()) ? null : new Time(tmpRole.getEffectiveStartTime());
            updateCtx.getUpdatableObj().setEffectiveStartTime(new UpdatableTimeAttr(TmpRole.ShowColumn.EFFECTIVE_START_TIME.getCode(), oldEffectiveStartTime, newEffectiveStartTime));
        }
        if (UpdatableVO.modified(existingTmpRole.getEffectiveEndTime(), tmpRole.getEffectiveEndTime())) {
            final Time oldEffectiveEndTime = Objects.isNull(existingTmpRole.getEffectiveEndTime()) ? null : new Time(existingTmpRole.getEffectiveEndTime());
            final Time newEffectiveEndTime = Objects.isNull(tmpRole.getEffectiveEndTime()) ? null : new Time(tmpRole.getEffectiveEndTime());
            updateCtx.getUpdatableObj().setEffectiveStartTime(new UpdatableTimeAttr(TmpRole.ShowColumn.EFFECTIVE_END_TIME.getCode(), oldEffectiveEndTime, newEffectiveEndTime));
        }
    }
}
