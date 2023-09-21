package com.ioiox.dei.duc.std.data.svc.impl.master;

import com.ioiox.dei.core.orm.mybatis.service.std.data.BaseDeiMasterStdDataSvc;
import com.ioiox.dei.duc.beans.entity.SimpleRole;
import com.ioiox.dei.duc.beans.model.master.SimpleRoleUpdatableObj;
import com.ioiox.dei.duc.beans.vo.std.master.SimpleRoleMasterVO;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public abstract class SimpleRoleMasterStdDataSvc<
        T extends SimpleRoleMasterVO,
        O extends SimpleRoleUpdatableObj,
        E extends SimpleRole>
        extends BaseDeiMasterStdDataSvc<T, O, E> {

    protected void assembleSimpleRoleAttrs(final SimpleRole newEntity, final SimpleRoleMasterVO role) {
        newEntity.setCode(role.getCode());
        newEntity.setName(role.getName());
        newEntity.setType(role.getType());
        newEntity.setStatus(role.getStatus());
        newEntity.setMemo(role.getMemo());
        newEntity.setSysPrjSid(role.getSysPrjId());
    }

    protected void assembleSimpleTmpRoleAttrs(final SimpleRole newEntity, final SimpleRoleMasterVO tmpRole) {
        assembleSimpleRoleAttrs(newEntity, tmpRole);
        newEntity.setUnlimitedDateRange(tmpRole.getUnlimitedDateRange());
        if (Objects.nonNull(tmpRole.getEffectiveStartDate())) {
            newEntity.setEffectiveStartDate(new Date(tmpRole.getEffectiveStartDate()));
        }
        if (Objects.nonNull(tmpRole.getEffectiveEndDate())) {
            newEntity.setEffectiveEndDate(new Date(tmpRole.getEffectiveEndDate()));
        }
        newEntity.setUnlimitedTimeRange(tmpRole.getUnlimitedTimeRange());
        if (Objects.nonNull(tmpRole.getEffectiveStartTime())) {
            newEntity.setEffectiveStartTime(new Time(tmpRole.getEffectiveStartTime()));
        }
        if (Objects.nonNull(tmpRole.getEffectiveEndTime())) {
            newEntity.setEffectiveEndTime(new Time(tmpRole.getEffectiveEndTime()));
        }
    }

    protected void assembleRoleUpdatableAttrs(final SimpleRole example, final SimpleRoleUpdatableObj updatableObj) {
        assembleCommonUpdatableAttrs(example, updatableObj);
        if (Objects.nonNull(updatableObj.getName())) {
            example.setName(updatableObj.getName().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getType())) {
            example.setType(updatableObj.getType().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getStatus())) {
            example.setStatus(updatableObj.getStatus().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getMemo())) {
            example.setMemo(updatableObj.getMemo().getNewVal());
        }
    }

    protected void assembleTmpRoleUpdatableAttrs(final SimpleRole example, final SimpleRoleUpdatableObj updatableObj) {
        assembleRoleUpdatableAttrs(example, updatableObj);
        if (Objects.nonNull(updatableObj.getUnlimitedDateRange())) {
            example.setUnlimitedDateRange(updatableObj.getUnlimitedDateRange().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getEffectiveStartDate())) {
            example.setEffectiveStartDate(updatableObj.getEffectiveStartDate().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getEffectiveEndDate())) {
            example.setEffectiveEndDate(updatableObj.getEffectiveEndDate().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getUnlimitedTimeRange())) {
            example.setUnlimitedTimeRange(updatableObj.getUnlimitedTimeRange().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getEffectiveStartTime())) {
            example.setEffectiveStartTime(updatableObj.getEffectiveStartTime().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getEffectiveEndTime())) {
            example.setEffectiveEndTime(updatableObj.getEffectiveEndTime().getNewVal());
        }
    }
}
