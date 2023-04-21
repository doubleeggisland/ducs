package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.RoleR2MenuSysApi;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctRoleR2MenuSysApiSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctRoleR2MenuSysApiSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("userAcctRoleR2MenuSysApiSlaveDbSvc")
public class UserAcctRoleR2MenuSysApiSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, RoleR2MenuSysApi, UserAcctRoleR2MenuSysApiSlaveMapper>
        implements UserAcctRoleR2MenuSysApiSlaveDbSvc {

    @Autowired
    private UserAcctRoleR2MenuSysApiSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedMappingSids(final List<Long> roleSids) {
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
                Arrays.asList(RoleR2MenuSysApi.ShowColumn.ROLE_SID.getCode(), RoleR2MenuSysApi.ShowColumn.MAPPING_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedRoleSids(final List<Long> mappingSids) {
        if (DeiCollectionUtil.isEmpty(mappingSids)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (mappingSids.size() > 1) {
            conditionsHolder.addQueryCondition("mappingSids", mappingSids);
        } else {
            conditionsHolder.addQueryCondition("mappingSid", mappingSids.get(0));
        }
        return getUniqueKeysGroupedBy2nd(conditionsHolder.queryParams(),
                Arrays.asList(RoleR2MenuSysApi.ShowColumn.ROLE_SID.getCode(), RoleR2MenuSysApi.ShowColumn.MAPPING_SID.getCode()));
    }

    @Override
    protected UserAcctRoleR2MenuSysApiSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户角色与菜单相关接口关联表";
    }
}
