package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeR2Role;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeR2RoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeR2RoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeR2RoleMasterDbSvc")
public class EmployeeR2RoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<EmployeeR2Role, EmployeeR2RoleMasterMapper>
        implements EmployeeR2RoleMasterDbSvc {

    @Autowired
    private EmployeeR2RoleMasterMapper mapper;

    @Override
    protected EmployeeR2RoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员与角色关联表";
    }
}
