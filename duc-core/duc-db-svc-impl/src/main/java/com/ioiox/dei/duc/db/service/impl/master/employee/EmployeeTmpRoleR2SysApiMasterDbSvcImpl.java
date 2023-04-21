package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeTmpRoleR2SysApiMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeTmpRoleR2SysApiMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("employeeTmpRoleR2SysApiMasterDbSvc")
public class EmployeeTmpRoleR2SysApiMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2SysApi, EmployeeTmpRoleR2SysApiMasterMapper>
        implements EmployeeTmpRoleR2SysApiMasterDbSvc {

    @Autowired
    private EmployeeTmpRoleR2SysApiMasterMapper mapper;

    @Override
    public int save(final List<Long> sysApiSids, final Long tmpRoleSid, final String operator, final Date operateTime) {
        return dbInsert(RoleR2SysApi.instances(sysApiSids, tmpRoleSid, operator, operateTime));
    }

    @Override
    protected EmployeeTmpRoleR2SysApiMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员临时角色与系统接口关联表";
    }
}
