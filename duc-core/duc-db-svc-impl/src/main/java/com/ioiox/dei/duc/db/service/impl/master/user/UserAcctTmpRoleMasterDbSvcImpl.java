package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.DeleteConditionsHolder;
import com.ioiox.dei.duc.beans.entity.UserAcctTmpRole;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctTmpRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("userAcctTmpRoleMasterDbSvc")
public class UserAcctTmpRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserAcctTmpRole, UserAcctTmpRoleMasterMapper>
        implements UserAcctTmpRoleMasterDbSvc {

    @Autowired
    private UserAcctTmpRoleMasterMapper mapper;

    @Autowired
    @Qualifier("userAcctTmpRoleR2MenuMasterDbSvc")
    private UserAcctTmpRoleR2MenuMasterDbSvc userAcctTmpRoleR2MenuMasterDbSvc;

    @Autowired
    @Qualifier("userAcctTmpRoleR2MenuSysApiMasterDbSvc")
    private UserAcctTmpRoleR2MenuSysApiMasterDbSvc userAcctTmpRoleR2MenuSysApiMasterDbSvc;

    @Autowired
    @Qualifier("userAcctTmpRoleR2SysApiMasterDbSvc")
    private UserAcctTmpRoleR2SysApiMasterDbSvc userAcctTmpRoleR2SysApiMasterDbSvc;

    @Override
    public int assignMenusToTmpRole(final List<Long> menuSids, final Long tmpRoleSid, final String operator, final Date operateTime) {
        return userAcctTmpRoleR2MenuMasterDbSvc.save(menuSids, tmpRoleSid, operator, operateTime);
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
        return userAcctTmpRoleR2MenuMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignMenuSysApisToTmpRole(final List<Long> sysApiMappingSids, final Long tmpRoleSid, final String operator, final Date operateTime) {
        return userAcctTmpRoleR2MenuSysApiMasterDbSvc.save(sysApiMappingSids, tmpRoleSid, operator, operateTime);
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
        return userAcctTmpRoleR2MenuSysApiMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignSysApisToTmpRole(final List<Long> sysApiSids, final Long tmpRoleSid, final String operator, final Date operateTime) {
        return userAcctTmpRoleR2SysApiMasterDbSvc.save(sysApiSids, tmpRoleSid, operator, operateTime);
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
        return userAcctTmpRoleR2SysApiMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    protected UserAcctTmpRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户临时角色表";
    }
}
