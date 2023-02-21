package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2SysResRole;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeUserGrpR2SysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeUserGrpR2SysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeUserGrpR2SysResRoleMasterDbSvc")
public class EmployeeUserGrpR2SysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserGrpR2SysResRole, EmployeeUserGrpR2SysResRoleMasterMapper>
        implements EmployeeUserGrpR2SysResRoleMasterDbSvc {

    @Autowired
    private EmployeeUserGrpR2SysResRoleMasterMapper mapper;

    @Override
    protected EmployeeUserGrpR2SysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员用户组与系统资源角色关联表";
    }
}
