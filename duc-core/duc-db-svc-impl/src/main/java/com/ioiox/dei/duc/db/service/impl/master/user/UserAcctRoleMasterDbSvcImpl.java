package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DeleteConditionsHolder;
import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.UserAcctRole;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.UserAcctRoleMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.user.UserAcctRoleR2MenuMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.user.UserAcctRoleR2MenuSysApiMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.user.UserAcctRoleR2SysApiMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("userAcctRoleMasterDbSvc")
public class UserAcctRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserAcctRole, UserAcctRoleMasterMapper>
        implements UserAcctRoleMasterDbSvc {

    @Autowired
    private UserAcctRoleMasterMapper mapper;

    @Autowired
    @Qualifier("userAcctRoleR2MenuMasterDbSvc")
    private UserAcctRoleR2MenuMasterDbSvc userAcctRoleR2MenuMasterDbSvc;

    @Autowired
    @Qualifier("userAcctRoleR2MenuSysApiMasterDbSvc")
    private UserAcctRoleR2MenuSysApiMasterDbSvc userAcctRoleR2MenuSysApiMasterDbSvc;

    @Autowired
    @Qualifier("userAcctRoleR2SysApiMasterDbSvc")
    private UserAcctRoleR2SysApiMasterDbSvc userAcctRoleR2SysApiMasterDbSvc;

    @Override
    public int assignMenusToRole(final List<Long> menuSids, final Long roleSid, final String operator, final Date operateTime) {
        return userAcctRoleR2MenuMasterDbSvc.save(menuSids, roleSid, operator, operateTime);
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
        return userAcctRoleR2MenuMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignMenuSysApisToRole(final List<Long> sysApiMappingSids, final Long roleSid, final String operator, final Date operateTime) {
        return userAcctRoleR2MenuSysApiMasterDbSvc.save(sysApiMappingSids, roleSid, operator, operateTime);
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
        return userAcctRoleR2MenuSysApiMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignSysApisToRole(final List<Long> sysApiSids, final Long roleSid, final String operator, final Date operateTime) {
        return userAcctRoleR2SysApiMasterDbSvc.save(sysApiSids, roleSid, operator, operateTime);
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
        return userAcctRoleR2SysApiMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    protected UserAcctRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户角色表";
    }
}
