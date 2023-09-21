package com.ioiox.dei.duc.std.data.svc.impl.slave.employee;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.EmployeeTmpRole;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeTmpRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeTmpRoleSlaveVO;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeTmpRoleR2MenuSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeTmpRoleR2MenuSysApiSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeTmpRoleR2SysApiSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeTmpRoleSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseRoleSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.employee.EmployeeTmpRoleSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("employeeTmpRoleSlaveStdDataSvc")
public class EmployeeTmpRoleSlaveStdDataSvcImpl
        extends BaseRoleSlaveStdDataSvc<EmployeeTmpRoleSlaveVO, EmployeeTmpRole, EmployeeTmpRoleQueryParam>
        implements EmployeeTmpRoleSlaveStdDataSvc {

    @Autowired
    @Qualifier("employeeTmpRoleSlaveDbSvc")
    private EmployeeTmpRoleSlaveDbSvc employeeTmpRoleSlaveDbSvc;

    @Autowired
    @Qualifier("employeeTmpRoleR2MenuSlaveDbSvc")
    private EmployeeTmpRoleR2MenuSlaveDbSvc employeeTmpRoleR2MenuSlaveDbSvc;

    @Autowired
    @Qualifier("employeeTmpRoleR2MenuSysApiSlaveDbSvc")
    private EmployeeTmpRoleR2MenuSysApiSlaveDbSvc employeeTmpRoleR2MenuSysApiSlaveDbSvc;

    @Autowired
    @Qualifier("employeeTmpRoleR2SysApiSlaveDbSvc")
    private EmployeeTmpRoleR2SysApiSlaveDbSvc employeeTmpRoleR2SysApiSlaveDbSvc;

    @Override
    public EmployeeTmpRoleSlaveVO queryByPk(final Long pk,
                                            final RoleQueryCfg queryCfg) {
        if (Objects.isNull(pk)) {
            return null;
        }
        final List<EmployeeTmpRoleSlaveVO> tmpRoles =
                queryByPks(Collections.singletonList(pk), queryCfg);
        if (DeiCollectionUtil.isEmpty(tmpRoles)) {
            return null;
        }
        return tmpRoles.get(0);
    }

    @Override
    public List<EmployeeTmpRoleSlaveVO> queryByPks(final List<Long> pks,
                                                   final RoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(pks)) {
            return Collections.emptyList();
        }
        final EmployeeTmpRoleQueryParam queryParam = new EmployeeTmpRoleQueryParam.Builder()
                .pks(pks)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return employeeTmpRoleSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<EmployeeTmpRole> findByParams(final Map<String, Object> queryParams, final List<String> showColumns) {
        return employeeTmpRoleSlaveDbSvc.findByParams(queryParams, showColumns);
    }

    @Override
    protected Map<Long, List<Long>> getMenuIds(final List<Long> tmpRoleIds) {
        return employeeTmpRoleR2MenuSlaveDbSvc.getGroupedMenuIds(tmpRoleIds);
    }

    @Override
    protected Map<Long, List<Long>> getSysApiMappingIds(final List<Long> tmpRoleIds) {
        return employeeTmpRoleR2MenuSysApiSlaveDbSvc.getGroupedMappingSids(tmpRoleIds);
    }

    @Override
    protected Map<Long, List<Long>> getSysApiIds(final List<Long> tmpRoleIds) {
        return employeeTmpRoleR2SysApiSlaveDbSvc.getGroupedSysApiSids(tmpRoleIds);
    }

    @Override
    public EmployeeTmpRoleSlaveVO transferToStdDataVO(final EmployeeTmpRole entity) {
        final EmployeeTmpRoleSlaveVO tmpRole = new EmployeeTmpRoleSlaveVO();
        assembleSimpleTmpRoleAttrs(tmpRole, entity);
        return tmpRole;
    }
}
