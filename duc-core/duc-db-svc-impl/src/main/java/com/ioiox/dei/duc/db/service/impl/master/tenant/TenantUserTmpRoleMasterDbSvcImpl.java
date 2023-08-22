package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DeleteConditionsHolder;
import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.TenantUserTmpRole;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserTmpRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("tenantUserTmpRoleMasterDbSvc")
public class TenantUserTmpRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<TenantUserTmpRole, TenantUserTmpRoleMasterMapper>
        implements TenantUserTmpRoleMasterDbSvc {

    @Autowired
    private TenantUserTmpRoleMasterMapper mapper;

    @Autowired
    @Qualifier("tenantUserTmpRoleR2MenuMasterDbSvc")
    private TenantUserTmpRoleR2MenuMasterDbSvc tenantUserTmpRoleR2MenuMasterDbSvc;

    @Autowired
    @Qualifier("tenantUserTmpRoleR2MenuSysApiMasterDbSvc")
    private TenantUserTmpRoleR2MenuSysApiMasterDbSvc tenantUserTmpRoleR2MenuSysApiMasterDbSvc;

    @Autowired
    @Qualifier("tenantUserTmpRoleR2SysApiMasterDbSvc")
    private TenantUserTmpRoleR2SysApiMasterDbSvc tenantUserTmpRoleR2SysApiMasterDbSvc;

    @Override
    public int assignMenusToTmpRole(final List<Long> menuSids, final Long tmpRoleSid, final String operator, final Date operateTime) {
        return tenantUserTmpRoleR2MenuMasterDbSvc.save(menuSids, tmpRoleSid, operator, operateTime);
    }

    @Override
    public int removeMenusFromTmpRoles(final List<Long> menuSids, final List<Long> tmpRoleSids) {
        if (DeiCollectionUtil.isEmpty(menuSids)
                && DeiCollectionUtil.isEmpty(tmpRoleSids)) {
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
        if (DeiCollectionUtil.isNotEmpty(tmpRoleSids)) {
            if (tmpRoleSids.size() > 1) {
                conditionsHolder.addDeleteCondition("roleSids", tmpRoleSids);
            } else {
                conditionsHolder.addDeleteCondition("roleSid", tmpRoleSids.get(0));
            }
        }
        return tenantUserTmpRoleR2MenuMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignMenuSysApisToTmpRole(final List<Long> sysApiMappingSids, final Long tmpRoleSid, final String operator, final Date operateTime) {
        return tenantUserTmpRoleR2MenuSysApiMasterDbSvc.save(sysApiMappingSids, tmpRoleSid, operator, operateTime);
    }

    @Override
    public int removeMenuSysApisFromTmpRoles(final List<Long> sysApiMappingSids, final List<Long> tmpRoleSids) {
        if (DeiCollectionUtil.isEmpty(sysApiMappingSids)
                && DeiCollectionUtil.isEmpty(tmpRoleSids)) {
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
        if (DeiCollectionUtil.isNotEmpty(tmpRoleSids)) {
            if (tmpRoleSids.size() > 1) {
                conditionsHolder.addDeleteCondition("roleSids", tmpRoleSids);
            } else {
                conditionsHolder.addDeleteCondition("roleSid", tmpRoleSids.get(0));
            }
        }
        return tenantUserTmpRoleR2MenuSysApiMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignSysApisToTmpRole(final List<Long> sysApiSids, final Long tmpRoleSid, final String operator, final Date operateTime) {
        return tenantUserTmpRoleR2SysApiMasterDbSvc.save(sysApiSids, tmpRoleSid, operator, operateTime);
    }

    @Override
    public int removeSysApisFromTmpRoles(final List<Long> sysApiSids, final List<Long> tmpRoleSids) {
        if (DeiCollectionUtil.isEmpty(sysApiSids)
                && DeiCollectionUtil.isEmpty(tmpRoleSids)) {
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
        if (DeiCollectionUtil.isNotEmpty(tmpRoleSids)) {
            if (tmpRoleSids.size() > 1) {
                conditionsHolder.addDeleteCondition("roleSids", tmpRoleSids);
            } else {
                conditionsHolder.addDeleteCondition("roleSid", tmpRoleSids.get(0));
            }
        }
        return tenantUserTmpRoleR2SysApiMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    protected TenantUserTmpRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户临时角色表";
    }
}
