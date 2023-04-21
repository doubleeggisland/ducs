package com.ioiox.dei.duc.db.service.impl.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.RoleR2MenuSysApi;
import com.ioiox.dei.duc.db.mapper.slave.user.UserAcctTmpRoleR2MenuSysApiSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctTmpRoleR2MenuSysApiSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("userAcctTmpRoleR2MenuSysApiSlaveDbSvc")
public class UserAcctTmpRoleR2MenuSysApiSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, RoleR2MenuSysApi, UserAcctTmpRoleR2MenuSysApiSlaveMapper>
        implements UserAcctTmpRoleR2MenuSysApiSlaveDbSvc {

    @Autowired
    private UserAcctTmpRoleR2MenuSysApiSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedMappingSids(final List<Long> tmpRoleSids) {
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
                Arrays.asList(RoleR2MenuSysApi.ShowColumn.ROLE_SID.getCode(), RoleR2MenuSysApi.ShowColumn.MAPPING_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedTmpRoleSids(final List<Long> mappingSids) {
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
    protected UserAcctTmpRoleR2MenuSysApiSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户临时角色与菜单相关接口关联表";
    }
}
