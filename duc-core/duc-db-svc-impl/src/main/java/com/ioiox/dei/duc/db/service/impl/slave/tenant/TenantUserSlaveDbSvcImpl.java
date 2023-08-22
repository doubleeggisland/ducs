package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.TenantUser;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserSlaveDbSvc")
public class TenantUserSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<TenantUser, TenantUserSlaveMapper>
        implements TenantUserSlaveDbSvc {

    @Autowired
    private TenantUserSlaveMapper mapper;

    @Override
    protected TenantUserSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户表";
    }
}
