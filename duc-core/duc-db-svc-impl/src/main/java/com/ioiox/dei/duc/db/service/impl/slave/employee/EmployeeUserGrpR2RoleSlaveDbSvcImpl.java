package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.model.std.data.QueryConditionsHolder;
import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.UserGrpR2Role;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeUserGrpR2RoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeUserGrpR2RoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("employeeUserGrpR2RoleSlaveDbSvc")
public class EmployeeUserGrpR2RoleSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, UserGrpR2Role, EmployeeUserGrpR2RoleSlaveMapper>
        implements EmployeeUserGrpR2RoleSlaveDbSvc {

    @Autowired
    private EmployeeUserGrpR2RoleSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedRoleIds(final List<Long> employeeUserGrpIds) {
        if (DeiCollectionUtil.isEmpty(employeeUserGrpIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (employeeUserGrpIds.size() > 1) {
            conditionsHolder.addQueryCondition("userGrpSids", employeeUserGrpIds);
        } else {
            conditionsHolder.addQueryCondition("userGrpSid", employeeUserGrpIds.get(0));
        }
        return getUniqueKeysGroupedBy1st(conditionsHolder.queryParams(),
                Arrays.asList(UserGrpR2Role.ShowColumn.USER_GROUP_SID.getCode(), UserGrpR2Role.ShowColumn.ROLE_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedEmployeeUserGrpIds(final List<Long> roleIds) {
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
    protected EmployeeUserGrpR2RoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员用户组与角色关联表";
    }
}
