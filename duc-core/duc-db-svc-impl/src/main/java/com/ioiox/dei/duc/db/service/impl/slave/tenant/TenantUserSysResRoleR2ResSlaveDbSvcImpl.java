package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.SysResRoleR2Res;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserSysResRoleR2ResSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserSysResRoleR2ResSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("tenantUserSysResRoleR2ResSlaveDbSvc")
public class TenantUserSysResRoleR2ResSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, SysResRoleR2Res, TenantUserSysResRoleR2ResSlaveMapper>
        implements TenantUserSysResRoleR2ResSlaveDbSvc {

    @Autowired
    private TenantUserSysResRoleR2ResSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedSysResIds(final List<Long> sysResRoleIds) {
        if (DeiCollectionUtil.isEmpty(sysResRoleIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (sysResRoleIds.size() > 1) {
            conditionsHolder.addQueryCondition("sysResRoleSids", sysResRoleIds);
        } else {
            conditionsHolder.addQueryCondition("sysResRoleSid", sysResRoleIds.get(0));
        }
        return getUniqueKeysGroupedBy1st(conditionsHolder.queryParams(),
                Arrays.asList(SysResRoleR2Res.ShowColumn.SYS_RES_ROLE_SID.getCode(), SysResRoleR2Res.ShowColumn.SYS_RES_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedSysResRoleIds(final List<Long> sysResIds) {
        if (DeiCollectionUtil.isEmpty(sysResIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (sysResIds.size() > 1) {
            conditionsHolder.addQueryCondition("sysResSids", sysResIds);
        } else {
            conditionsHolder.addQueryCondition("sysResSid", sysResIds.get(0));
        }
        return getUniqueKeysGroupedBy2nd(conditionsHolder.queryParams(),
                Arrays.asList(SysResRoleR2Res.ShowColumn.SYS_RES_ROLE_SID.getCode(), SysResRoleR2Res.ShowColumn.SYS_RES_SID.getCode()));
    }

    @Override
    protected TenantUserSysResRoleR2ResSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户系统资源角色与资源关联表";
    }
}
