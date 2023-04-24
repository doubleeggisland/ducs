package com.ioiox.dei.duc.std.data.svc.slave.user;

import com.ioiox.dei.duc.beans.vo.std.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctTmpSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctTmpSysResRoleSlaveStdVO;
import com.ioiox.dei.duc.std.data.svc.slave.SysResRoleSlaveStdDataSvc;

import java.util.List;

public interface UserAcctTmpSysResRoleSlaveStdDataSvc
        extends SysResRoleSlaveStdDataSvc<UserAcctTmpSysResRoleSlaveStdVO, UserAcctTmpSysResRoleQueryParam> {

    UserAcctTmpSysResRoleSlaveStdVO queryByPk(final Long tmpSysResRoleId,
                                              final SysResRoleQueryCfg queryCfg);
    List<UserAcctTmpSysResRoleSlaveStdVO> queryByPks(final List<Long> tmpSysResRoleIds,
                                                     final SysResRoleQueryCfg queryCfg);
}
