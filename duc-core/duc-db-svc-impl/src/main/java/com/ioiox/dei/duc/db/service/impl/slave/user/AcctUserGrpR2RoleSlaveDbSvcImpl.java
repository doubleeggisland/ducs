package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.UserGrpR2Role;
import com.ioiox.dei.duc.db.mapper.slave.user.AcctUserGrpR2RoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.AcctUserGrpR2RoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("acctUserGrpR2RoleSlaveDbSvc")
public class AcctUserGrpR2RoleSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, UserGrpR2Role, AcctUserGrpR2RoleSlaveMapper>
        implements AcctUserGrpR2RoleSlaveDbSvc {

    @Autowired
    private AcctUserGrpR2RoleSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedRoleIds(final List<Long> acctUserGrpIds) {
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
                Arrays.asList(UserGrpR2Role.ShowColumn.USER_GROUP_SID.getCode(), UserGrpR2Role.ShowColumn.ROLE_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedAcctUserGrpIds(final List<Long> roleIds) {
        if (DeiCollectionUtil.isEmpty(roleIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (roleIds.size() > 1) {
            conditionsHolder.addQueryCondition("roleSids", roleIds);
        } else {
            conditionsHolder.addQueryCondition("roleSid", roleIds.get(0));
        }
        return getUniqueKeysGroupedBy2nd(conditionsHolder.queryParams(),
                Arrays.asList(UserGrpR2Role.ShowColumn.USER_GROUP_SID.getCode(), UserGrpR2Role.ShowColumn.ROLE_SID.getCode()));
    }

    @Override
    protected AcctUserGrpR2RoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户组与角色关联表";
    }
}
