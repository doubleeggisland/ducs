package com.ioiox.dei.duc.std.data.svc.impl.master;

import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.exception.DeiServiceException;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.core.utils.RelationshipsAnalyser;
import com.ioiox.dei.duc.beans.entity.BaseRole;
import com.ioiox.dei.duc.beans.model.master.SimpleRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.SimpleRoleUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.SimpleRoleUpdateCtx;
import com.ioiox.dei.duc.beans.vo.std.master.BaseRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.BaseRoleSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSysApiMappingSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.SysApiSlaveVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseRoleMasterStdDataSvc<
        T extends BaseRoleMasterVO,
        O extends SimpleRoleUpdatableObj,
        C extends SimpleRoleUpdateCtx<O>,
        D extends SimpleRoleDelParam,
        S extends BaseRoleSlaveVO,
        E extends BaseRole>
        extends SimpleRoleMasterStdDataSvc<T, O, E> {

    private static final Logger log = LoggerFactory.getLogger(BaseRoleMasterStdDataSvc.class);

    public Long save(final T role) {
        if (Objects.isNull(role)) {
            return DeiGlobalConstant.DEFAULT_SID;
        }
        final E newEntity = toNewEntity(role);
        newEntity.setDefaultValueIfNeed();
        doSave(newEntity);
        final long id = newEntity.getSid();
        syncMenus(role.getMenuIds(), null, id, role.getUpdatedBy());
        syncMenuSysApis(role.getSysApiMappingIds(), null, id, role.getUpdatedBy());
        syncSysApis(role.getSysApiIds(), null, id, role.getUpdatedBy());
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
                syncMenus(
                        role.getMenuIds(),
                        DeiCollectionUtil.isEmpty(existingRole.getMenus())
                                ? Collections.emptyList() : existingRole.getMenus().stream().map(MenuSlaveVO::getId).collect(Collectors.toList()),
                        existingRole.getId(),
                        role.getUpdatedBy()
                );
        final int numOfMenuSysApisSync =
                syncMenuSysApis(role.getSysApiMappingIds(), getExistingSysApiMappingIds(existingRole), existingRole.getId(), role.getUpdatedBy());
        final int numOfSysApisSync =
                syncSysApis(
                        role.getSysApiIds(),
                        DeiCollectionUtil.isEmpty(existingRole.getSysApis())
                                ? Collections.emptyList() : existingRole.getSysApis().stream().map(SysApiSlaveVO::getId).collect(Collectors.toList()),
                        existingRole.getId(),
                        role.getUpdatedBy()
                );
        final C updateCtx = getUpdateContext(role, existingRole);
        final O updatableObj = updateCtx.getUpdatableObj();
        if (updatableObj.attrsNotUpdated()) {
            if (numOfMenusSync > 0
                    || numOfMenuSysApisSync > 0
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

    List<Long> getExistingSysApiMappingIds(final S existingRole) {
        if (DeiCollectionUtil.isEmpty(existingRole.getSysApiMappings())) {
            return Collections.emptyList();
        }
        final List<Long> sysApiMappingIds = new LinkedList<>();
        for (final List<MenuSysApiMappingSlaveStdVO> sysApiMappingsOfMenu : existingRole.getSysApiMappings().values()) {
            sysApiMappingIds.addAll(sysApiMappingsOfMenu.stream().map(MenuSysApiMappingSlaveStdVO::getId).collect(Collectors.toList()));
        }
        return sysApiMappingIds;
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
        final int numOfMenuSysApisRemoved = removeMenuSysApisFromRoles(roleIds);
        final int numOfSysApisRemoved = removeSysApisFromRoles(roleIds);
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menus from role as per roleIds: %s =====> numOfMenusRemoved: %s", JsonUtil.toJsonStr(roleIds), numOfMenusRemoved));
            log.info(String.format("remove menuSysApis from role as per roleIds: %s =====> numOfMenuSysApisRemoved: %s", JsonUtil.toJsonStr(roleIds), numOfMenuSysApisRemoved));
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

    protected int syncMenuSysApis(final List<Long> sysApiMappingIds,
                                  final List<Long> existingSysApiMappingIds,
                                  final Long roleId,
                                  final String operator) {
        final RelationshipsAnalyser.DiffHolder<Long> diff = RelationshipsAnalyser.analysisDiff(sysApiMappingIds, existingSysApiMappingIds);
        final int insertedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getNewRelationships())) {
            insertedRows = assignMenuSysApisToRole(diff.getNewRelationships(), roleId, operator);
        } else {
            insertedRows = DeiGlobalConstant.ZERO;
        }
        final int deletedRows;
        if (DeiCollectionUtil.isNotEmpty(diff.getRemovedRelationships())) {
            deletedRows = removeMenuSysApisFromRole(diff.getRemovedRelationships(), roleId, operator);
        } else {
            deletedRows = DeiGlobalConstant.ZERO;
        }
        if (log.isInfoEnabled()) {
            log.info(String.format("sync MenuSysApis =====> insertedRows: %s, deletedRows: %s",
                    insertedRows, deletedRows));
        }
        return insertedRows + deletedRows;
    }

    protected int syncSysApis(final List<Long> sysApiIds,
                              final List<Long> existingSysApiIds,
                              final Long roleId,
                              final String operator) {
        final RelationshipsAnalyser.DiffHolder<Long> diff = RelationshipsAnalyser.analysisDiff(sysApiIds, existingSysApiIds);
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

    protected abstract C getUpdateContext(final T role, final S existingRole);

    protected abstract void doSave(final E newEntity);

    protected abstract int doUpdate(final E example);

    protected abstract int doRemove(final Map<String, Object> deleteParams);

    protected abstract int assignMenusToRole(final List<Long> menuIds, final Long roleId, final String operator);

    protected abstract int removeMenusFromRole(final List<Long> menuIds, final Long roleId, final String operator);

    protected abstract int removeMenusFromRoles(final List<Long> roleIds);

    protected abstract int assignMenuSysApisToRole(final List<Long> sysApiMappingIds, final Long roleId, final String operator);

    protected abstract int removeMenuSysApisFromRole(final List<Long> sysApiMappingIds, final Long roleId, final String operator);

    protected abstract int removeMenuSysApisFromRoles(final List<Long> roleIds);

    protected abstract int assignSysApisToRole(final List<Long> sysApiIds, final Long roleId, final String operator);

    protected abstract int removeSysApisFromRole(final List<Long> sysApiIds, final Long roleId, final String operator);

    protected abstract int removeSysApisFromRoles(final List<Long> roleIds);
}
