package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserRole;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserRoleSlaveDbSvc")
public class TenantUserRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<TenantUserRole, TenantUserRoleSlaveMapper>
        implements TenantUserRoleSlaveDbSvc {

    @Autowired
    private TenantUserRoleSlaveMapper mapper;

    @Override
    protected TenantUserRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户角色表";
    }
}
