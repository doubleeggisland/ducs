package com.ioiox.dei.duc.std.data.svc.impl.master.user;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.AcctUserGrp;
import com.ioiox.dei.duc.beans.entity.UserGrp;
import com.ioiox.dei.duc.beans.model.user.AcctUserGrpUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.user.AcctUserGrpUpdatableObj;
import com.ioiox.dei.duc.beans.model.user.AcctUserGrpUpdateCtx;
import com.ioiox.dei.duc.beans.vo.std.master.user.AcctUserGrpDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.user.AcctUserGrpMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.UserGrpQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.user.AcctUserGrpQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.AcctUserGrpSlaveStdVO;
import com.ioiox.dei.duc.db.service.master.user.AcctUserGrpMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseUserGrpMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.user.AcctUserGrpMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.user.AcctUserGrpSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("acctUserGrpMasterStdDataSvc")
public class AcctUserGrpMasterStdDataSvcImpl
        extends BaseUserGrpMasterStdDataSvc<AcctUserGrpMasterStdVO, AcctUserGrpUpdatableObj, AcctUserGrpUpdateCtx, AcctUserGrpDelParam, AcctUserGrpSlaveStdVO, AcctUserGrp>
        implements AcctUserGrpMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(AcctUserGrpMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("acctUserGrpMasterDbSvc")
    private AcctUserGrpMasterDbSvc acctUserGrpMasterDbSvc;

    @Autowired
    @Qualifier("acctUserGrpSlaveStdDataSvc")
    private AcctUserGrpSlaveStdDataSvc acctUserGrpSlaveStdDataSvc;

    private final AcctUserGrpUpdatableAttrsAnalyser analyser = new AcctUserGrpUpdatableAttrsAnalyser();

    @Override
    protected AcctUserGrpSlaveStdVO getExistingUserGrp(final Long id) {
        return acctUserGrpSlaveStdDataSvc.queryByPk(id,
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
    protected List<AcctUserGrpSlaveStdVO> queryExistingUserGrps(final AcctUserGrpDelParam delParam) {
        final AcctUserGrpQueryParam queryParam = new AcctUserGrpQueryParam.Builder()
                .corpIds(delParam.getCorpIds())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return acctUserGrpSlaveStdDataSvc.queryByParam(queryParam,
                new UserGrpQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build());
    }

    @Override
    protected int assignRolesToUserGrp(final List<Long> roleIds, final Long userGrpId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign roles to acctUserGrp =====> roleIds: %s, userGrpId: %s",
                    JsonUtil.toJsonStr(roleIds), userGrpId));
        }
        return acctUserGrpMasterDbSvc.assignRolesToUserGrp(roleIds, userGrpId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeRolesFromUserGrp(final List<Long> roleIds, final Long userGrpId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove roles from acctUserGrp =====> roleIds: %s, userGrpId: %s",
                    JsonUtil.toJsonStr(roleIds), userGrpId));
        }
        return acctUserGrpMasterDbSvc.removeRolesFromUserGrps(roleIds, Collections.singletonList(userGrpId));
    }

    @Override
    protected int removeRolesFromUserGrps(final List<Long> userGrpIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove roles from acctUserGrps ======> userGrpIds: %s", JsonUtil.toJsonStr(userGrpIds)));
        }
        return acctUserGrpMasterDbSvc.removeRolesFromUserGrps(null, userGrpIds);
    }

    @Override
    protected int assignSysResRolesToUserGrp(final List<Long> sysResRoleIds, final Long userGrpId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign sysResRoles to acctUserGrp =====> sysResRoleIds: %s, userGrpId: %s",
                    JsonUtil.toJsonStr(sysResRoleIds), userGrpId));
        }
        return acctUserGrpMasterDbSvc.assignSysResRolesToUserGrp(sysResRoleIds, userGrpId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeSysResRolesFromUserGrp(final List<Long> sysResRoleIds, final Long userGrpId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResRoles from acctUserGrp =====> sysResRoleIds: %s, userGrpId: %s",
                    JsonUtil.toJsonStr(sysResRoleIds), userGrpId));
        }
        return acctUserGrpMasterDbSvc.removeSysResRolesFromUserGrps(sysResRoleIds, Collections.singletonList(userGrpId));
    }

    @Override
    protected int removeSysResRolesFromUserGrps(final List<Long> userGrpIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResRoles from acctUserGrps ======> userGrpIds: %s", JsonUtil.toJsonStr(userGrpIds)));
        }
        return acctUserGrpMasterDbSvc.removeSysResRolesFromUserGrps(null, userGrpIds);
    }

    @Override
    protected AcctUserGrpUpdateCtx getUpdateContext(final AcctUserGrpMasterStdVO userGrp,
                                                    final AcctUserGrpSlaveStdVO existingUserGrp) {
        return analyser.analyseUpdatedAttrs(userGrp, existingUserGrp);
    }

    @Override
    protected void doSave(final AcctUserGrp newEntity) {
        acctUserGrpMasterDbSvc.dbInsert(newEntity);
    }

    @Override
    protected int doUpdate(final AcctUserGrp example) {
        return acctUserGrpMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return acctUserGrpMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    public AcctUserGrp toNewEntity(final AcctUserGrpMasterStdVO userGrp) {
        final AcctUserGrp newEntity = new AcctUserGrp();
        assembleCommonAttrsOnInsert(newEntity, userGrp);
        assembleCommonAttrs(newEntity, userGrp);
        newEntity.setCorpSid(userGrp.getCorpId());
        newEntity.setTenantSid(userGrp.getTenantId());
        return newEntity;
    }

    @Override
    public AcctUserGrp toUpdatableObj(final AcctUserGrpUpdatableObj updatableVO) {
        final AcctUserGrp example = new AcctUserGrp();
        assembleUpdatableAttrs(example, updatableVO);
        return example;
    }
}
