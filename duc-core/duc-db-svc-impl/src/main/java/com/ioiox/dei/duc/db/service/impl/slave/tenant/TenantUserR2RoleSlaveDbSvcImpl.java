package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserR2Role;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserR2RoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserR2RoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserR2RoleSlaveDbSvc")
public class TenantUserR2RoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<TenantUserR2Role, TenantUserR2RoleSlaveMapper>
        implements TenantUserR2RoleSlaveDbSvc {

    @Autowired
    private TenantUserR2RoleSlaveMapper mapper;

    @Override
    protected TenantUserR2RoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户与角色关联表";
    }
}
