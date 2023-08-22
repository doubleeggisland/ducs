package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeSysPrjPrivilegeMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeSysPrjPrivilegeMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeSysPrjPrivilegeMasterDbSvc")
public class EmployeeSysPrjPrivilegeMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserSysPrjPrivilege, EmployeeSysPrjPrivilegeMasterMapper>
        implements EmployeeSysPrjPrivilegeMasterDbSvc {

    @Autowired
    private EmployeeSysPrjPrivilegeMasterMapper mapper;

    @Override
    protected EmployeeSysPrjPrivilegeMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员系统项目权限表";
    }
}
