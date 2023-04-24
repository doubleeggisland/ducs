package com.ioiox.dei.duc.std.data.svc.slave.user;

import com.ioiox.dei.duc.beans.vo.std.slave.UserQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.user.*;
import com.ioiox.dei.duc.std.data.svc.slave.UserSlaveStdDataSvc;

import java.util.List;

public interface UserAcctSlaveStdDataSvc
        extends UserSlaveStdDataSvc<UserAcctRoleSlaveStdVO, UserAcctSysResRoleSlaveStdVO, UserAcctTmpRoleSlaveStdVO, UserAcctTmpSysResRoleSlaveStdVO, AcctUserGrpSlaveStdVO,
        UserAcctSlaveStdVO, UserAcctQueryParam> {

    UserAcctSlaveStdVO queryByPk(final Long userAcctId,
                                 final UserQueryCfg queryCfg);
    List<UserAcctSlaveStdVO> queryByPks(final List<Long> userAcctIds,
                                        final UserQueryCfg queryCfg);
}
