package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeTmpRoleR2MenuSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeTmpRoleR2MenuSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeTmpRoleR2MenuSlaveDbSvc")
public class EmployeeTmpRoleR2MenuSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<RoleR2Menu, EmployeeTmpRoleR2MenuSlaveMapper>
        implements EmployeeTmpRoleR2MenuSlaveDbSvc {

    @Autowired
    private EmployeeTmpRoleR2MenuSlaveMapper mapper;

    @Override
    protected EmployeeTmpRoleR2MenuSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员临时角色与菜单关联表";
    }
}
