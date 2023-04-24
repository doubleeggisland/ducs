package com.ioiox.dei.duc.std.data.svc.impl.master.user;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.core.vo.DefaultStdDataQueryCfg;
import com.ioiox.dei.duc.beans.entity.Role;
import com.ioiox.dei.duc.beans.entity.TmpRole;
import com.ioiox.dei.duc.beans.entity.UserAcctTmpRole;
import com.ioiox.dei.duc.beans.model.user.UserAcctTmpRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.user.UserAcctTmpRoleUpdatableObj;
import com.ioiox.dei.duc.beans.model.user.UserAcctTmpRoleUpdateCtx;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctTmpRoleMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.*;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctTmpRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctTmpRoleSlaveStdVO;
import com.ioiox.dei.duc.db.service.master.user.UserAcctTmpRoleMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.user.UserAcctTmpRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.user.UserAcctTmpRoleSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("userAcctTmpRoleMasterStdDataSvc")
public class UserAcctTmpRoleMasterStdDataSvcImpl
        extends BaseRoleMasterStdDataSvc<UserAcctTmpRoleMasterStdVO, UserAcctTmpRoleUpdatableObj, UserAcctTmpRoleUpdateCtx, UserAcctRoleDelParam, UserAcctTmpRoleSlaveStdVO, UserAcctTmpRole>
        implements UserAcctTmpRoleMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(UserAcctTmpRoleMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("userAcctTmpRoleMasterDbSvc")
    private UserAcctTmpRoleMasterDbSvc userAcctTmpRoleMasterDbSvc;

    @Autowired
    @Qualifier("userAcctTmpRoleSlaveStdDataSvc")
    private UserAcctTmpRoleSlaveStdDataSvc userAcctTmpRoleSlaveStdDataSvc;

    private final UserAcctTmpRoleUpdatableAttrsAnalyser analyser = new UserAcctTmpRoleUpdatableAttrsAnalyser();

    @Override
    protected UserAcctTmpRoleSlaveStdVO getExistingRole(final Long id) {
        return userAcctTmpRoleSlaveStdDataSvc.queryByPk(id,
                new RoleQueryCfg.Builder()
                        .needMenus(DeiGlobalConstant.FLAG_YES)
                        .menuQueryCfg(new MenuQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .needSysApiMappings(DeiGlobalConstant.FLAG_YES)
                        .sysApiMappingQueryCfg(new MenuSysApiMappingQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .needSysApis(DeiGlobalConstant.FLAG_YES)
                        .sysApiQueryCfg(new DefaultStdDataQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .showColumns(Arrays.asList(BaseDeiEntity.ShowColumn.ID.getCode(),
                                Role.ShowColumn.CODE.getCode(), Role.ShowColumn.NAME.getCode(),
                                Role.ShowColumn.TYPE.getCode(), Role.ShowColumn.STATUS.getCode(),
                                Role.ShowColumn.MEMO.getCode(), Role.ShowColumn.SYS_PRJ_SID.getCode(),
                                TmpRole.ShowColumn.UNLIMITED_DATE_RANGE.getCode(), TmpRole.ShowColumn.EFFECTIVE_START_DATE.getCode(),
                                TmpRole.ShowColumn.EFFECTIVE_END_DATE.getCode(), TmpRole.ShowColumn.UNLIMITED_TIME_RANGE.getCode(),
                                TmpRole.ShowColumn.EFFECTIVE_START_TIME.getCode(), TmpRole.ShowColumn.EFFECTIVE_END_TIME.getCode(),
                                BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                        .build());
    }

    @Override
    protected List<UserAcctTmpRoleSlaveStdVO> queryExistingRoles(final UserAcctRoleDelParam delParam) {
        final UserAcctTmpRoleQueryParam queryParam = new UserAcctTmpRoleQueryParam.Builder()
                .corpIds(delParam.getCorpIds())
                .sysPrjIds(delParam.getSysPrjIds())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return userAcctTmpRoleSlaveStdDataSvc.queryByParam(queryParam,
                new RoleQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build());
    }

    @Override
    protected List<Long> getMenuIds(final UserAcctTmpRoleMasterStdVO tmpRole) {
        return tmpRole.getMenuIds();
    }

    @Override
    protected List<Long> getSysApiMappingIds(final UserAcctTmpRoleMasterStdVO tmpRole) {
        return tmpRole.getSysApiMappingIds();
    }

    @Override
    protected List<Long> getSysApiIds(final UserAcctTmpRoleMasterStdVO tmpRole) {
        return tmpRole.getSysApiIds();
    }

    @Override
    protected List<Long> getExistingMenuIds(final UserAcctTmpRoleSlaveStdVO existingTmpRole) {
        return DeiCollectionUtil.isEmpty(existingTmpRole.getMenus())
                ? Collections.emptyList() : existingTmpRole.getMenus().stream().map(MenuSlaveStdVO::getId).collect(Collectors.toList());
    }

    @Override
    protected List<Long> getExistingSysApiMappingIds(final UserAcctTmpRoleSlaveStdVO existingTmpRole) {
        if (DeiCollectionUtil.isEmpty(existingTmpRole.getSysApiMappings())) {
            return Collections.emptyList();
        }
        final List<Long> sysApiMappingIds = new LinkedList<>();
        for (final List<MenuSysApiMappingSlaveStdVO> sysApiMappingsOfMenu : existingTmpRole.getSysApiMappings().values()) {
            sysApiMappingIds.addAll(sysApiMappingsOfMenu.stream().map(MenuSysApiMappingSlaveStdVO::getId).collect(Collectors.toList()));
        }
        return sysApiMappingIds;
    }

    @Override
    protected List<Long> getExistingSysApiIds(final UserAcctTmpRoleSlaveStdVO existingTmpRole) {
        return DeiCollectionUtil.isEmpty(existingTmpRole.getSysApis())
                ? Collections.emptyList() : existingTmpRole.getSysApis().stream().map(SysApiSlaveStdVO::getId).collect(Collectors.toList());
    }

    @Override
    protected UserAcctTmpRoleUpdateCtx getUpdateContext(final UserAcctTmpRoleMasterStdVO tmpRole, final UserAcctTmpRoleSlaveStdVO existingTmpRole) {
        return analyser.analyseUpdatedAttrs(tmpRole, existingTmpRole);
    }

    @Override
    protected void doSave(final UserAcctTmpRole newEntity) {
        userAcctTmpRoleMasterDbSvc.dbInsert(newEntity);
    }

    @Override
    protected int doUpdate(final UserAcctTmpRole example) {
        return userAcctTmpRoleMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return userAcctTmpRoleMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    protected int assignMenusToRole(final List<Long> menuIds, final Long tmpRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign menus to userAcctTmpRole =====> menuIds: %s, tmpRoleId: %s",
                    JsonUtil.toJsonStr(menuIds), tmpRoleId));
        }
        return userAcctTmpRoleMasterDbSvc.assignMenusToTmpRole(menuIds, tmpRoleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeMenusFromRole(final List<Long> menuIds, final Long tmpRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menus from userAcctTmpRole =====> menuIds: %s, tmpRoleId: %s",
                    JsonUtil.toJsonStr(menuIds), tmpRoleId));
        }
        return userAcctTmpRoleMasterDbSvc.removeMenusFromTmpRoles(menuIds, Collections.singletonList(tmpRoleId));
    }

    @Override
    protected int removeMenusFromRoles(final List<Long> tmpRoleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menus from userAcctTmpRoles ======> tmpRoleIds: %s", JsonUtil.toJsonStr(tmpRoleIds)));
        }
        return userAcctTmpRoleMasterDbSvc.removeMenusFromTmpRoles(null, tmpRoleIds);
    }

    @Override
    protected int assignMenuSysApisToRole(final List<Long> sysApiMappingIds, final Long tmpRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign menuSysApis to userAcctTmpRole =====> sysApiMappingIds: %s, tmpRoleId: %s",
                    JsonUtil.toJsonStr(sysApiMappingIds), tmpRoleId));
        }
        return userAcctTmpRoleMasterDbSvc.assignMenuSysApisToTmpRole(sysApiMappingIds, tmpRoleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeMenuSysApisFromRole(final List<Long> sysApiMappingIds, final Long tmpRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menuSysApis from userAcctTmpRole =====> sysApiMappingIds: %s, tmpRoleId: %s",
                    JsonUtil.toJsonStr(sysApiMappingIds), tmpRoleId));
        }
        return userAcctTmpRoleMasterDbSvc.removeMenuSysApisFromTmpRoles(sysApiMappingIds, Collections.singletonList(tmpRoleId));
    }

    @Override
    protected int removeMenuSysApisFromRoles(final List<Long> tmpRoleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menuSysApis from userAcctTmpRoles ======> tmpRoleIds: %s", JsonUtil.toJsonStr(tmpRoleIds)));
        }
        return userAcctTmpRoleMasterDbSvc.removeMenuSysApisFromTmpRoles(null, tmpRoleIds);
    }

    @Override
    protected int assignSysApisToRole(final List<Long> sysApiIds, final Long tmpRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign sysApis to userAcctTmpRole =====> sysApiIds: %s, tmpRoleId: %s",
                    JsonUtil.toJsonStr(sysApiIds), tmpRoleId));
        }
        return userAcctTmpRoleMasterDbSvc.assignSysApisToTmpRole(sysApiIds, tmpRoleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeSysApisFromRole(final List<Long> sysApiIds, final Long tmpRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysApis from userAcctTmpRole =====> sysApiIds: %s, tmpRoleId: %s",
                    JsonUtil.toJsonStr(sysApiIds), tmpRoleId));
        }
        return userAcctTmpRoleMasterDbSvc.removeSysApisFromTmpRoles(sysApiIds, Collections.singletonList(tmpRoleId));
    }

    @Override
    protected int removeSysApisFromRoles(final List<Long> tmpRoleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysApis from userAcctTmpRoles ======> tmpRoleIds: %s", JsonUtil.toJsonStr(tmpRoleIds)));
        }
        return userAcctTmpRoleMasterDbSvc.removeSysApisFromTmpRoles(null, tmpRoleIds);
    }

    @Override
    public UserAcctTmpRole toNewEntity(final UserAcctTmpRoleMasterStdVO tmpRole) {
        final UserAcctTmpRole newEntity = new UserAcctTmpRole();
        assembleCommonAttrsOnInsert(newEntity, tmpRole);
        assembleTmpRoleCommonAttrs(newEntity, tmpRole);
        newEntity.setTenantSid(tmpRole.getTenantId());
        newEntity.setCorpSid(tmpRole.getTenantId());
        return newEntity;
    }

    @Override
    public UserAcctTmpRole toUpdatableObj(final UserAcctTmpRoleUpdatableObj updatableVO) {
        final UserAcctTmpRole example = new UserAcctTmpRole();
        assembleTmpRoleUpdatableAttrs(example, updatableVO);
        return example;
    }
}
