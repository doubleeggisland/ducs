package com.ioiox.dei.duc.std.data.svc.impl.master.tenant;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DefaultStdDataQueryCfg;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.SimpleRole;
import com.ioiox.dei.duc.beans.entity.TenantUserTmpRole;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserTmpRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserTmpRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserTmpRoleUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserTmpRoleUpdateCtx;
import com.ioiox.dei.duc.beans.model.slave.MenuQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.MenuSysApiMappingQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserTmpRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserTmpRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserTmpRoleSlaveVO;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserTmpRoleMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.tenant.TenantUserTmpRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserTmpRoleSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("tenantUserTmpRoleMasterStdDataSvc")
public class TenantUserTmpRoleMasterStdDataSvcImpl
        extends BaseRoleMasterStdDataSvc<TenantUserTmpRoleMasterVO, TenantUserTmpRoleUpdatableObj, TenantUserTmpRoleUpdateCtx, TenantUserTmpRoleDelParam, TenantUserTmpRoleSlaveVO, TenantUserTmpRole>
        implements TenantUserTmpRoleMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(TenantUserTmpRoleMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("tenantUserTmpRoleMasterDbSvc")
    private TenantUserTmpRoleMasterDbSvc tenantUserTmpRoleMasterDbSvc;

    @Autowired
    @Qualifier("tenantUserTmpRoleSlaveStdDataSvc")
    private TenantUserTmpRoleSlaveStdDataSvc tenantUserTmpRoleSlaveStdDataSvc;

    private final TenantUserTmpRoleUpdatableAttrsAnalyser updatableAttrsAnalyser = new TenantUserTmpRoleUpdatableAttrsAnalyser();

    @Override
    protected TenantUserTmpRoleSlaveVO getExistingRole(final Long id) {
        return tenantUserTmpRoleSlaveStdDataSvc.queryByPk(id,
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
                                SimpleRole.ShowColumn.UNLIMITED_DATE_RANGE.getCode(), SimpleRole.ShowColumn.EFFECTIVE_START_DATE.getCode(),
                                SimpleRole.ShowColumn.EFFECTIVE_END_DATE.getCode(), SimpleRole.ShowColumn.UNLIMITED_TIME_RANGE.getCode(),
                                SimpleRole.ShowColumn.EFFECTIVE_START_TIME.getCode(), SimpleRole.ShowColumn.EFFECTIVE_END_TIME.getCode(),
                                BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                        .build());
    }

    @Override
    protected List<TenantUserTmpRoleSlaveVO> queryExistingRoles(TenantUserTmpRoleDelParam delParam) {
        final TenantUserTmpRoleQueryParam queryParam = new TenantUserTmpRoleQueryParam.Builder()
                .tenantIds(delParam.getTenantIds())
                .sysPrjIds(delParam.getSysPrjIds())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return tenantUserTmpRoleSlaveStdDataSvc.queryByParam(queryParam,
                new RoleQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build());
    }

    @Override
    protected TenantUserTmpRoleUpdateCtx getUpdateContext(final TenantUserTmpRoleMasterVO tmpRole,
                                                          final TenantUserTmpRoleSlaveVO existingTmpRole) {
        return updatableAttrsAnalyser.analyseUpdatedAttrs(tmpRole, existingTmpRole);
    }

    @Override
    protected void doSave(final TenantUserTmpRole newEntity) {
        tenantUserTmpRoleMasterDbSvc.dbInsert(newEntity);
    }

    @Override
    protected int doUpdate(final TenantUserTmpRole example) {
        return tenantUserTmpRoleMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return tenantUserTmpRoleMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    protected int assignMenusToRole(final List<Long> menuIds, final Long tmpRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign menus to TenantUserTmpRole =====> menuIds: %s, tmpRoleId: %s",
                    JsonUtil.toJsonStr(menuIds), tmpRoleId));
        }
        return tenantUserTmpRoleMasterDbSvc.assignMenusToTmpRole(menuIds, tmpRoleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeMenusFromRole(final List<Long> menuIds, final Long tmpRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menus from TenantUserTmpRole =====> menuIds: %s, tmpRoleId: %s",
                    JsonUtil.toJsonStr(menuIds), tmpRoleId));
        }
        return tenantUserTmpRoleMasterDbSvc.removeMenusFromTmpRoles(menuIds, Collections.singletonList(tmpRoleId));
    }

    @Override
    protected int removeMenusFromRoles(final List<Long> tmpRoleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menus from TenantUserTmpRoles ======> tmpRoleIds: %s", JsonUtil.toJsonStr(tmpRoleIds)));
        }
        return tenantUserTmpRoleMasterDbSvc.removeMenusFromTmpRoles(null, tmpRoleIds);
    }

    @Override
    protected int assignMenuSysApisToRole(final List<Long> sysApiMappingIds, final Long tmpRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign menuSysApis to TenantUserTmpRole =====> sysApiMappingIds: %s, tmpRoleId: %s",
                    JsonUtil.toJsonStr(sysApiMappingIds), tmpRoleId));
        }
        return tenantUserTmpRoleMasterDbSvc.assignMenuSysApisToTmpRole(sysApiMappingIds, tmpRoleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeMenuSysApisFromRole(final List<Long> sysApiMappingIds, final Long tmpRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menuSysApis from TenantUserTmpRole =====> sysApiMappingIds: %s, tmpRoleId: %s",
                    JsonUtil.toJsonStr(sysApiMappingIds), tmpRoleId));
        }
        return tenantUserTmpRoleMasterDbSvc.removeMenuSysApisFromTmpRoles(sysApiMappingIds, Collections.singletonList(tmpRoleId));
    }

    @Override
    protected int removeMenuSysApisFromRoles(final List<Long> tmpRoleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove menuSysApis from TenantUserTmpRoles ======> tmpRoleIds: %s", JsonUtil.toJsonStr(tmpRoleIds)));
        }
        return tenantUserTmpRoleMasterDbSvc.removeMenuSysApisFromTmpRoles(null, tmpRoleIds);
    }

    @Override
    protected int assignSysApisToRole(final List<Long> sysApiIds, final Long tmpRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign sysApis to TenantUserTmpRole =====> sysApiIds: %s, tmpRoleId: %s",
                    JsonUtil.toJsonStr(sysApiIds), tmpRoleId));
        }
        return tenantUserTmpRoleMasterDbSvc.assignSysApisToTmpRole(sysApiIds, tmpRoleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeSysApisFromRole(final List<Long> sysApiIds, final Long tmpRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysApis from TenantUserTmpRole =====> sysApiIds: %s, tmpRoleId: %s",
                    JsonUtil.toJsonStr(sysApiIds), tmpRoleId));
        }
        return tenantUserTmpRoleMasterDbSvc.removeSysApisFromTmpRoles(sysApiIds, Collections.singletonList(tmpRoleId));
    }

    @Override
    protected int removeSysApisFromRoles(final List<Long> tmpRoleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysApis from TenantUserTmpRoles ======> tmpRoleIds: %s", JsonUtil.toJsonStr(tmpRoleIds)));
        }
        return tenantUserTmpRoleMasterDbSvc.removeSysApisFromTmpRoles(null, tmpRoleIds);
    }

    @Override
    public TenantUserTmpRole toNewEntity(final TenantUserTmpRoleMasterVO tmpRole) {
        final TenantUserTmpRole newEntity = new TenantUserTmpRole();
        assembleCommonAttrsOnInsert(newEntity, tmpRole);
        assembleSimpleTmpRoleAttrs(newEntity, tmpRole);
        newEntity.setTenantSid(tmpRole.getTenantId());
        return newEntity;
    }

    @Override
    public TenantUserTmpRole toUpdatableObj(final TenantUserTmpRoleUpdatableObj updatableObj) {
        final TenantUserTmpRole example = new TenantUserTmpRole();
        assembleTmpRoleUpdatableAttrs(example, updatableObj);
        return example;
    }
}
