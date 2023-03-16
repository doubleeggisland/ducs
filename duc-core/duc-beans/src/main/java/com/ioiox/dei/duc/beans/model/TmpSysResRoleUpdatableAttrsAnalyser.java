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

public abstract class TmpSysResRoleUpdatableAttrsAnalyser<
        M extends BaseTmpRoleMasterStdVO,
        S extends BaseTmpRoleSlaveStdVO,
        O extends TmpSysResRoleUpdatableObj,
        C extends TmpSysResRoleUpdateCtx<O>>
        extends SysResRoleUpdatableAttrsAnalyser<M, S, O, C> {

    @Override
    protected void analyseUpdatedAttrs(final M tmpSysResRole, final S existingTmpSysResRole, final C updateCtx) {
        super.analyseUpdatedAttrs(tmpSysResRole, existingTmpSysResRole, updateCtx);
        if (UpdatableVO.modified(existingTmpSysResRole.getUnlimitedDateRangeFlag(), tmpSysResRole.getUnlimitedDateRangeFlag())) {
            updateCtx.getUpdatableObj().setUnlimitedDateRangeFlag(new UpdatableAttr<>(TmpRole.ShowColumn.UNLIMITED_DATE_RANGE.getCode(), existingTmpSysResRole.getUnlimitedDateRangeFlag(), tmpSysResRole.getUnlimitedDateRangeFlag()));
        }
        if (UpdatableVO.modified(existingTmpSysResRole.getEffectiveStartDate(), tmpSysResRole.getEffectiveStartDate())) {
            final Date oldEffectiveStartDate = Objects.isNull(existingTmpSysResRole.getEffectiveStartDate()) ? null : new Date(existingTmpSysResRole.getEffectiveStartDate());
            final Date newEffectiveStartDate = Objects.isNull(tmpSysResRole.getEffectiveStartDate()) ? null : new Date(tmpSysResRole.getEffectiveStartDate());
            updateCtx.getUpdatableObj().setEffectiveStartDate(new UpdatableDateAttr(TmpRole.ShowColumn.EFFECTIVE_START_DATE.getCode(), oldEffectiveStartDate, newEffectiveStartDate));
        }
        if (UpdatableVO.modified(existingTmpSysResRole.getEffectiveEndDate(), tmpSysResRole.getEffectiveEndDate())) {
            final Date oldEffectiveEndDate = Objects.isNull(existingTmpSysResRole.getEffectiveEndDate()) ? null : new Date(existingTmpSysResRole.getEffectiveEndDate());
            final Date newEffectiveEndDate = Objects.isNull(tmpSysResRole.getEffectiveEndDate()) ? null : new Date(tmpSysResRole.getEffectiveEndDate());
            updateCtx.getUpdatableObj().setEffectiveStartDate(new UpdatableDateAttr(TmpRole.ShowColumn.EFFECTIVE_END_DATE.getCode(), oldEffectiveEndDate, newEffectiveEndDate));
        }
        if (UpdatableVO.modified(existingTmpSysResRole.getUnlimitedTimeRangeFlag(), tmpSysResRole.getUnlimitedTimeRangeFlag())) {
            updateCtx.getUpdatableObj().setUnlimitedDateRangeFlag(new UpdatableAttr<>(TmpRole.ShowColumn.UNLIMITED_TIME_RANGE.getCode(), existingTmpSysResRole.getUnlimitedTimeRangeFlag(), tmpSysResRole.getUnlimitedTimeRangeFlag()));
        }
        if (UpdatableVO.modified(existingTmpSysResRole.getEffectiveStartTime(), tmpSysResRole.getEffectiveStartTime())) {
            final Time oldEffectiveStartTime = Objects.isNull(existingTmpSysResRole.getEffectiveStartTime()) ? null : new Time(existingTmpSysResRole.getEffectiveStartTime());
            final Time newEffectiveStartTime = Objects.isNull(tmpSysResRole.getEffectiveStartTime()) ? null : new Time(tmpSysResRole.getEffectiveStartTime());
            updateCtx.getUpdatableObj().setEffectiveStartTime(new UpdatableTimeAttr(TmpRole.ShowColumn.EFFECTIVE_START_TIME.getCode(), oldEffectiveStartTime, newEffectiveStartTime));
        }
        if (UpdatableVO.modified(existingTmpSysResRole.getEffectiveEndTime(), tmpSysResRole.getEffectiveEndTime())) {
            final Time oldEffectiveEndTime = Objects.isNull(existingTmpSysResRole.getEffectiveEndTime()) ? null : new Time(existingTmpSysResRole.getEffectiveEndTime());
            final Time newEffectiveEndTime = Objects.isNull(tmpSysResRole.getEffectiveEndTime()) ? null : new Time(tmpSysResRole.getEffectiveEndTime());
            updateCtx.getUpdatableObj().setEffectiveStartTime(new UpdatableTimeAttr(TmpRole.ShowColumn.EFFECTIVE_END_TIME.getCode(), oldEffectiveEndTime, newEffectiveEndTime));
        }
    }
}
