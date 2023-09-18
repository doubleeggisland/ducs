package com.ioiox.dei.duc.std.data.svc.impl.master.user;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.BaseUser;
import com.ioiox.dei.duc.beans.entity.UserAcct;
import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctUpdateCtx;
import com.ioiox.dei.duc.beans.model.slave.*;
import com.ioiox.dei.duc.beans.vo.std.master.UserSysPrjPrivilegeMasterVO;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.*;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSlaveVO;
import com.ioiox.dei.duc.db.service.master.user.UserAcctMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseUserMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.UserSysPrjPrivilegeMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.user.UserAcctMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.user.UserAcctSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userAcctMasterStdDataSvc")
public class UserAcctMasterStdDataSvcImpl
        extends BaseUserMasterStdDataSvc<UserAcctMasterVO, UserAcctUpdatableObj, UserAcctUpdateCtx, UserAcctDelParam, UserAcctSlaveVO, UserAcct>
        implements UserAcctMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(UserAcctMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("userAcctMasterDbSvc")
    private UserAcctMasterDbSvc userAcctMasterDbSvc;

    @Autowired
    @Qualifier("userAcctSlaveStdDataSvc")
    private UserAcctSlaveStdDataSvc userAcctSlaveStdDataSvc;

    @Autowired
    @Qualifier("acctUserSysPrjPrivilegeMasterStdDataSvc")
    private UserSysPrjPrivilegeMasterStdDataSvc acctUserSysPrjPrivilegeMasterStdDataSvc;

    private final UserAcctUpdatableAttrsAnalyser analyser = new UserAcctUpdatableAttrsAnalyser();

    @Override
    protected UserAcctSlaveVO getExistingUser(final Long id) {
        return userAcctSlaveStdDataSvc.queryByPk(id,
                new UserQueryCfg.Builder()
                        .needUserGrps(DeiGlobalConstant.FLAG_YES)
                        .userGrpQueryCfg(new UserGrpQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .needSysPrjPrivileges(DeiGlobalConstant.FLAG_YES)
                        .sysPrjPrivilegeQueryCfg(new UserSysPrjPrivilegeQueryCfg.Builder()
                                .showColumns(Arrays.asList(BaseDeiEntity.ShowColumn.ID.getCode(),
                                        UserSysPrjPrivilege.ShowColumn.USER_SID.getCode(), UserSysPrjPrivilege.ShowColumn.SYS_PRJ_SID.getCode(),
                                        UserSysPrjPrivilege.ShowColumn.ACCESS_CONDITION.getCode(), BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                                .build())
                        .needRoles(DeiGlobalConstant.FLAG_YES)
                        .roleQueryCfg(new RoleQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .needSysResRoles(DeiGlobalConstant.FLAG_YES)
                        .sysResRoleQueryCfg(new SysResRoleQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .needTmpRoles(DeiGlobalConstant.FLAG_YES)
                        .tmpRoleQueryCfg(new RoleQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .needTmpSysResRoles(DeiGlobalConstant.FLAG_YES)
                        .tmpSysResRoleQueryCfg(new SysResRoleQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .showColumns(Arrays.asList(BaseDeiEntity.ShowColumn.ID.getCode(),
                                BaseUser.ShowColumn.USER_NAME.getCode(), BaseUser.ShowColumn.NICK_NAME.getCode(),
                                BaseUser.ShowColumn.MOBILE.getCode(), BaseUser.ShowColumn.EMAIL.getCode(),
                                BaseUser.ShowColumn.STATUS.getCode(), BaseUser.ShowColumn.PWD.getCode(),
                                BaseUser.ShowColumn.AVATAR_URL.getCode(), BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                        .build());
    }

    @Override
    protected List<UserAcctSlaveVO> queryExistingUsers(final UserAcctDelParam delParam) {
        final UserAcctQueryParam queryParam = new UserAcctQueryParam.Builder()
                .corpIds(delParam.getCorpIds())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return userAcctSlaveStdDataSvc.queryByParam(queryParam,
                new UserQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build());
    }

    @Override
    protected int syncSysPrjPrivileges(final List<UserSysPrjPrivilegeMasterVO> sysPrjPrivileges,
                                       final List<UserSysPrjPrivilegeSlaveVO> existingSysPrjPrivileges) {
        return acctUserSysPrjPrivilegeMasterStdDataSvc.sync(sysPrjPrivileges, existingSysPrjPrivileges);
    }

    @Override
    protected int removeSysPrjPrivileges(final List<Long> userAcctIds) {
        final int numOfSysPrjPrivilegesRemoved =
                acctUserSysPrjPrivilegeMasterStdDataSvc.removeByUserIds(userAcctIds);
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysPrjPrivileges from userAccts =====> userAcctIds: %s, numOfSysPrjPrivilegesRemoved: %s" ,
                    JsonUtil.toJsonStr(userAcctIds), numOfSysPrjPrivilegesRemoved));
        }
        return numOfSysPrjPrivilegesRemoved;
    }

    @Override
    protected int assignUserGrpsToUser(final List<Long> userGrpIds, final Long userAcctId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign userGrps to userAcct =====> userGrpIds: %s, userAcctId: %s",
                    JsonUtil.toJsonStr(userGrpIds), userAcctId));
        }
        return userAcctMasterDbSvc.assignUserGrpsToUser(userGrpIds, userAcctId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeUserGrpsFromUser(final List<Long> userGrpIds, final Long userAcctId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove userGrps from userAcct =====> userGrpIds: %s, userAcctId: %s",
                    JsonUtil.toJsonStr(userGrpIds), userAcctId));
        }
        return userAcctMasterDbSvc.removeUserGrpsFromUsers(userGrpIds, Collections.singletonList(userAcctId));
    }

    @Override
    protected int removeUserGrpsFromUsers(final List<Long> userAcctIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove userGrps from userAccts ======> userAcctIds: %s", JsonUtil.toJsonStr(userAcctIds)));
        }
        return userAcctMasterDbSvc.removeUserGrpsFromUsers(null, userAcctIds);
    }

    @Override
    protected int assignRolesToUser(final List<Long> roleIds, final Long userAcctId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign roles to userAcct =====> roleIds: %s, userAcctId: %s",
                    JsonUtil.toJsonStr(roleIds), userAcctId));
        }
        return userAcctMasterDbSvc.assignRolesToUser(roleIds, userAcctId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeRolesFromUser(final List<Long> roleIds, final Long userAcctId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove roles from userAcct =====> roleIds: %s, userAcctId: %s",
                    JsonUtil.toJsonStr(roleIds), userAcctId));
        }
        return userAcctMasterDbSvc.removeRolesFromUsers(roleIds, Collections.singletonList(userAcctId));
    }

    @Override
    protected int removeRolesFromUsers(final List<Long> userAcctIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove roles from userAccts ======> userAcctIds: %s", JsonUtil.toJsonStr(userAcctIds)));
        }
        return userAcctMasterDbSvc.removeRolesFromUsers(null, userAcctIds);
    }

    @Override
    protected int assignSysResRolesToUser(final List<Long> sysResRoleIds, final Long userAcctId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign sysResRoles to userAcct =====> sysResRoleIds: %s, userAcctId: %s",
                    JsonUtil.toJsonStr(sysResRoleIds), userAcctId));
        }
        return userAcctMasterDbSvc.assignSysResRolesToUser(sysResRoleIds, userAcctId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeSysResRolesFromUser(final List<Long> sysResRoleIds, final Long userAcctId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResRoles from userAcct =====> sysResRoleIds: %s, userAcctId: %s",
                    JsonUtil.toJsonStr(sysResRoleIds), userAcctId));
        }
        return userAcctMasterDbSvc.removeSysResRolesFromUsers(sysResRoleIds, Collections.singletonList(userAcctId));
    }

    @Override
    protected int removeSysResRolesFromUsers(final List<Long> userAcctIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResRoles from userAccts ======> userAcctIds: %s", JsonUtil.toJsonStr(userAcctIds)));
        }
        return userAcctMasterDbSvc.removeSysResRolesFromUsers(null, userAcctIds);
    }

    @Override
    protected int assignTmpRolesToUser(final List<Long> tmpRoleIds, final Long userAcctId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign tmpRoles to userAcct =====> tmpRoleIds: %s, userAcctId: %s",
                    JsonUtil.toJsonStr(tmpRoleIds), userAcctId));
        }
        return userAcctMasterDbSvc.assignTmpRolesToUser(tmpRoleIds, userAcctId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeTmpRolesFromUser(final List<Long> tmpRoleIds, final Long userAcctId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove tmpRoles from userAcct =====> tmpRoleIds: %s, userAcctId: %s",
                    JsonUtil.toJsonStr(tmpRoleIds), userAcctId));
        }
        return userAcctMasterDbSvc.removeTmpRolesFromUsers(tmpRoleIds, Collections.singletonList(userAcctId));
    }

    @Override
    protected int removeTmpRolesFromUsers(final List<Long> userAcctIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove tmpRoles from userAccts ======> userAcctIds: %s", JsonUtil.toJsonStr(userAcctIds)));
        }
        return userAcctMasterDbSvc.removeTmpRolesFromUsers(null, userAcctIds);
    }

    @Override
    protected int assignTmpSysResRolesToUser(final List<Long> tmpSysResRoleIds, final Long userAcctId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign tmpSysResRoles to userAcct =====> tmpSysResRoleIds: %s, userAcctId: %s",
                    JsonUtil.toJsonStr(tmpSysResRoleIds), userAcctId));
        }
        return userAcctMasterDbSvc.assignTmpSysResRolesToUser(tmpSysResRoleIds, userAcctId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeTmpSysResRolesFromUser(final List<Long> tmpSysResRoleIds, final Long userAcctId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove tmpSysResRoles from userAcct =====> tmpSysResRoleIds: %s, userAcctId: %s",
                    JsonUtil.toJsonStr(tmpSysResRoleIds), userAcctId));
        }
        return userAcctMasterDbSvc.removeTmpSysResRolesFromUsers(tmpSysResRoleIds, Collections.singletonList(userAcctId));
    }

    @Override
    protected int removeTmpSysResRolesFromUsers(final List<Long> userAcctIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove tmpSysResRoles from userAccts ======> userAcctIds: %s", JsonUtil.toJsonStr(userAcctIds)));
        }
        return userAcctMasterDbSvc.removeTmpSysResRolesFromUsers(null, userAcctIds);
    }

    @Override
    protected UserAcctUpdateCtx getUpdateContext(final UserAcctMasterVO userAcct, final UserAcctSlaveVO existingUserAcct) {
        return analyser.analyseUpdatedAttrs(userAcct, existingUserAcct);
    }

    @Override
    protected void doSave(final UserAcct newEntity) {
        userAcctMasterDbSvc.dbInsert(newEntity);
    }

    @Override
    protected int doUpdate(final UserAcct example) {
        return userAcctMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return userAcctMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    public UserAcct toNewEntity(final UserAcctMasterVO userAcct) {
        final UserAcct newEntity = new UserAcct();
        assembleCommonAttrsOnInsert(newEntity, userAcct);
        assembleCommonAttrs(newEntity, userAcct);
        newEntity.setCorpSid(userAcct.getCorpId());
        newEntity.setTenantSid(userAcct.getTenantId());
        return newEntity;
    }

    @Override
    public UserAcct toUpdatableObj(final UserAcctUpdatableObj updatableVO) {
        final UserAcct example = new UserAcct();
        assembleUpdatableAttrs(example, updatableVO);
        return example;
    }
}
