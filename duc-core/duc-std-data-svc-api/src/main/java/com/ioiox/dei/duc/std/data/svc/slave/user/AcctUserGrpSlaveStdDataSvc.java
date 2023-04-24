package com.ioiox.dei.duc.std.data.svc.slave.user;

import com.ioiox.dei.duc.beans.vo.std.slave.UserGrpQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.user.AcctUserGrpQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.AcctUserGrpSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctRoleSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSysResRoleSlaveStdVO;
import com.ioiox.dei.duc.std.data.svc.slave.UserGrpSlaveStdDataSvc;

import java.util.List;

public interface AcctUserGrpSlaveStdDataSvc
        extends UserGrpSlaveStdDataSvc<UserAcctRoleSlaveStdVO, UserAcctSysResRoleSlaveStdVO, AcctUserGrpSlaveStdVO, AcctUserGrpQueryParam> {

    AcctUserGrpSlaveStdVO queryByPk(final Long acctUserGrpId,
                                    final UserGrpQueryCfg queryCfg);
    List<AcctUserGrpSlaveStdVO> queryByPks(final List<Long> acctUserGrpIds,
                                           final UserGrpQueryCfg queryCfg);
}
