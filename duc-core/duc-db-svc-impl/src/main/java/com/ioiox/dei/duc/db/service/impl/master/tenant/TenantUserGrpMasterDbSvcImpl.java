package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.DeleteConditionsHolder;
import com.ioiox.dei.duc.beans.entity.TenantUserGrp;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserGrpMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserGrpMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserGrpR2RoleMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserGrpR2SysResRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("tenantUserGrpMasterDbSvc")
public class TenantUserGrpMasterDbSvcImpl
        extends BaseDeiMasterDbService<TenantUserGrp, TenantUserGrpMasterMapper>
        implements TenantUserGrpMasterDbSvc {

    @Autowired
    private TenantUserGrpMasterMapper mapper;

    @Autowired
    @Qualifier("tenantUserGrpR2RoleMasterDbSvc")
    private TenantUserGrpR2RoleMasterDbSvc tenantUserGrpR2RoleMasterDbSvc;

    @Autowired
    @Qualifier("tenantUserGrpR2SysResRoleMasterDbSvc")
    private TenantUserGrpR2SysResRoleMasterDbSvc tenantUserGrpR2SysResRoleMasterDbSvc;

    @Override
    public int assignRolesToUserGrp(final List<Long> roleSids, final Long userGrpSid, final String operator, final Date operateTime) {
        return tenantUserGrpR2RoleMasterDbSvc.save(roleSids, userGrpSid, operator, operateTime);
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
        return tenantUserGrpR2RoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignSysResRolesToUserGrp(final List<Long> sysResRoleSids, final Long userGrpSid, final String operator, final Date operateTime) {
        return tenantUserGrpR2SysResRoleMasterDbSvc.save(sysResRoleSids, userGrpSid, operator, operateTime);
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
        return tenantUserGrpR2SysResRoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    protected TenantUserGrpMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户组表";
    }
}
