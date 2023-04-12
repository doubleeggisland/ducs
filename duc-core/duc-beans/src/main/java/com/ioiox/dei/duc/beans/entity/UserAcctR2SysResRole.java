package com.ioiox.dei.duc.beans.entity;

import com.ioiox.dei.core.beans.BaseDeiEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class UserAcctR2SysResRole extends UserR2SysResRole {

    public static List<UserAcctR2SysResRole> instances(final List<Long> sysResRoleSids, final Long userAcctSid, final String operator, final Date operateTime) {
        final Date createdTime = Objects.isNull(operateTime) ? new Date(System.currentTimeMillis()) : operateTime;
        final List<UserAcctR2SysResRole> entities = new ArrayList<>(sysResRoleSids.size());
        for (final Long sysResRoleSid : sysResRoleSids) {
            final UserAcctR2SysResRole entity = new UserAcctR2SysResRole();
            entity.setUserSid(userAcctSid);
            entity.setSysResRoleSid(sysResRoleSid);
            entity.setCreatedBy(operator);
            entity.setCreatedTime(createdTime);
            entity.setVersionNum(BaseDeiEntity.INIT_VERSION_NUM);
            entities.add(entity);
        }
        return entities;
    }
}
