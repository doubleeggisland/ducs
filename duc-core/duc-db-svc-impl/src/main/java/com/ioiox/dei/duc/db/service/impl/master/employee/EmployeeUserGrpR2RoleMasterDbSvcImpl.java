package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2Role;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeUserGrpR2RoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeUserGrpR2RoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("employeeUserGrpR2RoleMasterDbSvc")
public class EmployeeUserGrpR2RoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserGrpR2Role, EmployeeUserGrpR2RoleMasterMapper>
        implements EmployeeUserGrpR2RoleMasterDbSvc {

    @Autowired
    private EmployeeUserGrpR2RoleMasterMapper mapper;

    @Override
    public int save(final List<Long> roleSids, final Long userGrpSid, final String operator, final Date operateTime) {
        return dbInsert(UserGrpR2Role.instances(roleSids, userGrpSid, operator, operateTime));
    }

    @Override
    protected EmployeeUserGrpR2RoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员用户组与角色关联表";
    }
}
