package com.ioiox.dei.duc.std.data.svc.slave;

import com.ioiox.dei.duc.beans.model.slave.UserSysPrjPrivilegeQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.UserSysPrjPrivilegeQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.UserSysPrjPrivilegeSlaveVO;

import java.util.List;

public interface UserSysPrjPrivilegeSlaveStdDataSvc {
    int countByParam(final UserSysPrjPrivilegeQueryParam queryParam);
    List<UserSysPrjPrivilegeSlaveVO> queryByUserIds(final List<Long> userIds,
                                                    final UserSysPrjPrivilegeQueryCfg queryCfg);
    List<UserSysPrjPrivilegeSlaveVO> queryByParam(final UserSysPrjPrivilegeQueryParam queryParam,
                                                  final UserSysPrjPrivilegeQueryCfg queryCfg);
}
