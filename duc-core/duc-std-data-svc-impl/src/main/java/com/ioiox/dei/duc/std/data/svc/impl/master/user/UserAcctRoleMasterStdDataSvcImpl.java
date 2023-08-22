package com.ioiox.dei.duc.std.data.svc.impl.master.user;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DefaultStdDataQueryCfg;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.Role;
import com.ioiox.dei.duc.beans.entity.UserAcctRole;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctRoleUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctRoleUpdateCtx;
import com.ioiox.dei.duc.beans.model.slave.MenuQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.MenuSysApiMappingQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctRoleMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.*;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctRoleSlaveStdVO;
import com.ioiox.dei.duc.db.service.master.user.UserAcctRoleMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.user.UserAcctRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.user.UserAcctRoleSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("userAcctRoleMasterStdDataSvc")
public class UserAcctRoleMasterStdDataSvcImpl
        extends BaseRoleMasterStdDataSvc<UserAcctRoleMasterStdVO, UserAcctRoleUpdatableObj, UserAcctRoleUpdateCtx, UserAcctRoleDelParam, UserAcctRoleSlaveStdVO, UserAcctRole>
        implements UserAcctRoleMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(UserAcctRoleMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("userAcctRoleMasterDbSvc")
    private UserAcctRoleMasterDbSvc userAcctRoleMasterDbSvc;

    @Autowired
    @Qualifier("userAcctRoleSlaveStdDataSvc")
    private UserAcctRoleSlaveStdDataSvc userAcctRoleSlaveStdDataSvc;

    private final UserAcctRoleUpdatableAttrsAnalyser analyser = new UserAcctRoleUpdatableAttrsAnalyser();

    @Override
    protected UserAcctRoleSlaveStdVO getExistingRole(final Long id) {
        return userAcctRoleSlaveStdDataSvc.queryByPk(id,
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
                                BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                        .build());
    }

    @Override
    protected List<UserAcctRoleSlaveStdVO> queryExistingRoles(final UserAcctRoleDelParam delParam) {
        final UserAcctRoleQueryParam queryParam = new UserAcctRoleQueryParam.Builder()
                .corpIds(delParam.getCorpIds())
                .sysPrjIds(delParam.getSysPrjIds())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return userAcctRoleSlaveStdDataSvc.queryByParam(queryParam,
                new RoleQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build());
    }

    @Override
    protected List<Long> getMenuIds(final UserAcctRoleMasterStdVO role) {
        return role.getMenuIds();
    }

    @Override
    protected List<Long> getSysApiMappingIds(final UserAcctRoleMasterStdVO role) {
        return role.getSysApiMappingIds();
    }

    @Override
    protected List<Long> getSysApiIds(final UserAcctRoleMasterStdVO role) {
        return role.getSysApiIds();
    }

    @Override
    protected List<Long> getExistingMenuIds(final UserAcctRoleSlaveStdVO existingRole) {
        return DeiCollectionUtil.isEmpty(existingRole.getMenus())
                ? Collections.emptyList() : existingRole.getMenus().stream().map(MenuSlaveStdVO::getId).collect(Collectors.toList());
    }

    @Override
    protected List<Long> getExistingSysApiMappingIds(final UserAcctRoleSlaveStdVO existingRole) {
        if (DeiCollectionUtil.isEmpty(existingRole.getSysApiMappings())) {
            return Collections.emptyList();
        }
        final List<Long> sysApiMappingIds = new LinkedList<>();
        for (final List<MenuSysApiMappingSlaveStdVO> sysApiMappingsOfMenu : existingRole.getSysApiMappings().values()) {
            sysApiMappingIds.addAll(sysApiMappingsOfMenu.stream().map(MenuSysApiMappingSlaveStdVO::getId).collect(Collectors.toList()));
        }
        return sysApiMappingIds;
    }

    @Override
    protected List<Long> getExistingSysApiIds(final UserAcctRoleSlaveStdVO existingRole) {
        return DeiCollectionUtil.isEmpty(existingRole.getSysApis())
                ? Collections.emptyList() : existingRole.getSysApis().stream().map(SysApiSlaveStdVO::getId).collect(Collectors.toList());
    }

    @Override
    protected UserAcctRoleUpdateCtx getUpdateContext(final UserAcctRoleMasterStdVO role, final UserAcctRoleSlaveStdVO existingRole) {
        return analyser.analyseUpdatedAttrs(role, existingRole);
    }

    @Override
    protected void doSave(final UserAcctRole newEntity) {
        userAcctRoleMasterDbSvc.dbInsert(newEntity);
    }

    @Override
    protected int doUpdate(final UserAcctRole example) {
        return userAcctRoleMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return userAcctRoleMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    protected int assignMenusToRole(final List<Long> menuIds, final Long roleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign menus to userAcctRole =====> menuIds: %s, roleId: %s",
                    JsonUtil.toJsonStr(menuIds), roleId));
        }
        return userAcctRoleMasterDbSvc.assignMenusToRole(menuIds, roleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeMenusFromRole(final List<Long> menuIds, final Long roleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menus from userAcctRole =====> menuIds: %s, roleId: %s",
                    JsonUtil.toJsonStr(menuIds), roleId));
        }
        return userAcctRoleMasterDbSvc.removeMenusFromRoles(menuIds, Collections.singletonList(roleId));
    }

    @Override
    protected int removeMenusFromRoles(final List<Long> roleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menus from userAcctRoles ======> roleIds: %s", JsonUtil.toJsonStr(roleIds)));
        }
        return userAcctRoleMasterDbSvc.removeMenusFromRoles(null, roleIds);
    }

    @Override
    protected int assignMenuSysApisToRole(List<Long> sysApiMappingIds, Long roleId, String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign menuSysApis to userAcctRole =====> sysApiMappingIds: %s, roleId: %s",
                    JsonUtil.toJsonStr(sysApiMappingIds), roleId));
        }
        return userAcctRoleMasterDbSvc.assignMenuSysApisToRole(sysApiMappingIds, roleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeMenuSysApisFromRole(List<Long> sysApiMappingIds, Long roleId, String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menuSysApis from userAcctRole =====> sysApiMappingIds: %s, roleId: %s",
                    JsonUtil.toJsonStr(sysApiMappingIds), roleId));
        }
        return userAcctRoleMasterDbSvc.removeMenuSysApisFromRoles(sysApiMappingIds, Collections.singletonList(roleId));
    }

    @Override
    protected int removeMenuSysApisFromRoles(List<Long> roleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menuSysApis from userAcctRoles ======> roleIds: %s", JsonUtil.toJsonStr(roleIds)));
        }
        return userAcctRoleMasterDbSvc.removeMenuSysApisFromRoles(null, roleIds);
    }

    @Override
    protected int assignSysApisToRole(final List<Long> sysApiIds, final Long roleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign sysApis to userAcctRole =====> sysApiIds: %s, roleId: %s",
                    JsonUtil.toJsonStr(sysApiIds), roleId));
        }
        return userAcctRoleMasterDbSvc.assignSysApisToRole(sysApiIds, roleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeSysApisFromRole(final List<Long> sysApiIds, final Long roleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysApis from userAcctRole =====> sysApiIds: %s, roleId: %s",
                    JsonUtil.toJsonStr(sysApiIds), roleId));
        }
        return userAcctRoleMasterDbSvc.removeSysApisFromRoles(sysApiIds, Collections.singletonList(roleId));
    }

    @Override
    protected int removeSysApisFromRoles(final List<Long> roleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysApis from userAcctRoles ======> roleIds: %s", JsonUtil.toJsonStr(roleIds)));
        }
        return userAcctRoleMasterDbSvc.removeSysApisFromRoles(null, roleIds);
    }

    @Override
    public UserAcctRole toNewEntity(final UserAcctRoleMasterStdVO userAcctRole) {
        final UserAcctRole newEntity = new UserAcctRole();
        assembleCommonAttrsOnInsert(newEntity, userAcctRole);
        assembleRoleCommonAttrs(newEntity, userAcctRole);
        newEntity.setTenantSid(userAcctRole.getTenantId());
        newEntity.setCorpSid(userAcctRole.getTenantId());
        return newEntity;
    }

    @Override
    public UserAcctRole toUpdatableObj(final UserAcctRoleUpdatableObj updatableVO) {
        final UserAcctRole example = new UserAcctRole();
        assembleRoleUpdatableAttrs(example, updatableVO);
        return example;
    }
}
