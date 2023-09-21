package com.ioiox.dei.duc.std.data.svc.impl.master.tenant;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DefaultStdDataQueryCfg;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.SimpleRole;
import com.ioiox.dei.duc.beans.entity.TenantUserRole;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserRoleUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserRoleUpdateCtx;
import com.ioiox.dei.duc.beans.model.slave.MenuQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.MenuSysApiMappingQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserRoleSlaveVO;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserRoleMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.tenant.TenantUserRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserRoleSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("tenantUserRoleMasterStdDataSvc")
public class TenantUserRoleMasterStdDataSvcImpl
        extends BaseRoleMasterStdDataSvc<TenantUserRoleMasterVO, TenantUserRoleUpdatableObj, TenantUserRoleUpdateCtx, TenantUserRoleDelParam, TenantUserRoleSlaveVO, TenantUserRole>
        implements TenantUserRoleMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(TenantUserRoleMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("tenantUserRoleMasterDbSvc")
    private TenantUserRoleMasterDbSvc tenantUserRoleMasterDbSvc;

    @Autowired
    @Qualifier("tenantUserRoleSlaveStdDataSvc")
    private TenantUserRoleSlaveStdDataSvc tenantUserRoleSlaveStdDataSvc;

    private final TenantUserRoleUpdatableAttrsAnalyser updatableAttrsAnalyser = new TenantUserRoleUpdatableAttrsAnalyser();

    @Override
    protected TenantUserRoleSlaveVO getExistingRole(final Long id) {
        return tenantUserRoleSlaveStdDataSvc.queryByPk(
                id,
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
                                SimpleRole.ShowColumn.CODE.getCode(), SimpleRole.ShowColumn.NAME.getCode(),
                                SimpleRole.ShowColumn.TYPE.getCode(), SimpleRole.ShowColumn.STATUS.getCode(),
                                SimpleRole.ShowColumn.MEMO.getCode(), SimpleRole.ShowColumn.SYS_PRJ_SID.getCode(),
                                BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                        .build()
        );
    }

    @Override
    protected List<TenantUserRoleSlaveVO> queryExistingRoles(final TenantUserRoleDelParam delParam) {
        final TenantUserRoleQueryParam queryParam = new TenantUserRoleQueryParam.Builder()
                .tenantIds(delParam.getTenantIds())
                .sysPrjIds(delParam.getSysPrjIds())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return tenantUserRoleSlaveStdDataSvc.queryByParam(
                queryParam,
                new RoleQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build()
        );
    }

    @Override
    protected TenantUserRoleUpdateCtx getUpdateContext(final TenantUserRoleMasterVO role,
                                                       final TenantUserRoleSlaveVO existingRole) {
        return updatableAttrsAnalyser.analyseUpdatedAttrs(role, existingRole);
    }

    @Override
    protected void doSave(final TenantUserRole newEntity) {
        tenantUserRoleMasterDbSvc.dbInsert(newEntity);
    }

    @Override
    protected int doUpdate(final TenantUserRole example) {
        return tenantUserRoleMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return tenantUserRoleMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    protected int assignMenusToRole(final List<Long> menuIds, final Long roleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign menus to TenantUserRole =====> menuIds: %s, roleId: %s",
                    JsonUtil.toJsonStr(menuIds), roleId));
        }
        return tenantUserRoleMasterDbSvc.assignMenusToRole(menuIds, roleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeMenusFromRole(final List<Long> menuIds, final Long roleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menus from TenantUserRole =====> menuIds: %s, roleId: %s",
                    JsonUtil.toJsonStr(menuIds), roleId));
        }
        return tenantUserRoleMasterDbSvc.removeMenusFromRoles(menuIds, Collections.singletonList(roleId));
    }

    @Override
    protected int removeMenusFromRoles(final List<Long> roleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menus from TenantUserRoles ======> roleIds: %s", JsonUtil.toJsonStr(roleIds)));
        }
        return tenantUserRoleMasterDbSvc.removeMenusFromRoles(null, roleIds);
    }

    @Override
    protected int assignMenuSysApisToRole(final List<Long> sysApiMappingIds, final Long roleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign menuSysApis to TenantUserRole =====> sysApiMappingIds: %s, roleId: %s",
                    JsonUtil.toJsonStr(sysApiMappingIds), roleId));
        }
        return tenantUserRoleMasterDbSvc.assignMenuSysApisToRole(sysApiMappingIds, roleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeMenuSysApisFromRole(final List<Long> sysApiMappingIds, final Long roleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menuSysApis from TenantUserRole =====> sysApiMappingIds: %s, roleId: %s",
                    JsonUtil.toJsonStr(sysApiMappingIds), roleId));
        }
        return tenantUserRoleMasterDbSvc.removeMenuSysApisFromRoles(sysApiMappingIds, Collections.singletonList(roleId));
    }

    @Override
    protected int removeMenuSysApisFromRoles(final List<Long> roleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menuSysApis from TenantUserRoles ======> roleIds: %s", JsonUtil.toJsonStr(roleIds)));
        }
        return tenantUserRoleMasterDbSvc.removeMenuSysApisFromRoles(null, roleIds);
    }

    @Override
    protected int assignSysApisToRole(final List<Long> sysApiIds, final Long roleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign sysApis to TenantUserRole =====> sysApiIds: %s, roleId: %s",
                    JsonUtil.toJsonStr(sysApiIds), roleId));
        }
        return tenantUserRoleMasterDbSvc.assignSysApisToRole(sysApiIds, roleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeSysApisFromRole(final List<Long> sysApiIds, final Long roleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysApis from TenantUserRole =====> sysApiIds: %s, roleId: %s",
                    JsonUtil.toJsonStr(sysApiIds), roleId));
        }
        return tenantUserRoleMasterDbSvc.removeSysApisFromRoles(sysApiIds, Collections.singletonList(roleId));
    }

    @Override
    protected int removeSysApisFromRoles(final List<Long> roleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysApis from TenantUserRoles ======> roleIds: %s", JsonUtil.toJsonStr(roleIds)));
        }
        return tenantUserRoleMasterDbSvc.removeSysApisFromRoles(null, roleIds);
    }

    @Override
    public TenantUserRole toNewEntity(final TenantUserRoleMasterVO tenantUserRole) {
        final TenantUserRole newEntity = new TenantUserRole();
        assembleCommonAttrsOnInsert(newEntity, tenantUserRole);
        assembleSimpleRoleAttrs(newEntity, tenantUserRole);
        newEntity.setTenantSid(tenantUserRole.getTenantId());
        return newEntity;
    }

    @Override
    public TenantUserRole toUpdatableObj(final TenantUserRoleUpdatableObj updatableVO) {
        final TenantUserRole example = new TenantUserRole();
        assembleRoleUpdatableAttrs(example, updatableVO);
        return example;
    }
}
