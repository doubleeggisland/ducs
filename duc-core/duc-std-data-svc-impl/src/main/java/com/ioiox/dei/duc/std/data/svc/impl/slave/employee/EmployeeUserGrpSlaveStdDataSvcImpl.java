package com.ioiox.dei.duc.std.data.svc.impl.slave.employee;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.EmployeeUserGrp;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.UserGrpQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeUserGrpQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeRoleSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeSysResRoleSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeUserGrpSlaveVO;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeUserGrpR2RoleSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeUserGrpR2SysResRoleSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeUserGrpSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseUserGrpSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.employee.EmployeeRoleSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.employee.EmployeeSysResRoleSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.employee.EmployeeUserGrpSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("employeeUserGrpSlaveStdDataSvc")
public class EmployeeUserGrpSlaveStdDataSvcImpl
        extends BaseUserGrpSlaveStdDataSvc<EmployeeRoleSlaveVO, EmployeeSysResRoleSlaveVO, EmployeeUserGrpSlaveVO, EmployeeUserGrp, EmployeeUserGrpQueryParam>
        implements EmployeeUserGrpSlaveStdDataSvc {

    @Autowired
    @Qualifier("employeeUserGrpSlaveDbSvc")
    private EmployeeUserGrpSlaveDbSvc employeeUserGrpSlaveDbSvc;

    @Autowired
    @Qualifier("employeeUserGrpR2RoleSlaveDbSvc")
    private EmployeeUserGrpR2RoleSlaveDbSvc employeeUserGrpR2RoleSlaveDbSvc;

    @Autowired
    @Qualifier("employeeUserGrpR2SysResRoleSlaveDbSvc")
    private EmployeeUserGrpR2SysResRoleSlaveDbSvc employeeUserGrpR2SysResRoleSlaveDbSvc;

    @Autowired
    @Qualifier("employeeRoleSlaveStdDataSvc")
    private EmployeeRoleSlaveStdDataSvc employeeRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("employeeSysResRoleSlaveStdDataSvc")
    private EmployeeSysResRoleSlaveStdDataSvc employeeSysResRoleSlaveStdDataSvc;

    @Override
    public EmployeeUserGrpSlaveVO queryByPk(final Long pk,
                                            final UserGrpQueryCfg queryCfg) {
        if (Objects.isNull(pk)) {
            return null;
        }
        final List<EmployeeUserGrpSlaveVO> userGrps =
                queryByPks(Collections.singletonList(pk), queryCfg);
        if (DeiCollectionUtil.isEmpty(userGrps)) {
            return null;
        }
        return userGrps.get(0);
    }

    @Override
    public List<EmployeeUserGrpSlaveVO> queryByPks(final List<Long> pks,
                                                   final UserGrpQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(pks)) {
            return Collections.emptyList();
        }
        final EmployeeUserGrpQueryParam queryParam = new EmployeeUserGrpQueryParam.Builder()
                .pks(pks)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return employeeUserGrpSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<EmployeeUserGrp> findByParams(final Map<String, Object> queryParams, final List<String> showColumns) {
        return employeeUserGrpSlaveDbSvc.findByParams(queryParams, showColumns);
    }

    @Override
    protected Map<Long, List<Long>> getRoleIds(final List<Long> userGrpIds) {
        return employeeUserGrpR2RoleSlaveDbSvc.getGroupedRoleIds(userGrpIds);
    }

    @Override
    protected List<EmployeeRoleSlaveVO> queryRolesByPks(final List<Long> roleIds, final RoleQueryCfg queryCfg) {
        return employeeRoleSlaveStdDataSvc.queryByPKs(roleIds, queryCfg);
    }

    @Override
    protected Map<Long, List<Long>> getSysResRoleIds(final List<Long> userGrpIds) {
        return employeeUserGrpR2SysResRoleSlaveDbSvc.getGroupedSysResRoleIds(userGrpIds);
    }

    @Override
    protected List<EmployeeSysResRoleSlaveVO> querySysResRolesByPks(final List<Long> sysResRoleIds,
                                                                    final SysResRoleQueryCfg queryCfg) {
        return employeeSysResRoleSlaveStdDataSvc.queryByPks(sysResRoleIds, queryCfg);
    }

    @Override
    public EmployeeUserGrpSlaveVO transferToStdDataVO(final EmployeeUserGrp entity) {
        final EmployeeUserGrpSlaveVO userGrp = new EmployeeUserGrpSlaveVO();
        assembleCommonAttrs(userGrp, entity);
        return userGrp;
    }
}
