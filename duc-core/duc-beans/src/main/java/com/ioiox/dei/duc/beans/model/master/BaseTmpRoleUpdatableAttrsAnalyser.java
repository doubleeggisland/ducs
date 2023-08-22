package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableDateAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObj;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableTimeAttr;
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
        if (UpdatableObj.modified(existingTmpRole.getUnlimitedDateRangeFlag(), tmpRole.getUnlimitedDateRangeFlag())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(TmpRole.ShowColumn.UNLIMITED_DATE_RANGE.getCode());
            updateCtx.getUpdatableObj().setUnlimitedDateRangeFlag(new UpdatableAttr<>(TmpRole.ShowColumn.UNLIMITED_DATE_RANGE.getCode(), existingTmpRole.getUnlimitedDateRangeFlag(), tmpRole.getUnlimitedDateRangeFlag()));
        }
        if (UpdatableObj.modified(existingTmpRole.getEffectiveStartDate(), tmpRole.getEffectiveStartDate())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(TmpRole.ShowColumn.EFFECTIVE_START_DATE.getCode());
            final Date oldEffectiveStartDate = Objects.isNull(existingTmpRole.getEffectiveStartDate()) ? null : new Date(existingTmpRole.getEffectiveStartDate());
            final Date newEffectiveStartDate = Objects.isNull(tmpRole.getEffectiveStartDate()) ? null : new Date(tmpRole.getEffectiveStartDate());
            updateCtx.getUpdatableObj().setEffectiveStartDate(new UpdatableDateAttr(TmpRole.ShowColumn.EFFECTIVE_START_DATE.getCode(), oldEffectiveStartDate, newEffectiveStartDate));
        }
        if (UpdatableObj.modified(existingTmpRole.getEffectiveEndDate(), tmpRole.getEffectiveEndDate())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(TmpRole.ShowColumn.EFFECTIVE_END_DATE.getCode());
            final Date oldEffectiveEndDate = Objects.isNull(existingTmpRole.getEffectiveEndDate()) ? null : new Date(existingTmpRole.getEffectiveEndDate());
            final Date newEffectiveEndDate = Objects.isNull(tmpRole.getEffectiveEndDate()) ? null : new Date(tmpRole.getEffectiveEndDate());
            updateCtx.getUpdatableObj().setEffectiveStartDate(new UpdatableDateAttr(TmpRole.ShowColumn.EFFECTIVE_END_DATE.getCode(), oldEffectiveEndDate, newEffectiveEndDate));
        }
        if (UpdatableObj.modified(existingTmpRole.getUnlimitedTimeRangeFlag(), tmpRole.getUnlimitedTimeRangeFlag())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(TmpRole.ShowColumn.UNLIMITED_TIME_RANGE.getCode());
            updateCtx.getUpdatableObj().setUnlimitedDateRangeFlag(new UpdatableAttr<>(TmpRole.ShowColumn.UNLIMITED_TIME_RANGE.getCode(), existingTmpRole.getUnlimitedTimeRangeFlag(), tmpRole.getUnlimitedTimeRangeFlag()));
        }
        if (UpdatableObj.modified(existingTmpRole.getEffectiveStartTime(), tmpRole.getEffectiveStartTime())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(TmpRole.ShowColumn.EFFECTIVE_START_TIME.getCode());
            final Time oldEffectiveStartTime = Objects.isNull(existingTmpRole.getEffectiveStartTime()) ? null : new Time(existingTmpRole.getEffectiveStartTime());
            final Time newEffectiveStartTime = Objects.isNull(tmpRole.getEffectiveStartTime()) ? null : new Time(tmpRole.getEffectiveStartTime());
            updateCtx.getUpdatableObj().setEffectiveStartTime(new UpdatableTimeAttr(TmpRole.ShowColumn.EFFECTIVE_START_TIME.getCode(), oldEffectiveStartTime, newEffectiveStartTime));
        }
        if (UpdatableObj.modified(existingTmpRole.getEffectiveEndTime(), tmpRole.getEffectiveEndTime())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(TmpRole.ShowColumn.EFFECTIVE_END_TIME.getCode());
            final Time oldEffectiveEndTime = Objects.isNull(existingTmpRole.getEffectiveEndTime()) ? null : new Time(existingTmpRole.getEffectiveEndTime());
            final Time newEffectiveEndTime = Objects.isNull(tmpRole.getEffectiveEndTime()) ? null : new Time(tmpRole.getEffectiveEndTime());
            updateCtx.getUpdatableObj().setEffectiveStartTime(new UpdatableTimeAttr(TmpRole.ShowColumn.EFFECTIVE_END_TIME.getCode(), oldEffectiveEndTime, newEffectiveEndTime));
        }
    }
}
