package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeSysResRole;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeSysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeSysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeSysResRoleMasterDbSvc")
public class EmployeeSysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<EmployeeSysResRole, EmployeeSysResRoleMasterMapper>
        implements EmployeeSysResRoleMasterDbSvc {

    @Autowired
    private EmployeeSysResRoleMasterMapper mapper;

    @Override
    protected EmployeeSysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员系统资源角色表";
    }
}
