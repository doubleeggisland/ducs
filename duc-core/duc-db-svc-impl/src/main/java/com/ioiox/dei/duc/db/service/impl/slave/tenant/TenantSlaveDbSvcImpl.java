package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.Tenant;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantSlaveDbSvc")
public class TenantSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<Tenant, TenantSlaveMapper>
        implements TenantSlaveDbSvc {

    @Autowired
    private TenantSlaveMapper mapper;

    @Override
    protected TenantSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户表";
    }
}
