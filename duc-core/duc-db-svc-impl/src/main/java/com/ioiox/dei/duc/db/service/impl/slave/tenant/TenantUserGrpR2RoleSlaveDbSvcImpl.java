package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.orm.mybatis.model.std.data.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.UserGrpR2Role;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserGrpR2RoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserGrpR2RoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("tenantUserGrpR2RoleSlaveDbSvc")
public class TenantUserGrpR2RoleSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, UserGrpR2Role, TenantUserGrpR2RoleSlaveMapper>
        implements TenantUserGrpR2RoleSlaveDbSvc {

    @Autowired
    private TenantUserGrpR2RoleSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedRoleIds(final List<Long> tenantUserGrpIds) {
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
                Arrays.asList(UserGrpR2Role.ShowColumn.USER_GROUP_SID.getCode(), UserGrpR2Role.ShowColumn.ROLE_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedTenantUserGrpIds(final List<Long> roleIds) {
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
    protected TenantUserGrpR2RoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户组与角色关联表";
    }
}
