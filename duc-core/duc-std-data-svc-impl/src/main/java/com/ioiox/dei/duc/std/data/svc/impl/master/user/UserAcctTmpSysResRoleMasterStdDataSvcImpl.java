package com.ioiox.dei.duc.std.data.svc.impl.master.user;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DefaultStdDataQueryCfg;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.SimpleRole;
import com.ioiox.dei.duc.beans.entity.UserAcctTmpSysResRole;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctSysResRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctTmpSysResRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctTmpSysResRoleUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctTmpSysResRoleUpdateCtx;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctTmpSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctTmpSysResRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctTmpSysResRoleSlaveVO;
import com.ioiox.dei.duc.db.service.master.user.UserAcctTmpSysResRoleMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseSysResRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.user.UserAcctTmpSysResRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.user.UserAcctTmpSysResRoleSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userAcctTmpSysResRoleMasterStdDataSvc")
public class UserAcctTmpSysResRoleMasterStdDataSvcImpl
        extends BaseSysResRoleMasterStdDataSvc<UserAcctTmpSysResRoleMasterVO, UserAcctTmpSysResRoleUpdatableObj, UserAcctTmpSysResRoleUpdateCtx, UserAcctSysResRoleDelParam, UserAcctTmpSysResRoleSlaveVO, UserAcctTmpSysResRole>
        implements UserAcctTmpSysResRoleMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(UserAcctTmpSysResRoleMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("userAcctTmpSysResRoleMasterDbSvc")
    private UserAcctTmpSysResRoleMasterDbSvc userAcctTmpSysResRoleMasterDbSvc;

    @Autowired
    @Qualifier("userAcctTmpSysResRoleSlaveStdDataSvc")
    private UserAcctTmpSysResRoleSlaveStdDataSvc userAcctTmpSysResRoleSlaveStdDataSvc;

    private final UserAcctTmpSysResRoleUpdatableAttrsAnalyser analyser = new UserAcctTmpSysResRoleUpdatableAttrsAnalyser();

    @Override
    protected UserAcctTmpSysResRoleSlaveVO getExistingSysResRole(final Long id) {
        return userAcctTmpSysResRoleSlaveStdDataSvc.queryByPk(id,
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
    protected List<UserAcctTmpSysResRoleSlaveVO> queryExistingSysResRoles(final UserAcctSysResRoleDelParam delParam) {
        final UserAcctTmpSysResRoleQueryParam queryParam = new UserAcctTmpSysResRoleQueryParam.Builder()
                .tenantIds(delParam.getTenantIds())
                .corpIds(delParam.getCorpIds())
                .sysPrjIds(delParam.getSysPrjIds())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return userAcctTmpSysResRoleSlaveStdDataSvc.queryByParam(queryParam,
                new SysResRoleQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build());
    }

    @Override
    protected UserAcctTmpSysResRoleUpdateCtx getUpdateContext(final UserAcctTmpSysResRoleMasterVO tmpSysResRole,
                                                              final UserAcctTmpSysResRoleSlaveVO existingTmpSysResRole) {
        return analyser.analyseUpdatedAttrs(tmpSysResRole, existingTmpSysResRole);
    }

    @Override
    protected void doSave(final UserAcctTmpSysResRole newEntity) {
        userAcctTmpSysResRoleMasterDbSvc.dbInsert(newEntity);
    }

    @Override
    protected int doUpdate(final UserAcctTmpSysResRole example) {
        return userAcctTmpSysResRoleMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return userAcctTmpSysResRoleMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    protected int assignSysResourcesToSysResRole(final List<Long> sysResIds, final Long tmpSysResRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign sysResources to userAcctTmpSysResRole =====> sysResIds: %s, tmpSysResRoleId: %s",
                    JsonUtil.toJsonStr(sysResIds), tmpSysResRoleId));
        }
        return userAcctTmpSysResRoleMasterDbSvc.assignSysResourcesToTmpSysResRole(sysResIds, tmpSysResRoleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeSysResourcesFromSysResRole(final List<Long> sysResIds, final Long tmpSysResRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResources from userAcctTmpSysResRole =====> sysResIds: %s, tmpSysResRoleId: %s",
                    JsonUtil.toJsonStr(sysResIds), tmpSysResRoleId));
        }
        return userAcctTmpSysResRoleMasterDbSvc.removeSysResourcesFromTmpSysResRoles(sysResIds, Collections.singletonList(tmpSysResRoleId));
    }

    @Override
    protected int removeSysResourcesFromSysResRoles(final List<Long> tmpSysResRoleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResources from userAcctTmpSysResRoles ======> tmpSysResRoleIds: %s", JsonUtil.toJsonStr(tmpSysResRoleIds)));
        }
        return userAcctTmpSysResRoleMasterDbSvc.removeSysResourcesFromTmpSysResRoles(null, tmpSysResRoleIds);
    }

    @Override
    public UserAcctTmpSysResRole toNewEntity(final UserAcctTmpSysResRoleMasterVO tmpSysResRole) {
        final UserAcctTmpSysResRole newEntity = new UserAcctTmpSysResRole();
        assembleCommonAttrsOnInsert(newEntity, tmpSysResRole);
        assembleSimpleTmpRoleAttrs(newEntity, tmpSysResRole);
        newEntity.setTenantSid(tmpSysResRole.getTenantId());
        newEntity.setCorpSid(tmpSysResRole.getCorpId());
        return newEntity;
    }

    @Override
    public UserAcctTmpSysResRole toUpdatableObj(final UserAcctTmpSysResRoleUpdatableObj updatableVO) {
        final UserAcctTmpSysResRole example = new UserAcctTmpSysResRole();
        assembleTmpRoleUpdatableAttrs(example, updatableVO);
        return example;
    }
}
