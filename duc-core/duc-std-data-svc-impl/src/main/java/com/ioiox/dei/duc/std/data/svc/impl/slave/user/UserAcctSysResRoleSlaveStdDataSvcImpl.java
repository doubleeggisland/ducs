package com.ioiox.dei.duc.std.data.svc.impl.slave.user;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.UserAcctSysResRole;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResSlaveVO;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSysResRoleSlaveVO;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctSysResRoleR2ResSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctSysResRoleSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseSysResRoleSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.user.UserAcctSysResRoleSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("userAcctSysResRoleSlaveStdDataSvc")
public class UserAcctSysResRoleSlaveStdDataSvcImpl
        extends BaseSysResRoleSlaveStdDataSvc<UserAcctSysResRoleSlaveVO, UserAcctSysResRole, UserAcctSysResRoleQueryParam>
        implements UserAcctSysResRoleSlaveStdDataSvc {

    @Autowired
    @Qualifier("userAcctSysResRoleSlaveDbSvc")
    private UserAcctSysResRoleSlaveDbSvc userAcctSysResRoleSlaveDbSvc;

    @Autowired
    @Qualifier("userAcctSysResRoleR2ResSlaveDbSvc")
    private UserAcctSysResRoleR2ResSlaveDbSvc userAcctSysResRoleR2ResSlaveDbSvc;

    @Override
    public UserAcctSysResRoleSlaveVO queryByPk(final Long sysResRoleId,
                                               final SysResRoleQueryCfg queryCfg) {
        if (Objects.isNull(sysResRoleId)) {
            return null;
        }
        final List<UserAcctSysResRoleSlaveVO> sysResRoles =
                queryByPks(Collections.singletonList(sysResRoleId), queryCfg);
        if (DeiCollectionUtil.isEmpty(sysResRoles)) {
            return null;
        }
        return sysResRoles.get(0);
    }

    @Override
    public List<UserAcctSysResRoleSlaveVO> queryByPks(final List<Long> sysResRoleIds,
                                                      final SysResRoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(sysResRoleIds)) {
            return Collections.emptyList();
        }
        final UserAcctSysResRoleQueryParam queryParam = new UserAcctSysResRoleQueryParam.Builder()
                .pks(sysResRoleIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return userAcctSysResRoleSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<UserAcctSysResRole> findByParams(final Map<String, Object> queryParams,
                                                    final List<String> showColumns) {
        return userAcctSysResRoleSlaveDbSvc.findByParams(queryParams, showColumns);
    }

    @Override
    protected void assembleSysResources(final UserAcctSysResRoleSlaveVO sysResRole,
                                        final List<SysResSlaveVO> sysResources) {
        sysResRole.setSysResources(sysResources);
    }

    @Override
    protected Map<Long, List<Long>> getSysResIds(final List<Long> sysResRoleIds) {
        return userAcctSysResRoleR2ResSlaveDbSvc.getGroupedSysResIds(sysResRoleIds);
    }

    @Override
    public UserAcctSysResRoleSlaveVO transferToStdDataVO(final UserAcctSysResRole entity) {
        final UserAcctSysResRoleSlaveVO stdVO = new UserAcctSysResRoleSlaveVO();
        assembleRoleAttrs(stdVO, entity);
        stdVO.setTenantId(entity.getTenantSid());
        stdVO.setCorpId(entity.getCorpSid());
        return stdVO;
    }
}
