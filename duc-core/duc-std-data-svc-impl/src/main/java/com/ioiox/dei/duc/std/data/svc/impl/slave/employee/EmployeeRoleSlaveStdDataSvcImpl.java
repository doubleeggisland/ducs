package com.ioiox.dei.duc.std.data.svc.impl.slave.employee;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.EmployeeRole;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSysApiMappingSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.SysApiSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeRoleSlaveVO;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeRoleR2MenuSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeRoleR2MenuSysApiSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeRoleR2SysApiSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeRoleSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseRoleSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.employee.EmployeeRoleSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("employeeRoleSlaveStdDataSvc")
public class EmployeeRoleSlaveStdDataSvcImpl
        extends BaseRoleSlaveStdDataSvc<EmployeeRoleSlaveVO, EmployeeRole, EmployeeRoleQueryParam>
        implements EmployeeRoleSlaveStdDataSvc {

    @Autowired
    @Qualifier("employeeRoleSlaveDbSvc")
    private EmployeeRoleSlaveDbSvc employeeRoleSlaveDbSvc;

    @Autowired
    @Qualifier("employeeRoleR2MenuSlaveDbSvc")
    private EmployeeRoleR2MenuSlaveDbSvc employeeRoleR2MenuSlaveDbSvc;

    @Autowired
    @Qualifier("employeeRoleR2MenuSysApiSlaveDbSvc")
    private EmployeeRoleR2MenuSysApiSlaveDbSvc employeeRoleR2MenuSysApiSlaveDbSvc;

    @Autowired
    @Qualifier("employeeRoleR2SysApiSlaveDbSvc")
    private EmployeeRoleR2SysApiSlaveDbSvc employeeRoleR2SysApiSlaveDbSvc;

    @Override
    public EmployeeRoleSlaveVO queryByPk(final Long pk, final RoleQueryCfg queryCfg) {
        if (Objects.isNull(pk)) {
            return null;
        }
        final List<EmployeeRoleSlaveVO> userAcctRoles = queryByPKs(Collections.singletonList(pk), queryCfg);
        if (DeiCollectionUtil.isEmpty(userAcctRoles)) {
            return null;
        }
        return userAcctRoles.get(0);
    }

    @Override
    public List<EmployeeRoleSlaveVO> queryByPKs(final List<Long> pks, final RoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(pks)) {
            return Collections.emptyList();
        }
        final EmployeeRoleQueryParam queryParam = new EmployeeRoleQueryParam.Builder()
                .pks(pks)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return employeeRoleSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<EmployeeRole> findByParams(final Map<String, Object> queryParams, final List<String> showColumns) {
        return employeeRoleSlaveDbSvc.findByParams(queryParams, showColumns);
    }

    @Override
    protected Map<Long, List<Long>> getMenuIds(final List<Long> roleIds) {
        return employeeRoleR2MenuSlaveDbSvc.getGroupedMenuIds(roleIds);
    }

    @Override
    protected Map<Long, List<Long>> getSysApiMappingIds(final List<Long> roleIds) {
        return employeeRoleR2MenuSysApiSlaveDbSvc.getGroupedMappingSids(roleIds);
    }

    @Override
    protected Map<Long, List<Long>> getSysApiIds(final List<Long> roleIds) {
        return employeeRoleR2SysApiSlaveDbSvc.getGroupedSysApiSids(roleIds);
    }

    @Override
    protected void assembleMenus(final EmployeeRoleSlaveVO role, final List<MenuSlaveVO> menus) {
        role.setMenus(menus);
    }

    @Override
    protected void assembleSysApiMappings(final EmployeeRoleSlaveVO role, final List<MenuSysApiMappingSlaveStdVO> sysApiMappings) {
        if (DeiCollectionUtil.isEmpty(sysApiMappings)) {
            role.setSysApiMappings(Collections.emptyMap());
        } else {
            role.setSysApiMappings(sysApiMappings.stream()
                    .collect(Collectors.groupingBy(MenuSysApiMappingSlaveStdVO::getMenuId)));
        }
    }

    @Override
    protected void assembleMenuSysApis(final EmployeeRoleSlaveVO role, final List<SysApiSlaveVO> menuSysApis) {
        role.setMenuSysApis(menuSysApis);
    }

    @Override
    protected void assembleSysApis(final EmployeeRoleSlaveVO role, final List<SysApiSlaveVO> sysApis) {
        role.setSysApis(sysApis);
    }

    @Override
    public EmployeeRoleSlaveVO transferToStdDataVO(final EmployeeRole entity) {
        final EmployeeRoleSlaveVO role = new EmployeeRoleSlaveVO();
        assembleRoleAttrs(role, entity);
        return role;
    }
}
