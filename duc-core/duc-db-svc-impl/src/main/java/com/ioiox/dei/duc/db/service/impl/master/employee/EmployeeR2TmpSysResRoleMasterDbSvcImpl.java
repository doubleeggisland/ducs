package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeR2SysResRole;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeR2TmpSysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeR2TmpSysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeR2TmpSysResRoleMasterDbSvc")
public class EmployeeR2TmpSysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<EmployeeR2SysResRole, EmployeeR2TmpSysResRoleMasterMapper>
        implements EmployeeR2TmpSysResRoleMasterDbSvc {

    @Autowired
    private EmployeeR2TmpSysResRoleMasterMapper mapper;

    @Override
    protected EmployeeR2TmpSysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员与临时系统资源角色关联表";
    }
}