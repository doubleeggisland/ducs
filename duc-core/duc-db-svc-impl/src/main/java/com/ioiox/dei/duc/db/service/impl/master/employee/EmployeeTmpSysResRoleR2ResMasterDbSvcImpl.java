package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.SysResRoleR2Res;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeTmpSysResRoleR2ResMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeTmpSysResRoleR2ResMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeTmpSysResRoleR2ResMasterDbSvc")
public class EmployeeTmpSysResRoleR2ResMasterDbSvcImpl
        extends BaseDeiMasterDbService<SysResRoleR2Res, EmployeeTmpSysResRoleR2ResMasterMapper>
        implements EmployeeTmpSysResRoleR2ResMasterDbSvc {

    @Autowired
    private EmployeeTmpSysResRoleR2ResMasterMapper mapper;

    @Override
    protected EmployeeTmpSysResRoleR2ResMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员临时系统资源角色与资源关联表";
    }
}
