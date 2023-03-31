package com.ioiox.dei.duc.std.data.svc.slave;

import com.ioiox.dei.duc.beans.vo.std.slave.UserSysPrjPrivilegeQueryCfg;
import com.ioiox.dei.duc.beans.vo.std.slave.UserSysPrjPrivilegeQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.UserSysPrjPrivilegeSlaveStdVO;

import java.util.List;

public interface UserSysPrjPrivilegeSlaveStdDataSvc {
    int countByParam(final UserSysPrjPrivilegeQueryParam queryParam);
    List<UserSysPrjPrivilegeSlaveStdVO> queryByUserIds(final List<Long> userIds,
                                                       final UserSysPrjPrivilegeQueryCfg queryCfg);
    List<UserSysPrjPrivilegeSlaveStdVO> queryByParam(final UserSysPrjPrivilegeQueryParam queryParam,
                                                     final UserSysPrjPrivilegeQueryCfg queryCfg);
}
