package com.ioiox.dei.duc.db.service.impl.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserRole;
import com.ioiox.dei.duc.db.mapper.master.tenant.TenantUserRoleMasterMapper;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserRoleMasterDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantUserRoleMasterDbSvc")
public class TenantUserRoleMasterDbSvcImpl
        extends BaseDeiMasterDbService<TenantUserRole, TenantUserRoleMasterMapper>
        implements TenantUserRoleMasterDbSvc {

    @Autowired
    private TenantUserRoleMasterMapper mapper;

    @Override
    protected TenantUserRoleMasterMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "租户用户角色表";
    }
}
