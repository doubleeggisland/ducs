package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.UserAcctR2SysResRole;
import com.ioiox.dei.duc.beans.entity.UserR2SysResRole;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctR2SysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctR2SysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("userAcctR2SysResRoleSlaveDbSvc")
public class UserAcctR2SysResRoleSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, UserAcctR2SysResRole, UserAcctR2SysResRoleSlaveMapper>
        implements UserAcctR2SysResRoleSlaveDbSvc {

    @Autowired
    private UserAcctR2SysResRoleSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedSysResRoleIds(final List<Long> userAcctIds) {
        if (DeiCollectionUtil.isEmpty(userAcctIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (userAcctIds.size() > 1) {
            conditionsHolder.addQueryCondition("userSids", userAcctIds);
        } else {
            conditionsHolder.addQueryCondition("userSid", userAcctIds.get(0));
        }
        return getUniqueKeysGroupedBy1st(conditionsHolder.queryParams(),
                Arrays.asList(UserR2SysResRole.ShowColumn.USER_SID.getCode(), UserR2SysResRole.ShowColumn.SYS_RES_ROLE_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedUserAcctIds(final List<Long> sysResRoleIds) {
        if (DeiCollectionUtil.isEmpty(sysResRoleIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (sysResRoleIds.size() > 1) {
            conditionsHolder.addQueryCondition("sysResRoleSids", sysResRoleIds);
        } else {
            conditionsHolder.addQueryCondition("sysResRoleSid", sysResRoleIds.get(0));
        }
        return getUniqueKeysGroupedBy2nd(conditionsHolder.queryParams(),
                Arrays.asList(UserR2SysResRole.ShowColumn.USER_SID.getCode(), UserR2SysResRole.ShowColumn.SYS_RES_ROLE_SID.getCode()));
    }

    @Override
    protected UserAcctR2SysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户与系统资源角色关系表";
    }
}
