package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DeleteConditionsHolder;
import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.Employee;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("employeeMasterDbSvc")
public class EmployeeMasterDbSvcImpl
        extends BaseDeiMasterDbService<Employee, EmployeeMasterMapper>
        implements EmployeeMasterDbSvc {

    @Autowired
    private EmployeeMasterMapper mapper;

    @Autowired
    @Qualifier("employeeUserGrpR2UserMasterDbSvc")
    private EmployeeUserGrpR2UserMasterDbSvc employeeUserGrpR2UserMasterDbSvc;

    @Autowired
    @Qualifier("employeeR2RoleMasterDbSvc")
    private EmployeeR2RoleMasterDbSvc employeeR2RoleMasterDbSvc;

    @Autowired
    @Qualifier("employeeR2SysResRoleMasterDbSvc")
    private EmployeeR2SysResRoleMasterDbSvc employeeR2SysResRoleMasterDbSvc;

    @Autowired
    @Qualifier("employeeR2TmpRoleMasterDbSvc")
    private EmployeeR2TmpRoleMasterDbSvc employeeR2TmpRoleMasterDbSvc;

    @Autowired
    @Qualifier("employeeR2TmpSysResRoleMasterDbSvc")
    private EmployeeR2TmpSysResRoleMasterDbSvc employeeR2TmpSysResRoleMasterDbSvc;

    @Override
    public int assignUserGrpsToUser(final List<Long> userGrpSids, final Long employeeSid, final String operator, final Date operateTime) {
        return employeeUserGrpR2UserMasterDbSvc.save(userGrpSids, employeeSid, operator, operateTime);
    }

    @Override
    public int removeUserGrpsFromUsers(final List<Long> userGrpSids, final List<Long> employeeSids) {
        if (DeiCollectionUtil.isEmpty(userGrpSids)
                && DeiCollectionUtil.isEmpty(employeeSids)) {
            return DeiGlobalConstant.ZERO;
        }
        final DeleteConditionsHolder conditionsHolder = new DeleteConditionsHolder();
        if (DeiCollectionUtil.isNotEmpty(userGrpSids)) {
            if (userGrpSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userGrpSids", userGrpSids);
            } else {
                conditionsHolder.addDeleteCondition("userGrpSid", userGrpSids.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(employeeSids)) {
            if (employeeSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userSids", employeeSids);
            } else {
                conditionsHolder.addDeleteCondition("userSid", employeeSids.get(0));
            }
        }
        return employeeUserGrpR2UserMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignRolesToUser(final List<Long> roleSids, final Long employeeSid, final String operator, final Date operateTime) {
        return employeeR2RoleMasterDbSvc.save(roleSids, employeeSid, operator, operateTime);
    }

    @Override
    public int removeRolesFromUsers(final List<Long> roleSids, final List<Long> employeeSids) {
        if (DeiCollectionUtil.isEmpty(roleSids)
                && DeiCollectionUtil.isEmpty(employeeSids)) {
            return DeiGlobalConstant.ZERO;
        }
        final DeleteConditionsHolder conditionsHolder = new DeleteConditionsHolder();
        if (DeiCollectionUtil.isNotEmpty(roleSids)) {
            if (roleSids.size() > 1) {
                conditionsHolder.addDeleteCondition("roleSids", roleSids);
            } else {
                conditionsHolder.addDeleteCondition("roleSid", roleSids.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(employeeSids)) {
            if (employeeSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userSids", employeeSids);
            } else {
                conditionsHolder.addDeleteCondition("userSid", employeeSids.get(0));
            }
        }
        return employeeR2RoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignSysResRolesToUser(final List<Long> sysResRoleSids, final Long employeeSid, final String operator, final Date operateTime) {
        return employeeR2SysResRoleMasterDbSvc.save(sysResRoleSids, employeeSid, operator, operateTime);
    }

    @Override
    public int removeSysResRolesFromUsers(final List<Long> sysResRoleSids, final List<Long> employeeSids) {
        if (DeiCollectionUtil.isEmpty(sysResRoleSids)
                && DeiCollectionUtil.isEmpty(employeeSids)) {
            return DeiGlobalConstant.ZERO;
        }
        final DeleteConditionsHolder conditionsHolder = new DeleteConditionsHolder();
        if (DeiCollectionUtil.isNotEmpty(sysResRoleSids)) {
            if (sysResRoleSids.size() > 1) {
                conditionsHolder.addDeleteCondition("sysResRoleSids", sysResRoleSids);
            } else {
                conditionsHolder.addDeleteCondition("sysResRoleSid", sysResRoleSids.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(employeeSids)) {
            if (employeeSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userSids", employeeSids);
            } else {
                conditionsHolder.addDeleteCondition("userSid", employeeSids.get(0));
            }
        }
        return employeeR2SysResRoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignTmpRolesToUser(final List<Long> tmpRoleSids, final Long employeeSid, final String operator, final Date operateTime) {
        return employeeR2TmpRoleMasterDbSvc.save(tmpRoleSids, employeeSid, operator, operateTime);
    }

    @Override
    public int removeTmpRolesFromUsers(final List<Long> tmpRoleSids, final List<Long> employeeSids) {
        if (DeiCollectionUtil.isEmpty(tmpRoleSids)
                && DeiCollectionUtil.isEmpty(employeeSids)) {
            return DeiGlobalConstant.ZERO;
        }
        final DeleteConditionsHolder conditionsHolder = new DeleteConditionsHolder();
        if (DeiCollectionUtil.isNotEmpty(tmpRoleSids)) {
            if (tmpRoleSids.size() > 1) {
                conditionsHolder.addDeleteCondition("roleSids", tmpRoleSids);
            } else {
                conditionsHolder.addDeleteCondition("roleSid", tmpRoleSids.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(employeeSids)) {
            if (employeeSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userSids", employeeSids);
            } else {
                conditionsHolder.addDeleteCondition("userSid", employeeSids.get(0));
            }
        }
        return employeeR2TmpRoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignTmpSysResRolesToUser(final List<Long> tmpSysResRoleSids, final Long employeeSid, final String operator, final Date operateTime) {
        return employeeR2TmpSysResRoleMasterDbSvc.save(tmpSysResRoleSids, employeeSid, operator, operateTime);
    }

    @Override
    public int removeTmpSysResRolesFromUsers(final List<Long> tmpSysResRoleSids, final List<Long> employeeSids) {
        if (DeiCollectionUtil.isEmpty(tmpSysResRoleSids)
                && DeiCollectionUtil.isEmpty(employeeSids)) {
            return DeiGlobalConstant.ZERO;
        }
        final DeleteConditionsHolder conditionsHolder = new DeleteConditionsHolder();
        if (DeiCollectionUtil.isNotEmpty(tmpSysResRoleSids)) {
            if (tmpSysResRoleSids.size() > 1) {
                conditionsHolder.addDeleteCondition("sysResRoleSids", tmpSysResRoleSids);
            } else {
                conditionsHolder.addDeleteCondition("sysResRoleSid", tmpSysResRoleSids.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(employeeSids)) {
            if (employeeSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userSids", employeeSids);
            } else {
                conditionsHolder.addDeleteCondition("userSid", employeeSids.get(0));
            }
        }
        return employeeR2TmpSysResRoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    protected EmployeeMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员表";
    }
}
