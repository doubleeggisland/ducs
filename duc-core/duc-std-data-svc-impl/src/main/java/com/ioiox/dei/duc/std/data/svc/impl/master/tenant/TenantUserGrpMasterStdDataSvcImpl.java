package com.ioiox.dei.duc.std.data.svc.impl.master.tenant;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.TenantUserGrp;
import com.ioiox.dei.duc.beans.entity.UserGrp;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserGrpDelParam;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserGrpUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserGrpUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserGrpUpdateCtx;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.UserGrpQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.tenant.TenantUserGrpQueryParam;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserGrpMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserGrpSlaveVO;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserGrpMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseUserGrpMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.tenant.TenantUserGrpMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserGrpSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("tenantUserGrpMasterStdDataSvc")
public class TenantUserGrpMasterStdDataSvcImpl
        extends BaseUserGrpMasterStdDataSvc<TenantUserGrpMasterVO, TenantUserGrpUpdatableObj, TenantUserGrpUpdateCtx, TenantUserGrpDelParam, TenantUserGrpSlaveVO, TenantUserGrp>
        implements TenantUserGrpMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(TenantUserGrpMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("tenantUserGrpMasterDbSvc")
    private TenantUserGrpMasterDbSvc tenantUserGrpMasterDbSvc;

    @Autowired
    @Qualifier("tenantUserGrpSlaveStdDataSvc")
    private TenantUserGrpSlaveStdDataSvc tenantUserGrpSlaveStdDataSvc;

    private final TenantUserGrpUpdatableAttrsAnalyser updatableAttrsAnalyser = new TenantUserGrpUpdatableAttrsAnalyser();

    @Override
    protected TenantUserGrpSlaveVO getExistingUserGrp(final Long id) {
        return tenantUserGrpSlaveStdDataSvc.queryByPk(id,
                new UserGrpQueryCfg.Builder()
                        .needRoles(DeiGlobalConstant.FLAG_YES)
                        .roleQueryCfg(new RoleQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .needSysResRoles(DeiGlobalConstant.FLAG_YES)
                        .sysResRoleQueryCfg(new SysResRoleQueryCfg.Builder()
                                .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                                .build())
                        .showColumns(Arrays.asList(BaseDeiEntity.ShowColumn.ID.getCode(),
                                UserGrp.ShowColumn.CODE.getCode(), UserGrp.ShowColumn.NAME.getCode(),
                                UserGrp.ShowColumn.MEMO.getCode(), UserGrp.ShowColumn.STATUS.getCode(),
                                BaseDeiEntity.ShowColumn.VERSION_NUM.getCode()))
                        .build());
    }

    @Override
    protected List<TenantUserGrpSlaveVO> queryExistingUserGrps(final TenantUserGrpDelParam delParam) {
        final TenantUserGrpQueryParam queryParam = new TenantUserGrpQueryParam.Builder()
                .tenantIds(delParam.getTenantIds())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return tenantUserGrpSlaveStdDataSvc.queryByParam(queryParam,
                new UserGrpQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build());
    }

    @Override
    protected int assignRolesToUserGrp(final List<Long> roleIds, final Long userGrpId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign roles to TenantUserGrp =====> roleIds: %s, userGrpId: %s",
                    JsonUtil.toJsonStr(roleIds), userGrpId));
        }
        return tenantUserGrpMasterDbSvc.assignRolesToUserGrp(roleIds, userGrpId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeRolesFromUserGrp(final List<Long> roleIds, final Long userGrpId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove roles from TenantUserGrp =====> roleIds: %s, userGrpId: %s",
                    JsonUtil.toJsonStr(roleIds), userGrpId));
        }
        return tenantUserGrpMasterDbSvc.removeRolesFromUserGrps(roleIds, Collections.singletonList(userGrpId));
    }

    @Override
    protected int removeRolesFromUserGrps(final List<Long> userGrpIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove roles from TenantUserGrps ======> userGrpIds: %s", JsonUtil.toJsonStr(userGrpIds)));
        }
        return tenantUserGrpMasterDbSvc.removeRolesFromUserGrps(null, userGrpIds);
    }

    @Override
    protected int assignSysResRolesToUserGrp(final List<Long> sysResRoleIds, final Long userGrpId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign sysResRoles to TenantUserGrp =====> sysResRoleIds: %s, userGrpId: %s",
                    JsonUtil.toJsonStr(sysResRoleIds), userGrpId));
        }
        return tenantUserGrpMasterDbSvc.assignSysResRolesToUserGrp(sysResRoleIds, userGrpId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeSysResRolesFromUserGrp(final List<Long> sysResRoleIds, final Long userGrpId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResRoles from TenantUserGrp =====> sysResRoleIds: %s, userGrpId: %s",
                    JsonUtil.toJsonStr(sysResRoleIds), userGrpId));
        }
        return tenantUserGrpMasterDbSvc.removeSysResRolesFromUserGrps(sysResRoleIds, Collections.singletonList(userGrpId));
    }

    @Override
    protected int removeSysResRolesFromUserGrps(final List<Long> userGrpIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResRoles from TenantUserGrps ======> userGrpIds: %s", JsonUtil.toJsonStr(userGrpIds)));
        }
        return tenantUserGrpMasterDbSvc.removeSysResRolesFromUserGrps(null, userGrpIds);
    }

    @Override
    protected TenantUserGrpUpdateCtx getUpdateContext(TenantUserGrpMasterVO userGrp, TenantUserGrpSlaveVO existingUserGrp) {
        return updatableAttrsAnalyser.analyseUpdatedAttrs(userGrp, existingUserGrp);
    }

    @Override
    protected void doSave(final TenantUserGrp newEntity) {
        tenantUserGrpMasterDbSvc.dbInsert(newEntity);
    }

    @Override
    protected int doUpdate(TenantUserGrp example) {
        return tenantUserGrpMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return tenantUserGrpMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    public TenantUserGrp toNewEntity(final TenantUserGrpMasterVO userGrp) {
        final TenantUserGrp newEntity = new TenantUserGrp();
        assembleCommonAttrsOnInsert(newEntity, userGrp);
        assembleCommonAttrs(newEntity, userGrp);
        newEntity.setTenantSid(userGrp.getTenantId());
        return newEntity;
    }

    @Override
    public TenantUserGrp toUpdatableObj(TenantUserGrpUpdatableObj updatableObj) {
        final TenantUserGrp example = new TenantUserGrp();
        assembleCommonUpdatableAttrs(example, updatableObj);
        return example;
    }
}
