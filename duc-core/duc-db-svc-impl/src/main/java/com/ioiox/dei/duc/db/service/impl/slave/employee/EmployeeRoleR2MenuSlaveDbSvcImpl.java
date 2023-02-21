package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeRoleR2MenuSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeRoleR2MenuSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeRoleR2MenuSlaveDbSvc")
public class EmployeeRoleR2MenuSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<RoleR2Menu, EmployeeRoleR2MenuSlaveMapper>
        implements EmployeeRoleR2MenuSlaveDbSvc {

    @Autowired
    private EmployeeRoleR2MenuSlaveMapper mapper;

    @Override
    protected EmployeeRoleR2MenuSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员角色与菜单关联表";
    }
}
