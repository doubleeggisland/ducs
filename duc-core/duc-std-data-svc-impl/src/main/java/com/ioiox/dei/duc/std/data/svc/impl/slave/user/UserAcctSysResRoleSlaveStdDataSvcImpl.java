package com.ioiox.dei.duc.std.data.svc.impl.slave.user;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.UserAcctSysResRole;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSysResRoleSlaveStdVO;
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

@Service("userAcctSysResRoleSlaveStdDataSvc")
public class UserAcctSysResRoleSlaveStdDataSvcImpl
        extends BaseSysResRoleSlaveStdDataSvc<UserAcctSysResRoleSlaveStdVO, UserAcctSysResRole, UserAcctSysResRoleQueryParam>
        implements UserAcctSysResRoleSlaveStdDataSvc {

    @Autowired
    @Qualifier("userAcctSysResRoleSlaveDbSvc")
    private UserAcctSysResRoleSlaveDbSvc userAcctSysResRoleSlaveDbSvc;

    @Autowired
    @Qualifier("userAcctSysResRoleR2ResSlaveDbSvc")
    private UserAcctSysResRoleR2ResSlaveDbSvc userAcctSysResRoleR2ResSlaveDbSvc;

    @Override
    public List<UserAcctSysResRoleSlaveStdVO> queryByPks(final List<Long> sysResRoleIds,
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
    protected void assembleSysResources(final UserAcctSysResRoleSlaveStdVO sysResRole,
                                        final List<SysResSlaveStdVO> sysResources) {
        sysResRole.setSysResources(sysResources);
    }

    @Override
    protected Map<Long, List<Long>> getSysResIds(final List<Long> sysResRoleIds) {
        return userAcctSysResRoleR2ResSlaveDbSvc.getGroupedSysResIds(sysResRoleIds);
    }

    @Override
    public UserAcctSysResRoleSlaveStdVO transferToStdDataVO(final UserAcctSysResRole entity) {
        final UserAcctSysResRoleSlaveStdVO stdVO = new UserAcctSysResRoleSlaveStdVO();
        assembleRoleAttrs(stdVO, entity);
        stdVO.setTenantId(entity.getTenantSid());
        stdVO.setCorpId(entity.getCorpSid());
        return stdVO;
    }
}