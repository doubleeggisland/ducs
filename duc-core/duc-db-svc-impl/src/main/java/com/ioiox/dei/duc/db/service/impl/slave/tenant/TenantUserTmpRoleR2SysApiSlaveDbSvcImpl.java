package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserTmpRoleR2SysApiSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserTmpRoleR2SysApiSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserTmpRoleR2SysApiSlaveDbSvc")
public class TenantUserTmpRoleR2SysApiSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<RoleR2SysApi, TenantUserTmpRoleR2SysApiSlaveMapper>
        implements TenantUserTmpRoleR2SysApiSlaveDbSvc {

    @Autowired
    private TenantUserTmpRoleR2SysApiSlaveMapper mapper;

    @Override
    protected TenantUserTmpRoleR2SysApiSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户临时角色与系统API关联表";
    }
}
