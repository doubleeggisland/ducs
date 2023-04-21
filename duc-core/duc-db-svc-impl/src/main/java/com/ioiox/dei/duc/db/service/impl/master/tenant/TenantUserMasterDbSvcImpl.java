package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.DeleteConditionsHolder;
import com.ioiox.dei.duc.beans.entity.TenantUser;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("tenantUserMasterDbSvc")
public class TenantUserMasterDbSvcImpl
        extends BaseDeiMasterDbService<TenantUser, TenantUserMasterMapper>
        implements TenantUserMasterDbSvc {

    @Autowired
    private TenantUserMasterMapper mapper;

    @Autowired
    @Qualifier("tenantUserGrpR2UserMasterDbSvc")
    private TenantUserGrpR2UserMasterDbSvcImpl tenantUserGrpR2UserMasterDbSvc;

    @Autowired
    @Qualifier("tenantUserR2RoleMasterDbSvc")
    private TenantUserR2RoleMasterDbSvc tenantUserR2RoleMasterDbSvc;

    @Autowired
    @Qualifier("tenantUserR2SysResRoleMasterDbSvc")
    private TenantUserR2SysResRoleMasterDbSvc tenantUserR2SysResRoleMasterDbSvc;

    @Autowired
    @Qualifier("tenantUserR2TmpRoleMasterDbSvc")
    private TenantUserR2TmpRoleMasterDbSvc tenantUserR2TmpRoleMasterDbSvc;

    @Autowired
    @Qualifier("tenantUserR2TmpSysResRoleMasterDbSvc")
    private TenantUserR2TmpSysResRoleMasterDbSvc tenantUserR2TmpSysResRoleMasterDbSvc;

    @Override
    public int assignUserGrpsToUser(final List<Long> userGrpSids, final Long tenantUserSid, final String operator, final Date operateTime) {
        return tenantUserGrpR2UserMasterDbSvc.save(userGrpSids, tenantUserSid, operator, operateTime);
    }

    @Override
    public int removeUserGrpsFromUsers(final List<Long> userGrpSids, final List<Long> tenantUserSids) {
        if (DeiCollectionUtil.isEmpty(userGrpSids)
                && DeiCollectionUtil.isEmpty(tenantUserSids)) {
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
        if (DeiCollectionUtil.isNotEmpty(tenantUserSids)) {
            if (tenantUserSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userSids", tenantUserSids);
            } else {
                conditionsHolder.addDeleteCondition("userSid", tenantUserSids.get(0));
            }
        }
        return tenantUserGrpR2UserMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignRolesToUser(final List<Long> roleSids, final Long tenantUserSid, final String operator, final Date operateTime) {
        return tenantUserR2RoleMasterDbSvc.save(roleSids, tenantUserSid, operator, operateTime);
    }

    @Override
    public int removeRolesFromUsers(final List<Long> roleSids, final List<Long> tenantUserSids) {
        if (DeiCollectionUtil.isEmpty(roleSids)
                && DeiCollectionUtil.isEmpty(tenantUserSids)) {
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
        if (DeiCollectionUtil.isNotEmpty(tenantUserSids)) {
            if (tenantUserSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userSids", tenantUserSids);
            } else {
                conditionsHolder.addDeleteCondition("userSid", tenantUserSids.get(0));
            }
        }
        return tenantUserR2RoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignSysResRolesToUser(final List<Long> sysResRoleSids, final Long tenantUserSid, final String operator, final Date operateTime) {
        return tenantUserR2SysResRoleMasterDbSvc.save(sysResRoleSids, tenantUserSid, operator, operateTime);
    }

    @Override
    public int removeSysResRolesFromUsers(final List<Long> sysResRoleSids, final List<Long> tenantUserSids) {
        if (DeiCollectionUtil.isEmpty(sysResRoleSids)
                && DeiCollectionUtil.isEmpty(tenantUserSids)) {
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
        if (DeiCollectionUtil.isNotEmpty(tenantUserSids)) {
            if (tenantUserSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userSids", tenantUserSids);
            } else {
                conditionsHolder.addDeleteCondition("userSid", tenantUserSids.get(0));
            }
        }
        return tenantUserR2SysResRoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignTmpRolesToUser(final List<Long> tmpRoleSids, final Long tenantUserSid, final String operator, final Date operateTime) {
        return tenantUserR2TmpRoleMasterDbSvc.save(tmpRoleSids, tenantUserSid, operator, operateTime);
    }

    @Override
    public int removeTmpRolesFromUsers(final List<Long> tmpRoleSids, final List<Long> tenantUserSids) {
        if (DeiCollectionUtil.isEmpty(tmpRoleSids)
                && DeiCollectionUtil.isEmpty(tenantUserSids)) {
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
        if (DeiCollectionUtil.isNotEmpty(tenantUserSids)) {
            if (tenantUserSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userSids", tenantUserSids);
            } else {
                conditionsHolder.addDeleteCondition("userSid", tenantUserSids.get(0));
            }
        }
        return tenantUserR2TmpRoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignTmpSysResRolesToUser(final List<Long> tmpSysResRoleSids, final Long tenantUserSid, final String operator, final Date operateTime) {
        return tenantUserR2TmpSysResRoleMasterDbSvc.save(tmpSysResRoleSids, tenantUserSid, operator, operateTime);
    }

    @Override
    public int removeTmpSysResRolesFromUsers(final List<Long> tmpSysResRoleSids, final List<Long> tenantUserSids) {
        if (DeiCollectionUtil.isEmpty(tmpSysResRoleSids)
                && DeiCollectionUtil.isEmpty(tenantUserSids)) {
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
        if (DeiCollectionUtil.isNotEmpty(tenantUserSids)) {
            if (tenantUserSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userSids", tenantUserSids);
            } else {
                conditionsHolder.addDeleteCondition("userSid", tenantUserSids.get(0));
            }
        }
        return tenantUserR2TmpSysResRoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    protected TenantUserMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户表";
    }
}
