package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class EmployeeR2SysResRole extends UserR2SysResRole {

    public static List<EmployeeR2SysResRole> instances(final List<Long> sysResRoleSids, final Long employeeSid, final String operator, final Date operateTime) {
        final Date createdTime = Objects.isNull(operateTime) ? new Date(System.currentTimeMillis()) : operateTime;
        final List<EmployeeR2SysResRole> entities = new ArrayList<>(sysResRoleSids.size());
        for (final Long sysResRoleSid : sysResRoleSids) {
            final EmployeeR2SysResRole entity = new EmployeeR2SysResRole();
            entity.setUserSid(employeeSid);
            entity.setSysResRoleSid(sysResRoleSid);
            entity.setCreatedBy(operator);
            entity.setCreatedTime(createdTime);
            entity.setVersionNum(BaseDeiEntity.INIT_VERSION_NUM);
            entities.add(entity);
        }
        return entities;
    }
}
