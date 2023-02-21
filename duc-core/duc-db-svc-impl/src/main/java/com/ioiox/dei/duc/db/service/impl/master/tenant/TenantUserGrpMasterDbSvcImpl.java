package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserGrp;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserGrpMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserGrpMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserGrpMasterDbSvc")
public class TenantUserGrpMasterDbSvcImpl
        extends BaseDeiMasterDbService<TenantUserGrp, TenantUserGrpMasterMapper>
        implements TenantUserGrpMasterDbSvc {

    @Autowired
    private TenantUserGrpMasterMapper mapper;

    @Override
    protected TenantUserGrpMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户组表";
    }
}
