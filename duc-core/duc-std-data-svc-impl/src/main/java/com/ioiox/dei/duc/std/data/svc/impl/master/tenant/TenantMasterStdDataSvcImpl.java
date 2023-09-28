package com.ioiox.dei.duc.std.data.svc.impl.master.tenant;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.exception.DeiServiceException;
import com.ioiox.dei.core.orm.mybatis.service.std.data.BaseDeiMasterStdDataSvc;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.Tenant;
import com.ioiox.dei.duc.beans.model.master.tenant.*;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantQueryParam;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantSlaveVO;
import com.ioiox.dei.duc.db.service.master.tenant.TenantMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.master.tenant.*;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("tenantMasterStdDataSvc")
public class TenantMasterStdDataSvcImpl
        extends BaseDeiMasterStdDataSvc<TenantMasterVO, TenantUpdatableObj, Tenant>
        implements TenantMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(TenantMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("tenantMasterDbSvc")
    private TenantMasterDbSvc tenantMasterDbSvc;

    @Autowired
    @Qualifier("tenantSlaveStdDataSvc")
    private TenantSlaveStdDataSvc tenantSlaveStdDataSvc;

    @Autowired
    @Qualifier("tenantUserMasterStdDataSvc")
    private TenantUserMasterStdDataSvc tenantUserMasterStdDataSvc;

    @Autowired
    @Qualifier("tenantUserGrpMasterStdDataSvc")
    private TenantUserGrpMasterStdDataSvc tenantUserGrpMasterStdDataSvc;

    @Autowired
    @Qualifier("tenantUserRoleMasterStdDataSvc")
    private TenantUserRoleMasterStdDataSvc tenantUserRoleMasterStdDataSvc;

    @Autowired
    @Qualifier("tenantUserSysResRoleMasterStdDataSvc")
    private TenantUserSysResRoleMasterStdDataSvc tenantUserSysResRoleMasterStdDataSvc;

    @Autowired
    @Qualifier("tenantUserTmpRoleMasterStdDataSvc")
    private TenantUserTmpRoleMasterStdDataSvc tenantUserTmpRoleMasterStdDataSvc;

    @Autowired
    @Qualifier("tenantUserTmpSysResRoleMasterStdDataSvc")
    private TenantUserTmpSysResRoleMasterStdDataSvc tenantUserTmpSysResRoleMasterStdDataSvc;

    private final TenantUpdatableAttrsAnalyser updatableAttrsAnalyser = new TenantUpdatableAttrsAnalyser();

    @Override
    public long save(final TenantMasterVO tenant) {
        if (Objects.isNull(tenant)) {
            return DeiGlobalConstant.DEFAULT_SID;
        }
        final Tenant newEntity = toNewEntity(tenant);
        newEntity.setDefaultValueIfNeed();
        tenantMasterDbSvc.dbInsert(newEntity);
        return newEntity.getSid();
    }

    @Override
    public boolean update(final TenantMasterVO tenant) {
        if (Objects.isNull(tenant)
                || Objects.isNull(tenant.getId())) {
            throw new DeiServiceException("Please choose a tenant to update!");
        }
        final TenantSlaveVO existingTenant = getExistingTenant(tenant.getId());
        if (Objects.isNull(existingTenant)) {
            throw new DeiServiceException(String.format("Tenant doesn't exist =====> id: %s", tenant.getId()));
        }
        return update(tenant, existingTenant);
    }

    boolean update(final TenantMasterVO tenant,
                   final TenantSlaveVO existingTenant) {
        final TenantUpdateCtx updateCtx = updatableAttrsAnalyser.analyseUpdatedAttrs(tenant, existingTenant);
        final boolean updated = updateCtx.getUpdatableObj().updated();
        if (updated) {
            if (log.isInfoEnabled()) {
                final Map<String, String> updateSummary = updateCtx.getUpdatableObj().updateSummary();
                log.info(String.format("update Tenant =====> id: %s, updateSummary: %s", existingTenant.getId(), JsonUtil.toJsonStr(updateSummary)));
            }
            final Tenant example = toUpdatableObj(updateCtx.getUpdatableObj());
            assembleCommonAttrsOnUpdate(example, existingTenant, tenant);
            final int updatedRows = tenantMasterDbSvc.dbUpdate(example);
            if (DeiGlobalConstant.ZERO == updatedRows) {
                throw new DeiServiceException(String.format("Tenant has been updated by others =====> id: %s, versionNum: %s",
                        existingTenant.getId(), existingTenant.getVersionNum()));
            }
        }
        return updated;
    }

    @Override
    public int remove(final TenantDelParam delParam) {
        final Map<String, Object> deleteParams =
                Objects.isNull(delParam) ? null : delParam.deleteParams();
        if (DeiCollectionUtil.isEmpty(deleteParams)) {
            return DeiGlobalConstant.ZERO;
        }
        final List<TenantSlaveVO> existingTenants = queryExistingTenants(delParam);
        if (DeiCollectionUtil.isEmpty(existingTenants)) {
            throw new DeiServiceException(String.format("Cannot find any Tenants as per delParam =====> %s", JsonUtil.toJsonStr(delParam)));
        }
        final List<Long> tenantIds = existingTenants.stream().map(TenantSlaveVO::getId).collect(Collectors.toList());
        final int numOfTenantUsersRemoved =
                tenantUserMasterStdDataSvc.remove(new TenantUserDelParam.Builder()
                        .tenantIds(tenantIds)
                        .build());
        final int numOfTenantUserGrpsRemoved =
                tenantUserGrpMasterStdDataSvc.remove(new TenantUserGrpDelParam.Builder()
                        .tenantIds(tenantIds)
                        .build());
        final int numOfTenantUserRolesRemoved =
                tenantUserRoleMasterStdDataSvc.remove(new TenantUserRoleDelParam.Builder()
                        .tenantIds(tenantIds)
                        .build());
        final int numOfTenantUserSysResRolesRemoved =
                tenantUserSysResRoleMasterStdDataSvc.remove(new TenantUserSysResRoleDelParam.Builder()
                        .tenantIds(tenantIds)
                        .build());
        final int numOfTenantUserTmpRolesRemoved =
                tenantUserTmpRoleMasterStdDataSvc.remove(new TenantUserTmpRoleDelParam.Builder()
                        .tenantIds(tenantIds)
                        .build());
        final int numOfTenantUserTmpSysResRolesRemoved =
                tenantUserTmpSysResRoleMasterStdDataSvc.remove(new TenantUserTmpSysResRoleDelParam.Builder()
                        .tenantIds(tenantIds)
                        .build());
        final int numOfTenantsRemoved = tenantMasterDbSvc.deleteByParams(deleteParams);
        if (log.isInfoEnabled()) {
            log.info(String.format("remove Tenants as per delParam: %s =====> numOfTenantUsersRemoved: %s, numOfTenantUserGrpsRemoved: %s, numOfTenantUserRolesRemoved: %s, numOfTenantUserSysResRolesRemoved: %s, numOfTenantUserTmpRolesRemoved: %s, numOfTenantUserTmpSysResRolesRemoved: %s",
                    JsonUtil.toJsonStr(delParam),
                    numOfTenantUsersRemoved, numOfTenantUserGrpsRemoved,
                    numOfTenantUserRolesRemoved, numOfTenantUserSysResRolesRemoved, numOfTenantUserTmpRolesRemoved, numOfTenantUserTmpSysResRolesRemoved));
        }
        return numOfTenantsRemoved;
    }

    List<TenantSlaveVO> queryExistingTenants(final TenantDelParam delParam) {
        final TenantQueryParam queryParam = new TenantQueryParam.Builder()
                .pids(delParam.getPids())
                .rids(delParam.getRids())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return tenantSlaveStdDataSvc.queryByParam(
                queryParam,
                new TenantQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build()
        );
    }

    TenantSlaveVO getExistingTenant(final Long pk) {
        return tenantSlaveStdDataSvc.queryByPk(
                pk,
                new TenantQueryCfg.Builder()
                        .showColumns(Arrays.asList(BaseDeiEntity.ShowColumn.ID.getCode(),
                                Tenant.ShowColumn.CODE.getCode(), Tenant.ShowColumn.NAME.getCode(),
                                Tenant.ShowColumn.MEMO.getCode(), Tenant.ShowColumn.STATUS.getCode(),
                                Tenant.ShowColumn.LEVEL.getCode(), Tenant.ShowColumn.PARENT_SID.getCode(),
                                Tenant.ShowColumn.ROOT_SID.getCode(), BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                        .build()
        );
    }

    @Override
    public Tenant toNewEntity(final TenantMasterVO tenant) {
        final Tenant newEntity = new Tenant();
        assembleCommonAttrsOnInsert(newEntity, tenant);
        newEntity.setCode(tenant.getCode());
        newEntity.setName(tenant.getName());
        newEntity.setMemo(tenant.getMemo());
        newEntity.setStatus(tenant.getStatus());
        newEntity.setLvl(tenant.getLvl());
        newEntity.setPid(tenant.getPid());
        newEntity.setRid(tenant.getRid());
        return newEntity;
    }

    @Override
    public Tenant toUpdatableObj(final TenantUpdatableObj updatableObj) {
        final Tenant example = new Tenant();
        assembleCommonUpdatableAttrs(example, updatableObj);
        if (Objects.nonNull(updatableObj.getCode())) {
            example.setCode(updatableObj.getCode().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getName())) {
            example.setName(updatableObj.getName().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getMemo())) {
            example.setMemo(updatableObj.getMemo().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getStatus())) {
            example.setStatus(updatableObj.getStatus().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getLvl())) {
            example.setLvl(updatableObj.getLvl().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getPid())) {
            example.setPid(updatableObj.getPid().getNewVal());
        }
        if (Objects.nonNull(updatableObj.getRid())) {
            example.setRid(updatableObj.getRid().getNewVal());
        }
        return example;
    }
}
