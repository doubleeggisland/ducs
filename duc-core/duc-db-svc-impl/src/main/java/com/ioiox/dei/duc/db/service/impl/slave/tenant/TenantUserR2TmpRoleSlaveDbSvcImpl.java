package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.TenantUserR2Role;
import com.ioiox.dei.duc.beans.entity.UserR2Role;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserR2TmpRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserR2TmpRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("tenantUserR2TmpRoleSlaveDbSvc")
public class TenantUserR2TmpRoleSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, TenantUserR2Role, TenantUserR2TmpRoleSlaveMapper>
        implements TenantUserR2TmpRoleSlaveDbSvc {

    @Autowired
    private TenantUserR2TmpRoleSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedTmpRoleIds(final List<Long> tenantUserIds) {
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
                Arrays.asList(UserR2Role.ShowColumn.USER_SID.getCode(), UserR2Role.ShowColumn.ROLE_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedTenantUserIds(final List<Long> tmpRoleIds) {
        if (DeiCollectionUtil.isEmpty(tmpRoleIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (tmpRoleIds.size() > 1) {
            conditionsHolder.addQueryCondition("roleSids", tmpRoleIds);
        } else {
            conditionsHolder.addQueryCondition("roleSid", tmpRoleIds.get(0));
        }
        return getUniqueKeysGroupedBy2nd(conditionsHolder.queryParams(),
                Arrays.asList(UserR2Role.ShowColumn.USER_SID.getCode(), UserR2Role.ShowColumn.ROLE_SID.getCode()));
    }

    @Override
    protected TenantUserR2TmpRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户与临时角色关联表";
    }
}
