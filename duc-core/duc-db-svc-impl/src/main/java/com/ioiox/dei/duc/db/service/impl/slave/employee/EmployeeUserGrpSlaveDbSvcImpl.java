package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeUserGrp;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeUserGrpSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeUserGrpSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeUserGrpSlaveDbSvc")
public class EmployeeUserGrpSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<EmployeeUserGrp, EmployeeUserGrpSlaveMapper>
        implements EmployeeUserGrpSlaveDbSvc {

    @Autowired
    private EmployeeUserGrpSlaveMapper mapper;

    @Override
    protected EmployeeUserGrpSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员用户组表";
    }
}
