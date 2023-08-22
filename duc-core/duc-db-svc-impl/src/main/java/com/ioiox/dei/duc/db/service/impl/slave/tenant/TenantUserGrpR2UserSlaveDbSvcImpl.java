package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiSlaveDbService;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.orm.mybatis.model.std.data.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.UserGrpR2User;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserGrpR2UserSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserGrpR2UserSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("tenantUserGrpR2UserSlaveDbSvc")
public class TenantUserGrpR2UserSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, UserGrpR2User, TenantUserGrpR2UserSlaveMapper>
        implements TenantUserGrpR2UserSlaveDbSvc {

    @Autowired
    private TenantUserGrpR2UserSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedUserGrpIds(final List<Long> tenantUserIds) {
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
                Arrays.asList(UserGrpR2User.ShowColumn.USER_SID.getCode(), UserGrpR2User.ShowColumn.USER_GROUP_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedTenantUserIds(final List<Long> tenantUserGrpIds) {
        if (DeiCollectionUtil.isEmpty(tenantUserGrpIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (tenantUserGrpIds.size() > 1) {
            conditionsHolder.addQueryCondition("userGrpSids", tenantUserGrpIds);
        } else {
            conditionsHolder.addQueryCondition("userGrpSid", tenantUserGrpIds.get(0));
        }
        return getUniqueKeysGroupedBy2nd(conditionsHolder.queryParams(),
                Arrays.asList(UserGrpR2User.ShowColumn.USER_SID.getCode(), UserGrpR2User.ShowColumn.USER_GROUP_SID.getCode()));
    }

    @Override
    protected TenantUserGrpR2UserSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户组与用户关联表";
    }
}
