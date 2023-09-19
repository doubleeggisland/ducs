package com.ioiox.dei.duc.std.data.svc.impl.slave.tenant;

import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserSysPrjPrivilegeSlaveDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.slave.BaseUserSysPrjPrivilegeSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("tenantUserSysPrjPrivilegeSlaveStdDataSvc")
public class TenantUserSysPrjPrivilegeSlaveStdDataSvcImpl
        extends BaseUserSysPrjPrivilegeSlaveStdDataSvc {

    @Autowired
    @Qualifier("tenantUserSysPrjPrivilegeSlaveDbSvc")
    private TenantUserSysPrjPrivilegeSlaveDbSvc tenantUserSysPrjPrivilegeSlaveDbSvc;

    @Override
    protected int countByParams(final Map<String, Object> queryParams) {
        return tenantUserSysPrjPrivilegeSlaveDbSvc.countByParams(queryParams);
    }

    @Override
    protected List<UserSysPrjPrivilege> findByParams(final Map<String, Object> queryParams, final List<String> showColumns) {
        return tenantUserSysPrjPrivilegeSlaveDbSvc.findByParams(queryParams, showColumns);
    }
}
