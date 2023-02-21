package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserTmpSysResRole;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserTmpSysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserTmpSysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserTmpSysResRoleSlaveDbSvc")
public class TenantUserTmpSysResRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<TenantUserTmpSysResRole, TenantUserTmpSysResRoleSlaveMapper>
        implements TenantUserTmpSysResRoleSlaveDbSvc {

    @Autowired
    private TenantUserTmpSysResRoleSlaveMapper mapper;

    @Override
    protected TenantUserTmpSysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户临时系统资源角色表";
    }
}
