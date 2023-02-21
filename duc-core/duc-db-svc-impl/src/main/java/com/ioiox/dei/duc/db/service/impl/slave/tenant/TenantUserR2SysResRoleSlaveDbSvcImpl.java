package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserR2SysResRole;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserR2SysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserR2SysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserR2SysResRoleSlaveDbSvc")
public class TenantUserR2SysResRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<TenantUserR2SysResRole, TenantUserR2SysResRoleSlaveMapper>
        implements TenantUserR2SysResRoleSlaveDbSvc {

    @Autowired
    private TenantUserR2SysResRoleSlaveMapper mapper;

    @Override
    protected TenantUserR2SysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户与系统资源角色关联表";
    }
}
