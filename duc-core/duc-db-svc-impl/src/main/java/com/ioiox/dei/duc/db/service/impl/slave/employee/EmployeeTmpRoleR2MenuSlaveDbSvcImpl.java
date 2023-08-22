package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.model.std.data.QueryConditionsHolder;
import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiRelationshipEntitySlaveDbSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeTmpRoleR2MenuSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeTmpRoleR2MenuSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("employeeTmpRoleR2MenuSlaveDbSvc")
public class EmployeeTmpRoleR2MenuSlaveDbSvcImpl
        extends BaseDeiRelationshipEntitySlaveDbSvc<Long, Long, RoleR2Menu, EmployeeTmpRoleR2MenuSlaveMapper>
        implements EmployeeTmpRoleR2MenuSlaveDbSvc {

    @Autowired
    private EmployeeTmpRoleR2MenuSlaveMapper mapper;

    @Override
    public Map<Long, List<Long>> getGroupedMenuIds(final List<Long> tmpRoleIds) {
        if (DeiCollectionUtil.isEmpty(tmpRoleIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (tmpRoleIds.size() > 1) {
            conditionsHolder.addQueryCondition("roleSids", tmpRoleIds);
        } else {
            conditionsHolder.addQueryCondition("roleSid", tmpRoleIds.get(0));
        }
        return getUniqueKeysGroupedBy1st(conditionsHolder.queryParams(),
                Arrays.asList(RoleR2Menu.ShowColumn.ROLE_SID.getCode(), RoleR2Menu.ShowColumn.MENU_SID.getCode()));
    }

    @Override
    public Map<Long, List<Long>> getGroupedTmpRoleIds(final List<Long> menuIds) {
        if (DeiCollectionUtil.isEmpty(menuIds)) {
            return Collections.emptyMap();
        }
        final QueryConditionsHolder conditionsHolder = new QueryConditionsHolder();
        if (menuIds.size() > 1) {
            conditionsHolder.addQueryCondition("menuSids", menuIds);
        } else {
            conditionsHolder.addQueryCondition("menuSid", menuIds.get(0));
        }
        return getUniqueKeysGroupedBy2nd(conditionsHolder.queryParams(),
                Arrays.asList(RoleR2Menu.ShowColumn.ROLE_SID.getCode(), RoleR2Menu.ShowColumn.MENU_SID.getCode()));
    }

    @Override
    protected EmployeeTmpRoleR2MenuSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员临时角色与菜单关联表";
    }
}
