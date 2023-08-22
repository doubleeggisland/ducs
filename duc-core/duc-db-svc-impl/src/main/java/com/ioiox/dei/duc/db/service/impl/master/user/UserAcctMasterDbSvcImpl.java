package com.ioiox.dei.duc.db.service.impl.master.user;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DeleteConditionsHolder;
import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiMasterDbService;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.UserAcct;
import com.ioiox.dei.duc.db.mapper.master.user.UserAcctMasterMapper;
import com.ioiox.dei.duc.db.service.master.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("userAcctMasterDbSvc")
public class UserAcctMasterDbSvcImpl
        extends BaseDeiMasterDbService<UserAcct, UserAcctMasterMapper>
        implements UserAcctMasterDbSvc {

    @Autowired
    private UserAcctMasterMapper mapper;

    @Autowired
    @Qualifier("acctUserGrpR2UserMasterDbSvc")
    private AcctUserGrpR2UserMasterDbSvc acctUserGrpR2UserMasterDbSvc;

    @Autowired
    @Qualifier("userAcctR2RoleMasterDbSvc")
    private UserAcctR2RoleMasterDbSvc userAcctR2RoleMasterDbSvc;

    @Autowired
    @Qualifier("userAcctR2SysResRoleMasterDbSvc")
    private UserAcctR2SysResRoleMasterDbSvc userAcctR2SysResRoleMasterDbSvc;

    @Autowired
    @Qualifier("userAcctR2TmpRoleMasterDbSvc")
    private UserAcctR2TmpRoleMasterDbSvc userAcctR2TmpRoleMasterDbSvc;

    @Autowired
    @Qualifier("userAcctR2TmpSysResRoleMasterDbSvc")
    private UserAcctR2TmpSysResRoleMasterDbSvc userAcctR2TmpSysResRoleMasterDbSvc;

    @Override
    public int assignUserGrpsToUser(final List<Long> userGrpSids, final Long userAcctSid, final String operator, final Date operateTime) {
        return acctUserGrpR2UserMasterDbSvc.save(userGrpSids, userAcctSid, operator, operateTime);
    }

    @Override
    public int removeUserGrpsFromUsers(final List<Long> userGrpSids, final List<Long> userAcctSids) {
        if (DeiCollectionUtil.isEmpty(userGrpSids)
                && DeiCollectionUtil.isEmpty(userAcctSids)) {
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
        if (DeiCollectionUtil.isNotEmpty(userAcctSids)) {
            if (userAcctSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userSids", userAcctSids);
            } else {
                conditionsHolder.addDeleteCondition("userSid", userAcctSids.get(0));
            }
        }
        return userAcctR2RoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignRolesToUser(final List<Long> roleSids, final Long userAcctSid, final String operator, final Date operateTime) {
        return userAcctR2RoleMasterDbSvc.save(roleSids, userAcctSid, operator, operateTime);
    }

    @Override
    public int removeRolesFromUsers(final List<Long> roleSids, final List<Long> userAcctSids) {
        if (DeiCollectionUtil.isEmpty(roleSids)
                && DeiCollectionUtil.isEmpty(userAcctSids)) {
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
        if (DeiCollectionUtil.isNotEmpty(userAcctSids)) {
            if (userAcctSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userSids", userAcctSids);
            } else {
                conditionsHolder.addDeleteCondition("userSid", userAcctSids.get(0));
            }
        }
        return userAcctR2RoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignSysResRolesToUser(final List<Long> sysResRoleSids, final Long userAcctSid, final String operator, final Date operateTime) {
        return userAcctR2SysResRoleMasterDbSvc.save(sysResRoleSids, userAcctSid, operator, operateTime);
    }

    @Override
    public int removeSysResRolesFromUsers(final List<Long> sysResRoleSids, final List<Long> userAcctSids) {
        if (DeiCollectionUtil.isEmpty(sysResRoleSids)
                && DeiCollectionUtil.isEmpty(userAcctSids)) {
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
        if (DeiCollectionUtil.isNotEmpty(userAcctSids)) {
            if (userAcctSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userSids", userAcctSids);
            } else {
                conditionsHolder.addDeleteCondition("userSid", userAcctSids.get(0));
            }
        }
        return userAcctR2RoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignTmpRolesToUser(final List<Long> tmpRoleSids, final Long userAcctSid, final String operator, final Date operateTime) {
        return userAcctR2TmpRoleMasterDbSvc.save(tmpRoleSids, userAcctSid, operator, operateTime);
    }

    @Override
    public int removeTmpRolesFromUsers(final List<Long> tmpRoleSids, final List<Long> userAcctSids) {
        if (DeiCollectionUtil.isEmpty(tmpRoleSids)
                && DeiCollectionUtil.isEmpty(userAcctSids)) {
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
        if (DeiCollectionUtil.isNotEmpty(userAcctSids)) {
            if (userAcctSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userSids", userAcctSids);
            } else {
                conditionsHolder.addDeleteCondition("userSid", userAcctSids.get(0));
            }
        }
        return userAcctR2TmpRoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    public int assignTmpSysResRolesToUser(final List<Long> tmpSysResRoleSids, final Long userAcctSid, final String operator, final Date operateTime) {
        return userAcctR2TmpSysResRoleMasterDbSvc.save(tmpSysResRoleSids, userAcctSid, operator, operateTime);
    }

    @Override
    public int removeTmpSysResRolesFromUsers(final List<Long> tmpSysResRoleSids, final List<Long> userAcctSids) {
        if (DeiCollectionUtil.isEmpty(tmpSysResRoleSids)
                && DeiCollectionUtil.isEmpty(userAcctSids)) {
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
        if (DeiCollectionUtil.isNotEmpty(userAcctSids)) {
            if (userAcctSids.size() > 1) {
                conditionsHolder.addDeleteCondition("userSids", userAcctSids);
            } else {
                conditionsHolder.addDeleteCondition("userSid", userAcctSids.get(0));
            }
        }
        return userAcctR2TmpSysResRoleMasterDbSvc.deleteByParams(conditionsHolder.deleteParams());
    }

    @Override
    protected UserAcctMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "用户账号表";
    }
}
