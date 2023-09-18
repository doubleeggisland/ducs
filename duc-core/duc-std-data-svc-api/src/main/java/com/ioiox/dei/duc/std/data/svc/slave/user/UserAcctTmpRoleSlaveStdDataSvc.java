package com.ioiox.dei.duc.std.data.svc.slave.user;

import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.user.UserAcctTmpRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctTmpRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.RoleSlaveStdDataSvc;

import java.util.List;

public interface UserAcctTmpRoleSlaveStdDataSvc
        extends RoleSlaveStdDataSvc<UserAcctTmpRoleSlaveVO, UserAcctTmpRoleQueryParam> {

    UserAcctTmpRoleSlaveVO queryByPk(final Long tmpRoleId,
                                     final RoleQueryCfg queryCfg);
    List<UserAcctTmpRoleSlaveVO> queryByPks(final List<Long> tmpRoleIds,
                                            final RoleQueryCfg queryCfg);
}
