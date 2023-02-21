package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeRoleR2SysApiMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeRoleR2SysApiMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeRoleR2SysApiMasterDbSvc")
public class EmployeeRoleR2SysApiMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2SysApi, EmployeeRoleR2SysApiMasterMapper>
        implements EmployeeRoleR2SysApiMasterDbSvc {

    @Autowired
    private EmployeeRoleR2SysApiMasterMapper mapper;

    @Override
    protected EmployeeRoleR2SysApiMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员角色与系统API关联表";
    }
}
