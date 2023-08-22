package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeTmpRoleR2MenuMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeTmpRoleR2MenuMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("employeeTmpRoleR2MenuMasterDbSvc")
public class EmployeeTmpRoleR2MenuMasterDbSvcImpl
        extends BaseDeiMasterDbService<RoleR2Menu, EmployeeTmpRoleR2MenuMasterMapper>
        implements EmployeeTmpRoleR2MenuMasterDbSvc {

    @Autowired
    private EmployeeTmpRoleR2MenuMasterMapper mapper;

    @Override
    public int save(final List<Long> menuSids, final Long tmpRoleSid, final String operator, final Date operateTime) {
        return dbInsert(RoleR2Menu.instances(menuSids, tmpRoleSid, operator, operateTime));
    }

    @Override
    protected EmployeeTmpRoleR2MenuMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员临时角色与菜单关联表";
    }
}
