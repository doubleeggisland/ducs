package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.model.master.UserSysPrjPrivilegeDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.UserSysPrjPrivilegeMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.UserSysPrjPrivilegeSlaveStdVO;

import java.util.List;

public interface UserSysPrjPrivilegeMasterStdDataSvc {
    int sync(final List<UserSysPrjPrivilegeMasterStdVO> sysPrjPrivileges,
             final List<UserSysPrjPrivilegeSlaveStdVO> existingSysPrjPrivileges);
    int removeByUserIds(final List<Long> userIds);
    int remove(final UserSysPrjPrivilegeDelParam delParam);
}
