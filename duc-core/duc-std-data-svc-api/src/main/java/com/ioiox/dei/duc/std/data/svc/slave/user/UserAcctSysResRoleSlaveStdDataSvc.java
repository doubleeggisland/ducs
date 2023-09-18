package com.ioiox.dei.duc.std.data.svc.slave.user;

import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSysResRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.SysResRoleSlaveStdDataSvc;

import java.util.List;

public interface UserAcctSysResRoleSlaveStdDataSvc
        extends SysResRoleSlaveStdDataSvc<UserAcctSysResRoleSlaveVO, UserAcctSysResRoleQueryParam> {

    UserAcctSysResRoleSlaveVO queryByPk(final Long sysResRoleId,
                                        final SysResRoleQueryCfg queryCfg);
    List<UserAcctSysResRoleSlaveVO> queryByPks(final List<Long> sysResRoleIds,
                                               final SysResRoleQueryCfg queryCfg);
}
