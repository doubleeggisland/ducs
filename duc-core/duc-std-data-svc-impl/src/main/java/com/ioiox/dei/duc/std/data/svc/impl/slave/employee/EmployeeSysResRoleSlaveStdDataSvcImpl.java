package com.ioiox.dei.duc.std.data.svc.impl.slave.employee;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.EmployeeSysResRole;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeSysResRoleSlaveVO;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeSysResRoleR2ResSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeSysResRoleSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseSysResRoleSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.employee.EmployeeSysResRoleSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("employeeSysResRoleSlaveStdDataSvc")
public class EmployeeSysResRoleSlaveStdDataSvcImpl
        extends BaseSysResRoleSlaveStdDataSvc<EmployeeSysResRoleSlaveVO, EmployeeSysResRole, EmployeeSysResRoleQueryParam>
        implements EmployeeSysResRoleSlaveStdDataSvc {

    @Autowired
    @Qualifier("employeeSysResRoleSlaveDbSvc")
    private EmployeeSysResRoleSlaveDbSvc employeeSysResRoleSlaveDbSvc;

    @Autowired
    @Qualifier("employeeSysResRoleR2ResSlaveDbSvc")
    private EmployeeSysResRoleR2ResSlaveDbSvc employeeSysResRoleR2ResSlaveDbSvc;

    @Override
    public EmployeeSysResRoleSlaveVO queryByPk(final Long pk,
                                               final SysResRoleQueryCfg queryCfg) {
        if (Objects.isNull(pk)) {
            return null;
        }
        final List<EmployeeSysResRoleSlaveVO> sysResRoles =
                queryByPks(Collections.singletonList(pk), queryCfg);
        if (DeiCollectionUtil.isEmpty(sysResRoles)) {
            return null;
        }
        return sysResRoles.get(0);
    }

    @Override
    public List<EmployeeSysResRoleSlaveVO> queryByPks(final List<Long> pks,
                                                      final SysResRoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(pks)) {
            return Collections.emptyList();
        }
        final EmployeeSysResRoleQueryParam queryParam = new EmployeeSysResRoleQueryParam.Builder()
                .pks(pks)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return employeeSysResRoleSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<EmployeeSysResRole> findByParams(final Map<String, Object> queryParams, final List<String> showColumns) {
        return employeeSysResRoleSlaveDbSvc.findByParams(queryParams, showColumns);
    }

    @Override
    protected Map<Long, List<Long>> getSysResIds(final List<Long> sysResRoleIds) {
        return employeeSysResRoleR2ResSlaveDbSvc.getGroupedSysResIds(sysResRoleIds);
    }

    @Override
    public EmployeeSysResRoleSlaveVO transferToStdDataVO(final EmployeeSysResRole entity) {
        final EmployeeSysResRoleSlaveVO sysResRole = new EmployeeSysResRoleSlaveVO();
        assembleSimpleRoleAttrs(sysResRole, entity);
        return sysResRole;
    }
}
