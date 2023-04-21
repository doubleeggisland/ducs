package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.DeleteConditionsHolder;
import com.ioiox.dei.duc.beans.entity.EmployeeUserGrp;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeUserGrpMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeUserGrpMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeUserGrpR2RoleMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeUserGrpR2SysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("employeeUserGrpMasterDbSvc")
public class EmployeeUserGrpMasterDbSvcImpl
        extends BaseDeiMasterDbService<EmployeeUserGrp, EmployeeUserGrpMasterMapper>
        implements EmployeeUserGrpMasterDbSvc {

    @Autowired
    private EmployeeUserGrpMasterMapper mapper;

    @Autowired
    @Qualifier("employeeUserGrpR2RoleMasterDbSvc")
    private EmployeeUserGrpR2RoleMasterDbSvc employeeUserGrpR2RoleMasterDbSvc;

    @Autowired
    @Qualifier("employeeUserGrpR2SysResRoleMasterDbSvc")
    private EmployeeUserGrpR2SysResRoleMasterDbSvc employeeUserGrpR2SysResRoleMasterDbSvc;

    @Override
    public int assignRolesToUserGrp(final List<Long> roleSids, final Long userGrpSid, final String operator, final Date operateTime) {
        return employeeUserGrpR2RoleMasterDbSvc.save(roleSids, userGrpSid, operator, operateTime);
    }

    @Override
    public int removeRolesFromUserGrps(final List<Long> roleSids, final List<Long> userGrpSids) {
        if (DeiCollectionUtil.isEmpty(roleSids)
                && DeiCollectionUtil.isEmpty(userGrpSids)) {
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
        if (DeiCollectionUtil.isNotEmpty(userGrpSids)) {
            if (userGrpSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userGrpSids", userGrpSids);
            } else {
                conditionsHolder.addDeleteCondition("userGrpSid", userGrpSids.get(0));
            }
        }
        return employeeUserGrpR2RoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignSysResRolesToUserGrp(final List<Long> sysResRoleSids, final Long userGrpSid, final String operator, final Date operateTime) {
        return employeeUserGrpR2SysResRoleMasterDbSvc.save(sysResRoleSids, userGrpSid, operator, operateTime);
    }

    @Override
    public int removeSysResRolesFromUserGrps(final List<Long> sysResRoleSids, final List<Long> userGrpSids) {
        if (DeiCollectionUtil.isEmpty(sysResRoleSids)
                && DeiCollectionUtil.isEmpty(userGrpSids)) {
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
        if (DeiCollectionUtil.isNotEmpty(userGrpSids)) {
            if (userGrpSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userGrpSids", userGrpSids);
            } else {
                conditionsHolder.addDeleteCondition("userGrpSid", userGrpSids.get(0));
            }
        }
        return employeeUserGrpR2SysResRoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    protected EmployeeUserGrpMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员用户组表";
    }
}
