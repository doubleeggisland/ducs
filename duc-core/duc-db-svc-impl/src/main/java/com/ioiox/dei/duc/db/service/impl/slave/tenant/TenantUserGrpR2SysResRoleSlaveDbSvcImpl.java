package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2SysResRole;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserGrpR2SysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserGrpR2SysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserGrpR2SysResRoleSlaveDbSvc")
public class TenantUserGrpR2SysResRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<UserGrpR2SysResRole, TenantUserGrpR2SysResRoleSlaveMapper>
        implements TenantUserGrpR2SysResRoleSlaveDbSvc {

    @Autowired
    private TenantUserGrpR2SysResRoleSlaveMapper mapper;

    @Override
    protected TenantUserGrpR2SysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户组与系统资源角色关联表";
    }
}
