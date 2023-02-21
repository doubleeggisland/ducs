package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2Role;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserGrpR2RoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserGrpR2RoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserGrpR2RoleSlaveDbSvc")
public class TenantUserGrpR2RoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<UserGrpR2Role, TenantUserGrpR2RoleSlaveMapper>
        implements TenantUserGrpR2RoleSlaveDbSvc {

    @Autowired
    private TenantUserGrpR2RoleSlaveMapper mapper;

    @Override
    protected TenantUserGrpR2RoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户组与角色关联表";
    }
}
