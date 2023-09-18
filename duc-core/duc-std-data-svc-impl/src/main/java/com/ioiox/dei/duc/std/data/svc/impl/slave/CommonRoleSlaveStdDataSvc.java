package com.ioiox.dei.duc.std.data.svc.impl.slave;

import com.ioiox.dei.core.orm.mybatis.service.std.data.BaseDeiSlaveStdDataSvc;
import com.ioiox.dei.duc.beans.entity.Role;
import com.ioiox.dei.duc.beans.entity.TmpRole;
import com.ioiox.dei.duc.beans.vo.std.slave.BaseRoleSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.BaseTmpRoleSlaveVO;

import java.util.Objects;

public abstract class CommonRoleSlaveStdDataSvc<
        R extends BaseRoleSlaveVO,
        E extends Role>
        extends BaseDeiSlaveStdDataSvc<R, E> {

    protected void assembleRoleAttrs(final BaseRoleSlaveVO stdVO, final Role entity) {
        assembleCommonAttrs(stdVO, entity);
        stdVO.setCode(entity.getCode());
        stdVO.setName(entity.getName());
        stdVO.setType(entity.getType());
        stdVO.setStatus(entity.getStatus());
        stdVO.setMemo(entity.getMemo());
        stdVO.setSysPrjId(entity.getSysPrjSid());
    }

    protected void assembleTmpRoleAttrs(final BaseTmpRoleSlaveVO stdVO, final TmpRole entity) {
        assembleRoleAttrs(stdVO, entity);
        stdVO.setUnlimitedDateRangeFlag(entity.getUnlimitedDateRangeFlag());
        if (Objects.nonNull(entity.getEffectiveStartDate())) {
            stdVO.setEffectiveStartDate(entity.getEffectiveStartDate().getTime());
        }
        if (Objects.nonNull(entity.getEffectiveEndDate())) {
            stdVO.setEffectiveEndDate(entity.getEffectiveEndDate().getTime());
        }
        stdVO.setUnlimitedTimeRangeFlag(entity.getUnlimitedTimeRangeFlag());
        if (Objects.nonNull(entity.getEffectiveStartTime())) {
            stdVO.setEffectiveStartTime(entity.getEffectiveStartTime().getTime());
        }
        if (Objects.nonNull(entity.getEffectiveEndTime())) {
            stdVO.setEffectiveEndTime(entity.getEffectiveEndTime().getTime());
        }
    }
}
