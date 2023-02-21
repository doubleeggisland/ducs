package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserR2SysResRole;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserR2TmpSysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserR2TmpSysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserR2TmpSysResRoleSlaveDbSvc")
public class TenantUserR2TmpSysResRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<TenantUserR2SysResRole, TenantUserR2TmpSysResRoleSlaveMapper>
        implements TenantUserR2TmpSysResRoleSlaveDbSvc {

    @Autowired
    private TenantUserR2TmpSysResRoleSlaveMapper mapper;

    @Override
    protected TenantUserR2TmpSysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户与临时系统资源角色关联表";
    }
}
