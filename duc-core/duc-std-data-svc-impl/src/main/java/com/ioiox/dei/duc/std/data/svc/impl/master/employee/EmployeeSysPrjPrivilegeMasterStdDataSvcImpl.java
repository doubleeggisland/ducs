package com.ioiox.dei.duc.std.data.svc.impl.master.employee;

import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeSysPrjPrivilegeMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseUserSysPrjPrivilegeMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("employeeSysPrjPrivilegeMasterStdDataSvc")
public class EmployeeSysPrjPrivilegeMasterStdDataSvcImpl
        extends BaseUserSysPrjPrivilegeMasterStdDataSvc {

    @Autowired
    @Qualifier("employeeSysPrjPrivilegeMasterDbSvc")
    private EmployeeSysPrjPrivilegeMasterDbSvc employeeSysPrjPrivilegeMasterDbSvc;

    @Override
    protected int doSave(final List<UserSysPrjPrivilege> newEntities) {
        return employeeSysPrjPrivilegeMasterDbSvc.dbInsert(newEntities);
    }

    @Override
    protected int doUpdate(final UserSysPrjPrivilege example) {
        return employeeSysPrjPrivilegeMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return employeeSysPrjPrivilegeMasterDbSvc.deleteByParams(deleteParams);
    }
}
