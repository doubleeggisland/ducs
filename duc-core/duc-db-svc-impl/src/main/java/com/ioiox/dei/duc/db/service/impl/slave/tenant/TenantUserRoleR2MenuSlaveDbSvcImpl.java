package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserRoleR2MenuSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserRoleR2MenuSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserRoleR2MenuSlaveDbSvc")
public class TenantUserRoleR2MenuSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<RoleR2Menu, TenantUserRoleR2MenuSlaveMapper>
        implements TenantUserRoleR2MenuSlaveDbSvc {

    @Autowired
    private TenantUserRoleR2MenuSlaveMapper mapper;

    @Override
    protected TenantUserRoleR2MenuSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户角色与菜单关联表";
    }
}
