package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.orm.mybatis.model.std.data.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.SysResRoleR2Res;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserTmpSysResRoleR2ResSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserTmpSysResRoleR2ResSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("tenantUserTmpSysResRoleR2ResSlaveDbSvc")
public class TenantUserTmpSysResRoleR2ResSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, SysResRoleR2Res, TenantUserTmpSysResRoleR2ResSlaveMapper>
        implements TenantUserTmpSysResRoleR2ResSlaveDbSvc {

    @Autowired
    private TenantUserTmpSysResRoleR2ResSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedSysResIds(final List<Long> tmpSysResRoleIds) {
        if (DeiCollectionUtil.isEmpty(tmpSysResRoleIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (tmpSysResRoleIds.size() > 1) {
            conditionsHolder.addQueryCondition("sysResRoleSids", tmpSysResRoleIds);
        } else {
            conditionsHolder.addQueryCondition("sysResRoleSid", tmpSysResRoleIds.get(0));
        }
        return getUniqueKeysGroupedBy1st(conditionsHolder.queryParams(),
                Arrays.asList(SysResRoleR2Res.ShowColumn.SYS_RES_ROLE_SID.getCode(), SysResRoleR2Res.ShowColumn.SYS_RES_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedTmpSysResRoleIds(final List<Long> sysResIds) {
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
    protected TenantUserTmpSysResRoleR2ResSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户临时系统资源角色与资源关联表";
    }
}
