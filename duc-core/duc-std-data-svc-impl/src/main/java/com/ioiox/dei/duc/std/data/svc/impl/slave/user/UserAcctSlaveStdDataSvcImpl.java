package com.ioiox.dei.duc.std.data.svc.impl.slave.user;

import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.duc.beans.entity.UserAcct;
import com.ioiox.dei.duc.beans.model.slave.*;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.*;
import com.ioiox.dei.duc.beans.vo.std.slave.user.*;
import com.ioiox.dei.duc.db.service.slave.user.*;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseUserSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.UserSysPrjPrivilegeSlaveStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("userAcctSlaveStdDataSvc")
public class UserAcctSlaveStdDataSvcImpl
        extends BaseUserSlaveStdDataSvc<
        UserAcctRoleSlaveVO,
        UserAcctSysResRoleSlaveVO,
        UserAcctTmpRoleSlaveVO,
        UserAcctTmpSysResRoleSlaveVO,
        AcctUserGrpSlaveVO,
        UserAcctSlaveVO,
        UserAcct,
        UserAcctQueryParam>
        implements UserAcctSlaveStdDataSvc {

    @Autowired
    @Qualifier("userAcctSlaveDbSvc")
    private UserAcctSlaveDbSvc userAcctSlaveDbSvc;

    @Autowired
    @Qualifier("userAcctRoleSlaveStdDataSvc")
    private UserAcctRoleSlaveStdDataSvc userAcctRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("userAcctSysResRoleSlaveStdDataSvc")
    private UserAcctSysResRoleSlaveStdDataSvc userAcctSysResRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("userAcctTmpRoleSlaveStdDataSvc")
    private UserAcctTmpRoleSlaveStdDataSvc userAcctTmpRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("userAcctTmpSysResRoleSlaveStdDataSvc")
    private UserAcctTmpSysResRoleSlaveStdDataSvc userAcctTmpSysResRoleSlaveStdDataSvc;

    @Autowired
    @Qualifier("acctUserSysPrjPrivilegeSlaveStdDataSvc")
    private UserSysPrjPrivilegeSlaveStdDataSvc acctUserSysPrjPrivilegeSlaveStdDataSvc;

    @Autowired
    @Qualifier("acctUserGrpSlaveStdDataSvc")
    private AcctUserGrpSlaveStdDataSvc acctUserGrpSlaveStdDataSvc;

    @Autowired
    @Qualifier("userAcctR2RoleSlaveDbSvc")
    private UserAcctR2RoleSlaveDbSvc userAcctR2RoleSlaveDbSvc;

    @Autowired
    @Qualifier("userAcctR2SysResRoleSlaveDbSvc")
    private UserAcctR2SysResRoleSlaveDbSvc userAcctR2SysResRoleSlaveDbSvc;

    @Autowired
    @Qualifier("userAcctR2TmpRoleSlaveDbSvc")
    private UserAcctR2TmpRoleSlaveDbSvc userAcctR2TmpRoleSlaveDbSvc;

    @Autowired
    @Qualifier("userAcctR2TmpSysResRoleSlaveDbSvc")
    private UserAcctR2TmpSysResRoleSlaveDbSvc userAcctR2TmpSysResRoleSlaveDbSvc;

    @Autowired
    @Qualifier("acctUserGrpR2UserSlaveDbSvc")
    private AcctUserGrpR2UserSlaveDbSvc acctUserGrpR2UserSlaveDbSvc;

    @Override
    public UserAcctSlaveVO queryByPk(final Long userAcctId,
                                     final UserQueryCfg queryCfg) {
        if (Objects.isNull(userAcctId)) {
            return null;
        }
        final List<UserAcctSlaveVO> userAccts =
                queryByPks(Collections.singletonList(userAcctId), queryCfg);
        if (DeiCollectionUtil.isEmpty(userAccts)) {
            return null;
        }
        return userAccts.get(0);
    }

    @Override
    public List<UserAcctSlaveVO> queryByPks(final List<Long> userAcctIds,
                                            final UserQueryCfg queryCfg) {
        if (DeiCollectionUtil.isEmpty(userAcctIds)) {
            return Collections.emptyList();
        }
        final UserAcctQueryParam queryParam = new UserAcctQueryParam.Builder()
                .pks(userAcctIds)
                .build();
        return queryByParam(queryParam, queryCfg);
    }

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return userAcctSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<UserAcct> findByParams(final Map<String, Object> queryParams,
                                          final List<String> showColumns) {
        return userAcctSlaveDbSvc.findByParams(queryParams, showColumns);
    }

    @Override
    protected Map<Long, List<Long>> getRoleIds(final List<Long> userAcctIds) {
        return userAcctR2RoleSlaveDbSvc.getGroupedRoleIds(userAcctIds);
    }

    @Override
    protected List<UserAcctRoleSlaveVO> queryRolesByPks(final List<Long> roleIds,
                                                        final RoleQueryCfg queryCfg) {
        return userAcctRoleSlaveStdDataSvc.queryByPKs(roleIds, queryCfg);
    }

    @Override
    protected Map<Long, List<Long>> getSysResRoleIds(final List<Long> userAcctIds) {
        return userAcctR2SysResRoleSlaveDbSvc.getGroupedSysResRoleIds(userAcctIds);
    }

    @Override
    protected List<UserAcctSysResRoleSlaveVO> querySysResRolesByPks(final List<Long> sysResRoleIds,
                                                                    final SysResRoleQueryCfg queryCfg) {
        return userAcctSysResRoleSlaveStdDataSvc.queryByPks(sysResRoleIds, queryCfg);
    }

    @Override
    protected Map<Long, List<Long>> getTmpRoleIds(final List<Long> userAcctIds) {
        return userAcctR2TmpRoleSlaveDbSvc.getGroupedTmpRoleIds(userAcctIds);
    }

    @Override
    protected List<UserAcctTmpRoleSlaveVO> queryTmpRolesByPks(final List<Long> tmpRoleIds,
                                                              final RoleQueryCfg queryCfg) {
        return userAcctTmpRoleSlaveStdDataSvc.queryByPks(tmpRoleIds, queryCfg);
    }

    @Override
    protected Map<Long, List<Long>> getTmpSysResRoleIds(final List<Long> userAcctIds) {
        return userAcctR2TmpSysResRoleSlaveDbSvc.getGroupedTmpSysResRoleIds(userAcctIds);
    }

    @Override
    protected List<UserAcctTmpSysResRoleSlaveVO> queryTmpSysResRolesByPks(final List<Long> tmpSysResRoleIds,
                                                                          final SysResRoleQueryCfg queryCfg) {
        return userAcctTmpSysResRoleSlaveStdDataSvc.queryByPks(tmpSysResRoleIds, queryCfg);
    }

    @Override
    protected Map<Long, List<Long>> getUserGrpIds(final List<Long> userAcctIds) {
        return acctUserGrpR2UserSlaveDbSvc.getGroupedUserGrpIds(userAcctIds);
    }

    @Override
    protected List<AcctUserGrpSlaveVO> queryUserGrpsByPks(final List<Long> acctUserGrpIds,
                                                          final UserGrpQueryCfg queryCfg) {
        return acctUserGrpSlaveStdDataSvc.queryByPks(acctUserGrpIds, queryCfg);
    }

    @Override
    protected List<UserSysPrjPrivilegeSlaveVO> querySysPrjPrivilegesByUserIds(final List<Long> userAcctIds,
                                                                              final UserSysPrjPrivilegeQueryCfg queryCfg) {
        return acctUserSysPrjPrivilegeSlaveStdDataSvc.queryByUserIds(userAcctIds, queryCfg);
    }

    @Override
    public UserAcctSlaveVO transferToStdDataVO(final UserAcct entity) {
        final UserAcctSlaveVO stdVO = new UserAcctSlaveVO();
        assembleCommonAttrs(stdVO, entity);
        stdVO.setTenantId(entity.getTenantSid());
        stdVO.setCorpId(entity.getCorpSid());
        return stdVO;
    }
}