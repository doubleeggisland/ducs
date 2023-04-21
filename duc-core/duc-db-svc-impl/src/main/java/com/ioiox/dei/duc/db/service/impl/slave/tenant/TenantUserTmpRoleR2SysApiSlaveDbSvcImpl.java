package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserTmpRoleR2SysApiSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserTmpRoleR2SysApiSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("tenantUserTmpRoleR2SysApiSlaveDbSvc")
public class TenantUserTmpRoleR2SysApiSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, RoleR2SysApi, TenantUserTmpRoleR2SysApiSlaveMapper>
        implements TenantUserTmpRoleR2SysApiSlaveDbSvc {

    @Autowired
    private TenantUserTmpRoleR2SysApiSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedSysApiSids(final List<Long> tmpRoleSids) {
        if (DeiCollectionUtil.isEmpty(tmpRoleSids)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (tmpRoleSids.size() > 1) {
            conditionsHolder.addQueryCondition("roleSids", tmpRoleSids);
        } else {
            conditionsHolder.addQueryCondition("roleSid", tmpRoleSids.get(0));
        }
        return getUniqueKeysGroupedBy1st(conditionsHolder.queryParams(),
                Arrays.asList(RoleR2SysApi.ShowColumn.ROLE_SID.getCode(), RoleR2SysApi.ShowColumn.SYS_API_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedTmpRoleSids(final List<Long> sysApiSids) {
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
    protected TenantUserTmpRoleR2SysApiSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户临时角色与系统接口关联表";
    }
}
