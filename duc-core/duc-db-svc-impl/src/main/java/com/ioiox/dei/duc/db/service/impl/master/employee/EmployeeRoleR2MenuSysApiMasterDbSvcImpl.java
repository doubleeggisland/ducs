package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2MenuSysApi;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeRoleR2MenuSysApiMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeRoleR2MenuSysApiMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("employeeRoleR2MenuSysApiMasterDbSvc")
public class EmployeeRoleR2MenuSysApiMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2MenuSysApi, EmployeeRoleR2MenuSysApiMasterMapper>
        implements EmployeeRoleR2MenuSysApiMasterDbSvc {

    @Autowired
    private EmployeeRoleR2MenuSysApiMasterMapper mapper;

    @Override
    public int save(final List<Long> sysApiMappingSids, final Long roleSid, final String operator, final Date operateTime) {
        return dbInsert(RoleR2MenuSysApi.instances(sysApiMappingSids, roleSid, operator, operateTime));
    }

    @Override
    protected EmployeeRoleR2MenuSysApiMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员角色与菜单相关接口关联表";
    }
}
