package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserTmpRole;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserTmpRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserTmpRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserTmpRoleSlaveDbSvc")
public class TenantUserTmpRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<TenantUserTmpRole, TenantUserTmpRoleSlaveMapper>
        implements TenantUserTmpRoleSlaveDbSvc {

    @Autowired
    private TenantUserTmpRoleSlaveMapper mapper;

    @Override
    protected TenantUserTmpRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户临时角色表";
    }
}
