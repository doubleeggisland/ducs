package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.SysResRoleR2Res;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserSysResRoleR2ResSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserSysResRoleR2ResSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserSysResRoleR2ResSlaveDbSvc")
public class TenantUserSysResRoleR2ResSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<SysResRoleR2Res, TenantUserSysResRoleR2ResSlaveMapper>
        implements TenantUserSysResRoleR2ResSlaveDbSvc {

    @Autowired
    private TenantUserSysResRoleR2ResSlaveMapper mapper;

    @Override
    protected TenantUserSysResRoleR2ResSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户系统资源角色与资源关联表";
    }
}
