package com.ioiox.dei.duc.std.data.svc.impl.master;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.exception.DeiServiceException;
import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterStdDataSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.core.utils.RelationshipsAnalyser;
import com.ioiox.dei.duc.beans.entity.UserGrp;
import com.ioiox.dei.duc.beans.model.UserGrpUpdatableObj;
import com.ioiox.dei.duc.beans.model.UserGrpUpdateCtx;
import com.ioiox.dei.duc.beans.vo.std.master.UserGrpDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.UserGrpMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.RoleSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResRoleSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.UserGrpSlaveStdVO;
import com.ioiox.dei.duc.std.data.svc.master.UserGrpMasterStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class BaseUserGrpMasterStdDataSvc<
        T extends UserGrpMasterStdVO,
        O extends UserGrpUpdatableObj,
        C extends UserGrpUpdateCtx<O>,
        D extends UserGrpDelParam,
        S extends UserGrpSlaveStdVO<? extends RoleSlaveStdVO, ? extends SysResRoleSlaveStdVO>,
        E extends UserGrp>
        extends BaseDeiMasterStdDataSvc<T, O, E>
        implements UserGrpMasterStdDataSvc<T, D> {

    private static final Logger log = LoggerFactory.getLogger(BaseUserGrpMasterStdDataSvc.class);

    @Override
    public Long save(final T userGrp) {
        if (Objects.isNull(userGrp)) {
            return DeiGlobalConstant.DEFAULT_SID;
        }
        final E newEntity = toNewEntity(userGrp);
        newEntity.setDefaultValueIfNeed();
        doSave(newEntity);
        final Long userGrpId = newEntity.getSid();
        syncRoles(userGrp.getRoleIds(), null, userGrpId, userGrp.getUpdatedBy());
        syncSysResRoles(userGrp.getSysResRoleIds(), null, userGrpId, userGrp.getUpdatedBy());
        return userGrpId;
    }

    @Override
    public boolean update(final T userGrp) {
        if (Objects.isNull(userGrp)
                || Objects.isNull(userGrp.getId())) {
            throw new DeiServiceException("Please choose a userGrp to update!");
        }
        final S existingUserGrp = getExistingUserGrp(userGrp.getId());
        if (Objects.isNull(existingUserGrp)) {
            throw new DeiServiceException(String.format("UserGrp doesn't exist =====> id: %s", userGrp.getId()));
        }
        return update(userGrp, existingUserGrp);
    }

    boolean update(final T userGrp,
                   final S existingUserGrp) {
        final int numOfRolesSync =
                syncRoles(userGrp.getRoleIds(),
                        DeiCollectionUtil.isEmpty(existingUserGrp.getRoles()) ? Collections.emptyList() : existingUserGrp.getRoles().stream().map(RoleSlaveStdVO::getId).collect(Collectors.toList()),
                        existingUserGrp.getId(), userGrp.getUpdatedBy());
        final int numOfSysResRolesSync =
                syncSysResRoles(userGrp.getSysResRoleIds(),
                        DeiCollectionUtil.isEmpty(existingUserGrp.getSysResRoles()) ? Collections.emptyList() : existingUserGrp.getSysResRoles().stream().map(SysResRoleSlaveStdVO::getId).collect(Collectors.toList()),
                        existingUserGrp.getId(), userGrp.getUpdatedBy());
        final C updateCtx = getUpdateContext(userGrp, existingUserGrp);
        final O updatableObj = updateCtx.getUpdatableObj();
        if (updatableObj.attrsNotUpdated()) {
            if (numOfRolesSync > 0
                    || numOfSysResRolesSync > 0) {
                updatableObj.updateLastModifiedTime();
            }
        }
        final boolean updated = updatableObj.updated();
        if (updated) {
            if (log.isInfoEnabled()) {
                if (updatableObj.attrsUpdated()) {
                    final Map<String, String> updateSummary = updatableObj.updateSummary();
                    log.info(String.format("update UserGrp =====> id: %s, updateSummary: %s", existingUserGrp.getId(), JsonUtil.toJsonStr(updateSummary)));
                } else {
                    log.info(String.format("update UserGrp lastModifiedTime =====> id: %s, lastUpdateTime: %s",
                            existingUserGrp.getId(), updatableObj.formatLastModifiedTime()));
                }
            }
            final E example = toUpdatableObj(updatableObj);
            assembleCommonAttrsOnUpdate(example, existingUserGrp, userGrp);
            final int updatedRows = doUpdate(example);
            if (DeiGlobalConstant.ZERO == updatedRows) {
                throw new DeiServiceException(String.format("UserGrp has been updated by others =====> id: %s, versionNum: %s",
                        existingUserGrp.getId(), existingUserGrp.getVersionNum()));
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
        final List<S> existingUserGrps = queryExistingUserGrps(delParam);
        if (DeiCollectionUtil.isEmpty(existingUserGrps)) {
            throw new DeiServiceException(String.format("Cannot find any userGrps as per delParam =====> %s", JsonUtil.toJsonStr(delParam)));
        }
        final List<Long> userGrpIds = existingUserGrps.stream().map(S::getId).collect(Collectors.toList());
        final int numOfRolesRemoved = removeRolesFromUserGrps(userGrpIds);
        final int numOfSysResRolesRemoved = removeSysResRolesFromUserGrps(userGrpIds);
        if (log.isInfoEnabled()) {
            log.info(String.format("remove roles from userGrps as per userGrpIds: %s =====> numOfRolesRemoved: %s", JsonUtil.toJsonStr(userGrpIds), numOfRolesRemoved));
            log.info(String.format("remove sysResRoles from userGrps as per userGrpIds: %s =====> numOfSysResRolesRemoved: %s", JsonUtil.toJsonStr(userGrpIds), numOfSysResRolesRemoved));
        }
        final int numOfUserGrpsRemoved = doRemove(deleteParams);
        if (log.isInfoEnabled()) {
            log.info(String.format("remove userGrps as per params: %s =====> numOfUserGrpsRemoved: %s", JsonUtil.toJsonStr(delParam), numOfUserGrpsRemoved));
        }
        return numOfUserGrpsRemoved;
    }

    int syncRoles(final List<Long> roleIds, final List<Long> existingRoleIds, final Long userGrpId, final String operator) {
        final RelationshipsAnalyser.DiffHolder<Long> diff =
                RelationshipsAnalyser.analysisDiff(roleIds, existingRoleIds);
        final int insertedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getNewRelationships())) {
            insertedRows = assignRolesToUserGrp(diff.getNewRelationships(), userGrpId, operator);
        } else {
            insertedRows = DeiGlobalConstant.ZERO;
        }
        final int deletedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getRemovedRelationships())) {
            deletedRows = removeRolesFromUserGrp(diff.getRemovedRelationships(), userGrpId, operator);
        } else {
            deletedRows = DeiGlobalConstant.ZERO;
        }
        if (log.isInfoEnabled()) {
            log.info(String.format("sync Roles for userGrp =====> userGrpId: %s, insertedRows: %s, deletedRows: %s",
                    userGrpId, insertedRows, deletedRows));
        }
        return insertedRows + deletedRows;
    }

    int syncSysResRoles(final List<Long> sysResRoleIds, final List<Long> existingSysResRoleIds, final Long userGrpId, final String operator) {
        final RelationshipsAnalyser.DiffHolder<Long> diff =
                RelationshipsAnalyser.analysisDiff(sysResRoleIds, existingSysResRoleIds);
        final int insertedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getNewRelationships())) {
            insertedRows = assignSysResRolesToUserGrp(diff.getNewRelationships(), userGrpId, operator);
        } else {
            insertedRows = DeiGlobalConstant.ZERO;
        }
        final int deletedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getRemovedRelationships())) {
            deletedRows = removeSysResRolesFromUserGrp(diff.getRemovedRelationships(), userGrpId, operator);
        } else {
            deletedRows = DeiGlobalConstant.ZERO;
        }
        if (log.isInfoEnabled()) {
            log.info(String.format("sync SysResRoles for userGrp =====> insertedRows: %s, deletedRows: %s",
                    insertedRows, deletedRows));
        }
        return insertedRows + deletedRows;
    }

    protected abstract S getExistingUserGrp(final Long id);
    protected abstract List<S> queryExistingUserGrps(final D delParam);

    protected abstract int assignRolesToUserGrp(final List<Long> roleIds, final Long userGrpId, final String operator);
    protected abstract int removeRolesFromUserGrp(final List<Long> roleIds, final Long userGrpId, final String operator);
    protected abstract int removeRolesFromUserGrps(final List<Long> userGrpIds);

    protected abstract int assignSysResRolesToUserGrp(final List<Long> sysResRoleIds, final Long userGrpId, final String operator);
    protected abstract int removeSysResRolesFromUserGrp(final List<Long> sysResRoleIds, final Long userGrpId, final String operator);
    protected abstract int removeSysResRolesFromUserGrps(final List<Long> userGrpIds);

    protected abstract C getUpdateContext(final T userGrp, final S existingUserGrp);
    protected abstract void doSave(final E newEntity);
    protected abstract int doUpdate(final E example);
    protected abstract int doRemove(final Map<String, Object> deleteParams);

    protected void assembleCommonAttrs(final UserGrp newEntity, final UserGrpMasterStdVO userGrp) {
        newEntity.setCode(userGrp.getCode());
        newEntity.setName(userGrp.getName());
        newEntity.setMemo(userGrp.getMemo());
        newEntity.setStatus(userGrp.getStatus());
    }

    protected void assembleUpdatableAttrs(final UserGrp example, final UserGrpUpdatableObj updatableVO) {
        super.assembleCommonAttrs(example, updatableVO);
        if (Objects.nonNull(updatableVO.getCode())) {
            example.setCode(updatableVO.getCode().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getName())) {
            example.setName(updatableVO.getName().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getMemo())) {
            example.setMemo(updatableVO.getMemo().getNewVal());
        }
        if (Objects.nonNull(updatableVO.getStatus())) {
            example.setStatus(updatableVO.getStatus().getNewVal());
        }
    }
}