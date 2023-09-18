package com.ioiox.dei.duc.std.data.svc.impl.slave.user;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.AcctUserGrp;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.UserGrpQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.user.AcctUserGrpQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.AcctUserGrpSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctRoleSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSysResRoleSlaveVO;
import com.ioiox.dei.duc.db.service.slave.user.AcctUserGrpR2RoleSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.user.AcctUserGrpR2SysResRoleSlaveDbSvc;
import com.ioiox.dei.duc.db.service.slave.user.AcctUserGrpSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseUserGrpSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.user.AcctUserGrpSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.user.UserAcctRoleSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.user.UserAcctSysResRoleSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("acctUserGrpSlaveStdDataSvc")
public class AcctUserGrpSlaveStdDataSvcImpl
        extends BaseUserGrpSlaveStdDataSvc<UserAcctRoleSlaveVO, UserAcctSysResRoleSlaveVO, AcctUserGrpSlaveVO, AcctUserGrp, AcctUserGrpQueryParam>
        implements AcctUserGrpSlaveStdDataSvc {

    @Autowired
    @Qualifier("acctUserGrpSlaveDbSvc")
    private AcctUserGrpSlaveDbSvc acctUserGrpSlaveDbSvc;

    @Autowired
    @Qualifier("userAcctRoleSlaveStdDataSvc")
    private UserAcctRoleSlaveStdDataSvc userAcctRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("userAcctSysResRoleSlaveStdDataSvc")
    private UserAcctSysResRoleSlaveStdDataSvc userAcctSysResRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("acctUserGrpR2RoleSlaveDbSvc")
    private AcctUserGrpR2RoleSlaveDbSvc acctUserGrpR2RoleSlaveDbSvc;

    @Autowired
    @Qualifier("acctUserGrpR2SysResRoleSlaveDbSvc")
    private AcctUserGrpR2SysResRoleSlaveDbSvc acctUserGrpR2SysResRoleSlaveDbSvc;

    public AcctUserGrpSlaveVO queryByPk(final Long acctUserGrpId,
                                        final UserGrpQueryCfg queryCfg) {
        if (Objects.isNull(acctUserGrpId)) {
            return null;
        }
        final List<AcctUserGrpSlaveVO> userGrps =
                queryByPks(Collections.singletonList(acctUserGrpId), queryCfg);
        if (DeiCollectionUtil.isEmpty(userGrps)) {
            return null;
        }
        return userGrps.get(0);
    }

    @Override
    public List<AcctUserGrpSlaveVO> queryByPks(final List<Long> acctUserGrpIds,
                                               final UserGrpQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(acctUserGrpIds)) {
            return Collections.emptyList();
        }
        final AcctUserGrpQueryParam queryParam = new AcctUserGrpQueryParam.Builder()
                .pks(acctUserGrpIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return acctUserGrpSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<AcctUserGrp> findByParams(final Map<String, Object> queryParams, final List<String> showColumns) {
        return acctUserGrpSlaveDbSvc.findByParams(queryParams, showColumns);
    }

    @Override
    protected Map<Long, List<Long>> getRoleIds(final List<Long> acctUserGrpIds) {
        return acctUserGrpR2RoleSlaveDbSvc.getGroupedRoleIds(acctUserGrpIds);
    }

    @Override
    protected List<UserAcctRoleSlaveVO> queryRolesByPks(final List<Long> roleIds, final RoleQueryCfg queryCfg) {
        return userAcctRoleSlaveStdDataSvc.queryByPKs(roleIds, queryCfg);
    }

    @Override
    protected Map<Long, List<Long>> getSysResRoleIds(final List<Long> acctUserGrpIds) {
        return acctUserGrpR2SysResRoleSlaveDbSvc.getGroupedSysResRoleIds(acctUserGrpIds);
    }

    @Override
    protected List<UserAcctSysResRoleSlaveVO> querySysResRolesByPks(final List<Long> sysResRoleIds, final SysResRoleQueryCfg queryCfg) {
        return userAcctSysResRoleSlaveStdDataSvc.queryByPks(sysResRoleIds, queryCfg);
    }

    @Override
    public AcctUserGrpSlaveVO transferToStdDataVO(AcctUserGrp entity) {
        final AcctUserGrpSlaveVO stdVO = new AcctUserGrpSlaveVO();
        assembleCommonAttrs(stdVO, entity);
        stdVO.setTenantId(entity.getTenantSid());
        stdVO.setCorpId(entity.getCorpSid());
        return stdVO;
    }
}
