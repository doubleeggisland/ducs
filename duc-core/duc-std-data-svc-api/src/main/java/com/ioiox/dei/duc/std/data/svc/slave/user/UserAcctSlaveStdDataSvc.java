package com.ioiox.dei.duc.std.data.svc.slave.user;

import com.ioiox.dei.duc.beans.model.slave.UserQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.*;
import com.ioiox.dei.duc.std.data.svc.slave.UserSlaveStdDataSvc;

import java.util.List;

public interface UserAcctSlaveStdDataSvc
        extends UserSlaveStdDataSvc<UserAcctRoleSlaveVO, UserAcctSysResRoleSlaveVO, UserAcctTmpRoleSlaveVO, UserAcctTmpSysResRoleSlaveVO, AcctUserGrpSlaveVO,
        UserAcctSlaveVO, UserAcctQueryParam> {

    UserAcctSlaveVO queryByPk(final Long userAcctId,
                              final UserQueryCfg queryCfg);
    List<UserAcctSlaveVO> queryByPks(final List<Long> userAcctIds,
                                     final UserQueryCfg queryCfg);
}
