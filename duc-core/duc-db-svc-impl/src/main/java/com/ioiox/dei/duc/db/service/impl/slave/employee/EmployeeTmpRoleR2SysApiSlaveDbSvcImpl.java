package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeTmpRoleR2SysApiSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeTmpRoleR2SysApiSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeTmpRoleR2SysApiSlaveDbSvc")
public class EmployeeTmpRoleR2SysApiSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<RoleR2SysApi, EmployeeTmpRoleR2SysApiSlaveMapper>
        implements EmployeeTmpRoleR2SysApiSlaveDbSvc {

    @Autowired
    private EmployeeTmpRoleR2SysApiSlaveMapper mapper;

    @Override
    protected EmployeeTmpRoleR2SysApiSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员临时角色与系统API关联表";
    }
}
