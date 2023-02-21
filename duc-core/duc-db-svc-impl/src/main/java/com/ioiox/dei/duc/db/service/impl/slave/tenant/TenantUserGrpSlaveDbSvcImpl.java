package com.ioiox.dei.duc.db.service.impl.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserGrp;
import com.ioiox.dei.duc.db.mapper.slave.tenant.TenantUserGrpSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.tenant.TenantUserGrpSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserGrpSlaveDbSvc")
public class TenantUserGrpSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<TenantUserGrp, TenantUserGrpSlaveMapper>
        implements TenantUserGrpSlaveDbSvc {

    @Autowired
    private TenantUserGrpSlaveMapper mapper;

    @Override
    protected TenantUserGrpSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户组表";
    }
}
