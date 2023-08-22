package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DeleteConditionsHolder;
import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.EmployeeRole;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeRoleMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeRoleR2MenuMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeRoleR2MenuSysApiMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeRoleR2SysApiMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("employeeRoleMasterDbSvc")
public class EmployeeRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<EmployeeRole, EmployeeRoleMasterMapper>
        implements EmployeeRoleMasterDbSvc {

    @Autowired
    private EmployeeRoleMasterMapper mapper;

    @Autowired
    @Qualifier("employeeRoleR2MenuMasterDbSvc")
    private EmployeeRoleR2MenuMasterDbSvc employeeRoleR2MenuMasterDbSvc;

    @Autowired
    @Qualifier("employeeRoleR2MenuSysApiMasterDbSvc")
    private EmployeeRoleR2MenuSysApiMasterDbSvc employeeRoleR2MenuSysApiMasterDbSvc;

    @Autowired
    @Qualifier("employeeRoleR2SysApiMasterDbSvc")
    private EmployeeRoleR2SysApiMasterDbSvc employeeRoleR2SysApiMasterDbSvc;

    @Override
    public int assignMenusToRole(final List<Long> menuSids, final Long roleSid, final String operator, final Date operateTime) {
        return employeeRoleR2MenuMasterDbSvc.save(menuSids, roleSid, operator, operateTime);
    }

    @Override
    public int removeMenusFromRoles(final List<Long> menuSids, final List<Long> roleSids) {
        if (DeiCollectionUtil.isEmpty(menuSids)
                && DeiCollectionUtil.isEmpty(roleSids)) {
            return DeiGlobalConstant.ZERO;
        }
        final DeleteConditionsHolder conditionsHolder = new DeleteConditionsHolder();
        if (DeiCollectionUtil.isNotEmpty(menuSids)) {
            if (menuSids.size() > 1) {
                conditionsHolder.addDeleteCondition("menuSids", menuSids);
            } else {
                conditionsHolder.addDeleteCondition("menuSid", menuSids.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(roleSids)) {
            if (roleSids.size() > 1) {
                conditionsHolder.addDeleteCondition("roleSids", roleSids);
            } else {
                conditionsHolder.addDeleteCondition("roleSid", roleSids.get(0));
            }
        }
        return employeeRoleR2MenuMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignMenuSysApisToRole(final List<Long> sysApiMappingSids, final Long roleSid, final String operator, final Date operateTime) {
        return employeeRoleR2MenuSysApiMasterDbSvc.save(sysApiMappingSids, roleSid, operator, operateTime);
    }

    @Override
    public int removeMenuSysApisFromRoles(final List<Long> sysApiMappingSids, final List<Long> roleSids) {
        if (DeiCollectionUtil.isEmpty(sysApiMappingSids)
                && DeiCollectionUtil.isEmpty(roleSids)) {
            return DeiGlobalConstant.ZERO;
        }
        final DeleteConditionsHolder conditionsHolder = new DeleteConditionsHolder();
        if (DeiCollectionUtil.isNotEmpty(sysApiMappingSids)) {
            if (sysApiMappingSids.size() > 1) {
                conditionsHolder.addDeleteCondition("mappingSids", sysApiMappingSids);
            } else {
                conditionsHolder.addDeleteCondition("mappingSid", sysApiMappingSids.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(roleSids)) {
            if (roleSids.size() > 1) {
                conditionsHolder.addDeleteCondition("roleSids", roleSids);
            } else {
                conditionsHolder.addDeleteCondition("roleSid", roleSids.get(0));
            }
        }
        return employeeRoleR2MenuSysApiMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignSysApisToRole(final List<Long> sysApiSids, final Long roleSid, final String operator, final Date operateTime) {
        return employeeRoleR2SysApiMasterDbSvc.save(sysApiSids, roleSid, operator, operateTime);
    }

    @Override
    public int removeSysApisFromRoles(final List<Long> sysApiSids, final List<Long> roleSids) {
        if (DeiCollectionUtil.isEmpty(sysApiSids)
                && DeiCollectionUtil.isEmpty(roleSids)) {
            return DeiGlobalConstant.ZERO;
        }
        final DeleteConditionsHolder conditionsHolder = new DeleteConditionsHolder();
        if (DeiCollectionUtil.isNotEmpty(sysApiSids)) {
            if (sysApiSids.size() > 1) {
                conditionsHolder.addDeleteCondition("sysApiSids", sysApiSids);
            } else {
                conditionsHolder.addDeleteCondition("sysApiSid", sysApiSids.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(roleSids)) {
            if (roleSids.size() > 1) {
                conditionsHolder.addDeleteCondition("roleSids", roleSids);
            } else {
                conditionsHolder.addDeleteCondition("roleSid", roleSids.get(0));
            }
        }
        return employeeRoleR2SysApiMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    protected EmployeeRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员角色表";
    }
}
