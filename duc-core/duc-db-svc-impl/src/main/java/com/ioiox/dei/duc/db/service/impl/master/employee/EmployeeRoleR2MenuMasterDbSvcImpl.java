package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeRoleR2MenuMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeRoleR2MenuMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("employeeRoleR2MenuMasterDbSvc")
public class EmployeeRoleR2MenuMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2Menu, EmployeeRoleR2MenuMasterMapper>
        implements EmployeeRoleR2MenuMasterDbSvc {

    @Autowired
    private EmployeeRoleR2MenuMasterMapper mapper;

    @Override
    public int save(final List<Long> menuSids, final Long roleSid, final String operator, final Date operateTime) {
        return dbInsert(RoleR2Menu.instances(menuSids, roleSid, operator, operateTime));
    }

    @Override
    protected EmployeeRoleR2MenuMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员角色与菜单关联表";
    }
}
