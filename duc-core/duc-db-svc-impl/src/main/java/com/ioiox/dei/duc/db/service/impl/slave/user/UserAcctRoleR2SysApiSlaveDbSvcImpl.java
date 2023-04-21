package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctRoleR2SysApiSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctRoleR2SysApiSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("userAcctRoleR2SysApiSlaveDbSvc")
public class UserAcctRoleR2SysApiSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, RoleR2SysApi, UserAcctRoleR2SysApiSlaveMapper>
        implements UserAcctRoleR2SysApiSlaveDbSvc {

    @Autowired
    private UserAcctRoleR2SysApiSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedSysApiSids(final List<Long> roleSids) {
        if (DeiCollectionUtil.isEmpty(roleSids)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (roleSids.size() > 1) {
            conditionsHolder.addQueryCondition("roleSids", roleSids);
        } else {
            conditionsHolder.addQueryCondition("roleSid", roleSids.get(0));
        }
        return getUniqueKeysGroupedBy1st(conditionsHolder.queryParams(),
                Arrays.asList(RoleR2SysApi.ShowColumn.ROLE_SID.getCode(), RoleR2SysApi.ShowColumn.SYS_API_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedRoleSids(final List<Long> sysApiSids) {
        if (DeiCollectionUtil.isEmpty(sysApiSids)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (sysApiSids.size() > 1) {
            conditionsHolder.addQueryCondition("sysApiSids", sysApiSids);
        } else {
            conditionsHolder.addQueryCondition("sysApiSid", sysApiSids.get(0));
        }
        return getUniqueKeysGroupedBy2nd(conditionsHolder.queryParams(),
                Arrays.asList(RoleR2SysApi.ShowColumn.ROLE_SID.getCode(), RoleR2SysApi.ShowColumn.SYS_API_SID.getCode()));
    }

    @Override
    protected UserAcctRoleR2SysApiSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户角色与系统接口关联表";
    }
}
