package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.UserAcctR2Role;
import com.ioiox.dei.duc.beans.entity.UserR2Role;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctR2RoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctR2RoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("userAcctR2RoleSlaveDbSvc")
public class UserAcctR2RoleSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, UserAcctR2Role, UserAcctR2RoleSlaveMapper>
        implements UserAcctR2RoleSlaveDbSvc {

    @Autowired
    private UserAcctR2RoleSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedRoleIds(final List<Long> userAcctIds) {
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
                Arrays.asList(UserR2Role.ShowColumn.USER_SID.getCode(), UserR2Role.ShowColumn.ROLE_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedUserAcctIds(final List<Long> roleIds) {
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
                Arrays.asList(UserR2Role.ShowColumn.USER_SID.getCode(), UserR2Role.ShowColumn.ROLE_SID.getCode()));
    }

    @Override
    protected UserAcctR2RoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户与角色关联表";
    }
}
