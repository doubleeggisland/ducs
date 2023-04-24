package com.ioiox.dei.duc.std.data.svc.impl.master;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterStdDataSvc;
import com.ioiox.dei.duc.beans.entity.Role;
import com.ioiox.dei.duc.beans.entity.TmpRole;
import com.ioiox.dei.duc.beans.model.BaseRoleUpdatableObj;
import com.ioiox.dei.duc.beans.model.BaseTmpRoleUpdatableObj;
import com.ioiox.dei.duc.beans.vo.std.master.BaseRoleMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.master.BaseTmpRoleMasterStdVO;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public abstract class CommonRoleMasterStdDataSvc<
        T extends BaseRoleMasterStdVO,
        O extends BaseRoleUpdatableObj,
        E extends Role>
        extends BaseDeiMasterStdDataSvc<T, O, E>  {

    protected void assembleRoleCommonAttrs(final Role newEntity, final BaseRoleMasterStdVO masterStdVO) {
        newEntity.setCode(masterStdVO.getCode());
        newEntity.setName(masterStdVO.getName());
        newEntity.setType(masterStdVO.getType());
        newEntity.setStatus(masterStdVO.getStatus());
        newEntity.setMemo(masterStdVO.getMemo());
        newEntity.setSysPrjSid(masterStdVO.getSysPrjId());
    }

    protected void assembleTmpRoleCommonAttrs(final TmpRole newEntity, final BaseTmpRoleMasterStdVO masterStdVO) {
        assembleRoleCommonAttrs(newEntity, masterStdVO);
        newEntity.setUnlimitedDateRangeFlag(masterStdVO.getUnlimitedDateRangeFlag());
        if (Objects.nonNull(masterStdVO.getEffectiveStartDate())) {
            newEntity.setEffectiveStartDate(new Date(masterStdVO.getEffectiveStartDate()));
        }
        if (Objects.nonNull(masterStdVO.getEffectiveEndDate())) {
            newEntity.setEffectiveEndDate(new Date(masterStdVO.getEffectiveEndDate()));
        }
        newEntity.setUnlimitedTimeRangeFlag(masterStdVO.getUnlimitedTimeRangeFlag());
        if (Objects.nonNull(masterStdVO.getEffectiveStartTime())) {
            newEntity.setEffectiveStartTime(new Time(masterStdVO.getEffectiveStartTime()));
        }
        if (Objects.nonNull(masterStdVO.getEffectiveEndTime())) {
            newEntity.setEffectiveEndTime(new Time(masterStdVO.getEffectiveEndTime()));
        }
    }

    protected void assembleRoleUpdatableAttrs(final Role example, final BaseRoleUpdatableObj updatableObj) {
        assembleCommonAttrs(example, updatableObj);
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

    protected void assembleTmpRoleUpdatableAttrs(final TmpRole example, final BaseTmpRoleUpdatableObj updatableObj) {
        assembleRoleUpdatableAttrs(example, updatableObj);
        if (Objects.nonNull(updatableObj.getUnlimitedDateRangeFlag())) {
            example.setUnlimitedDateRangeFlag(updatableObj.getUnlimitedDateRangeFlag().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getEffectiveStartDate())) {
            example.setEffectiveStartDate(updatableObj.getEffectiveStartDate().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getEffectiveEndDate())) {
            example.setEffectiveEndDate(updatableObj.getEffectiveEndDate().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getUnlimitedTimeRangeFlag())) {
            example.setUnlimitedTimeRangeFlag(updatableObj.getUnlimitedTimeRangeFlag().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getEffectiveStartTime())) {
            example.setEffectiveStartTime(updatableObj.getEffectiveStartTime().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getEffectiveEndTime())) {
            example.setEffectiveEndTime(updatableObj.getEffectiveEndTime().getNewVal());
        }
    }
}
