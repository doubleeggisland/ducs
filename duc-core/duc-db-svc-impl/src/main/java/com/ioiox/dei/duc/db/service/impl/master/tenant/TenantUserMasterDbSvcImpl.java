package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.TenantUser;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserMasterDbSvc")
public class TenantUserMasterDbSvcImpl
        extends BaseDeiMasterDbService<TenantUser, TenantUserMasterMapper>
        implements TenantUserMasterDbSvc {

    @Autowired
    private TenantUserMasterMapper mapper;

    @Override
    protected TenantUserMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户表";
    }
}
