package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeR2SysResRole;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeR2SysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeR2SysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeR2SysResRoleMasterDbSvc")
public class EmployeeR2SysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<EmployeeR2SysResRole, EmployeeR2SysResRoleMasterMapper>
        implements EmployeeR2SysResRoleMasterDbSvc {

    @Autowired
    private EmployeeR2SysResRoleMasterMapper mapper;

    @Override
    protected EmployeeR2SysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员与系统资源角色关联表";
    }
}
