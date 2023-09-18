package com.ioiox.dei.duc.std.data.svc.impl.slave.employee;

import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeSysPrjPrivilegeSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseUserSysPrjPrivilegeSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("employeeSysPrjPrivilegeSlaveStdDataSvc")
public class EmployeeSysPrjPrivilegeSlaveStdDataSvcImpl
        extends BaseUserSysPrjPrivilegeSlaveStdDataSvc {

    @Autowired
    @Qualifier("employeeSysPrjPrivilegeSlaveDbSvc")
    private EmployeeSysPrjPrivilegeSlaveDbSvc employeeSysPrjPrivilegeSlaveDbSvc;

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return employeeSysPrjPrivilegeSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<UserSysPrjPrivilege> findByParams(final Map<String, Object> queryParams, final List<String> showColumns) {
        return employeeSysPrjPrivilegeSlaveDbSvc.findByParams(queryParams, showColumns);
    }
}
