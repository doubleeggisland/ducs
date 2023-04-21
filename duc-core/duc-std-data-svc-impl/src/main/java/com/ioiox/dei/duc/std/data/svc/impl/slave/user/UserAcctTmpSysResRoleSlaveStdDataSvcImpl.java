package com.ioiox.dei.duc.std.data.svc.impl.slave.user;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.UserAcctTmpSysResRole;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctTmpSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctTmpSysResRoleSlaveStdVO;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctTmpSysResRoleR2ResSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.user.UserAcctTmpSysResRoleSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseSysResRoleSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.user.UserAcctTmpSysResRoleSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service("userAcctTmpSysResRoleSlaveStdDataSvc")
public class UserAcctTmpSysResRoleSlaveStdDataSvcImpl
        extends BaseSysResRoleSlaveStdDataSvc<UserAcctTmpSysResRoleSlaveStdVO, UserAcctTmpSysResRole, UserAcctTmpSysResRoleQueryParam>
        implements UserAcctTmpSysResRoleSlaveStdDataSvc {

    @Autowired
    @Qualifier("userAcctTmpSysResRoleSlaveDbSvc")
    private UserAcctTmpSysResRoleSlaveDbSvc userAcctTmpSysResRoleSlaveDbSvc;

    @Autowired
    @Qualifier("userAcctTmpSysResRoleR2ResSlaveDbSvc")
    private UserAcctTmpSysResRoleR2ResSlaveDbSvc userAcctTmpSysResRoleR2ResSlaveDbSvc;

    @Override
    public List<UserAcctTmpSysResRoleSlaveStdVO> queryByPks(final List<Long> tmpSysResRoleIds,
                                                            final SysResRoleQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(tmpSysResRoleIds)) {
            return Collections.emptyList();
        }
        final UserAcctTmpSysResRoleQueryParam queryParam = new UserAcctTmpSysResRoleQueryParam.Builder()
                .pks(tmpSysResRoleIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return userAcctTmpSysResRoleSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<UserAcctTmpSysResRole> findByParams(Map<String, Object> queryParams, List<String> showColumns) {
        return userAcctTmpSysResRoleSlaveDbSvc.findByParams(queryParams, showColumns);
    }

    @Override
    protected void assembleSysResources(final UserAcctTmpSysResRoleSlaveStdVO sysResRole,
                                        final List<SysResSlaveStdVO> sysResources) {
        sysResRole.setSysResources(sysResources);
    }

    @Override
    protected Map<Long, List<Long>> getSysResIds(final List<Long> sysResRoleIds) {
        return userAcctTmpSysResRoleR2ResSlaveDbSvc.getGroupedSysResIds(sysResRoleIds);
    }

    @Override
    public UserAcctTmpSysResRoleSlaveStdVO transferToStdDataVO(UserAcctTmpSysResRole entity) {
        final UserAcctTmpSysResRoleSlaveStdVO stdVO = new UserAcctTmpSysResRoleSlaveStdVO();
        assembleTmpRoleAttrs(stdVO, entity);
        stdVO.setTenantId(entity.getTenantSid());
        stdVO.setCorpId(entity.getCorpSid());
        return stdVO;
    }
}