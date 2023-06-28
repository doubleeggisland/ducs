package com.ioiox.dei.duc.std.data.svc.impl.master;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.exception.DeiServiceException;
import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterStdDataSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.core.utils.RelationshipsAnalyser;
import com.ioiox.dei.duc.beans.entity.BaseUser;
import com.ioiox.dei.duc.beans.model.master.UserUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.UserUpdateCtx;
import com.ioiox.dei.duc.beans.model.master.UserDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.UserMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.master.UserSysPrjPrivilegeMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.*;
import com.ioiox.dei.duc.std.data.svc.master.UserMasterStdDataSvc;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class BaseUserMasterStdDataSvc<
        T extends UserMasterStdVO,
        O extends UserUpdatableObj,
        C extends UserUpdateCtx<O>,
        D extends UserDelParam,
        S extends UserSlaveStdVO<
                ? extends RoleSlaveStdVO,
                ? extends SysResRoleSlaveStdVO,
                ? extends TmpRoleSlaveStdVO,
                ? extends TmpSysResRoleSlaveStdVO,
                ? extends UserGrpSlaveStdVO<? extends RoleSlaveStdVO, ? extends SysResRoleSlaveStdVO>>,
        E extends BaseUser>
        extends BaseDeiMasterStdDataSvc<T, O, E>
        implements UserMasterStdDataSvc<T, D> {

    private static final Logger log = LoggerFactory.getLogger(BaseUserMasterStdDataSvc.class);

    @Override
    public Long save(final T user) {
        if (Objects.isNull(user)) {
            return DeiGlobalConstant.DEFAULT_SID;
        }
        final E newEntity = toNewEntity(user);
        newEntity.setDefaultValueIfNeed();
        doSave(newEntity);
        final Long userId = newEntity.getSid();
        syncSysPrjPrivileges(user.getSysPrjPrivileges(), null, userId, user.getCreatedBy());
        syncUserGrps(user.getUserGrpIds(), null, userId, user.getCreatedBy());
        syncRoles(user.getRoleIds(), null, userId, user.getCreatedBy());
        syncSysResRoles(user.getSysResRoleIds(), null, userId, user.getCreatedBy());
        syncTmpRoles(user.getTmpRoleIds(), null, userId, user.getCreatedBy());
        syncTmpSysResRoles(user.getTmpSysResRoleIds(), null, userId, user.getCreatedBy());
        return userId;
    }

    @Override
    public boolean update(final T user) {
        if (Objects.isNull(user)
                || Objects.isNull(user.getId())) {
            throw new DeiServiceException("Please choose a user to update!");
        }
        final S existingUser = getExistingUser(user.getId());
        if (Objects.isNull(existingUser)) {
            throw new DeiServiceException(String.format("User doesn't exist =====> id: %s", user.getId()));
        }
        return update(user, existingUser);
    }

    boolean update(final T user, final S existingUser) {
        final int numOfSysPrjPrivilegesSync = syncSysPrjPrivileges(user.getSysPrjPrivileges(), existingUser.getSysPrjPrivileges(), existingUser.getId(), user.getUpdatedBy());

        final List<Long> existingUserGrpIds = DeiCollectionUtil.isEmpty(existingUser.getUserGrps())
                ? Collections.emptyList() : existingUser.getUserGrps().stream().map(UserGrpSlaveStdVO::getId).collect(Collectors.toList());
        final int numOfUserGrpsSync = syncUserGrps(user.getUserGrpIds(), existingUserGrpIds, existingUser.getId(), user.getUpdatedBy());

        final List<Long> existingRoleIds = DeiCollectionUtil.isEmpty(existingUser.getRoles())
                ? Collections.emptyList() : existingUser.getRoles().stream().map(RoleSlaveStdVO::getId).collect(Collectors.toList());
        final int numOfRolesSync = syncRoles(user.getRoleIds(), existingRoleIds, existingUser.getId(), user.getUpdatedBy());

        final List<Long> existingSysResRoleIds = DeiCollectionUtil.isEmpty(existingUser.getSysResRoles())
                ? Collections.emptyList() : existingUser.getSysResRoles().stream().map(SysResRoleSlaveStdVO::getId).collect(Collectors.toList());
        final int numOfSysResRolesSync = syncSysResRoles(user.getSysResRoleIds(), existingSysResRoleIds, existingUser.getId(), user.getUpdatedBy());

        final List<Long> existingTmpRoleIds = DeiCollectionUtil.isEmpty(existingUser.getTmpRoles())
                ? Collections.emptyList() : existingUser.getTmpRoles().stream().map(TmpRoleSlaveStdVO::getId).collect(Collectors.toList());
        final int numOfTmpRolesSync = syncTmpRoles(user.getTmpRoleIds(), existingTmpRoleIds, existingUser.getId(), user.getUpdatedBy());

        final List<Long> existingTmpSysResRoleIds = DeiCollectionUtil.isEmpty(existingUser.getTmpSysResRoles())
                ? Collections.emptyList() : existingUser.getTmpSysResRoles().stream().map(TmpSysResRoleSlaveStdVO::getId).collect(Collectors.toList());
        final int numOfTmpSysResRolesSync = syncTmpSysResRoles(user.getTmpSysResRoleIds(), existingTmpSysResRoleIds, existingUser.getId(), user.getUpdatedBy());

        final C updateCtx = getUpdateContext(user, existingUser);
        final O updatableObj = updateCtx.getUpdatableObj();
        if (updatableObj.attrsNotUpdated()) {
            if (numOfSysPrjPrivilegesSync > 0
                    || numOfUserGrpsSync > 0
                    || numOfRolesSync > 0
                    || numOfSysResRolesSync > 0
                    || numOfTmpRolesSync > 0
                    || numOfTmpSysResRolesSync > 0) {
                updatableObj.updateLastModifiedTime();
            }
        }
        final boolean updated = updatableObj.updated();
        if (updated) {
            if (log.isInfoEnabled()) {
                if (updatableObj.attrsUpdated()) {
                    final Map<String, String> updateSummary = updatableObj.updateSummary();
                    log.info(String.format("update User =====> id: %s, updateSummary: %s", existingUser.getId(), JsonUtil.toJsonStr(updateSummary)));
                } else {
                    log.info(String.format("update User lastModifiedTime =====> id: %s, lastUpdateTime: %s",
                            existingUser.getId(), updatableObj.formatLastModifiedTime()));
                }
            }
            final E example = toUpdatableObj(updatableObj);
            assembleCommonAttrsOnUpdate(example, existingUser, user);
            final int updatedRows = doUpdate(example);
            if (DeiGlobalConstant.ZERO == updatedRows) {
                throw new DeiServiceException(String.format("User has been updated by others =====> id: %s, versionNum: %s",
                        existingUser.getId(), existingUser.getVersionNum()));
            }
        }
        return updated;
    }

    @Override
    public int remove(final D delParam) {
        final Map<String, Object> deleteParams =
                Objects.isNull(delParam) ? null : delParam.deleteParams();
        if (DeiCollectionUtil.isEmpty(deleteParams)) {
            return DeiGlobalConstant.ZERO;
        }
        final List<S> existingUses = queryExistingUsers(delParam);
        if (DeiCollectionUtil.isEmpty(existingUses)) {
            throw new DeiServiceException(String.format("Cannot find any users as per delParam =====> %s", JsonUtil.toJsonStr(delParam)));
        }
        final List<Long> userIds = existingUses.stream().map(S::getId).collect(Collectors.toList());
        final int numOfSysPrjPrivilegesRemoved = removeSysPrjPrivileges(userIds);
        final int numOfUserGrpsRemoved = removeUserGrpsFromUsers(userIds);
        final int numOfRolesRemoved = removeRolesFromUsers(userIds);
        final int numOfSysResRolesRemoved = removeSysResRolesFromUsers(userIds);
        final int numOfTmpRolesRemoved = removeTmpRolesFromUsers(userIds);
        final int numOfTmpSysResRolesRemoved = removeTmpSysResRolesFromUsers(userIds);

        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysPrjPrivileges from users as per userIds: %s =====> numOfSysPrjPrivilegesRemoved: %s", JsonUtil.toJsonStr(userIds), numOfSysPrjPrivilegesRemoved));
            log.info(String.format("remove userGrps from users as per userIds: %s =====> numOfUserGrpsRemoved: %s", JsonUtil.toJsonStr(userIds), numOfUserGrpsRemoved));

            log.info(String.format("remove roles from users as per userIds: %s =====> numOfRolesRemoved: %s", JsonUtil.toJsonStr(userIds), numOfRolesRemoved));
            log.info(String.format("remove sysResRoles from users as per userIds: %s =====> numOfSysResRolesRemoved: %s", JsonUtil.toJsonStr(userIds), numOfSysResRolesRemoved));
            log.info(String.format("remove tmpRoles from users as per userIds: %s =====> numOfTmpRolesRemoved: %s", JsonUtil.toJsonStr(userIds), numOfTmpRolesRemoved));
            log.info(String.format("remove tmpSysResRoles from users as per userIds: %s =====> numOfSysResRolesRemoved: %s", JsonUtil.toJsonStr(userIds), numOfTmpSysResRolesRemoved));
        }

        final int numOfUsersRemoved = doRemove(deleteParams);
        if (log.isInfoEnabled()) {
            log.info(String.format("remove users as per params: %s =====> numOfUsersRemoved: %s", JsonUtil.toJsonStr(delParam), numOfUsersRemoved));
        }
        return numOfUsersRemoved;
    }

    int syncSysPrjPrivileges(final List<UserSysPrjPrivilegeMasterStdVO> sysPrjPrivileges,
                             final List<UserSysPrjPrivilegeSlaveStdVO> existingSysPrjPrivileges,
                             final Long userId,
                             final String operator) {
        if (DeiCollectionUtil.isNotEmpty(sysPrjPrivileges)) {
            sysPrjPrivileges.forEach(sysPrjPrivilege -> {
                if (Objects.isNull(sysPrjPrivilege.getUserId())) {
                    sysPrjPrivilege.setUserId(userId);
                }
                if (StringUtils.isNotBlank(operator)) {
                    sysPrjPrivilege.setCreatedBy(operator);
                    sysPrjPrivilege.setUpdatedBy(operator);
                }
            });
        }
        return syncSysPrjPrivileges(sysPrjPrivileges, existingSysPrjPrivileges);
    }

    int syncUserGrps(final List<Long> userGrpIds, final List<Long> existingUserGrpIds, final Long userId, final String operator) {
        final RelationshipsAnalyser.DiffHolder<Long> diff =
                RelationshipsAnalyser.analysisDiff(userGrpIds, existingUserGrpIds);
        final int insertedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getNewRelationships())) {
            insertedRows = assignUserGrpsToUser(diff.getNewRelationships(), userId, operator);
        } else {
            insertedRows = DeiGlobalConstant.ZERO;
        }
        final int deletedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getRemovedRelationships())) {
            deletedRows = removeUserGrpsFromUser(diff.getRemovedRelationships(), userId, operator);
        } else {
            deletedRows = DeiGlobalConstant.ZERO;
        }
        if (log.isInfoEnabled()) {
            log.info(String.format("sync UserGrps for user =====> userId: %s, insertedRows: %s, deletedRows: %s",
                    userId, insertedRows, deletedRows));
        }
        return insertedRows + deletedRows;
    }

    int syncRoles(final List<Long> roleIds, final List<Long> existingRoleIds, final Long userId, final String operator) {
        final RelationshipsAnalyser.DiffHolder<Long> diff =
                RelationshipsAnalyser.analysisDiff(roleIds, existingRoleIds);
        final int insertedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getNewRelationships())) {
            insertedRows = assignRolesToUser(diff.getNewRelationships(), userId, operator);
        } else {
            insertedRows = DeiGlobalConstant.ZERO;
        }
        final int deletedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getRemovedRelationships())) {
            deletedRows = removeRolesFromUser(diff.getRemovedRelationships(), userId, operator);
        } else {
            deletedRows = DeiGlobalConstant.ZERO;
        }
        if (log.isInfoEnabled()) {
            log.info(String.format("sync Roles for user =====> userId: %s, insertedRows: %s, deletedRows: %s",
                    userId, insertedRows, deletedRows));
        }
        return insertedRows + deletedRows;
    }

    int syncSysResRoles(final List<Long> sysResRoleIds, final List<Long> existingSysResRoleIds, final Long userId, final String operator) {
        final RelationshipsAnalyser.DiffHolder<Long> diff =
                RelationshipsAnalyser.analysisDiff(sysResRoleIds, existingSysResRoleIds);
        final int insertedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getNewRelationships())) {
            insertedRows = assignSysResRolesToUser(diff.getNewRelationships(), userId, operator);
        } else {
            insertedRows = DeiGlobalConstant.ZERO;
        }
        final int deletedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getRemovedRelationships())) {
            deletedRows = removeSysResRolesFromUser(diff.getRemovedRelationships(), userId, operator);
        } else {
            deletedRows = DeiGlobalConstant.ZERO;
        }
        if (log.isInfoEnabled()) {
            log.info(String.format("sync SysResRoles for user =====> userId: %s, insertedRows: %s, deletedRows: %s",
                    userId, insertedRows, deletedRows));
        }
        return insertedRows + deletedRows;
    }

    int syncTmpRoles(final List<Long> tmpRoleIds, final List<Long> existingTmpRoleIds, final Long userId, final String operator) {
        final RelationshipsAnalyser.DiffHolder<Long> diff =
                RelationshipsAnalyser.analysisDiff(tmpRoleIds, existingTmpRoleIds);
        final int insertedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getNewRelationships())) {
            insertedRows = assignTmpRolesToUser(diff.getNewRelationships(), userId, operator);
        } else {
            insertedRows = DeiGlobalConstant.ZERO;
        }
        final int deletedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getRemovedRelationships())) {
            deletedRows = removeTmpRolesFromUser(diff.getRemovedRelationships(), userId, operator);
        } else {
            deletedRows = DeiGlobalConstant.ZERO;
        }
        if (log.isInfoEnabled()) {
            log.info(String.format("sync TmpRoles for user =====> userId: %s, insertedRows: %s, deletedRows: %s",
                    userId, insertedRows, deletedRows));
        }
        return insertedRows + deletedRows;
    }

    int syncTmpSysResRoles(final List<Long> tmpSysResRoleIds, final List<Long> existingTmpSysResRoleIds, final Long userId, final String operator) {
        final RelationshipsAnalyser.DiffHolder<Long> diff =
                RelationshipsAnalyser.analysisDiff(tmpSysResRoleIds, existingTmpSysResRoleIds);
        final int insertedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getNewRelationships())) {
            insertedRows = assignTmpSysResRolesToUser(diff.getNewRelationships(), userId, operator);
        } else {
            insertedRows = DeiGlobalConstant.ZERO;
        }
        final int deletedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getRemovedRelationships())) {
            deletedRows = removeTmpSysResRolesFromUser(diff.getRemovedRelationships(), userId, operator);
        } else {
            deletedRows = DeiGlobalConstant.ZERO;
        }
        if (log.isInfoEnabled()) {
            log.info(String.format("sync TmpSysResRoles for user =====> userId: %s, insertedRows: %s, deletedRows: %s",
                    userId, insertedRows, deletedRows));
        }
        return insertedRows + deletedRows;
    }

    protected void assembleCommonAttrs(final BaseUser newEntity, final UserMasterStdVO user) {
        newEntity.setUsername(user.getUsername());
        newEntity.setNickName(user.getNickName());
        newEntity.setMobile(user.getMobile());
        newEntity.setEmail(user.getEmail());
        newEntity.setStatus(user.getStatus());
        newEntity.setPwd(user.getPwd());
        newEntity.setAvatarUrl(user.getAvatarUrl());
    }

    protected void assembleUpdatableAttrs(final BaseUser example, final UserUpdatableObj updatableVO) {
        super.assembleCommonAttrs(example, updatableVO);
        if (Objects.nonNull(updatableVO.getUsername())) {
            example.setUsername(updatableVO.getUsername().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getNickName())) {
            example.setNickName(updatableVO.getNickName().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getMobile())) {
            example.setMobile(updatableVO.getMobile().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getEmail())) {
            example.setEmail(updatableVO.getEmail().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getStatus())) {
            example.setStatus(updatableVO.getStatus().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getPwd())) {
            example.setPwd(updatableVO.getPwd().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getAvatarUrl())) {
            example.setAvatarUrl(updatableVO.getAvatarUrl().getNewVal());
        }
    }

    protected abstract S getExistingUser(final Long id);
    protected abstract List<S> queryExistingUsers(final D delParam);

    protected abstract int syncSysPrjPrivileges(final List<UserSysPrjPrivilegeMasterStdVO> sysPrjPrivileges,
                                                final List<UserSysPrjPrivilegeSlaveStdVO> existingSysPrjPrivileges);
    protected abstract int removeSysPrjPrivileges(final List<Long> userIds);

    protected abstract int assignUserGrpsToUser(final List<Long> userGrpIds, final Long userId, final String operator);
    protected abstract int removeUserGrpsFromUser(final List<Long> userGrpIds, final Long userId, final String operator);
    protected abstract int removeUserGrpsFromUsers(final List<Long> userIds);

    protected abstract int assignRolesToUser(final List<Long> roleIds, final Long userId, final String operator);
    protected abstract int removeRolesFromUser(final List<Long> roleIds, final Long userId, final String operator);
    protected abstract int removeRolesFromUsers(final List<Long> userIds);

    protected abstract int assignSysResRolesToUser(final List<Long> sysResRoleIds, final Long userId, final String operator);
    protected abstract int removeSysResRolesFromUser(final List<Long> sysResRoleIds, final Long userId, final String operator);
    protected abstract int removeSysResRolesFromUsers(final List<Long> userIds);

    protected abstract int assignTmpRolesToUser(final List<Long> tmpRoleIds, final Long userId, final String operator);
    protected abstract int removeTmpRolesFromUser(final List<Long> tmpRoleIds, final Long userId, final String operator);
    protected abstract int removeTmpRolesFromUsers(final List<Long> userIds);

    protected abstract int assignTmpSysResRolesToUser(final List<Long> tmpSysResRoleIds, final Long userId, final String operator);
    protected abstract int removeTmpSysResRolesFromUser(final List<Long> tmpSysResRoleIds, final Long userId, final String operator);
    protected abstract int removeTmpSysResRolesFromUsers(final List<Long> userIds);

    protected abstract C getUpdateContext(final T user, final S existingUser);
    protected abstract void doSave(final E newEntity);
    protected abstract int doUpdate(final E example);
    protected abstract int doRemove(final Map<String, Object> deleteParams);
}
