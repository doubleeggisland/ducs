package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserSysResRole;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserSysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserSysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserSysResRoleSlaveDbSvc")
public class TenantUserSysResRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<TenantUserSysResRole, TenantUserSysResRoleSlaveMapper>
        implements TenantUserSysResRoleSlaveDbSvc {

    @Autowired
    private TenantUserSysResRoleSlaveMapper mapper;

    @Override
    protected TenantUserSysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户系统资源角色表";
    }
}
