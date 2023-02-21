package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeSysPrjPrivilegeSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeSysPrjPrivilegeSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeSysPrjPrivilegeSlaveDbSvc")
public class EmployeeSysPrjPrivilegeSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<UserSysPrjPrivilege, EmployeeSysPrjPrivilegeSlaveMapper>
        implements EmployeeSysPrjPrivilegeSlaveDbSvc {

    @Autowired
    private EmployeeSysPrjPrivilegeSlaveMapper mapper;

    @Override
    protected EmployeeSysPrjPrivilegeSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员系统项目权限表";
    }
}
