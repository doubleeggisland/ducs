package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.SysResRoleR2Res;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeSysResRoleR2ResSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeSysResRoleR2ResSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeSysResRoleR2ResSlaveDbSvc")
public class EmployeeSysResRoleR2ResSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<SysResRoleR2Res, EmployeeSysResRoleR2ResSlaveMapper>
        implements EmployeeSysResRoleR2ResSlaveDbSvc {

    @Autowired
    private EmployeeSysResRoleR2ResSlaveMapper mapper;

    @Override
    protected EmployeeSysResRoleR2ResSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员系统资源角色与资源关联表";
    }
}
