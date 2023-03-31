package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.TenantUserR2SysResRole;
import com.ioiox.dei.duc.beans.entity.UserR2SysResRole;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserR2TmpSysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserR2TmpSysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("tenantUserR2TmpSysResRoleSlaveDbSvc")
public class TenantUserR2TmpSysResRoleSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, TenantUserR2SysResRole, TenantUserR2TmpSysResRoleSlaveMapper>
        implements TenantUserR2TmpSysResRoleSlaveDbSvc {

    @Autowired
    private TenantUserR2TmpSysResRoleSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedTmpSysResRoleIds(final List<Long> tenantUserIds) {
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
    public Map<Long, List<Long>> getGroupedTenantUserIds(final List<Long> tmpSysResRoleIds) {
        if (DeiCollectionUtil.isEmpty(tmpSysResRoleIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (tmpSysResRoleIds.size() > 1) {
            conditionsHolder.addQueryCondition("sysResRoleSids", tmpSysResRoleIds);
        } else {
            conditionsHolder.addQueryCondition("sysResRoleSid", tmpSysResRoleIds.get(0));
        }
        return getUniqueKeysGroupedBy2nd(conditionsHolder.queryParams(),
                Arrays.asList(UserR2SysResRole.ShowColumn.USER_SID.getCode(), UserR2SysResRole.ShowColumn.SYS_RES_ROLE_SID.getCode()));
    }

    @Override
    protected TenantUserR2TmpSysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户与临时系统资源角色关联表";
    }
}
