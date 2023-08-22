package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DeleteConditionsHolder;
import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.TenantUserSysResRole;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserSysResRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserSysResRoleMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserSysResRoleR2ResMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("tenantUserSysResRoleMasterDbSvc")
public class TenantUserSysResRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<TenantUserSysResRole, TenantUserSysResRoleMasterMapper>
        implements TenantUserSysResRoleMasterDbSvc {

    @Autowired
    private TenantUserSysResRoleMasterMapper mapper;

    @Autowired
    @Qualifier("tenantUserSysResRoleR2ResMasterDbSvc")
    private TenantUserSysResRoleR2ResMasterDbSvc tenantUserSysResRoleR2ResMasterDbSvc;

    @Override
    public int assignSysResourcesToSysResRole(final List<Long> sysResSids, final Long sysResRoleSid, final String operator, final Date operateTime) {
        return tenantUserSysResRoleR2ResMasterDbSvc.save(sysResSids, sysResRoleSid, operator, operateTime);
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
        return tenantUserSysResRoleR2ResMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    protected TenantUserSysResRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户系统资源角色表";
    }
}
