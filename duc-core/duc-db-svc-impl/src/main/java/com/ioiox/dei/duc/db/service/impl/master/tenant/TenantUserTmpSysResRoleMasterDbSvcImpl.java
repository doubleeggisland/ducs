package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.DeleteConditionsHolder;
import com.ioiox.dei.duc.beans.entity.TenantUserTmpSysResRole;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserTmpSysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserTmpSysResRoleMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserTmpSysResRoleR2ResMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("tenantUserTmpSysResRoleMasterDbSvc")
public class TenantUserTmpSysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<TenantUserTmpSysResRole, TenantUserTmpSysResRoleMasterMapper>
        implements TenantUserTmpSysResRoleMasterDbSvc {

    @Autowired
    private TenantUserTmpSysResRoleMasterMapper mapper;

    @Autowired
    @Qualifier("tenantUserTmpSysResRoleR2ResMasterDbSvc")
    private TenantUserTmpSysResRoleR2ResMasterDbSvc tenantUserTmpSysResRoleR2ResMasterDbSvc;

    @Override
    public int assignSysResourcesToTmpSysResRole(final List<Long> sysResSids, final Long tmpSysResRoleSid, final String operator, final Date operateTime) {
        return tenantUserTmpSysResRoleR2ResMasterDbSvc.save(sysResSids, tmpSysResRoleSid, operator, operateTime);
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
        return tenantUserTmpSysResRoleR2ResMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    protected TenantUserTmpSysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户临时系统资源角色表";
    }
}
