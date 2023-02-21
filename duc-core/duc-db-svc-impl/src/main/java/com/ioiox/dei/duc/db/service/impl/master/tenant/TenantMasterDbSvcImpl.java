package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.Tenant;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantMasterDbSvc")
public class TenantMasterDbSvcImpl
        extends BaseDeiMasterDbService<Tenant, TenantMasterMapper>
        implements TenantMasterDbSvc {

    @Autowired
    private TenantMasterMapper mapper;

    @Override
    protected TenantMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户表";
    }
}
