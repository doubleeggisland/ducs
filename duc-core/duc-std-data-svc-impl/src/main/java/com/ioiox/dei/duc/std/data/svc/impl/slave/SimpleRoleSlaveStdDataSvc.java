package com.ioiox.dei.duc.std.data.svc.impl.slave;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.service.std.data.BaseDeiSlaveStdDataSvc;
import com.ioiox.dei.duc.beans.entity.SimpleRole;
import com.ioiox.dei.duc.beans.vo.std.slave.SimpleRoleSlaveVO;

import java.util.Objects;

public abstract class SimpleRoleSlaveStdDataSvc<
        T extends SimpleRoleSlaveVO,
        E extends SimpleRole>
        extends BaseDeiSlaveStdDataSvc<T, E> {

    void assembleSimpleRoleCommonAttrs(final SimpleRoleSlaveVO role, final SimpleRole entity) {
        assembleCommonAttrs(role, entity);
        role.setCode(entity.getCode());
        role.setName(entity.getName());
        role.setType(entity.getType());
        role.setStatus(entity.getStatus());
        role.setMemo(entity.getMemo());
        role.setSysPrjId(entity.getSysPrjSid());
    }

    protected void assembleSimpleRoleAttrs(final SimpleRoleSlaveVO role, final SimpleRole entity) {
        assembleSimpleRoleCommonAttrs(role, entity);
        role.setUnlimitedDateRangeFlag(DeiGlobalConstant.FLAG_YES);
        role.setUnlimitedTimeRangeFlag(DeiGlobalConstant.FLAG_YES);
    }

    protected void assembleSimpleTmpRoleAttrs(final SimpleRoleSlaveVO role, final SimpleRole entity) {
        assembleSimpleRoleCommonAttrs(role, entity);
        role.setUnlimitedDateRangeFlag(entity.getUnlimitedDateRange());
        if (Objects.nonNull(entity.getEffectiveStartDate())) {
            role.setEffectiveStartDate(entity.getEffectiveStartDate().getTime());
        }
        if (Objects.nonNull(entity.getEffectiveEndDate())) {
            role.setEffectiveEndDate(entity.getEffectiveEndDate().getTime());
        }
        role.setUnlimitedTimeRangeFlag(entity.getUnlimitedTimeRange());
        if (Objects.nonNull(entity.getEffectiveStartTime())) {
            role.setEffectiveStartTime(entity.getEffectiveStartTime().getTime());
        }
        if (Objects.nonNull(entity.getEffectiveEndTime())) {
            role.setEffectiveEndTime(entity.getEffectiveEndTime().getTime());
        }
    }
}
