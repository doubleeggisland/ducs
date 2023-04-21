package com.ioiox.dei.duc.std.data.svc.impl.master.user;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.UserAcctRole;
import com.ioiox.dei.duc.beans.model.user.UserAcctRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.user.UserAcctRoleUpdatableObj;
import com.ioiox.dei.duc.beans.model.user.UserAcctRoleUpdateCtx;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctRoleMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSysApiMappingSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctRoleSlaveStdVO;
import com.ioiox.dei.duc.db.service.master.user.UserAcctRoleMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.user.UserAcctRoleR2MenuMasterDbSvc;
import com.ioiox.dei.duc.db.service.master.user.UserAcctRoleR2MenuSysApiMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.user.UserAcctRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.user.UserAcctRoleSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("userAcctRoleMasterStdDataSvc")
public class UserAcctRoleMasterStdDataSvcImpl
        extends BaseRoleMasterStdDataSvc<UserAcctRoleMasterStdVO, UserAcctRoleUpdatableObj, UserAcctRoleUpdateCtx, UserAcctRoleDelParam, UserAcctRoleSlaveStdVO, UserAcctRole>
        implements UserAcctRoleMasterStdDataSvc {

    @Autowired
    @Qualifier("userAcctRoleMasterDbSvc")
    private UserAcctRoleMasterDbSvc userAcctRoleMasterDbSvc;

    @Autowired
    @Qualifier("userAcctRoleR2MenuMasterDbSvc")
    private UserAcctRoleR2MenuMasterDbSvc userAcctRoleR2MenuMasterDbSvc;

    @Autowired
    @Qualifier("userAcctRoleR2SysApiMasterDbSvc")
    private UserAcctRoleR2MenuSysApiMasterDbSvc userAcctRoleR2SysApiMasterDbSvc;

    @Autowired
    @Qualifier("userAcctRoleSlaveStdDataSvc")
    private UserAcctRoleSlaveStdDataSvc userAcctRoleSlaveStdDataSvc;

    private final UserAcctRoleUpdatableAttrsAnalyser analyser = new UserAcctRoleUpdatableAttrsAnalyser();

    @Override
    protected UserAcctRoleSlaveStdVO getExistingRole(final Long id) {
        return userAcctRoleSlaveStdDataSvc.queryByPk(id, null);
    }

    @Override
    protected List<UserAcctRoleSlaveStdVO> queryExistingRoles(final UserAcctRoleDelParam delParam) {
        final UserAcctRoleQueryParam queryParam = new UserAcctRoleQueryParam.Builder()
                .corpIds(delParam.getCorpIds())
                .sysPrjIds(delParam.getSysPrjIds())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return userAcctRoleSlaveStdDataSvc.queryByParam(queryParam, null);
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
        return userAcctRoleR2MenuMasterDbSvc.save(menuIds, roleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeMenusFromRole(final List<Long> menuIds, final Long roleId, final String operator) {
        return 0;
    }

    @Override
    protected int removeMenusFromRoles(final List<Long> roleIds) {
        return 0;
    }

    @Override
    protected int assignSysApisToRole(final List<Long> sysApiMappingIds, final Long roleId, final String operator) {
        return 0;
    }

    @Override
    protected int removeSysApisFromRole(final List<Long> sysApiMappingIds, final Long roleId, final String operator) {
        return 0;
    }

    @Override
    protected int removeSysApisFromRoles(final List<Long> roleIds) {
        return 0;
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
