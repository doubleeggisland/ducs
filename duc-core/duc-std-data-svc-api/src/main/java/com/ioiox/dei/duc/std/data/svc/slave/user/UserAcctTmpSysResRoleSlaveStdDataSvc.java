package com.ioiox.dei.duc.std.data.svc.slave.user;

import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctTmpSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctTmpSysResRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.SysResRoleSlaveStdDataSvc;

import java.util.List;

public interface UserAcctTmpSysResRoleSlaveStdDataSvc
        extends SysResRoleSlaveStdDataSvc<UserAcctTmpSysResRoleSlaveVO, UserAcctTmpSysResRoleQueryParam> {

    UserAcctTmpSysResRoleSlaveVO queryByPk(final Long tmpSysResRoleId,
                                           final SysResRoleQueryCfg queryCfg);
    List<UserAcctTmpSysResRoleSlaveVO> queryByPks(final List<Long> tmpSysResRoleIds,
                                                  final SysResRoleQueryCfg queryCfg);
}
