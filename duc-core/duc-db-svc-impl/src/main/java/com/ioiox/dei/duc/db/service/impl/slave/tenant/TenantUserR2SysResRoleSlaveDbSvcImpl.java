package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.orm.mybatis.model.std.data.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.TenantUserR2SysResRole;
import com.ioiox.dei.duc.beans.entity.UserR2SysResRole;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserR2SysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserR2SysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("tenantUserR2SysResRoleSlaveDbSvc")
public class TenantUserR2SysResRoleSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, TenantUserR2SysResRole, TenantUserR2SysResRoleSlaveMapper>
        implements TenantUserR2SysResRoleSlaveDbSvc {

    @Autowired
    private TenantUserR2SysResRoleSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedSysResRoleIds(final List<Long> tenantUserIds) {
        if (DeiCollectionUtil.isEmpty(tenantUserIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (tenantUserIds.size() > 1) {
            conditionsHolder.addQueryCondition("userSids", tenantUserIds);
        } else {
            conditionsHolder.addQueryCondition("userSid", tenantUserIds.get(0));
        }
        return getUniqueKeysGroupedBy1st(conditionsHolder.queryParams(),
                Arrays.asList(UserR2SysResRole.ShowColumn.USER_SID.getCode(), UserR2SysResRole.ShowColumn.SYS_RES_ROLE_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedTenantUserIds(final List<Long> sysResRoleIds) {
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
    protected TenantUserR2SysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户与系统资源角色关联表";
    }
}
