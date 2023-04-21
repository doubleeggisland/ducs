package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.DeleteConditionsHolder;
import com.ioiox.dei.duc.beans.entity.EmployeeSysResRole;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeSysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeSysResRoleMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeSysResRoleR2ResMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("employeeSysResRoleMasterDbSvc")
public class EmployeeSysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<EmployeeSysResRole, EmployeeSysResRoleMasterMapper>
        implements EmployeeSysResRoleMasterDbSvc {

    @Autowired
    private EmployeeSysResRoleMasterMapper mapper;

    @Autowired
    @Qualifier("employeeSysResRoleR2ResMasterDbSvc")
    private EmployeeSysResRoleR2ResMasterDbSvc employeeSysResRoleR2ResMasterDbSvc;

    @Override
    public int assignSysResourcesToSysResRole(final List<Long> sysResSids, final Long sysResRoleSid, final String operator, final Date operateTime) {
        return employeeSysResRoleR2ResMasterDbSvc.save(sysResSids, sysResRoleSid, operator, operateTime);
    }

    @Override
    public int removeSysResourcesFromSysResRoles(final List<Long> sysResSids, final List<Long> sysResRoleSids) {
        if (DeiCollectionUtil.isEmpty(sysResSids)
                && DeiCollectionUtil.isEmpty(sysResRoleSids)) {
            return DeiGlobalConstant.ZERO;
        }
        final DeleteConditionsHolder conditionsHolder = new DeleteConditionsHolder();
        if (DeiCollectionUtil.isNotEmpty(sysResSids)) {
            if (sysResSids.size() > 1) {
                conditionsHolder.addDeleteCondition("sysResSids", sysResSids);
            } else {
                conditionsHolder.addDeleteCondition("sysResSid", sysResSids.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(sysResRoleSids)) {
            if (sysResRoleSids.size() > 1) {
                conditionsHolder.addDeleteCondition("sysResRoleSids", sysResRoleSids);
            } else {
                conditionsHolder.addDeleteCondition("sysResRoleSid", sysResRoleSids.get(0));
            }
        }
        return employeeSysResRoleR2ResMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    protected EmployeeSysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员系统资源角色表";
    }
}
