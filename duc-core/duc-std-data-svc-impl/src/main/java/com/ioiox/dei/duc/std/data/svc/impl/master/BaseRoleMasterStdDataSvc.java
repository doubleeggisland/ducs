package com.ioiox.dei.duc.std.data.svc.impl.master;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.exception.DeiServiceException;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.core.utils.RelationshipsAnalyser;
import com.ioiox.dei.duc.beans.entity.Role;
import com.ioiox.dei.duc.beans.model.RoleUpdatableObj;
import com.ioiox.dei.duc.beans.model.RoleUpdateCtx;
import com.ioiox.dei.duc.beans.vo.std.master.BaseRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.BaseRoleMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.BaseRoleSlaveStdVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class BaseRoleMasterStdDataSvc<
        T extends BaseRoleMasterStdVO,
        O extends RoleUpdatableObj,
        C extends RoleUpdateCtx<O>,
        D extends BaseRoleDelParam,
        S extends BaseRoleSlaveStdVO,
        E extends Role>
        extends CommonRoleMasterStdDataSvc<T, O, E> {

    private static final Logger log = LoggerFactory.getLogger(BaseRoleMasterStdDataSvc.class);

    public Long save(final T role) {
        if (Objects.isNull(role)) {
            return DeiGlobalConstant.DEFAULT_SID;
        }
        final E newEntity = toNewEntity(role);
        newEntity.setDefaultValueIfNeed();
        doSave(newEntity);
        final long id = newEntity.getSid();
        syncMenus(getMenuIds(role), null, id, role.getUpdatedBy());
        syncSysApis(getSysApiMappingIds(role), null, id, role.getUpdatedBy());
        return id;
    }

    public boolean update(final T role) {
        if (Objects.isNull(role)
                || Objects.isNull(role.getId())) {
            throw new DeiServiceException("Please choose a role to update!");
        }
        final S existingRole = getExistingRole(role.getId());
        if (Objects.isNull(existingRole)) {
            throw new DeiServiceException(String.format("Role doesn't exist =====> id: %s", role.getId()));
        }
        return update(role, existingRole);
    }

    public boolean update(final T role, final S existingRole) {
        final int numOfMenusSync =
                syncMenus(getMenuIds(role), getExistingMenuIds(existingRole), existingRole.getId(), role.getUpdatedBy());
        final int numOfSysApisSync =
                syncSysApis(getSysApiMappingIds(role), getExistingSysApiMappingIds(existingRole), existingRole.getId(), role.getUpdatedBy());
        final C updateCtx = getUpdateContext(role, existingRole);
        final O updatableObj = updateCtx.getUpdatableObj();
        if (updatableObj.attrsNotUpdated()) {
            if (numOfMenusSync > 0
                    || numOfSysApisSync > 0) {
                updatableObj.updateLastModifiedTime();
            }
        }
        final boolean updated = updatableObj.updated();
        if (updated) {
            if (log.isInfoEnabled()) {
                if (updatableObj.attrsUpdated()) {
                    final Map<String, String> updateSummary = updatableObj.updateSummary();
                    log.info(String.format("update Role =====> id: %s, updateSummary: %s", existingRole.getId(), JsonUtil.toJsonStr(updateSummary)));
                } else {
                    log.info(String.format("update Role lastModifiedTime =====> id: %s, lastUpdateTime: %s",
                            existingRole.getId(), updatableObj.formatLastModifiedTime()));
                }
            }
            final E example = toUpdatableObj(updatableObj);
            assembleCommonAttrsOnUpdate(example, existingRole, role);
            final int updatedRows = doUpdate(example);
            if (DeiGlobalConstant.ZERO == updatedRows) {
                throw new DeiServiceException(String.format("Role has been updated by others =====> id: %s, versionNum: %s",
                        existingRole.getId(), existingRole.getVersionNum()));
            }
        }
        return updated;
    }

    public int remove(final D delParam) {
        final Map<String, Object> deleteParams =
                Objects.isNull(delParam) ? null : delParam.deleteParams();
        if (DeiCollectionUtil.isEmpty(deleteParams)) {
            return DeiGlobalConstant.ZERO;
        }
        final List<S> existingRoles = queryExistingRoles(delParam);
        if (DeiCollectionUtil.isEmpty(existingRoles)) {
            throw new DeiServiceException(String.format("Cannot find any roles as per delParam =====> %s", JsonUtil.toJsonStr(delParam)));
        }
        final List<Long> roleIds = existingRoles.stream().map(S::getId).collect(Collectors.toList());
        final int numOfMenusRemoved = removeMenusFromRoles(roleIds);
        final int numOfSysApisRemoved = removeSysApisFromRoles(roleIds);
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menus from role as per roleIds: %s =====> numOfMenusRemoved: %s", JsonUtil.toJsonStr(roleIds), numOfMenusRemoved));
            log.info(String.format("remove sysApis from role as per roleIds: %s =====> numOfSysApisRemoved: %s", JsonUtil.toJsonStr(roleIds), numOfSysApisRemoved));
        }
        final int numOfRolesRemoved = doRemove(deleteParams);
        if (log.isInfoEnabled()) {
            log.info(String.format("remove roles as per params: %s =====> numOfRolesRemoved: %s", JsonUtil.toJsonStr(delParam), numOfRolesRemoved));
        }
        return numOfRolesRemoved;
    }

    protected int syncMenus(final List<Long> menuIds,
                            final List<Long> existingMenuIds,
                            final Long roleId,
                            final String operator) {
        final RelationshipsAnalyser.DiffHolder<Long> diff = RelationshipsAnalyser.analysisDiff(menuIds, existingMenuIds);
        final int insertedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getNewRelationships())) {
            insertedRows = assignMenusToRole(diff.getNewRelationships(), roleId, operator);
        } else {
            insertedRows = DeiGlobalConstant.ZERO;
        }
        final int deletedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getRemovedRelationships())) {
            deletedRows = removeMenusFromRole(diff.getRemovedRelationships(), roleId, operator);
        } else {
            deletedRows = DeiGlobalConstant.ZERO;
        }
        if (log.isInfoEnabled()) {
            log.info(String.format("sync Menus =====> insertedRows: %s, deletedRows: %s",
                    insertedRows, deletedRows));
        }
        return insertedRows + deletedRows;
    }

    protected int syncSysApis(final List<Long> sysApiMappingIds,
                              final List<Long> existingSysApiMappingIds,
                              final Long roleId,
                              final String operator) {
        final RelationshipsAnalyser.DiffHolder<Long> diff = RelationshipsAnalyser.analysisDiff(sysApiMappingIds, existingSysApiMappingIds);
        final int insertedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getNewRelationships())) {
            insertedRows = assignSysApisToRole(diff.getNewRelationships(), roleId, operator);
        } else {
            insertedRows = DeiGlobalConstant.ZERO;
        }
        final int deletedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getRemovedRelationships())) {
            deletedRows = removeSysApisFromRole(diff.getRemovedRelationships(), roleId, operator);
        } else {
            deletedRows = DeiGlobalConstant.ZERO;
        }
        if (log.isInfoEnabled()) {
            log.info(String.format("sync SysApis =====> insertedRows: %s, deletedRows: %s",
                    insertedRows, deletedRows));
        }
        return insertedRows + deletedRows;
    }

    protected abstract S getExistingRole(final Long id);

    protected abstract List<S> queryExistingRoles(final D delParam);

    protected abstract List<Long> getMenuIds(final T role);

    protected abstract List<Long> getSysApiMappingIds(final T role);

    protected abstract List<Long> getExistingMenuIds(final S existingRole);

    protected abstract List<Long> getExistingSysApiMappingIds(final S existingRole);

    protected abstract C getUpdateContext(final T role, final S existingRole);

    protected abstract void doSave(final E newEntity);

    protected abstract int doUpdate(final E example);

    protected abstract int doRemove(final Map<String, Object> deleteParams);

    protected abstract int assignMenusToRole(final List<Long> menuIds, final Long roleId, final String operator);

    protected abstract int removeMenusFromRole(final List<Long> menuIds, final Long roleId, final String operator);

    protected abstract int removeMenusFromRoles(final List<Long> roleIds);

    protected abstract int assignSysApisToRole(final List<Long> sysApiMappingIds, final Long roleId, final String operator);

    protected abstract int removeSysApisFromRole(final List<Long> sysApiMappingIds, final Long roleId, final String operator);

    protected abstract int removeSysApisFromRoles(final List<Long> roleIds);
}
