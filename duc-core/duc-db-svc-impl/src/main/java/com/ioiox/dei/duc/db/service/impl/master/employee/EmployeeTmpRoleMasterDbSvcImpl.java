package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeTmpRole;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeTmpRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeTmpRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeTmpRoleMasterDbSvc")
public class EmployeeTmpRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<EmployeeTmpRole, EmployeeTmpRoleMasterMapper>
        implements EmployeeTmpRoleMasterDbSvc {

    @Autowired
    private EmployeeTmpRoleMasterMapper mapper;

    @Override
    protected EmployeeTmpRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员临时角色表";
    }
}
