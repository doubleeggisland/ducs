package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.model.std.data.QueryConditionsHolder;
import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.EmployeeR2SysResRole;
import com.ioiox.dei.duc.beans.entity.UserR2SysResRole;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeR2TmpSysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeR2TmpSysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("employeeR2TmpSysResRoleSlaveDbSvc")
public class EmployeeR2TmpSysResRoleSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, EmployeeR2SysResRole, EmployeeR2TmpSysResRoleSlaveMapper>
        implements EmployeeR2TmpSysResRoleSlaveDbSvc {

    @Autowired
    private EmployeeR2TmpSysResRoleSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedTmpSysResRoleIds(final List<Long> employeeIds) {
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
                Arrays.asList(UserR2SysResRole.ShowColumn.USER_SID.getCode(), UserR2SysResRole.ShowColumn.SYS_RES_ROLE_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedEmployeeIds(final List<Long> tmpSysResRoleIds) {
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
    protected EmployeeR2TmpSysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员与临时系统资源角色关联表";
    }
}
