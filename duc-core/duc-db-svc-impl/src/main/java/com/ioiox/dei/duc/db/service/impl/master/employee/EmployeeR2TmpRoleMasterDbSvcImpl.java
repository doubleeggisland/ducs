package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeR2Role;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeR2TmpRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeR2TmpRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeR2TmpRoleMasterDbSvc")
public class EmployeeR2TmpRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<EmployeeR2Role, EmployeeR2TmpRoleMasterMapper>
        implements EmployeeR2TmpRoleMasterDbSvc {

    @Autowired
    private EmployeeR2TmpRoleMasterMapper mapper;

    @Override
    protected EmployeeR2TmpRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员与临时角色关联表";
    }
}
