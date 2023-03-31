package com.ioiox.dei.duc.std.data.svc.slave.user;

import com.ioiox.dei.duc.beans.vo.std.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSysResRoleSlaveStdVO;
import com.ioiox.dei.duc.std.data.svc.slave.SysResRoleSlaveStdDataSvc;

import java.util.List;

public interface UserAcctSysResRoleSlaveStdDataSvc
        extends SysResRoleSlaveStdDataSvc<UserAcctSysResRoleSlaveStdVO, UserAcctSysResRoleQueryParam> {

    List<UserAcctSysResRoleSlaveStdVO> queryByPks(final List<Long> sysResRoleIds,
                                                  final SysResRoleQueryCfg queryCfg);
}
