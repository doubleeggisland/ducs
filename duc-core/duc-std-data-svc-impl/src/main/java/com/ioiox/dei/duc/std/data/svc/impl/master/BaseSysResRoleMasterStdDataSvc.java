package com.ioiox.dei.duc.std.data.svc.impl.master;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.exception.DeiServiceException;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.core.utils.RelationshipsAnalyser;
import com.ioiox.dei.duc.beans.entity.Role;
import com.ioiox.dei.duc.beans.model.master.BaseRoleUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.BaseRoleUpdateCtx;
import com.ioiox.dei.duc.beans.model.master.BaseRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.BaseRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.BaseRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.master.SysResRoleMasterStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class BaseSysResRoleMasterStdDataSvc<
        T extends BaseRoleMasterVO,
        O extends BaseRoleUpdatableObj,
        C extends BaseRoleUpdateCtx<O>,
        D extends BaseRoleDelParam,
        S extends BaseRoleSlaveVO,
        E extends Role>
        extends CommonRoleMasterStdDataSvc<T, O, E>
        implements SysResRoleMasterStdDataSvc<T, D> {

    private static final Logger log = LoggerFactory.getLogger(BaseSysResRoleMasterStdDataSvc.class);

    @Override
    public Long save(final T sysResRole) {
        if (Objects.isNull(sysResRole)) {
            return DeiGlobalConstant.DEFAULT_SID;
        }
        final E newEntity = toNewEntity(sysResRole);
        newEntity.setDefaultValueIfNeed();
        doSave(newEntity);
        final long id = newEntity.getSid();
        syncSysResources(getSysResIds(sysResRole), null, id, sysResRole.getUpdatedBy());
        return id;
    }

    @Override
    public boolean update(final T sysResRole) {
        if (Objects.isNull(sysResRole)
                || Objects.isNull(sysResRole.getId())) {
            throw new DeiServiceException("Please choose a sysResRole to update!");
        }
        final S existingSysResRole = getExistingSysResRole(sysResRole.getId());
        if (Objects.isNull(existingSysResRole)) {
            throw new DeiServiceException(String.format("SysResRole doesn't exist =====> id: %s", sysResRole.getId()));
        }
        return update(sysResRole, existingSysResRole);
    }

    boolean update(final T sysResRole,
                   final S existingSysResRole) {
        final int numOfSysResourcesSync =
                syncSysResources(getSysResIds(sysResRole), getExistingSysResIds(existingSysResRole), existingSysResRole.getId(), sysResRole.getUpdatedBy());
        final C updateCtx = getUpdateContext(sysResRole, existingSysResRole);
        final O updatableObj = updateCtx.getUpdatableObj();
        if (updatableObj.attrsNotUpdated()) {
            if (numOfSysResourcesSync > 0) {
                updatableObj.updateLastModifiedTime();
            }
        }
        final boolean updated = updatableObj.updated();
        if (updated) {
            if (log.isInfoEnabled()) {
                if (updatableObj.attrsUpdated()) {
                    final Map<String, String> updateSummary = updatableObj.updateSummary();
                    log.info(String.format("update SysResRole =====> id: %s, updateSummary: %s", existingSysResRole.getId(), JsonUtil.toJsonStr(updateSummary)));
                } else {
                    log.info(String.format("update SysResRole lastModifiedTime =====> id: %s, lastUpdateTime: %s",
                            existingSysResRole.getId(), updatableObj.formatLastModifiedTime()));
                }
            }
            final E example = toUpdatableObj(updatableObj);
            assembleCommonAttrsOnUpdate(example, existingSysResRole, sysResRole);
            final int updatedRows = doUpdate(example);
            if (DeiGlobalConstant.ZERO == updatedRows) {
                throw new DeiServiceException(String.format("SysResRole has been updated by others =====> id: %s, versionNum: %s",
                        existingSysResRole.getId(), existingSysResRole.getVersionNum()));
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
        final List<S> existingSysResRoles = queryExistingSysResRoles(delParam);
        if (DeiCollectionUtil.isEmpty(existingSysResRoles)) {
            throw new DeiServiceException(String.format("Cannot find any sysResRoles as per delParam =====> %s", JsonUtil.toJsonStr(delParam)));
        }
        final List<Long> sysResRoleIds = existingSysResRoles.stream().map(S::getId).collect(Collectors.toList());
        final int numOfSysResourcesRemoved = removeSysResourcesFromSysResRoles(sysResRoleIds);
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResources from sysResRole as per sysResRoleIds: %s =====> numOfSysResourcesRemoved: %s", JsonUtil.toJsonStr(sysResRoleIds), numOfSysResourcesRemoved));
        }
        final int numOfSysResRolesRemoved = doRemove(deleteParams);
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResRoles as per params: %s =====> numOfRolesRemoved: %s", JsonUtil.toJsonStr(delParam), numOfSysResRolesRemoved));
        }
        return numOfSysResRolesRemoved;
    }

    protected int syncSysResources(final List<Long> sysResIds,
                                   final List<Long> existingSysResIds,
                                   final Long sysResRoleId,
                                   final String operator) {
        final RelationshipsAnalyser.DiffHolder<Long> diff = RelationshipsAnalyser.analysisDiff(sysResIds, existingSysResIds);
        final int insertedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getNewRelationships())) {
            insertedRows = assignSysResourcesToSysResRole(diff.getNewRelationships(), sysResRoleId, operator);
        } else {
            insertedRows = DeiGlobalConstant.ZERO;
        }
        final int deletedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getRemovedRelationships())) {
            deletedRows = removeSysResourcesFromSysResRole(diff.getRemovedRelationships(), sysResRoleId, operator);
        } else {
            deletedRows = DeiGlobalConstant.ZERO;
        }
        if (log.isInfoEnabled()) {
            log.info(String.format("sync SysResources =====> insertedRows: %s, deletedRows: %s",
                    insertedRows, deletedRows));
        }
        return insertedRows + deletedRows;
    }

    protected abstract S getExistingSysResRole(final Long id);

    protected abstract List<S> queryExistingSysResRoles(final D delParam);

    protected abstract List<Long> getSysResIds(final T sysResRole);

    protected abstract List<Long> getExistingSysResIds(final S existingSysResRole);

    protected abstract C getUpdateContext(final T sysResRole, final S existingSysResRole);

    protected abstract void doSave(final E newEntity);

    protected abstract int doUpdate(final E example);

    protected abstract int doRemove(final Map<String, Object> deleteParams);

    protected abstract int assignSysResourcesToSysResRole(final List<Long> sysResIds, final Long sysResRoleId, final String operator);

    protected abstract int removeSysResourcesFromSysResRole(final List<Long> sysResIds, final Long sysResRoleId, final String operator);

    protected abstract int removeSysResourcesFromSysResRoles(final List<Long> sysResRoleIds);
}
