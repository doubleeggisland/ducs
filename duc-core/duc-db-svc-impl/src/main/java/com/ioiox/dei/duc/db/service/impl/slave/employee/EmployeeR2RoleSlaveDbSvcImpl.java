package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.QueryConditionsHolder;
import com.ioiox.dei.duc.beans.entity.EmployeeR2Role;
import com.ioiox.dei.duc.beans.entity.UserR2Role;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeR2RoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeR2RoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("employeeR2RoleSlaveDbSvc")
public class EmployeeR2RoleSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, EmployeeR2Role, EmployeeR2RoleSlaveMapper>
        implements EmployeeR2RoleSlaveDbSvc {

    @Autowired
    private EmployeeR2RoleSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedRoleIds(final List<Long> employeeIds) {
        if (DeiCollectionUtil.isEmpty(employeeIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (employeeIds.size() > 1) {
            conditionsHolder.addQueryCondition("userSids", employeeIds);
        } else {
            conditionsHolder.addQueryCondition("userSid", employeeIds.get(0));
        }
        return getUniqueKeysGroupedBy1st(conditionsHolder.queryParams(),
                Arrays.asList(UserR2Role.ShowColumn.USER_SID.getCode(), UserR2Role.ShowColumn.ROLE_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedEmployeeIds(final List<Long> roleIds) {
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
                Arrays.asList(UserR2Role.ShowColumn.USER_SID.getCode(), UserR2Role.ShowColumn.ROLE_SID.getCode()));
    }

    @Override
    protected EmployeeR2RoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员与角色关联表";
    }
}
