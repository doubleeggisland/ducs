package com.ioiox.dei.duc.std.data.svc.impl.slave.employee;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.Employee;
import com.ioiox.dei.duc.beans.model.slave.*;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.UserSysPrjPrivilegeSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.*;
import com.ioiox.dei.duc.db.service.slave.employee.*;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseUserSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.UserSysPrjPrivilegeSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.employee.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("employeeSlaveStdDataSvc")
public class EmployeeSlaveStdDataSvcImpl
        extends BaseUserSlaveStdDataSvc<EmployeeRoleSlaveVO, EmployeeSysResRoleSlaveVO, EmployeeTmpRoleSlaveVO, EmployeeTmpSysResRoleSlaveVO, EmployeeUserGrpSlaveVO, EmployeeSlaveVO, Employee, EmployeeQueryParam>
        implements EmployeeSlaveStdDataSvc {

    @Autowired
    @Qualifier("employeeSlaveDbSvc")
    private EmployeeSlaveDbSvc employeeSlaveDbSvc;

    @Autowired
    @Qualifier("employeeR2RoleSlaveDbSvc")
    private EmployeeR2RoleSlaveDbSvc employeeR2RoleSlaveDbSvc;

    @Autowired
    @Qualifier("employeeR2SysResRoleSlaveDbSvc")
    private EmployeeR2SysResRoleSlaveDbSvc employeeR2SysResRoleSlaveDbSvc;

    @Autowired
    @Qualifier("employeeR2TmpRoleSlaveDbSvc")
    private EmployeeR2TmpRoleSlaveDbSvc employeeR2TmpRoleSlaveDbSvc;

    @Autowired
    @Qualifier("employeeR2TmpSysResRoleSlaveDbSvc")
    private EmployeeR2TmpSysResRoleSlaveDbSvc employeeR2TmpSysResRoleSlaveDbSvc;

    @Autowired
    @Qualifier("employeeUserGrpR2UserSlaveDbSvc")
    private EmployeeUserGrpR2UserSlaveDbSvc employeeUserGrpR2UserSlaveDbSvc;

    @Autowired
    @Qualifier("employeeRoleSlaveStdDataSvc")
    private EmployeeRoleSlaveStdDataSvc employeeRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("employeeSysResRoleSlaveStdDataSvc")
    private EmployeeSysResRoleSlaveStdDataSvc employeeSysResRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("employeeTmpRoleSlaveStdDataSvc")
    private EmployeeTmpRoleSlaveStdDataSvc employeeTmpRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("employeeTmpSysResRoleSlaveStdDataSvc")
    private EmployeeTmpSysResRoleSlaveStdDataSvc employeeTmpSysResRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("employeeUserGrpSlaveStdDataSvc")
    private EmployeeUserGrpSlaveStdDataSvc employeeUserGrpSlaveStdDataSvc;

    @Autowired
    @Qualifier("employeeSysPrjPrivilegeSlaveStdDataSvc")
    private UserSysPrjPrivilegeSlaveStdDataSvc employeeSysPrjPrivilegeSlaveStdDataSvc;

    @Override
    public EmployeeSlaveVO queryByPk(final Long pk,
                                     final UserQueryCfg queryCfg) {
        if (Objects.isNull(pk)) {
            return null;
        }
        final List<EmployeeSlaveVO> employees =
                queryByPks(Collections.singletonList(pk), queryCfg);
        if (DeiCollectionUtil.isEmpty(employees)) {
            return null;
        }
        return employees.get(0);
    }

    @Override
    public List<EmployeeSlaveVO> queryByPks(final List<Long> pks,
                                            final UserQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(pks)) {
            return Collections.emptyList();
        }
        final EmployeeQueryParam queryParam = new EmployeeQueryParam.Builder()
                .pks(pks)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return employeeSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<Employee> findByParams(final Map<String, Object> queryParams, final List<String> showColumns) {
        return employeeSlaveDbSvc.findByParams(queryParams, showColumns);
    }

    @Override
    protected Map<Long, List<Long>> getRoleIds(final List<Long> employeeIds) {
        return employeeR2RoleSlaveDbSvc.getGroupedRoleIds(employeeIds);
    }

    @Override
    protected List<EmployeeRoleSlaveVO> queryRolesByPks(final List<Long> roleIds, final RoleQueryCfg queryCfg) {
        return employeeRoleSlaveStdDataSvc.queryByPKs(roleIds, queryCfg);
    }

    @Override
    protected Map<Long, List<Long>> getSysResRoleIds(final List<Long> employeeIds) {
        return employeeR2SysResRoleSlaveDbSvc.getGroupedSysResRoleIds(employeeIds);
    }

    @Override
    protected List<EmployeeSysResRoleSlaveVO> querySysResRolesByPks(final List<Long> sysResRoleIds, final SysResRoleQueryCfg queryCfg) {
        return employeeSysResRoleSlaveStdDataSvc.queryByPks(sysResRoleIds, queryCfg);
    }

    @Override
    protected Map<Long, List<Long>> getTmpRoleIds(List<Long> employeeIds) {
        return employeeR2TmpRoleSlaveDbSvc.getGroupedTmpRoleIds(employeeIds);
    }

    @Override
    protected List<EmployeeTmpRoleSlaveVO> queryTmpRolesByPks(List<Long> tmpRoleIds, RoleQueryCfg queryCfg) {
        return employeeTmpRoleSlaveStdDataSvc.queryByPks(tmpRoleIds, queryCfg);
    }

    @Override
    protected Map<Long, List<Long>> getTmpSysResRoleIds(final List<Long> employeeIds) {
        return employeeR2TmpSysResRoleSlaveDbSvc.getGroupedTmpSysResRoleIds(employeeIds);
    }

    @Override
    protected List<EmployeeTmpSysResRoleSlaveVO> queryTmpSysResRolesByPks(final List<Long> tmpSysResRoleIds, final SysResRoleQueryCfg queryCfg) {
        return employeeTmpSysResRoleSlaveStdDataSvc.queryByPks(tmpSysResRoleIds, queryCfg);
    }

    @Override
    protected Map<Long, List<Long>> getUserGrpIds(List<Long> employeeIds) {
        return employeeUserGrpR2UserSlaveDbSvc.getGroupedUserGrpIds(employeeIds);
    }

    @Override
    protected List<EmployeeUserGrpSlaveVO> queryUserGrpsByPks(final List<Long> userGrpIds, final UserGrpQueryCfg queryCfg) {
        return employeeUserGrpSlaveStdDataSvc.queryByPks(userGrpIds, queryCfg);
    }

    @Override
    protected List<UserSysPrjPrivilegeSlaveVO> querySysPrjPrivilegesByUserIds(final List<Long> employeeIds, final UserSysPrjPrivilegeQueryCfg queryCfg) {
        return employeeSysPrjPrivilegeSlaveStdDataSvc.queryByUserIds(employeeIds, queryCfg);
    }

    @Override
    public EmployeeSlaveVO transferToStdDataVO(final Employee entity) {
        final EmployeeSlaveVO employee = new EmployeeSlaveVO();
        assembleCommonAttrs(employee, entity);
        employee.setRealName(entity.getRealName());
        if (Objects.nonNull(entity.getDateOfBirth())) {
            employee.setDateOfBirth(entity.getDateOfBirth().getTime());
        }
        employee.setGender(entity.getGender());
        return employee;
    }

    @Override
    public int countByParam(com.ioiox.dei.duc.beans.model.slave.employee.EmployeeQueryParam queryParam) {
        return 0;
    }

    @Override
    public List<EmployeeSlaveVO> queryByParam(com.ioiox.dei.duc.beans.model.slave.employee.EmployeeQueryParam queryParam, UserQueryCfg queryCfg) {
        return null;
    }
}
