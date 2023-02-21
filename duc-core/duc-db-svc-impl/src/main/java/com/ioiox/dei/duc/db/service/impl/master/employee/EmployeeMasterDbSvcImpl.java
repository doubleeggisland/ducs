package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.Employee;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeMasterDbSvc")
public class EmployeeMasterDbSvcImpl
        extends BaseDeiMasterDbService<Employee, EmployeeMasterMapper>
        implements EmployeeMasterDbSvc {

    @Autowired
    private EmployeeMasterMapper mapper;

    @Override
    protected EmployeeMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员表";
    }
}
