package com.ioiox.dei.duc.std.data.svc.impl.slave.employee;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.EmployeeTmpSysResRole;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeTmpSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeTmpSysResRoleSlaveVO;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeTmpSysResRoleR2ResSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeTmpSysResRoleSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseSysResRoleSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.employee.EmployeeTmpSysResRoleSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("employeeTmpSysResRoleSlaveStdDataSvc")
public class EmployeeTmpSysResRoleSlaveStdDataSvcImpl
        extends BaseSysResRoleSlaveStdDataSvc<EmployeeTmpSysResRoleSlaveVO, EmployeeTmpSysResRole, EmployeeTmpSysResRoleQueryParam>
        implements EmployeeTmpSysResRoleSlaveStdDataSvc {

    @Autowired
    @Qualifier("employeeTmpSysResRoleSlaveDbSvc")
    private EmployeeTmpSysResRoleSlaveDbSvc employeeTmpSysResRoleSlaveDbSvc;

    @Autowired
    @Qualifier("employeeTmpSysResRoleR2ResSlaveDbSvc")
    private EmployeeTmpSysResRoleR2ResSlaveDbSvc employeeTmpSysResRoleR2ResSlaveDbSvc;

    @Override
    public EmployeeTmpSysResRoleSlaveVO queryByPk(final Long pk,
                                                  final SysResRoleQueryCfg queryCfg) {
        if (Objects.isNull(pk)) {
            return null;
        }
        final List<EmployeeTmpSysResRoleSlaveVO> tmpSysResRoles =
                queryByPks(Collections.singletonList(pk), queryCfg);
        if (DeiCollectionUtil.isEmpty(tmpSysResRoles)) {
            return null;
        }
        return tmpSysResRoles.get(0);
    }

    @Override
    public List<EmployeeTmpSysResRoleSlaveVO> queryByPks(final List<Long> pks,
                                                         final SysResRoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(pks)) {
            return Collections.emptyList();
        }
        final EmployeeTmpSysResRoleQueryParam queryParam = new EmployeeTmpSysResRoleQueryParam.Builder()
                .pks(pks)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return employeeTmpSysResRoleSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<EmployeeTmpSysResRole> findByParams(final Map<String, Object> queryParams, final List<String> showColumns) {
        return employeeTmpSysResRoleSlaveDbSvc.findByParams(queryParams, showColumns);
    }

    @Override
    protected Map<Long, List<Long>> getSysResIds(final List<Long> tmpSysResRoleIds) {
        return employeeTmpSysResRoleR2ResSlaveDbSvc.getGroupedSysResIds(tmpSysResRoleIds);
    }

    @Override
    public EmployeeTmpSysResRoleSlaveVO transferToStdDataVO(final EmployeeTmpSysResRole entity) {
        final EmployeeTmpSysResRoleSlaveVO tmpSysResRole = new EmployeeTmpSysResRoleSlaveVO();
        assembleSimpleTmpRoleAttrs(tmpSysResRole, entity);
        return tmpSysResRole;
    }
}
