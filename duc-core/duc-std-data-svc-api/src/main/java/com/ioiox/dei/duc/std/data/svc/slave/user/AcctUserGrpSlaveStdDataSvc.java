package com.ioiox.dei.duc.std.data.svc.slave.user;

import com.ioiox.dei.duc.beans.model.slave.UserGrpQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.user.AcctUserGrpQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.AcctUserGrpSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctRoleSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSysResRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.UserGrpSlaveStdDataSvc;

import java.util.List;

public interface AcctUserGrpSlaveStdDataSvc
        extends UserGrpSlaveStdDataSvc<UserAcctRoleSlaveVO, UserAcctSysResRoleSlaveVO, AcctUserGrpSlaveVO, AcctUserGrpQueryParam> {

    AcctUserGrpSlaveVO queryByPk(final Long acctUserGrpId,
                                 final UserGrpQueryCfg queryCfg);
    List<AcctUserGrpSlaveVO> queryByPks(final List<Long> acctUserGrpIds,
                                        final UserGrpQueryCfg queryCfg);
}
