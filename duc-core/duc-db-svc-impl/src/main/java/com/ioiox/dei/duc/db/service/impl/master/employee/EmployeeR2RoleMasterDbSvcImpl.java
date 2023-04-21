package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeR2Role;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeR2RoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeR2RoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("employeeR2RoleMasterDbSvc")
public class EmployeeR2RoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<EmployeeR2Role, EmployeeR2RoleMasterMapper>
        implements EmployeeR2RoleMasterDbSvc {

    @Autowired
    private EmployeeR2RoleMasterMapper mapper;

    @Override
    public int save(final List<Long> roleSids, final Long employeeSid, final String operator, final Date operateTime) {
        return dbInsert(EmployeeR2Role.instances(roleSids, employeeSid, operator, operateTime));
    }

    @Override
    protected EmployeeR2RoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员与角色关联表";
    }
}
