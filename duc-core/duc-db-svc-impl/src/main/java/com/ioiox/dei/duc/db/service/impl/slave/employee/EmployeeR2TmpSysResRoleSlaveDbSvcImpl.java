package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeR2SysResRole;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeR2TmpSysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeR2TmpSysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeR2TmpSysResRoleSlaveDbSvc")
public class EmployeeR2TmpSysResRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<EmployeeR2SysResRole, EmployeeR2TmpSysResRoleSlaveMapper>
        implements EmployeeR2TmpSysResRoleSlaveDbSvc {

    @Autowired
    private EmployeeR2TmpSysResRoleSlaveMapper mapper;

    @Override
    protected EmployeeR2TmpSysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员与临时系统资源角色关联表";
    }
}
