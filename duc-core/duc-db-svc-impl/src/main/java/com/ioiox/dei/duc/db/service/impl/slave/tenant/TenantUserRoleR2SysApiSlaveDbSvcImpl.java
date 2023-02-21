package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserRoleR2SysApiSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserRoleR2SysApiSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserRoleR2SysApiSlaveDbSvc")
public class TenantUserRoleR2SysApiSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<RoleR2SysApi, TenantUserRoleR2SysApiSlaveMapper>
        implements TenantUserRoleR2SysApiSlaveDbSvc {

    @Autowired
    private TenantUserRoleR2SysApiSlaveMapper mapper;

    @Override
    protected TenantUserRoleR2SysApiSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户角色与系统API关联表";
    }
}
