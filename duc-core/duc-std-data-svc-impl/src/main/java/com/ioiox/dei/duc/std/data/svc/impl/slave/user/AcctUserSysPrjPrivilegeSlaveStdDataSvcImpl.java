package com.ioiox.dei.duc.std.data.svc.impl.slave.user;

import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.db.service.slave.user.AcctUserSysPrjPrivilegeSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseUserSysPrjPrivilegeSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("acctUserSysPrjPrivilegeSlaveStdDataSvc")
public class AcctUserSysPrjPrivilegeSlaveStdDataSvcImpl
        extends BaseUserSysPrjPrivilegeSlaveStdDataSvc {

    @Autowired
    @Qualifier("acctUserSysPrjPrivilegeSlaveDbSvc")
    private AcctUserSysPrjPrivilegeSlaveDbSvc acctUserSysPrjPrivilegeSlaveDbSvc;

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return acctUserSysPrjPrivilegeSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<UserSysPrjPrivilege> findByParams(final Map<String, Object> queryParams, final List<String> showColumns) {
        return acctUserSysPrjPrivilegeSlaveDbSvc.findByParams(queryParams, showColumns);
    }
}
