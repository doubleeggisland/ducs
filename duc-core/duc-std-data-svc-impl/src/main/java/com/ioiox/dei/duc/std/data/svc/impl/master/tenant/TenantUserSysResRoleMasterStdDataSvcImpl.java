package com.ioiox.dei.duc.std.data.svc.impl.master.tenant;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DefaultStdDataQueryCfg;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.SimpleRole;
import com.ioiox.dei.duc.beans.entity.TenantUserSysResRole;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserSysResRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserSysResRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserSysResRoleUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserSysResRoleUpdateCtx;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserSysResRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserSysResRoleSlaveVO;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserSysResRoleMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseSysResRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.tenant.TenantUserSysResRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserSysResRoleSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("tenantUserSysResRoleMasterStdDataSvc")
public class TenantUserSysResRoleMasterStdDataSvcImpl
        extends BaseSysResRoleMasterStdDataSvc<TenantUserSysResRoleMasterVO, TenantUserSysResRoleUpdatableObj, TenantUserSysResRoleUpdateCtx, TenantUserSysResRoleDelParam, TenantUserSysResRoleSlaveVO, TenantUserSysResRole>
        implements TenantUserSysResRoleMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(TenantUserSysResRoleMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("tenantUserSysResRoleMasterDbSvc")
    private TenantUserSysResRoleMasterDbSvc tenantUserSysResRoleMasterDbSvc;

    @Autowired
    @Qualifier("tenantUserSysResRoleSlaveStdDataSvc")
    private TenantUserSysResRoleSlaveStdDataSvc tenantUserSysResRoleSlaveStdDataSvc;

    private final TenantUserSysResRoleUpdatableAttrsAnalyser updatableAttrsAnalyser = new TenantUserSysResRoleUpdatableAttrsAnalyser();

    @Override
    protected TenantUserSysResRoleSlaveVO getExistingSysResRole(final Long id) {
        return tenantUserSysResRoleSlaveStdDataSvc.queryByPk(id,
                new SysResRoleQueryCfg.Builder()
                        .needSysResources(DeiGlobalConstant.FLAG_YES)
                        .sysResQueryCfg(new DefaultStdDataQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .showColumns(Arrays.asList(BaseDeiEntity.ShowColumn.ID.getCode(),
                                SimpleRole.ShowColumn.CODE.getCode(), SimpleRole.ShowColumn.NAME.getCode(),
                                SimpleRole.ShowColumn.TYPE.getCode(), SimpleRole.ShowColumn.STATUS.getCode(),
                                SimpleRole.ShowColumn.MEMO.getCode(), SimpleRole.ShowColumn.SYS_PRJ_SID.getCode(),
                                BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                        .build());
    }

    @Override
    protected List<TenantUserSysResRoleSlaveVO> queryExistingSysResRoles(final TenantUserSysResRoleDelParam delParam) {
        final TenantUserSysResRoleQueryParam queryParam = new TenantUserSysResRoleQueryParam.Builder()
                .tenantIds(delParam.getTenantIds())
                .sysPrjIds(delParam.getSysPrjIds())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return tenantUserSysResRoleSlaveStdDataSvc.queryByParam(queryParam,
                new SysResRoleQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build());
    }

    @Override
    protected TenantUserSysResRoleUpdateCtx getUpdateContext(final TenantUserSysResRoleMasterVO sysResRole,
                                                             final TenantUserSysResRoleSlaveVO existingSysResRole) {
        return updatableAttrsAnalyser.analyseUpdatedAttrs(sysResRole, existingSysResRole);
    }

    @Override
    protected void doSave(final TenantUserSysResRole newEntity) {
        tenantUserSysResRoleMasterDbSvc.dbInsert(newEntity);
    }

    @Override
    protected int doUpdate(final TenantUserSysResRole example) {
        return tenantUserSysResRoleMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return tenantUserSysResRoleMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    protected int assignSysResourcesToSysResRole(List<Long> sysResIds, Long sysResRoleId, String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign sysResources to TenantUserSysResRole =====> sysResIds: %s, sysResRoleId: %s",
                    JsonUtil.toJsonStr(sysResIds), sysResRoleId));
        }
        return tenantUserSysResRoleMasterDbSvc.assignSysResourcesToSysResRole(sysResIds, sysResRoleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeSysResourcesFromSysResRole(List<Long> sysResIds, Long sysResRoleId, String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResources from TenantUserSysResRole =====> sysResIds: %s, sysResRoleId: %s",
                    JsonUtil.toJsonStr(sysResIds), sysResRoleId));
        }
        return tenantUserSysResRoleMasterDbSvc.removeSysResourcesFromSysResRoles(sysResIds, Collections.singletonList(sysResRoleId));
    }

    @Override
    protected int removeSysResourcesFromSysResRoles(List<Long> sysResRoleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResources from TenantUserSysResRoles ======> sysResRoleIds: %s", JsonUtil.toJsonStr(sysResRoleIds)));
        }
        return tenantUserSysResRoleMasterDbSvc.removeSysResourcesFromSysResRoles(null, sysResRoleIds);
    }

    @Override
    public TenantUserSysResRole toNewEntity(final TenantUserSysResRoleMasterVO sysResRole) {
        final TenantUserSysResRole newEntity = new TenantUserSysResRole();
        assembleCommonAttrsOnInsert(newEntity, sysResRole);
        assembleSimpleRoleAttrs(newEntity, sysResRole);
        newEntity.setTenantSid(sysResRole.getTenantId());
        return newEntity;
    }

    @Override
    public TenantUserSysResRole toUpdatableObj(final TenantUserSysResRoleUpdatableObj updatableVO) {
        final TenantUserSysResRole example = new TenantUserSysResRole();
        assembleRoleUpdatableAttrs(example, updatableVO);
        return example;
    }
}
