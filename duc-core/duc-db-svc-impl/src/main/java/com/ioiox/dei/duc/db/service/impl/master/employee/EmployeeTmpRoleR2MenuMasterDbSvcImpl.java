package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeTmpRoleR2MenuMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeTmpRoleR2MenuMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeTmpRoleR2MenuMasterDbSvc")
public class EmployeeTmpRoleR2MenuMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2Menu, EmployeeTmpRoleR2MenuMasterMapper>
        implements EmployeeTmpRoleR2MenuMasterDbSvc {

    @Autowired
    private EmployeeTmpRoleR2MenuMasterMapper mapper;

    @Override
    protected EmployeeTmpRoleR2MenuMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员临时角色与菜单关联表";
    }
}
