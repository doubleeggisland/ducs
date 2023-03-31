package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.UserGrpR2SysResRole;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserGrpR2SysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserGrpR2SysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("tenantUserGrpR2SysResRoleSlaveDbSvc")
public class TenantUserGrpR2SysResRoleSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, UserGrpR2SysResRole, TenantUserGrpR2SysResRoleSlaveMapper>
        implements TenantUserGrpR2SysResRoleSlaveDbSvc {

    @Autowired
    private TenantUserGrpR2SysResRoleSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedSysResRoleIds(final List<Long> tenantUserGrpIds) {
        if (DeiCollectionUtil.isEmpty(tenantUserGrpIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (tenantUserGrpIds.size() > 1) {
            conditionsHolder.addQueryCondition("userGrpSids", tenantUserGrpIds);
        } else {
            conditionsHolder.addQueryCondition("userGrpSid", tenantUserGrpIds.get(0));
        }
        return getUniqueKeysGroupedBy1st(conditionsHolder.queryParams(),
                Arrays.asList(UserGrpR2SysResRole.ShowColumn.USER_GROUP_SID.getCode(), UserGrpR2SysResRole.ShowColumn.SYS_RES_ROLE_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedTenantUserGrpIds(final List<Long> sysResRoleIds) {
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
    protected TenantUserGrpR2SysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户组与系统资源角色关联表";
    }
}
