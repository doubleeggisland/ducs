package com.ioiox.dei.duc.std.data.svc.impl.master.tenant;

import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserSysPrjPrivilegeMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseUserSysPrjPrivilegeMasterStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("tenantUserSysPrjPrivilegeMasterStdDataSvc")
public class TenantUserSysPrjPrivilegeMasterStdDataSvcImpl
        extends BaseUserSysPrjPrivilegeMasterStdDataSvc {

    @Autowired
    @Qualifier("tenantUserSysPrjPrivilegeMasterDbSvc")
    private TenantUserSysPrjPrivilegeMasterDbSvc tenantUserSysPrjPrivilegeMasterDbSvc;

    @Override
    protected int doSave(final List<UserSysPrjPrivilege> newEntities) {
        return tenantUserSysPrjPrivilegeMasterDbSvc.dbInsert(newEntities);
    }

    @Override
    protected int doUpdate(final UserSysPrjPrivilege example) {
        return tenantUserSysPrjPrivilegeMasterDbSvc.dbUpdate(example);
    }

    @Override
    protected int doRemove(final Map<String, Object> deleteParams) {
        return tenantUserSysPrjPrivilegeMasterDbSvc.deleteByParams(deleteParams);
    }
}
