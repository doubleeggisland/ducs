package com.ioiox.dei.duc.std.data.svc.slave.user;

import com.ioiox.dei.duc.beans.vo.std.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctRoleSlaveStdVO;
import com.ioiox.dei.duc.std.data.svc.slave.RoleSlaveStdDataSvc;

import java.util.List;

public interface UserAcctRoleSlaveStdDataSvc
        extends RoleSlaveStdDataSvc<UserAcctRoleSlaveStdVO, UserAcctRoleQueryParam> {

    UserAcctRoleSlaveStdVO queryByPk(final Long userId, final RoleQueryCfg queryCfg);
    List<UserAcctRoleSlaveStdVO> queryByPKs(final List<Long> userIds, final RoleQueryCfg queryCfg);
}
