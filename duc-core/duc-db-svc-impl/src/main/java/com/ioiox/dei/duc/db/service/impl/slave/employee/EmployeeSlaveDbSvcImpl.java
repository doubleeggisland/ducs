package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.Employee;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("employeeSlaveDbSvc")
public class EmployeeSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<Employee, EmployeeSlaveMapper>
        implements EmployeeSlaveDbSvc {

    @Autowired
    private EmployeeSlaveMapper mapper;

    @Override
    protected EmployeeSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员表";
    }
}
