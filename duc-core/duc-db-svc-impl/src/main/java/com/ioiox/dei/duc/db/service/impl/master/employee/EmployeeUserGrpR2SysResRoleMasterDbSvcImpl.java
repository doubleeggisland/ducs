package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2SysResRole;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeUserGrpR2SysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeUserGrpR2SysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("employeeUserGrpR2SysResRoleMasterDbSvc")
public class EmployeeUserGrpR2SysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserGrpR2SysResRole, EmployeeUserGrpR2SysResRoleMasterMapper>
        implements EmployeeUserGrpR2SysResRoleMasterDbSvc {

    @Autowired
    private EmployeeUserGrpR2SysResRoleMasterMapper mapper;

    @Override
    public int save(final List<Long> sysResRoleSids, final Long userGrpSid, final String operator, final Date operateTime) {
        return dbInsert(UserGrpR2SysResRole.instances(sysResRoleSids, userGrpSid, operator, operateTime));
    }

    @Override
    protected EmployeeUserGrpR2SysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员用户组与系统资源角色关联表";
    }
}
