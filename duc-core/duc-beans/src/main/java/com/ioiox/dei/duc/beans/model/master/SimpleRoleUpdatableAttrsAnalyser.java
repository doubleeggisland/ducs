package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.*;
import com.ioiox.dei.duc.beans.entity.SimpleRole;
import com.ioiox.dei.duc.beans.vo.std.master.SimpleRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.SimpleRoleSlaveVO;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public abstract class SimpleRoleUpdatableAttrsAnalyser<
        M extends SimpleRoleMasterVO,
        S extends SimpleRoleSlaveVO,
        O extends SimpleRoleUpdatableObj,
        C extends SimpleRoleUpdateCtx<O>>
        extends UpdatableObjAnalyser<M, S, O, C> {

    @Override
    protected void analyseUpdatedAttrs(final M role, final S existingRole, final C updateCtx) {
        if (UpdatableObj.modified(existingRole.getName(), role.getName())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SimpleRole.ShowColumn.NAME.getCode());
            updateCtx.getUpdatableObj().setName(new UpdatableAttr<>(SimpleRole.ShowColumn.NAME.getCode(), existingRole.getName(), role.getName()));
        }
        if (UpdatableObj.modified(existingRole.getType(), role.getType())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SimpleRole.ShowColumn.TYPE.getCode());
            updateCtx.getUpdatableObj().setType(new UpdatableAttr<>(SimpleRole.ShowColumn.TYPE.getCode(), existingRole.getType(), role.getType()));
        }
        if (UpdatableObj.modified(existingRole.getStatus(), role.getStatus())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SimpleRole.ShowColumn.STATUS.getCode());
            updateCtx.getUpdatableObj().setStatus(new UpdatableAttr<>(SimpleRole.ShowColumn.STATUS.getCode(), existingRole.getStatus(), role.getStatus()));
        }
        if (UpdatableObj.modified(existingRole.getMemo(), role.getMemo())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SimpleRole.ShowColumn.MEMO.getCode());
            updateCtx.getUpdatableObj().setMemo(new UpdatableAttr<>(SimpleRole.ShowColumn.MEMO.getCode(), existingRole.getMemo(), role.getMemo()));
        }
        if (UpdatableObj.modified(existingRole.getUnlimitedDateRangeFlag(), role.getUnlimitedDateRange())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SimpleRole.ShowColumn.UNLIMITED_DATE_RANGE.getCode());
            updateCtx.getUpdatableObj().setUnlimitedDateRange(new UpdatableAttr<>(SimpleRole.ShowColumn.UNLIMITED_DATE_RANGE.getCode(), existingRole.getUnlimitedDateRangeFlag(), role.getUnlimitedDateRange()));
        }
        if (UpdatableObj.modified(existingRole.getEffectiveStartDate(), role.getEffectiveStartDate())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SimpleRole.ShowColumn.EFFECTIVE_START_DATE.getCode());
            final Date oldEffectiveStartDate = Objects.isNull(existingRole.getEffectiveStartDate()) ? null : new Date(existingRole.getEffectiveStartDate());
            final Date newEffectiveStartDate = Objects.isNull(role.getEffectiveStartDate()) ? null : new Date(role.getEffectiveStartDate());
            updateCtx.getUpdatableObj().setEffectiveStartDate(new UpdatableDateAttr(SimpleRole.ShowColumn.EFFECTIVE_START_DATE.getCode(), oldEffectiveStartDate, newEffectiveStartDate));
        }
        if (UpdatableObj.modified(existingRole.getEffectiveEndDate(), role.getEffectiveEndDate())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SimpleRole.ShowColumn.EFFECTIVE_END_DATE.getCode());
            final Date oldEffectiveEndDate = Objects.isNull(existingRole.getEffectiveEndDate()) ? null : new Date(existingRole.getEffectiveEndDate());
            final Date newEffectiveEndDate = Objects.isNull(role.getEffectiveEndDate()) ? null : new Date(role.getEffectiveEndDate());
            updateCtx.getUpdatableObj().setEffectiveStartDate(new UpdatableDateAttr(SimpleRole.ShowColumn.EFFECTIVE_END_DATE.getCode(), oldEffectiveEndDate, newEffectiveEndDate));
        }
        if (UpdatableObj.modified(existingRole.getUnlimitedTimeRangeFlag(), role.getUnlimitedTimeRange())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SimpleRole.ShowColumn.UNLIMITED_TIME_RANGE.getCode());
            updateCtx.getUpdatableObj().setUnlimitedDateRange(new UpdatableAttr<>(SimpleRole.ShowColumn.UNLIMITED_TIME_RANGE.getCode(), existingRole.getUnlimitedTimeRangeFlag(), role.getUnlimitedTimeRange()));
        }
        if (UpdatableObj.modified(existingRole.getEffectiveStartTime(), role.getEffectiveStartTime())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SimpleRole.ShowColumn.EFFECTIVE_START_TIME.getCode());
            final Time oldEffectiveStartTime = Objects.isNull(existingRole.getEffectiveStartTime()) ? null : new Time(existingRole.getEffectiveStartTime());
            final Time newEffectiveStartTime = Objects.isNull(role.getEffectiveStartTime()) ? null : new Time(role.getEffectiveStartTime());
            updateCtx.getUpdatableObj().setEffectiveStartTime(new UpdatableTimeAttr(SimpleRole.ShowColumn.EFFECTIVE_START_TIME.getCode(), oldEffectiveStartTime, newEffectiveStartTime));
        }
        if (UpdatableObj.modified(existingRole.getEffectiveEndTime(), role.getEffectiveEndTime())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SimpleRole.ShowColumn.EFFECTIVE_END_TIME.getCode());
            final Time oldEffectiveEndTime = Objects.isNull(existingRole.getEffectiveEndTime()) ? null : new Time(existingRole.getEffectiveEndTime());
            final Time newEffectiveEndTime = Objects.isNull(role.getEffectiveEndTime()) ? null : new Time(role.getEffectiveEndTime());
            updateCtx.getUpdatableObj().setEffectiveStartTime(new UpdatableTimeAttr(SimpleRole.ShowColumn.EFFECTIVE_END_TIME.getCode(), oldEffectiveEndTime, newEffectiveEndTime));
        }
    }
}
