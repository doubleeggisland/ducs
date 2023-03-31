package com.ioiox.dei.duc.std.data.svc.slave.user;

import com.ioiox.dei.duc.beans.vo.std.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctTmpRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctTmpRoleSlaveStdVO;
import com.ioiox.dei.duc.std.data.svc.slave.RoleSlaveStdDataSvc;

import java.util.List;

public interface UserAcctTmpRoleSlaveStdDataSvc
        extends RoleSlaveStdDataSvc<UserAcctTmpRoleSlaveStdVO, UserAcctTmpRoleQueryParam> {

    List<UserAcctTmpRoleSlaveStdVO> queryByPks(final List<Long> tmpRoleIds,
                                               final RoleQueryCfg queryCfg);
}
