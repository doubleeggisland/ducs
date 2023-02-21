package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeTmpSysResRole;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeTmpSysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeTmpSysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeTmpSysResRoleMasterDbSvc")
public class EmployeeTmpSysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<EmployeeTmpSysResRole, EmployeeTmpSysResRoleMasterMapper>
        implements EmployeeTmpSysResRoleMasterDbSvc {

    @Autowired
    private EmployeeTmpSysResRoleMasterMapper mapper;

    @Override
    protected EmployeeTmpSysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员临时系统资源角色表";
    }
}
