package com.ioiox.dei.duc.std.data.svc.master;

import com.ioiox.dei.duc.beans.model.master.UserSysPrjPrivilegeDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.UserSysPrjPrivilegeMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.UserSysPrjPrivilegeSlaveVO;

import java.util.List;

public interface UserSysPrjPrivilegeMasterStdDataSvc {
    int sync(final List<UserSysPrjPrivilegeMasterVO> sysPrjPrivileges,
             final List<UserSysPrjPrivilegeSlaveVO> existingSysPrjPrivileges);
    int removeByUserIds(final List<Long> userIds);
    int remove(final UserSysPrjPrivilegeDelParam delParam);
}
