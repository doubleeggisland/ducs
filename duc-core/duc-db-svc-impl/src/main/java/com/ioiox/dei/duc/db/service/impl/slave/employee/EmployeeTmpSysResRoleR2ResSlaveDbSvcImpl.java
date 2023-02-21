package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.SysResRoleR2Res;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeTmpSysResRoleR2ResSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeTmpSysResRoleR2ResSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeTmpSysResRoleR2ResSlaveDbSvc")
public class EmployeeTmpSysResRoleR2ResSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<SysResRoleR2Res, EmployeeTmpSysResRoleR2ResSlaveMapper>
        implements EmployeeTmpSysResRoleR2ResSlaveDbSvc {

    @Autowired
    private EmployeeTmpSysResRoleR2ResSlaveMapper mapper;

    @Override
    protected EmployeeTmpSysResRoleR2ResSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员临时系统资源角色与资源关联表";
    }
}
