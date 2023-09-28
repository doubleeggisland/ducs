package com.ioiox.dei.duc.std.data.svc.impl.master.tenant;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.BaseUser;
import com.ioiox.dei.duc.beans.entity.TenantUser;
import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.beans.model.master.tenant.*;
import com.ioiox.dei.duc.beans.model.slave.*;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserQueryParam;
import com.ioiox.dei.duc.beans.vo.std.master.UserSysPrjPrivilegeMasterVO;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.UserSysPrjPrivilegeSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserSlaveVO;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseUserMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.UserSysPrjPrivilegeMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.tenant.TenantUserMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("tenantUserMasterStdDataSvc")
public class TenantUserMasterStdDataSvcImpl
        extends BaseUserMasterStdDataSvc<TenantUserMasterVO, TenantUserUpdatableObj, TenantUserUpdateCtx, TenantUserDelParam, TenantUserSlaveVO, TenantUser>
        implements TenantUserMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(TenantUserMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("tenantUserMasterDbSvc")
    private TenantUserMasterDbSvc tenantUserMasterDbSvc;

    @Autowired
    @Qualifier("tenantUserSlaveStdDataSvc")
    private TenantUserSlaveStdDataSvc tenantUserSlaveStdDataSvc;

    @Autowired
    @Qualifier("tenantUserSysPrjPrivilegeMasterStdDataSvc")
    private UserSysPrjPrivilegeMasterStdDataSvc tenantUserSysPrjPrivilegeMasterStdDataSvc;

    private final TenantUserUpdatableAttrsAnalyser updatableAttrsAnalyser = new TenantUserUpdatableAttrsAnalyser();

    @Override
    protected TenantUserSlaveVO getExistingUser(final Long id) {
        return tenantUserSlaveStdDataSvc.queryByPk(id,
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
    protected List<TenantUserSlaveVO> queryExistingUsers(final TenantUserDelParam delParam) {
        final TenantUserQueryParam queryParam = new TenantUserQueryParam.Builder()
                .tenantIds(delParam.getTenantIds())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return tenantUserSlaveStdDataSvc.queryByParam(queryParam,
                new UserQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build());
    }

    @Override
    protected int syncSysPrjPrivileges(final List<UserSysPrjPrivilegeMasterVO> sysPrjPrivileges,
                                       final List<UserSysPrjPrivilegeSlaveVO> existingSysPrjPrivileges) {
        return tenantUserSysPrjPrivilegeMasterStdDataSvc.sync(sysPrjPrivileges, existingSysPrjPrivileges);
    }

    @Override
    protected int removeSysPrjPrivileges(final List<Long> tenantUserIds) {
        final int numOfSysPrjPrivilegesRemoved =
                tenantUserSysPrjPrivilegeMasterStdDataSvc.removeByUserIds(tenantUserIds);
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysPrjPrivileges from TenantUsers =====> tenantUserIds: %s, numOfSysPrjPrivilegesRemoved: %s" ,
                    JsonUtil.toJsonStr(tenantUserIds), numOfSysPrjPrivilegesRemoved));
        }
        return numOfSysPrjPrivilegesRemoved;
    }

    @Override
    protected int assignUserGrpsToUser(final List<Long> userGrpIds, final Long tenantUserId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign userGrps to TenantUser =====> userGrpIds: %s, tenantUserId: %s",
                    JsonUtil.toJsonStr(userGrpIds), tenantUserId));
        }
        return tenantUserMasterDbSvc.assignUserGrpsToUser(userGrpIds, tenantUserId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeUserGrpsFromUser(final List<Long> userGrpIds, final Long tenantUserId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove userGrps from TenantUser =====> userGrpIds: %s, tenantUserId: %s",
                    JsonUtil.toJsonStr(userGrpIds), tenantUserId));
        }
        return tenantUserMasterDbSvc.removeUserGrpsFromUsers(userGrpIds, Collections.singletonList(tenantUserId));
    }

    @Override
    protected int removeUserGrpsFromUsers(final List<Long> tenantUserIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove userGrps from TenantUsers ======> userAcctIds: %s", JsonUtil.toJsonStr(tenantUserIds)));
        }
        return tenantUserMasterDbSvc.removeUserGrpsFromUsers(null, tenantUserIds);
    }

    @Override
    protected int assignRolesToUser(final List<Long> roleIds, final Long tenantUserId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign roles to TenantUser =====> roleIds: %s, tenantUserId: %s",
                    JsonUtil.toJsonStr(roleIds), tenantUserId));
        }
        return tenantUserMasterDbSvc.assignRolesToUser(roleIds, tenantUserId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeRolesFromUser(final List<Long> roleIds, final Long tenantUserId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove roles from TenantUser =====> roleIds: %s, tenantUserId: %s",
                    JsonUtil.toJsonStr(roleIds), tenantUserId));
        }
        return tenantUserMasterDbSvc.removeRolesFromUsers(roleIds, Collections.singletonList(tenantUserId));
    }

    @Override
    protected int removeRolesFromUsers(final List<Long> tenantUserIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove roles from TenantUsers ======> tenantUserIds: %s", JsonUtil.toJsonStr(tenantUserIds)));
        }
        return tenantUserMasterDbSvc.removeRolesFromUsers(null, tenantUserIds);
    }

    @Override
    protected int assignSysResRolesToUser(final List<Long> sysResRoleIds, final Long tenantUserId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign sysResRoles to TenantUser =====> sysResRoleIds: %s, tenantUserId: %s",
                    JsonUtil.toJsonStr(sysResRoleIds), tenantUserId));
        }
        return tenantUserMasterDbSvc.assignSysResRolesToUser(sysResRoleIds, tenantUserId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeSysResRolesFromUser(final List<Long> sysResRoleIds, final Long tenantUserId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResRoles from TenantUser =====> sysResRoleIds: %s, tenantUserId: %s",
                    JsonUtil.toJsonStr(sysResRoleIds), tenantUserId));
        }
        return tenantUserMasterDbSvc.removeSysResRolesFromUsers(sysResRoleIds, Collections.singletonList(tenantUserId));
    }

    @Override
    protected int removeSysResRolesFromUsers(final List<Long> tenantUserIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResRoles from TenantUsers ======> tenantUserIds: %s", JsonUtil.toJsonStr(tenantUserIds)));
        }
        return tenantUserMasterDbSvc.removeSysResRolesFromUsers(null, tenantUserIds);
    }

    @Override
    protected int assignTmpRolesToUser(final List<Long> tmpRoleIds, final Long tenantUserId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign tmpRoles to TenantUser =====> tmpRoleIds: %s, tenantUserId: %s",
                    JsonUtil.toJsonStr(tmpRoleIds), tenantUserId));
        }
        return tenantUserMasterDbSvc.assignTmpRolesToUser(tmpRoleIds, tenantUserId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeTmpRolesFromUser(final List<Long> tmpRoleIds, final Long tenantUserId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove tmpRoles from TenantUser =====> tmpRoleIds: %s, tenantUserId: %s",
                    JsonUtil.toJsonStr(tmpRoleIds), tenantUserId));
        }
        return tenantUserMasterDbSvc.removeTmpRolesFromUsers(tmpRoleIds, Collections.singletonList(tenantUserId));
    }

    @Override
    protected int removeTmpRolesFromUsers(final List<Long> tenantUserIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove tmpRoles from TenantUsers ======> tenantUserIds: %s", JsonUtil.toJsonStr(tenantUserIds)));
        }
        return tenantUserMasterDbSvc.removeTmpRolesFromUsers(null, tenantUserIds);
    }

    @Override
    protected int assignTmpSysResRolesToUser(final List<Long> tmpSysResRoleIds, final Long tenantUserId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign tmpSysResRoles to TenantUser =====> tmpSysResRoleIds: %s, tenantUserId: %s",
                    JsonUtil.toJsonStr(tmpSysResRoleIds), tenantUserId));
        }
        return tenantUserMasterDbSvc.assignTmpSysResRolesToUser(tmpSysResRoleIds, tenantUserId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeTmpSysResRolesFromUser(final List<Long> tmpSysResRoleIds, final Long tenantUserId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove tmpSysResRoles from TenantUser =====> tmpSysResRoleIds: %s, tenantUserId: %s",
                    JsonUtil.toJsonStr(tmpSysResRoleIds), tenantUserId));
        }
        return tenantUserMasterDbSvc.removeTmpSysResRolesFromUsers(tmpSysResRoleIds, Collections.singletonList(tenantUserId));
    }

    @Override
    protected int removeTmpSysResRolesFromUsers(final List<Long> tenantUserIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove tmpSysResRoles from TenantUsers ======> tenantUserIds: %s", JsonUtil.toJsonStr(tenantUserIds)));
        }
        return tenantUserMasterDbSvc.removeTmpSysResRolesFromUsers(null, tenantUserIds);
    }

    @Override
    protected TenantUserUpdateCtx getUpdateContext(final TenantUserMasterVO tenantUser, final TenantUserSlaveVO existingTenantUser) {
        return updatableAttrsAnalyser.analyseUpdatedAttrs(tenantUser, existingTenantUser);
    }

    @Override
    protected void doSave(final TenantUser newEntity) {
        tenantUserMasterDbSvc.dbInsert(newEntity);
    }

    @Override
    protected int doUpdate(final TenantUser example) {
        return tenantUserMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return tenantUserMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    public TenantUser toNewEntity(final TenantUserMasterVO tenantUser) {
        final TenantUser newEntity = new TenantUser();
        assembleCommonAttrsOnInsert(newEntity, tenantUser);
        assembleCommonAttrs(newEntity, tenantUser);
        newEntity.setTenantSid(tenantUser.getTenantId());
        return newEntity;
    }

    @Override
    public TenantUser toUpdatableObj(final TenantUserUpdatableObj updatableObj) {
        final TenantUser example = new TenantUser();
        assembleCommonUpdatableAttrs(example, updatableObj);
        return example;
    }
}
