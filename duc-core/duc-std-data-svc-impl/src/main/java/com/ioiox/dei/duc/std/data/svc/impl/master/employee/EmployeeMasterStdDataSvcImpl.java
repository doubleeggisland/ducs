package com.ioiox.dei.duc.std.data.svc.impl.master.employee;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.BaseUser;
import com.ioiox.dei.duc.beans.entity.Employee;
import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeDelParam;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.employee.EmployeeUpdateCtx;
import com.ioiox.dei.duc.beans.model.slave.*;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeQueryParam;
import com.ioiox.dei.duc.beans.vo.std.master.UserSysPrjPrivilegeMasterVO;
import com.ioiox.dei.duc.beans.vo.std.master.employee.EmployeeMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.UserSysPrjPrivilegeSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeSlaveVO;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseUserMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.UserSysPrjPrivilegeMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.employee.EmployeeMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.employee.EmployeeSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("employeeMasterStdDataSvc")
public class EmployeeMasterStdDataSvcImpl
        extends BaseUserMasterStdDataSvc<EmployeeMasterVO, EmployeeUpdatableObj, EmployeeUpdateCtx, EmployeeDelParam, EmployeeSlaveVO, Employee>
        implements EmployeeMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(EmployeeMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("employeeMasterDbSvc")
    private EmployeeMasterDbSvc employeeMasterDbSvc;

    @Autowired
    @Qualifier("employeeSysPrjPrivilegeMasterStdDataSvc")
    private UserSysPrjPrivilegeMasterStdDataSvc employeeSysPrjPrivilegeMasterStdDataSvc;

    @Autowired
    @Qualifier("employeeSlaveStdDataSvc")
    private EmployeeSlaveStdDataSvc employeeSlaveStdDataSvc;

    private final EmployeeUpdatableAttrsAnalyser updatableAttrsAnalyser = new EmployeeUpdatableAttrsAnalyser();

    @Override
    protected EmployeeSlaveVO getExistingUser(final Long id) {
        return employeeSlaveStdDataSvc.queryByPk(id,
                new UserQueryCfg.Builder()
                        .needUserGrps(DeiGlobalConstant.FLAG_YES)
                        .userGrpQueryCfg(new UserGrpQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .needSysPrjPrivileges(DeiGlobalConstant.FLAG_YES)
                        .sysPrjPrivilegeQueryCfg(new UserSysPrjPrivilegeQueryCfg.Builder()
                                .showColumns(Arrays.asList(BaseDeiEntity.ShowColumn.ID.getCode(),
                                        UserSysPrjPrivilege.ShowColumn.USER_SID.getCode(), UserSysPrjPrivilege.ShowColumn.SYS_PRJ_SID.getCode(),
                                        UserSysPrjPrivilege.ShowColumn.ACCESS_CONDITION.getCode(), BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                                .build())
                        .needRoles(DeiGlobalConstant.FLAG_YES)
                        .roleQueryCfg(new RoleQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .needSysResRoles(DeiGlobalConstant.FLAG_YES)
                        .sysResRoleQueryCfg(new SysResRoleQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .needTmpRoles(DeiGlobalConstant.FLAG_YES)
                        .tmpRoleQueryCfg(new RoleQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .needTmpSysResRoles(DeiGlobalConstant.FLAG_YES)
                        .tmpSysResRoleQueryCfg(new SysResRoleQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .showColumns(Arrays.asList(BaseDeiEntity.ShowColumn.ID.getCode(),
                                BaseUser.ShowColumn.USER_NAME.getCode(), BaseUser.ShowColumn.NICK_NAME.getCode(),
                                BaseUser.ShowColumn.MOBILE.getCode(), BaseUser.ShowColumn.EMAIL.getCode(),
                                BaseUser.ShowColumn.STATUS.getCode(), BaseUser.ShowColumn.PWD.getCode(),
                                BaseUser.ShowColumn.AVATAR_URL.getCode(), BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                        .build());
    }

    @Override
    protected List<EmployeeSlaveVO> queryExistingUsers(final EmployeeDelParam delParam) {
        final EmployeeQueryParam queryParam = new EmployeeQueryParam.Builder()
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return employeeSlaveStdDataSvc.queryByParam(queryParam,
                new UserQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build());
    }

    @Override
    protected int syncSysPrjPrivileges(final List<UserSysPrjPrivilegeMasterVO> sysPrjPrivileges,
                                       final List<UserSysPrjPrivilegeSlaveVO> existingSysPrjPrivileges) {
        return employeeSysPrjPrivilegeMasterStdDataSvc.sync(sysPrjPrivileges, existingSysPrjPrivileges);
    }

    @Override
    protected int removeSysPrjPrivileges(final List<Long> employeeIds) {
        final int numOfSysPrjPrivilegesRemoved =
                employeeSysPrjPrivilegeMasterStdDataSvc.removeByUserIds(employeeIds);
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysPrjPrivileges from Employees =====> employeeIds: %s, numOfSysPrjPrivilegesRemoved: %s" ,
                    JsonUtil.toJsonStr(employeeIds), numOfSysPrjPrivilegesRemoved));
        }
        return numOfSysPrjPrivilegesRemoved;
    }

    @Override
    protected int assignUserGrpsToUser(final List<Long> userGrpIds, final Long employeeId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign userGrps to Employee =====> userGrpIds: %s, employeeId: %s",
                    JsonUtil.toJsonStr(userGrpIds), employeeId));
        }
        return employeeMasterDbSvc.assignUserGrpsToUser(userGrpIds, employeeId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeUserGrpsFromUser(final List<Long> userGrpIds, final Long employeeId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove userGrps from Employee =====> userGrpIds: %s, employeeId: %s",
                    JsonUtil.toJsonStr(userGrpIds), employeeId));
        }
        return employeeMasterDbSvc.removeUserGrpsFromUsers(userGrpIds, Collections.singletonList(employeeId));
    }

    @Override
    protected int removeUserGrpsFromUsers(final List<Long> employeeIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove userGrps from Employees ======> employeeIds: %s", JsonUtil.toJsonStr(employeeIds)));
        }
        return employeeMasterDbSvc.removeUserGrpsFromUsers(null, employeeIds);
    }

    @Override
    protected int assignRolesToUser(final List<Long> roleIds, final Long employeeId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign roles to Employee =====> roleIds: %s, employeeId: %s",
                    JsonUtil.toJsonStr(roleIds), employeeId));
        }
        return employeeMasterDbSvc.assignRolesToUser(roleIds, employeeId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeRolesFromUser(final List<Long> roleIds, final Long employeeId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove roles from Employee =====> roleIds: %s, employeeId: %s",
                    JsonUtil.toJsonStr(roleIds), employeeId));
        }
        return employeeMasterDbSvc.removeRolesFromUsers(roleIds, Collections.singletonList(employeeId));
    }

    @Override
    protected int removeRolesFromUsers(final List<Long> employeeIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove roles from Employees ======> employeeIds: %s", JsonUtil.toJsonStr(employeeIds)));
        }
        return employeeMasterDbSvc.removeRolesFromUsers(null, employeeIds);
    }

    @Override
    protected int assignSysResRolesToUser(final List<Long> sysResRoleIds, final Long employeeId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign sysResRoles to Employee =====> sysResRoleIds: %s, employeeId: %s",
                    JsonUtil.toJsonStr(sysResRoleIds), employeeId));
        }
        return employeeMasterDbSvc.assignSysResRolesToUser(sysResRoleIds, employeeId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeSysResRolesFromUser(final List<Long> sysResRoleIds, final Long employeeId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResRoles from Employee =====> sysResRoleIds: %s, employeeId: %s",
                    JsonUtil.toJsonStr(sysResRoleIds), employeeId));
        }
        return employeeMasterDbSvc.removeSysResRolesFromUsers(sysResRoleIds, Collections.singletonList(employeeId));
    }

    @Override
    protected int removeSysResRolesFromUsers(final List<Long> employeeIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResRoles from Employees ======> employeeIds: %s", JsonUtil.toJsonStr(employeeIds)));
        }
        return employeeMasterDbSvc.removeSysResRolesFromUsers(null, employeeIds);
    }

    @Override
    protected int assignTmpRolesToUser(final List<Long> tmpRoleIds, final Long employeeId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign tmpRoles to Employee =====> tmpRoleIds: %s, employeeId: %s",
                    JsonUtil.toJsonStr(tmpRoleIds), employeeId));
        }
        return employeeMasterDbSvc.assignTmpRolesToUser(tmpRoleIds, employeeId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeTmpRolesFromUser(final List<Long> tmpRoleIds, final Long employeeId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove tmpRoles from Employee =====> tmpRoleIds: %s, employeeId: %s",
                    JsonUtil.toJsonStr(tmpRoleIds), employeeId));
        }
        return employeeMasterDbSvc.removeTmpRolesFromUsers(tmpRoleIds, Collections.singletonList(employeeId));
    }

    @Override
    protected int removeTmpRolesFromUsers(final List<Long> employeeIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove tmpRoles from Employees ======> employeeIds: %s", JsonUtil.toJsonStr(employeeIds)));
        }
        return employeeMasterDbSvc.removeTmpRolesFromUsers(null, employeeIds);
    }

    @Override
    protected int assignTmpSysResRolesToUser(final List<Long> tmpSysResRoleIds, final Long employeeId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign tmpSysResRoles to Employee =====> tmpSysResRoleIds: %s, employeeId: %s",
                    JsonUtil.toJsonStr(tmpSysResRoleIds), employeeId));
        }
        return employeeMasterDbSvc.assignTmpSysResRolesToUser(tmpSysResRoleIds, employeeId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeTmpSysResRolesFromUser(final List<Long> tmpSysResRoleIds, final Long employeeId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove tmpSysResRoles from Employee =====> tmpSysResRoleIds: %s, employeeId: %s",
                    JsonUtil.toJsonStr(tmpSysResRoleIds), employeeId));
        }
        return employeeMasterDbSvc.removeTmpSysResRolesFromUsers(tmpSysResRoleIds, Collections.singletonList(employeeId));
    }

    @Override
    protected int removeTmpSysResRolesFromUsers(final List<Long> employeeIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove tmpSysResRoles from Employees ======> employeeIds: %s", JsonUtil.toJsonStr(employeeIds)));
        }
        return employeeMasterDbSvc.removeTmpSysResRolesFromUsers(null, employeeIds);
    }

    @Override
    protected EmployeeUpdateCtx getUpdateContext(final EmployeeMasterVO employee, final EmployeeSlaveVO existingEmployee) {
        return updatableAttrsAnalyser.analyseUpdatedAttrs(employee, existingEmployee);
    }

    @Override
    protected void doSave(final Employee newEntity) {
        employeeMasterDbSvc.dbInsert(newEntity);
    }

    @Override
    protected int doUpdate(final Employee example) {
        return employeeMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return employeeMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    public Employee toNewEntity(final EmployeeMasterVO employee) {
        final Employee newEntity = new Employee();
        assembleCommonAttrsOnInsert(newEntity, employee);
        assembleCommonAttrs(newEntity, employee);
        newEntity.setRealName(employee.getRealName());
        if (Objects.nonNull(employee.getDateOfBirth())) {
            newEntity.setDateOfBirth(new Date(employee.getDateOfBirth()));
        }
        newEntity.setGender(employee.getGender());
        return newEntity;
    }

    @Override
    public Employee toUpdatableObj(final EmployeeUpdatableObj updatableObj) {
        final Employee example = new Employee();
        assembleCommonUpdatableAttrs(example, updatableObj);
        if (Objects.nonNull(updatableObj.getRealName())) {
            example.setRealName(updatableObj.getRealName().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getDateOfBirth())) {
            example.setDateOfBirth(updatableObj.getDateOfBirth().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getGender())) {
            example.setGender(updatableObj.getGender().getNewVal());
        }
        return example;
    }
}
