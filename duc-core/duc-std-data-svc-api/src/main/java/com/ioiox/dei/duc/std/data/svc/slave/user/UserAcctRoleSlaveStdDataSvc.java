package com.ioiox.dei.duc.std.data.svc.slave.user;

import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.RoleSlaveStdDataSvc;

import java.util.List;

public interface UserAcctRoleSlaveStdDataSvc
        extends RoleSlaveStdDataSvc<UserAcctRoleSlaveVO, UserAcctRoleQueryParam> {

    UserAcctRoleSlaveVO queryByPk(final Long userId, final RoleQueryCfg queryCfg);
    List<UserAcctRoleSlaveVO> queryByPKs(final List<Long> userIds, final RoleQueryCfg queryCfg);
}
