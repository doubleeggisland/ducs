package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.orm.mybatis.model.std.data.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.UserGrpR2SysResRole;
import com.ioiox.dei.duc.db.mapper.slave.user.AcctUserGrpR2SysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.AcctUserGrpR2SysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("acctUserGrpR2SysResRoleSlaveDbSvc")
public class AcctUserGrpR2SysResRoleSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, UserGrpR2SysResRole, AcctUserGrpR2SysResRoleSlaveMapper>
        implements AcctUserGrpR2SysResRoleSlaveDbSvc {

    @Autowired
    private AcctUserGrpR2SysResRoleSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedSysResRoleIds(final List<Long> acctUserGrpIds) {
        if (DeiCollectionUtil.isEmpty(acctUserGrpIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (acctUserGrpIds.size() > 1) {
            conditionsHolder.addQueryCondition("userGrpSids", acctUserGrpIds);
        } else {
            conditionsHolder.addQueryCondition("userGrpSid", acctUserGrpIds.get(0));
        }
        return getUniqueKeysGroupedBy1st(conditionsHolder.queryParams(),
                Arrays.asList(UserGrpR2SysResRole.ShowColumn.USER_GROUP_SID.getCode(), UserGrpR2SysResRole.ShowColumn.SYS_RES_ROLE_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedAcctUserGrpIds(final List<Long> sysResRoleIds) {
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
                Arrays.asList(UserGrpR2SysResRole.ShowColumn.USER_GROUP_SID.getCode(), UserGrpR2SysResRole.ShowColumn.SYS_RES_ROLE_SID.getCode()));
    }

    @Override
    protected AcctUserGrpR2SysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户组与系统资源角色关系表";
    }
}
