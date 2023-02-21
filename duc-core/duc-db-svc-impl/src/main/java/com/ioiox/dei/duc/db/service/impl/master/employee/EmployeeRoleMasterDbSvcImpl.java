package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeRole;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeRoleMasterDbSvc")
public class EmployeeRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<EmployeeRole, EmployeeRoleMasterMapper>
        implements EmployeeRoleMasterDbSvc {

    @Autowired
    private EmployeeRoleMasterMapper mapper;

    @Override
    protected EmployeeRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员角色表";
    }
}
