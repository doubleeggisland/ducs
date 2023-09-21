package com.ioiox.dei.duc.std.data.svc.impl.master.tenant;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DefaultStdDataQueryCfg;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.SimpleRole;
import com.ioiox.dei.duc.beans.entity.TenantUserTmpSysResRole;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserTmpSysResRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserTmpSysResRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserTmpSysResRoleUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserTmpSysResRoleUpdateCtx;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserTmpSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserTmpSysResRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserTmpSysResRoleSlaveVO;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserTmpSysResRoleMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseSysResRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.tenant.TenantUserTmpSysResRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserTmpSysResRoleSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("tenantUserTmpSysResRoleMasterStdDataSvc")
public class TenantUserTmpSysResRoleMasterStdDataSvcImpl
        extends BaseSysResRoleMasterStdDataSvc<TenantUserTmpSysResRoleMasterVO, TenantUserTmpSysResRoleUpdatableObj, TenantUserTmpSysResRoleUpdateCtx, TenantUserTmpSysResRoleDelParam, TenantUserTmpSysResRoleSlaveVO, TenantUserTmpSysResRole>
        implements TenantUserTmpSysResRoleMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(TenantUserTmpSysResRoleMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("tenantUserTmpSysResRoleMasterDbSvc")
    private TenantUserTmpSysResRoleMasterDbSvc tenantUserTmpSysResRoleMasterDbSvc;

    @Autowired
    @Qualifier("tenantUserTmpSysResRoleSlaveStdDataSvc")
    private TenantUserTmpSysResRoleSlaveStdDataSvc tenantUserTmpSysResRoleSlaveStdDataSvc;

    private final TenantUserTmpSysResRoleUpdatableAttrsAnalyser updatableAttrsAnalyser = new TenantUserTmpSysResRoleUpdatableAttrsAnalyser();

    @Override
    protected TenantUserTmpSysResRoleSlaveVO getExistingSysResRole(final Long id) {
        return tenantUserTmpSysResRoleSlaveStdDataSvc.queryByPk(id,
                new SysResRoleQueryCfg.Builder()
                        .needSysResources(DeiGlobalConstant.FLAG_YES)
                        .sysResQueryCfg(new DefaultStdDataQueryCfg.Builder()
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
    protected List<TenantUserTmpSysResRoleSlaveVO> queryExistingSysResRoles(final TenantUserTmpSysResRoleDelParam delParam) {
        final TenantUserTmpSysResRoleQueryParam queryParam = new TenantUserTmpSysResRoleQueryParam.Builder()
                .tenantIds(delParam.getTenantIds())
                .sysPrjIds(delParam.getSysPrjIds())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return tenantUserTmpSysResRoleSlaveStdDataSvc.queryByParam(queryParam,
                new SysResRoleQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build());
    }

    @Override
    protected TenantUserTmpSysResRoleUpdateCtx getUpdateContext(final TenantUserTmpSysResRoleMasterVO tmpSysResRole,
                                                                final TenantUserTmpSysResRoleSlaveVO existingTmpSysResRole) {
        return updatableAttrsAnalyser.analyseUpdatedAttrs(tmpSysResRole, existingTmpSysResRole);
    }

    @Override
    protected void doSave(final TenantUserTmpSysResRole newEntity) {
        tenantUserTmpSysResRoleMasterDbSvc.dbInsert(newEntity);
    }

    @Override
    protected int doUpdate(final TenantUserTmpSysResRole example) {
        return tenantUserTmpSysResRoleMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return tenantUserTmpSysResRoleMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    protected int assignSysResourcesToSysResRole(final List<Long> sysResIds, final Long tmpSysResRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign sysResources to TenantUserTmpSysResRole =====> sysResIds: %s, tmpSysResRoleId: %s",
                    JsonUtil.toJsonStr(sysResIds), tmpSysResRoleId));
        }
        return tenantUserTmpSysResRoleMasterDbSvc.assignSysResourcesToTmpSysResRole(sysResIds, tmpSysResRoleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeSysResourcesFromSysResRole(final List<Long> sysResIds, final Long tmpSysResRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResources from TenantUserTmpSysResRole =====> sysResIds: %s, tmpSysResRoleId: %s",
                    JsonUtil.toJsonStr(sysResIds), tmpSysResRoleId));
        }
        return tenantUserTmpSysResRoleMasterDbSvc.removeSysResourcesFromTmpSysResRoles(sysResIds, Collections.singletonList(tmpSysResRoleId));
    }

    @Override
    protected int removeSysResourcesFromSysResRoles(final List<Long> tmpSysResRoleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResources from TenantUserTmpSysResRoles ======> tmpSysResRoleIds: %s", JsonUtil.toJsonStr(tmpSysResRoleIds)));
        }
        return tenantUserTmpSysResRoleMasterDbSvc.removeSysResourcesFromTmpSysResRoles(null, tmpSysResRoleIds);
    }

    @Override
    public TenantUserTmpSysResRole toNewEntity(final TenantUserTmpSysResRoleMasterVO tmpSysResRole) {
        final TenantUserTmpSysResRole newEntity = new TenantUserTmpSysResRole();
        assembleCommonAttrsOnInsert(newEntity, tmpSysResRole);
        assembleSimpleTmpRoleAttrs(newEntity, tmpSysResRole);
        newEntity.setTenantSid(tmpSysResRole.getTenantId());
        return newEntity;
    }

    @Override
    public TenantUserTmpSysResRole toUpdatableObj(final TenantUserTmpSysResRoleUpdatableObj updatableObj) {
        final TenantUserTmpSysResRole example = new TenantUserTmpSysResRole();
        assembleTmpRoleUpdatableAttrs(example, updatableObj);
        return example;
    }
}
