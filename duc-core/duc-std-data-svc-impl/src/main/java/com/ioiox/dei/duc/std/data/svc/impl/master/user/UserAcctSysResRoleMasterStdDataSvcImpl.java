package com.ioiox.dei.duc.std.data.svc.impl.master.user;

import com.ioiox.dei.core.beans.BaseDeiEntity;
import com.ioiox.dei.core.constant.DeiGlobalConstant;
import com.ioiox.dei.core.orm.mybatis.model.std.data.DefaultStdDataQueryCfg;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.utils.JsonUtil;
import com.ioiox.dei.duc.beans.entity.Role;
import com.ioiox.dei.duc.beans.entity.UserAcctSysResRole;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctSysResRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctSysResRoleUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctSysResRoleUpdateCtx;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctSysResRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctSysResRoleMasterStdVO;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResSlaveStdVO;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSysResRoleSlaveStdVO;
import com.ioiox.dei.duc.db.service.master.user.UserAcctSysResRoleMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseSysResRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.user.UserAcctSysResRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.user.UserAcctSysResRoleSlaveStdDataSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("userAcctSysResRoleMasterStdDataSvc")
public class UserAcctSysResRoleMasterStdDataSvcImpl
        extends BaseSysResRoleMasterStdDataSvc<UserAcctSysResRoleMasterStdVO, UserAcctSysResRoleUpdatableObj, UserAcctSysResRoleUpdateCtx, UserAcctSysResRoleDelParam, UserAcctSysResRoleSlaveStdVO, UserAcctSysResRole>
        implements UserAcctSysResRoleMasterStdDataSvc {

    private static final Logger log = LoggerFactory.getLogger(UserAcctSysResRoleMasterStdDataSvcImpl.class);

    @Autowired
    @Qualifier("userAcctSysResRoleMasterDbSvc")
    private UserAcctSysResRoleMasterDbSvc userAcctSysResRoleMasterDbSvc;

    @Autowired
    @Qualifier("userAcctSysResRoleSlaveStdDataSvc")
    private UserAcctSysResRoleSlaveStdDataSvc userAcctSysResRoleSlaveStdDataSvc;

    private final UserAcctSysResRoleUpdatableAttrsAnalyser analyser = new UserAcctSysResRoleUpdatableAttrsAnalyser();

    @Override
    protected UserAcctSysResRoleSlaveStdVO getExistingSysResRole(final Long id) {
        return userAcctSysResRoleSlaveStdDataSvc.queryByPk(id,
                new SysResRoleQueryCfg.Builder()
                        .needSysResources(DeiGlobalConstant.FLAG_YES)
                        .sysResQueryCfg(new DefaultStdDataQueryCfg.Builder()
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
    protected List<UserAcctSysResRoleSlaveStdVO> queryExistingSysResRoles(final UserAcctSysResRoleDelParam delParam) {
        final UserAcctSysResRoleQueryParam queryParam = new UserAcctSysResRoleQueryParam.Builder()
                .corpIds(delParam.getCorpIds())
                .sysPrjIds(delParam.getSysPrjIds())
                .statuses(delParam.getStatuses())
                .pks(delParam.getPks())
                .build();
        return userAcctSysResRoleSlaveStdDataSvc.queryByParam(queryParam,
                new SysResRoleQueryCfg.Builder()
                        .showColumns(Collections.singletonList(BaseDeiEntity.ShowColumn.ID.getCode()))
                        .build());
    }

    @Override
    protected List<Long> getSysResIds(final UserAcctSysResRoleMasterStdVO sysResRole) {
        return sysResRole.getSysResIds();
    }

    @Override
    protected List<Long> getExistingSysResIds(final UserAcctSysResRoleSlaveStdVO existingSysResRole) {
        return DeiCollectionUtil.isEmpty(existingSysResRole.getSysResources())
                ? Collections.emptyList() : existingSysResRole.getSysResources().stream().map(SysResSlaveStdVO::getId).collect(Collectors.toList());
    }

    @Override
    protected UserAcctSysResRoleUpdateCtx getUpdateContext(final UserAcctSysResRoleMasterStdVO sysResRole,
                                                           final UserAcctSysResRoleSlaveStdVO existingSysResRole) {
        return analyser.analyseUpdatedAttrs(sysResRole, existingSysResRole);
    }

    @Override
    protected void doSave(final UserAcctSysResRole newEntity) {
        userAcctSysResRoleMasterDbSvc.dbInsert(newEntity);
    }

    @Override
    protected int doUpdate(final UserAcctSysResRole example) {
        return userAcctSysResRoleMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return userAcctSysResRoleMasterDbSvc.deleteByParams(deleteParams);
    }

    @Override
    protected int assignSysResourcesToSysResRole(final List<Long> sysResIds, final Long sysResRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("assign sysResources to userAcctSysResRole =====> sysResIds: %s, sysResRoleId: %s",
                    JsonUtil.toJsonStr(sysResIds), sysResRoleId));
        }
        return userAcctSysResRoleMasterDbSvc.assignSysResourcesToSysResRole(sysResIds, sysResRoleId, operator, new Date(System.currentTimeMillis()));
    }

    @Override
    protected int removeSysResourcesFromSysResRole(final List<Long> sysResIds, final Long sysResRoleId, final String operator) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResources from userAcctSysResRole =====> sysResIds: %s, sysResRoleId: %s",
                    JsonUtil.toJsonStr(sysResIds), sysResRoleId));
        }
        return userAcctSysResRoleMasterDbSvc.removeSysResourcesFromSysResRoles(sysResIds, Collections.singletonList(sysResRoleId));
    }

    @Override
    protected int removeSysResourcesFromSysResRoles(final List<Long> sysResRoleIds) {
        if (log.isInfoEnabled()) {
            log.info(String.format("remove sysResources from userAcctSysResRoles ======> sysResRoleIds: %s", JsonUtil.toJsonStr(sysResRoleIds)));
        }
        return userAcctSysResRoleMasterDbSvc.removeSysResourcesFromSysResRoles(null, sysResRoleIds);
    }

    @Override
    public UserAcctSysResRole toNewEntity(final UserAcctSysResRoleMasterStdVO sysResRole) {
        final UserAcctSysResRole newEntity = new UserAcctSysResRole();
        assembleCommonAttrsOnInsert(newEntity, sysResRole);
        assembleRoleCommonAttrs(newEntity, sysResRole);
        newEntity.setTenantSid(sysResRole.getTenantId());
        newEntity.setCorpSid(sysResRole.getTenantId());
        return newEntity;
    }

    @Override
    public UserAcctSysResRole toUpdatableObj(final UserAcctSysResRoleUpdatableObj updatableVO) {
        final UserAcctSysResRole example = new UserAcctSysResRole();
        assembleRoleUpdatableAttrs(example, updatableVO);
        return example;
    }
}
