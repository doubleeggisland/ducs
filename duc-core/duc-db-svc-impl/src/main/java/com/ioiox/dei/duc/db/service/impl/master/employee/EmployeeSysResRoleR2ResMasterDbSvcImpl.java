package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.SysResRoleR2Res;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeSysResRoleR2ResMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeSysResRoleR2ResMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("employeeSysResRoleR2ResMasterDbSvc")
public class EmployeeSysResRoleR2ResMasterDbSvcImpl
        extends BaseDeiMasterDbService<SysResRoleR2Res, EmployeeSysResRoleR2ResMasterMapper>
        implements EmployeeSysResRoleR2ResMasterDbSvc {

    @Autowired
    private EmployeeSysResRoleR2ResMasterMapper mapper;

    @Override
    public int save(final List<Long> sysResSids, final Long sysResRoleSid, final String operator, final Date operateTime) {
        return dbInsert(SysResRoleR2Res.instances(sysResSids, sysResRoleSid, operator, operateTime));
    }

    @Override
    protected EmployeeSysResRoleR2ResMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员系统资源角色与资源关联表";
    }
}
