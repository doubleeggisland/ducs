package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.DeleteConditionsHolder;
import com.ioiox.dei.duc.beans.entity.TenantUserRole;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserRoleMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserRoleR2MenuMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserRoleR2MenuSysApiMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserRoleR2SysApiMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("tenantUserRoleMasterDbSvc")
public class TenantUserRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<TenantUserRole, TenantUserRoleMasterMapper>
        implements TenantUserRoleMasterDbSvc {

    @Autowired
    private TenantUserRoleMasterMapper mapper;

    @Autowired
    @Qualifier("tenantUserRoleR2MenuMasterDbSvc")
    private TenantUserRoleR2MenuMasterDbSvc tenantUserRoleR2MenuMasterDbSvc;

    @Autowired
    @Qualifier("tenantUserRoleR2MenuSysApiMasterDbSvc")
    private TenantUserRoleR2MenuSysApiMasterDbSvc tenantUserRoleR2MenuSysApiMasterDbSvc;

    @Autowired
    @Qualifier("tenantUserRoleR2SysApiMasterDbSvc")
    private TenantUserRoleR2SysApiMasterDbSvc tenantUserRoleR2SysApiMasterDbSvc;

    @Override
    public int assignMenusToRole(final List<Long> menuSids, final Long roleSid, final String operator, final Date operateTime) {
        return tenantUserRoleR2MenuMasterDbSvc.save(menuSids, roleSid, operator, operateTime);
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
        return tenantUserRoleR2MenuMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignMenuSysApisToRole(final List<Long> sysApiMappingSids, final Long roleSid, final String operator, final Date operateTime) {
        return tenantUserRoleR2MenuSysApiMasterDbSvc.save(sysApiMappingSids, roleSid, operator, operateTime);
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
        return tenantUserRoleR2MenuSysApiMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignSysApisToRole(final List<Long> sysApiSids, final Long roleSid, final String operator, final Date operateTime) {
        return tenantUserRoleR2SysApiMasterDbSvc.save(sysApiSids, roleSid, operator, operateTime);
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
        return tenantUserRoleR2SysApiMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    protected TenantUserRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户角色表";
    }
}
