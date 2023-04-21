package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeRoleR2SysApiSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeRoleR2SysApiSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("employeeRoleR2SysApiSlaveDbSvc")
public class EmployeeRoleR2SysApiSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, RoleR2SysApi, EmployeeRoleR2SysApiSlaveMapper>
        implements EmployeeRoleR2SysApiSlaveDbSvc {

    @Autowired
    private EmployeeRoleR2SysApiSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedSysApiSids(final List<Long> roleSids) {
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
                Arrays.asList(RoleR2SysApi.ShowColumn.ROLE_SID.getCode(), RoleR2SysApi.ShowColumn.SYS_API_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedRoleSids(final List<Long> sysApiSids) {
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
    protected EmployeeRoleR2SysApiSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员角色与系统接口关联表";
    }
}
