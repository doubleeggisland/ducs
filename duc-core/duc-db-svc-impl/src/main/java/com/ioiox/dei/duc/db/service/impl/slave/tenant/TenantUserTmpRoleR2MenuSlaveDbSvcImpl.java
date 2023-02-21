package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserTmpRoleR2MenuSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserTmpRoleR2MenuSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserTmpRoleR2MenuSlaveDbSvc")
public class TenantUserTmpRoleR2MenuSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<RoleR2Menu, TenantUserTmpRoleR2MenuSlaveMapper>
        implements TenantUserTmpRoleR2MenuSlaveDbSvc {

    @Autowired
    private TenantUserTmpRoleR2MenuSlaveMapper mapper;

    @Override
    protected TenantUserTmpRoleR2MenuSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户临时角色与菜单关联表";
    }
}
