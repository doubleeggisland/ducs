package com.ioiox.dei.duc.db.service.impl.master.employee;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.DeleteConditionsHolder;
import com.ioiox.dei.duc.beans.entity.EmployeeTmpSysResRole;
import com.ioiox.dei.duc.db.mapper.master.employee.EmployeeTmpSysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeTmpSysResRoleMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.employee.EmployeeTmpSysResRoleR2ResMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("employeeTmpSysResRoleMasterDbSvc")
public class EmployeeTmpSysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<EmployeeTmpSysResRole, EmployeeTmpSysResRoleMasterMapper>
        implements EmployeeTmpSysResRoleMasterDbSvc {

    @Autowired
    private EmployeeTmpSysResRoleMasterMapper mapper;

    @Autowired
    @Qualifier("employeeTmpSysResRoleR2ResMasterDbSvc")
    private EmployeeTmpSysResRoleR2ResMasterDbSvc employeeTmpSysResRoleR2ResMasterDbSvc;

    @Override
    public int assignSysResourcesToTmpSysResRole(final List<Long> sysResSids, final Long tmpSysResRoleSid, final String operator, final Date operateTime) {
        return employeeTmpSysResRoleR2ResMasterDbSvc.save(sysResSids, tmpSysResRoleSid, operator, operateTime);
    }

    @Override
    public int removeSysResourcesFromTmpSysResRoles(final List<Long> sysResSids, final List<Long> tmpSysResRoleSids) {
        if (DeiCollectionUtil.isEmpty(sysResSids)
                && DeiCollectionUtil.isEmpty(tmpSysResRoleSids)) {
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
        if (DeiCollectionUtil.isNotEmpty(tmpSysResRoleSids)) {
            if (tmpSysResRoleSids.size() > 1) {
                conditionsHolder.addDeleteCondition("sysResRoleSids", tmpSysResRoleSids);
            } else {
                conditionsHolder.addDeleteCondition("sysResRoleSid", tmpSysResRoleSids.get(0));
            }
        }
        return employeeTmpSysResRoleR2ResMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    protected EmployeeTmpSysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员临时系统资源角色表";
    }
}
